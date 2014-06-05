define([
    // Standard Libs
    'Console',      // lib/console/console
    'Underscore', // lib/underscore/underscore

    // routing
    'routes/routes',

    // Application Controller
    'controllers/AppController',
    'controllers/PresentationController',
    'controllers/StatsController',
    'controllers/HomeController',
    'controllers/ChallengesWebappController',

    //Modules Controller
    'widgets/BestlistController',
    'widgets/ChallengeController',
    'widgets/NewsstackController',
    'widgets/DraftKitController',
    'widgets/SplashScreenController',
    'widgets/LineChartController',
    'widgets/AmountChartController',
    'widgets/NewsfeedController',
    'widgets/AchievementfeedController',

    'widgets/ZapfmasterSplashController',
    'widgets/UserProfileController',
    'widgets/AchievementStatsController',
    'widgets/DistributionController',
    'widgets/AboutUsController',

    //Constants
    'constants'
], function (Console, _, routes, app, presentation, stats,home,challengesWebapp, bestlist, challenge, newsstack, draftkit, splash, linechart, amountchart, newsfeed, achievementfeed, zmsplash, userprofile, achievementstats, distribution, aboutUs, constants) {
    "use strict";
    Console.group("Entering controllers module.");
    Console.info("AppController", app);

    var controllers = {
        PresentationController: presentation,
        HomeController:home,
        ChallengesWebappController:challengesWebapp,
        StatsController: stats,

        BestlistController: bestlist,
        ChallengeController: challenge,
        NewsstackController: newsstack,
        DraftKitController: draftkit,
        SplashScreenController: splash,
        LineChartController: linechart,
        AmountChartController: amountchart,
        NewsfeedController: newsfeed,
        AchievementfeedController: achievementfeed,
        ZapfmasterSplashController: zmsplash,
        UserProfileController: userprofile,
        AchievementStatsController: achievementstats,
        DistributionController: distribution,
        AboutUsController: aboutUs

    };

    var setUpConstants = function (angModule) {
        Console.group('Initializing constants.');
        angModule.constant('ZMConstants', constants);
        Console.debug('Registered Constants: ', constants);
        Console.groupEnd();
    };
    var setUpRoutes = function (angModule) {
        // hook up routing
        Console.group('Initializing navigation and routing.');
        angModule.config(['$routeProvider', function ($routeProvider) {
            _.each(routes, function (value, key) {
                Console.debug("Adding ", key, ":", value);
                $routeProvider.when(
                    value.route,
                    {
                        template: value.template,
                        controller: value.controller,
                        title: value.title
                    }
                );
            });
            $routeProvider.otherwise({ redirectTo: routes.presentation.route });
        }]);
        angModule.run(["$rootScope", "$templateCache", "CometService", function ($rootScope, $templateCache, CometService) {
            $rootScope.$on('$routeChangeSuccess', function (next, last) {
                Console.debug("Navigating from ", last);
                Console.debug("Navigating to   ", next);
            });
            $rootScope.$on('$viewContentLoaded', function () {
                $templateCache.removeAll();
            });
        }]);
    };
    var setUpLanguage = function (angModule) {
        angModule.config(['$translateProvider', function ($translateProvider) {
            // $translateProvider.useLocalStorage();
            $translateProvider
                .useStaticFilesLoader({
                    prefix: 'l10n/',
                    suffix: '.json'
                })
                .registerAvailableLanguageKeys(["de", "en"], {
                    'en_US': 'en',
                    'en_UK': 'en',
                    'de_DE': 'de',
                    'de_CH': 'de'
                })
                .fallbackLanguage("en");
            if (localStorage.getItem("zm-lang")) {
                $translateProvider.preferredLanguage(localStorage.getItem("zm-lang"));
            } else {
                $translateProvider.determinePreferredLanguage();
            }

        }]);
    };
    var initialize = function (angModule) {
        angModule.controller('AppController', app);
        _.each(controllers, function (controller, name) {
            angModule.controller(name, controller);
        });
        setUpRoutes(angModule);
        setUpConstants(angModule);
        setUpLanguage(angModule);
        Console.info("Registered Controllers: ", controllers);
    };


    Console.groupEnd();
    return {
        initialize: initialize
    };
});
