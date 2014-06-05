define([
    'text!templates/presentation.html',
    'text!templates/home.html',
    'text!templates/stats.html',
    'text!templates/challenges.html',

], function (presentationTemplate, homeTemplate) {
    "use strict";
    return {
        presentationSlide: {
            title: 'Presentation',
            route: '/presentation/:widgetId',
            controller: 'PresentationController',
            template: presentationTemplate
        },
        presentation: {
            title: 'Presentation',
            route: '/presentation',
            controller: 'PresentationController',
            template: presentationTemplate
        },
        home: {
            title: 'Home',
            route: '/home',
            controller: 'HomeController',
            template: homeTemplate
        },
        statistics: {
            title: 'Stats',
            route: '/statistics',
            controller: 'StatsController',
            template: homeTemplate
        },
        challenges: {
            title: 'Challenges',
            route: '/challenges',
            controller: 'ChallengesWebappController',
            template: homeTemplate
        }





    };
});
