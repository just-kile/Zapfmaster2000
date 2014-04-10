define([
    // Standard Libs
    'Console',      // lib/console/console
     'Underscore', // lib/underscore/underscore

    // routing
     'routes/routes',

    // Application Controller
     'controllers/AppController',
     'controllers/HomeController',
     'controllers/StatsController',

    //Modules Controller
     'widgets/bestlist/BestlistController',
     'widgets/challenges/ChallengeController',
     'widgets/newsstack/NewsstackController',
     'widgets/draftkits/DraftKitController',
     'widgets/splashscreen/SplashScreenController',
    'widgets/linechart/LineChartController',
    'widgets/amountchart/AmountChartController',
    'widgets/newsfeed/NewsfeedController',
    'widgets/achievementfeed/AchievementfeedController',

    //Constants
    'constants'
], function (Console, _, routes, app, home, stats,bestlist,challenge,newsstack,draftkit,splash,linechart,amountchart,newsfeed,achievementfeed,constants) {
    "use strict";
    Console.group("Entering controllers module.");
    Console.info("AppController", app);

    var controllers = {
        home: home,
        BestlistController:bestlist,
        ChallengeController:challenge,
        NewsstackController:newsstack,
        DraftKitController:draftkit,
        SplashScreenController:splash,
        LineChartController:linechart,
        AmountChartController:amountchart,
        NewsfeedController:newsfeed,
        AchievementfeedController:achievementfeed,
        stats: stats

    };

    var setUpConstants = function(angModule){
        Console.group('Initializing constants.');
        angModule.constant('ZMConstants', constants);
        Console.debug('Registered Constants: ',constants);
        Console.groupEnd();
    };
    var setUpRoutes = function (angModule) {
        // hook up routing
        Console.group('Initializing navigation and routing.');
        angModule.config(function ($routeProvider) {
            _.each(routes, function (value, key) {
                Console.debug("Adding ", key, ":", value);
                $routeProvider.when(
                    value.route
                    , {
                        template: value.template, controller: value.controller, title: value.title
                    }
                );
            });
            $routeProvider.otherwise({ redirectTo: routes.home.route });
        });
        angModule.run(["$rootScope","$templateCache","CometService",function ($rootScope,$templateCache,CometService) {
            $rootScope.$on('$routeChangeSuccess', function (next, last) {
                Console.debug("Navigating from ", last);
                Console.debug("Navigating to   ", next);
                CometService.reset();


            });
            $rootScope.$on('$viewContentLoaded', function() {
                $templateCache.removeAll();
            });
        }]);
    }

    var initialize = function (angModule) {
        angModule.controller('AppController', app);
        _.each(controllers, function (controller, name) {
            angModule.controller(name, controller);
        })
        setUpRoutes(angModule);
        setUpConstants(angModule);
        Console.info("Registered Controllers: ", controllers);
    };


    Console.groupEnd();
    return {
        initialize: initialize
    };
});
