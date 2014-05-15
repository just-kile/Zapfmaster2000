var require = require || {};
var gulp = require('gulp'),
    bower = require('gulp-bower'),
    rjs = require("gulp-requirejs"),
    jshint = require('gulp-jshint'),
    uglify = require('gulp-uglify');

var basedir = "../src/main/webapp/v2";
var distdir = "../src/main/webapp/v2/dist";

gulp.task('bower', function () {
    "use strict";
    return bower()
        .pipe(gulp.dest(basedir + '/js/vendor'));
});

gulp.task('rjs', ['bower'], function () {
    "use strict";
    rjs({
        baseUrl: basedir + "/js",
        out: 'bootstrap.js',
        name: "bootstrap",
        mainConfigFile: basedir + "/js/bootstrap.js",
        findNestedDependencies: true
    })
        .pipe(uglify({
            outSourceMap: true
        }))
        .pipe(gulp.dest(distdir + '/js'));
});
gulp.task('html', function () {
    "use strict";

});

gulp.task('lint', function () {
    "use strict";

    return gulp.src(basedir + '/js/**/*.js')
        .pipe(jshint())
        .pipe(jshint.reporter('default'));
});


gulp.task('default', ['bower', 'rjs', 'lint']);
