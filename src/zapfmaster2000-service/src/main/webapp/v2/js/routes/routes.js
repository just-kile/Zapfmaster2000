define([
    'text!templates/presentation.html',
    'text!templates/webapp.html'
], function (presentationTemplate, webappTemplate) {
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
            controller: 'WebappController',
            template: webappTemplate
        }




    };
});
