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
        AngularTranslate:"libs/angular/angular-translate.min",
        AngularTranslateStaticFiles:"libs/angular/angular-translate-loader-static-files.min",
        AngularTranslateLoader:"libs/angular/angular-translate-loader-url.min",
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
        AngularTranslate:{
            deps:['Angular']
        },
        AngularTranslateStaticFiles:{
            deps:['Angular','AngularTranslate']
        },
        AngularTranslateLoader:{
            deps:['Angular','AngularTranslate']
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
        }

    }, priority: [
        "Console"
        , "jQuery"
        , "Underscore"
        , "Angular"
    ], urlArgs: 'token='+localStorage.getItem("token")
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
    'AngularWebworker',
    'AngularTranslate',
    'AngularTranslateStaticFiles',
    //'AngularTranslateLoader'
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
