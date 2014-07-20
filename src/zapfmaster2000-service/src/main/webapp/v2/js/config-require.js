define(function () {
    "use strict";
    var config;
    requirejs.config({
        paths: {
            Console: '../vendor/console/ba-debug',
            Bootstrap: '../vendor/bootstrap/dist/js/bootstrap.min',
            jQuery: '../vendor/jquery/dist/jquery.min',
            Underscore: '../vendor/underscore/underscore',
            //d3: '../vendor/d3/d3.min',
            d3: '//cdnjs.cloudflare.com/ajax/libs/d3/3.4.11/d3.min',
            nvd3fix: "js/nvd3.fix",
            nvd3: '../vendor/nvd3/nv.d3.min',
            Angular: '../vendor/angular/angular.min',
            AngularRoute: '../vendor/angular-route/angular-route.min',
            AngularResource: "../vendor/angular-resource/angular-resource.min",
            AngularAnimate: "../vendor/angular-animate/angular-animate.min",
            AngularD3Directives: "../vendor/angularjs-nvd3-directives/dist/angularjs-nvd3-directives.min",
            AngularTranslate: "../vendor/angular-translate/angular-translate.min",
            AngularTranslateLocalStorage: "../vendor/angular-translate-storage-local/angular-translate-storage-local.min",
            AngularTranslateCookieStorage: "../vendor/angular-translate-storage-cookie/angular-translate-storage-cookie.min",
            AngularTranslateStaticFileLoader: "../vendor/angular-translate-loader-static-files/angular-translate-loader-static-files.min",
            AngularGoogleAnalytics: "../vendor/angular-google-analytics/dist/angular-google-analytics.min",
            AngularInfiniteScroll: "../vendor/ngInfiniteScroll/build/ng-infinite-scroll.min",
            moment: "../vendor/moment/min/moment.min",
            text: "../vendor/requirejs-text/text",
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
            AngularGoogleAnalytics: {
                deps: ['Angular']
            },
            AngularInfiniteScroll: {
                deps: ['Angular']
            },
            jQuery: {
                exports: "jQuery"
            },
            Bootstrap: {
                deps: ["jQuery"]
            },
            Underscore: {
                exports: "_"
            },

            d3: {
                exports: "d3",
                init: function (d3) {
                    window.d3 = d3;
                }
            },
            nvd3: {
                deps: ['d3', 'nvd3.fix'],
                exports: "nv"
            },
            Console: {
                exports: "debug"
            }

        },
        priority: [
            "Console",
            "jQuery",
            "Underscore",
            "Angular"
        ]
    });


    return require.s.contexts._.config;
});