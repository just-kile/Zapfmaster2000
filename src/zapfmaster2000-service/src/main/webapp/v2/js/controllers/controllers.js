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
     'modules/bestlist/BestlistController',
     'modules/challenges/ChallengeController',
     'modules/newsstack/NewsstackController',
     'modules/draftkits/DraftKitController',
     'modules/splashscreen/SplashScreenController',

    //Constants
    'constants'
], function (Console, _, routes, app, home, stats,bestlist,challenge,newsstack,draftkit,splash,constants) {
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
        angModule.run(["$rootScope","CometService",function ($rootScope,CometService) {
            $rootScope.$on('$routeChangeSuccess', function (next, last) {
                Console.debug("Navigating from ", last);
                Console.debug("Navigating to   ", next);
                CometService.reset();


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
