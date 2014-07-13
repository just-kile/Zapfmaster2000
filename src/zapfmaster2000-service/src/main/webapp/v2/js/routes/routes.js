define([
    'text!templates/presentation.html',
    'text!templates/home.html',
    'text!templates/stats.html',
    'text!templates/challenges.html',
    'text!templates/logout.html',

], function (presentationTemplate, homeTemplate, statsTemplate, challengesTemplate,logoutTemplate) {
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
            route: '/news',
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
        },
        user: {
            title: 'User',
            route: '/user',
            controller: 'ChallengesWebappController',
            template: challengesTemplate
        },
        logout: {
            title: 'Logout',
            route: '/logout',
            controller: 'LogoutController',
            template: logoutTemplate
        }
    };
});
