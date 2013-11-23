define([
    'text!templates/index.html',
    'text!templates/stats.html'
], function (homeTemplate, statsTemplate) {
    return {
        home: {
            title: 'Home', route: '/home', controller: 'home', template: homeTemplate
        },
        stats: {
            title: 'Data List', route: '/stats', controller: 'stats', template: statsTemplate
        }
    };
})
