define([
    'text!templates/index.html',
    'text!templates/stats.html'
], function (homeTemplate, statsTemplate) {
    return {
        home: {
            title: 'Home', route: '/home', controller: 'home', template: homeTemplate
        },
        stats: {
            title: 'Stats', route: '/stats', controller: 'stats', template: statsTemplate
        }
    };
})
