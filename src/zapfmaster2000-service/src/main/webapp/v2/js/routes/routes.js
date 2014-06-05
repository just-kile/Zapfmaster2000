define([
    'text!templates/index.html',
    'text!templates/stats.html'
], function (presentationTemplate, statsTemplate) {
    "use strict";
    return {
        presentationSlide: {
            title: 'Presentation',
            route: '/presentation/:widgetId',
            controller: 'presentationController',
            template: presentationTemplate
        },
        presentation: {
            title: 'Presentation',
            route: '/presentation',
            controller: 'presentationController',
            template: presentationTemplate
        },
        home: {
            title: 'Home',
            route: '/home',
            controller: 'presentationController',
            template: presentationTemplate
        }




    };
});
