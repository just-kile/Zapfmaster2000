"use strict";

requirejs.config({

    paths: {
        Console: 'libs/console/console-min',
        jQuery: 'libs/jquery/jquery-2.0.3.min',//'http://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min'
        Underscore: 'libs/underscore/underscore-min',
        d3:'libs/d3js/d3.min',
        Angular: 'libs/angular/angular.min',
        AngularRoute: 'libs/angular/angular-route',
        AngularResource: "libs/angular/angular-resource",
        AngularAnimate:"libs/angular/angular-animate",
        AngularWebworker:"libs/angular/webworkerpool-core-angular.min",
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
        AngularWebworker:{
          deps:['Angular']
        },
        jQuery: {
            exports: "jQuery"
        },
        Underscore: {
            exports: "_"
        },
        d3:{
          exports:"d3"
        },
        Console: {
            exports: "console"
        } ,

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
    ,'d3'
    , 'Angular',
    'AngularRoute',
    'AngularResource',
    'AngularAnimate',
    'AngularWebworker'
], function (require, Console, $, _,moment,d3, angular,angularRoute,angularResource,angularAnimate) {
    Console.group("Bootstrap dependencies loaded.");
    Console.info("Console", Console);
    Console.info("jQuery", $);
    Console.info("Underscore: ", _);
    Console.info("Moment: ",moment);
    Console.info("d3: ",d3);
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
