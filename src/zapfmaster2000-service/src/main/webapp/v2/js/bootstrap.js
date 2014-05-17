require(["config"],function(){
    requirejs([
        // Standard Libs
        'require',
        'Console',
        'jQuery',
        'Underscore',
        'moment',
        'd3',
        'nvd3',
        'text',
        'AngularD3Directives',
        'Angular',
        'AngularRoute',
        'AngularResource',
        'AngularAnimate',
        'AngularTranslate',
        'AngularTranslateStaticFileLoader',
        'AngularTranslateLocalStorage',
        'AngularTranslateCookieStorage'

    ], function (require, Console, $, _, moment, d3, angular, angularRoute, angularResource, angularAnimate) {
        "use strict";
        Console.group("Bootstrap dependencies loaded.");
        Console.info("Console", Console);
        Console.info("jQuery", $);
        Console.info("Underscore: ", _);
        Console.info("Moment: ", moment);
        Console.info("d3: ", d3);
        Console.info("Angular: ", angular);
        Console.group("Angular Directives");
        Console.info("ngRoute: ", angularRoute);
        Console.info("ngResource: ", angularResource);
        Console.info("ngAnimate: ", angularAnimate);
        Console.groupEnd();


        require(['app'], function (App) {
            Console.group("Starting bootstrap.");
            Console.info("App: ", App);
            Console.groupEnd();
            App.initialize();
        });

        Console.groupEnd();
    });

});
