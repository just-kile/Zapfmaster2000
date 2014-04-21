"use strict";

requirejs.config({

    paths: {
        Console: 'libs/console/console-min',
        jQuery: 'libs/jquery/jquery-2.0.3.min',//'http://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min'
        Underscore: 'libs/underscore/underscore-min',
        d3:'libs/d3js/d3.v2',
        nvd3:'libs/d3js/nv.d3',
        Angular: 'libs/angular/angular.min',
        AngularRoute: 'libs/angular/angular-route',
        AngularResource: "libs/angular/angular-resource",
        AngularAnimate:"libs/angular/angular-animate",
        AngularTranslate:"libs/angular/angular-translate.min",
        AngularWebworker:"libs/angular/webworkerpool-core-angular.min",
        AngularD3Directives:"libs/angular/angularjs-nvd3-directives.min",
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
        AngularD3Directives: {
            deps: ['Angular','d3'],
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
        jQuery: {
            exports: "jQuery"
        },
        Underscore: {
            exports: "_"
        },
        d3:{
          exports:"d3",
            init:function(d3){
                window.d3 = d3;
            }
        },
        nvd3:{
            deps:['d3'],
            exports:"nv"
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
    'AngularD3Directives',

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
    window.d3 = d3;
    require(['app','nvd3'], function (App) {
        Console.group("Starting bootstrap.");
        Console.info("App: ", App);
        Console.groupEnd();
        App.initialize();


    });

    Console.groupEnd();
});
