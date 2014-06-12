var gulp = require('gulp'),
    bower = require('gulp-bower'),
    rjs = require('gulp-requirejs'),
    jshint = require('gulp-jshint'),
    uglify = require('gulp-uglify'),
    clean = require('gulp-clean'),
    sass = require('gulp-sass'),
    watch = require('gulp-watch'),
    plumber = require('gulp-plumber'),
    minifyCSS = require('gulp-minify-css');


var basedirV2 = '../src/main/webapp/v2';
var basedir = '../src/main/webapp';
var distdir = '../target/dist';

gulp.task('clean', function () {
    'use strict';
    gulp.src(distdir, {read: false})
        .pipe(clean({force: true}));
    return gulp.src(basedirV2 + '/vendor/', {read: false})
        .pipe(clean({force: true}));

});

/*Installs frontend dependencies*/
gulp.task('bower', function () {
    'use strict';
    return bower()
        .pipe(gulp.dest(basedirV2 + '/vendor'));
});

/*Concat and compress js files*/
gulp.task('rjs', ['bower'], function () {
    'use strict';
    return rjs({
        baseUrl: basedirV2 + '/js',
        out: 'bootstrap.js',
        name: 'bootstrap',
        // fileExclusionRegExp:/^(nv.d3.min)/,
        mainConfigFile: basedirV2 + '/js/config-require.js',
        findNestedDependencies: true
    })
        .pipe(uglify({
            outSourceMap: true
        }))
        .pipe(gulp.dest(distdir + '/v2/js'));
});

/*Only concat js filees*/
gulp.task('rjs-nouglify', ['bower'], function () {
    'use strict';
    return rjs({
        baseUrl: basedirV2 + '/js',
        out: 'bootstrap.js',
        name: 'bootstrap',
        paths: {
            nvd3: 'empty:'
        },
        //mainConfigFile: basedirV2 + '/js/config-require.js',
        mainConfigFile: basedirV2 + '/js/config-require.js',
        findNestedDependencies: true
    })
        .pipe(gulp.dest(distdir + '/v2/js'));
});
/*Checks js syntax*/
gulp.task('lint', function () {
    'use strict';
    return gulp.src(basedirV2 + '/js/**/*.js')
        .pipe(jshint())
        .pipe(jshint.reporter('default'));
});

/*Compiles Sass*/
gulp.task('sass', function () {
    return gulp.src(basedirV2 + '/sass/*.scss')
        .pipe(sass({style: 'compressed'}))
        .pipe(minifyCSS())
        .pipe(gulp.dest(basedirV2 + '/css'));
});

/*Watch task for sass development*/
gulp.task('watch', function () {
    gulp.src(basedirV2 + '/sass/**/*.scss')
        .pipe(watch(function (files) {
            return gulp.src(basedirV2 + '/sass/**/*.scss')
                .pipe(plumber())
                .pipe(sass())
                .pipe(gulp.dest(basedirV2 + '/css'));
        }));
});


gulp.task('default', [ 'bower', 'lint', 'sass']);
gulp.task('develop', [ 'lint', 'sass', 'rjs-nouglify']);
gulp.task('production', [ 'lint', 'sass', 'rjs']);