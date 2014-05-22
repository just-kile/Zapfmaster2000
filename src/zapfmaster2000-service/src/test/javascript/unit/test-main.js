/**
 * another one monkey patch to prevent "no timestamp" error
 * https://github.com/karma-runner/karma-requirejs/issues/6#issuecomment-23037725
 */
(function (global) {
    "use strict";
    var fileWithoutLeadingSlash;
    // array where all spec files will be included
    global.tests = [];

    for (var file in global.__karma__.files) {
        if (global.__karma__.files.hasOwnProperty(file)) {
            // get rid of leading slash in file path - prevents "no timestamp" error
            fileWithoutLeadingSlash = file.replace(/^\//, '');
            global.__karma__.files[fileWithoutLeadingSlash] = global.__karma__.files[file];
            delete global.__karma__.files[file];

            // we get all the test files automatically and store to window.tests array
            if (/Spec\.js$/.test(fileWithoutLeadingSlash)) {
                global.tests.push(fileWithoutLeadingSlash);
            }
        }
    }
})(this);


require(["base/main/webapp/v2/js/config-require"], function (config) {
    //var config = {paths:{},shim:{}};
    // improve config
    config.baseUrl = 'base/main/webapp/v2/js';
    config.deps = window.tests;
    config.callback = window.__karma__.start;

    // adapt paths to work with built app
    for (var i in config.paths) {
        //config.paths[i] = config.paths[i].replace('../vendor', 'main/webapp/v2/vendor');
    }

    // add config for test dependencies
    config.paths['angular-mocks'] = '../vendor/angular-mocks/angular-mocks';
    config.shim['angular-mocks'] = ['Angular'];

    // alias
    config.paths.Source = 'main/webapp/v2/js';

    // apply config to require
    window.require.config(config);
});


/*var tests = [];
 for (var file in window.__karma__.files) {
 if (/Spec\.js$/.test(file)) {
 tests.push(file);
 }
 }

 requirejs.config({
 baseUrl: 'base/main/webapp/v2/js',

 paths: {
 Console: '../vendor/console/ba-debug',
 jQuery: '../vendor/jquery/dist/jquery.min',//'http://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min'
 Underscore: '../vendor/underscore/underscore',
 d3: '../vendor/d3/d3.min',
 nvd3fix: "js/nvd3.fix",
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
 jQuery: {
 exports: "jQuery"
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
 exports: "console"
 }

 },
 deps: tests,
 callback: window.__karma__.start
 });*/