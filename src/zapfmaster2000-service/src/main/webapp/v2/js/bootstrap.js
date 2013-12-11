"use strict";

requirejs.config({

    paths: {
        Console: 'libs/console/console-min',
        jQuery: 'libs/jquery/jquery-2.0.3.min',//'http://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min'
        Underscore: 'libs/underscore/underscore-min',
        Angular: 'libs/angular/angular.min',
        AngularRoute: 'libs/angular/angular-route',
        AngularResource: "libs/angular/angular-resource",
        AngularAnimate:"libs/angular/angular-animate",
        moment:"libs/moment/moment.min",
        templates: './../views'
    },
    shim: {
        Angular: {
            exports: "angular"
        },
        AngularResource: {
            deps: ['Angular'],
            exports:""
        },
        AngularRoute: {
            deps: ['Angular']
        },
        AngularAnimate: {
            deps: ['Angular']
        },
        jQuery: {
            exports: "jQuery"
        },
        Underscore: {
            exports: "_"
        },

        Console: {
            exports: "console"
        }
    }, priority: [
        "Console"
        , "jQuery"
        , "Underscore"
        , "Angular"
    ], urlArgs: 'v=1.0'
});

requirejs([
    // Standard Libs
    'require'
    , 'Console'
    , 'jQuery'
    , 'Underscore'
    , 'moment'
    , 'Angular',
    'AngularRoute',
    'AngularResource',
    'AngularAnimate'
], function (require, Console, $, _,moment, angular,angularRoute,angularResource,angularAnimate) {
    Console.group("Bootstrap dependencies loaded.");
    Console.info("Console", Console);
    Console.info("jQuery", $);
    Console.info("Underscore: ", _);
    Console.info("Moment: ",moment);
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
