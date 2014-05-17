requirejs.config({

    paths: {
        Console: '../vendor/console/ba-debug',
        jQuery: '../vendor/jquery/dist/jquery.min',//'http://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min'
        Underscore: '../vendor/underscore/underscore',
        d3: '../vendor/d3/d3.min',
        nvd3fix:"js/nvd3.fix",
        nvd3: "//cdnjs.cloudflare.com/ajax/libs/nvd3/1.1.15-beta/nv.d3.min",//'../vendor/nvd3/nv.d3.min',
        Angular: '../vendor/angular/angular.min',
        AngularRoute: '../vendor/angular-route/angular-route.min',
        AngularResource: "../vendor/angular-resource/angular-resource.min",
        AngularAnimate: "../vendor/angular-animate/angular-animate.min",
        AngularD3Directives: "../vendor/angularjs-nvd3-directives/dist/angularjs-nvd3-directives.min",
        AngularTranslate: "../vendor/angular-translate/angular-translate.min",
        AngularTranslateLocalStorage: "../vendor/angular-translate-storage-local/angular-translate-storage-local.min",
        AngularTranslateCookieStorage: "../vendor/angular-translate-storage-cookie/angular-translate-storage-cookie.min",
        AngularTranslateStaticFileLoader: "../vendor/angular-translate-loader-static-files/angular-translate-loader-static-files.min",
        moment: "../vendor/moment/min/moment.min",
        templates: './../views'
    },
    shim: {
        Angular: {
            exports: "angular"
        },
        AngularResource: {
            deps: ['Angular'],
            exports: ""
        },
        AngularD3Directives: {
            deps: ['Angular', 'd3'],
            exports: ""
        },
        AngularRoute: {
            deps: ['Angular']
        },
        AngularAnimate: {
            deps: ['Angular']
        },
        AngularTranslate: {
            deps: ['Angular']
        },
        AngularTranslateLocalStorage: {
            deps: ['Angular', 'AngularTranslate']
        },
        AngularTranslateCookieStorage: {
            deps: ['Angular', 'AngularTranslate']
        },
        AngularTranslateStaticFileLoader: {
            deps: ['Angular', 'AngularTranslate']
        },
        jQuery: {
            exports: "jQuery"
        },
        Underscore: {
            exports: "_"
        },

        d3: {
            exports: "d3",
            init: function (d3) {
                "use strict";
                window.d3 = d3;
            }
        },
        nvd3: {
            deps: ['d3','nvd3.fix'],
            exports: "nv"
        },
        Console: {
            exports: "console"
        }

    },
    priority: [
        "Console",
        "jQuery",
        "Underscore",
        "Angular"
    ]
    //urlArgs: 'token=' + localStorage.getItem("token")
});
requirejs([
    // Standard Libs
    'require',
    'Console',
    'jQuery',
    'Underscore',
    'moment',
    'd3',
    'nvd3',
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
