var gulp = require('gulp'),
    bower = require('gulp-bower'),
    rjs = require("gulp-requirejs"),
    jshint = require('gulp-jshint'),
    uglify = require('gulp-uglify'),
    clean = require('gulp-clean');

var basedirV2 = "../src/main/webapp/v2";
var basedir = "../src/main/webapp";
var distdir = "../dist";

gulp.task("clean", function () {
    "use strict";
    gulp.src(distdir, {read: false})
        .pipe(clean({force: true}));
    return gulp.src(basedirV2 + "/vendor/", {read: false})
        .pipe(clean({force: true}));

});

gulp.task('bower', function () {
    "use strict";
    return bower()
        .pipe(gulp.dest(basedirV2 + '/vendor'));
});


gulp.task('rjs', ['bower'], function () {
    "use strict";
    return rjs({
        baseUrl: basedirV2 + "/js",
        out: 'bootstrap.js',
        name: "bootstrap",
        fileExclusionRegExp:/^(nv.d3.min)$/,
        mainConfigFile: basedirV2 + "/js/bootstrap.js",
        findNestedDependencies: true
    })
        .pipe(uglify({
            outSourceMap: true
        }))
        .pipe(gulp.dest(distdir + '/v2/js'));
});
gulp.task('rjs-nouglify', ['bower'], function () {
    "use strict";
    return rjs({
        baseUrl: basedirV2 + "/js",
        out: 'bootstrap.js',
        name: "bootstrap",
        mainConfigFile: basedirV2 + "/js/bootstrap.js",
        findNestedDependencies: true
    })
        .pipe(gulp.dest(distdir + '/v2/js'));
});

gulp.task('lint', function () {
    "use strict";
    return gulp.src(basedirV2 + '/js/**/*.js')
        .pipe(jshint())
        .pipe(jshint.reporter('default'));
});

gulp.task("jasmine", function () {

});

gulp.task("scss", function () {

});


gulp.task('default', [ "bower", "lint", 'jasmine']);
gulp.task('develop', [ "lint", 'jasmine', 'scss', 'rjs-nouglify']);
gulp.task('production', [ "lint", 'jasmine', 'scss', 'rjs']);