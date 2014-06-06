define([
    'text!templates/presentation.html',
    'text!templates/home.html',
    'text!templates/stats.html',
    'text!templates/challenges.html',

], function (presentationTemplate, homeTemplate,statsTemplate,challengesTemplate) {
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
            route: '/stats',
            controller: 'StatsController',
            template: statsTemplate
        },
        challenges: {
            title: 'Challenges',
            route: '/challenges',
            controller: 'ChallengesWebappController',
            template: challengesTemplate
        }





    };
});
