require(["config-require"],function(config){
    "use strict";
    //config.urlArgs = 'token=' + localStorage.getItem("token");
    require.config(config);
    require([
        // Standard Libs
        'require',
        'Console',
        'jQuery',
        'Underscore',
        'moment',
        'd3',
        'Angular',
        'nvd3',
        'text',
        'Bootstrap',
        'AngularD3Directives',
        'AngularRoute',
        'AngularResource',
        'AngularAnimate',
        'AngularTranslate',
        'AngularTranslateStaticFileLoader',
        'AngularTranslateLocalStorage',
        'AngularTranslateCookieStorage'

    ], function (require, Console, $, _, moment, d3, angular) {
        Console.group("Bootstrap dependencies loaded.");
        Console.info("Console", Console);
        Console.info("jQuery", $);
        Console.info("Underscore: ", _);
        Console.info("Moment: ", moment);
        Console.info("d3: ", d3);
        Console.info("Angular: ", angular);
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
