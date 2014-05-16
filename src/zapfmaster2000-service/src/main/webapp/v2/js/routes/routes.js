define([
    'text!templates/index.html',
    'text!templates/stats.html'
], function (homeTemplate, statsTemplate) {
    return {
        homeWithWidget: {
            title: 'Home',
            route: '/home/:widgetId',
            controller: 'home',
            template: homeTemplate
        },
        home: {
            title: 'Home',
            route: '/home',
            controller: 'home',
            template: homeTemplate
        },

        stats: {
            title: 'Stats',
            route: '/stats',
            controller: 'stats',
            template: statsTemplate
        }
    };
})
