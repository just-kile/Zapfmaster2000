var require = require || {};
var gulp = require('gulp'),
    bower = require('gulp-bower'),
    rjs = require("gulp-requirejs"),
    jshint = require('gulp-jshint'),
    uglify = require('gulp-uglify'),
    clean = require('gulp-clean');

var basedirV2 = "../src/main/webapp/v2";
var basedir = "../src/main/webapp";
var distdir = "../dist";


gulp.task('resources', function () {
    "use strict";

    //copy web.xml
    gulp.src(basedir + "/WEB-INF/web.xml")
        .pipe(gulp.dest(distdir + "/WEB-INF"));
    //copy index.html
    gulp.src(basedir + "/index.html")
        .pipe(gulp.dest(distdir));
    gulp.src(basedir + "/install.html")
        .pipe(gulp.dest(distdir));
    gulp.src(basedir + "/css/login.css")
        .pipe(gulp.dest(distdir + "/css"));
    gulp.src(basedir + "/libs/**/*.*")
        .pipe(gulp.dest(distdir + "/libs"));

    //copy dashboard TODO: Add missing dependencies
    gulp.src(basedir + "/dashboard/*.html")
        .pipe(gulp.dest(distdir + "/dashboard"));
    gulp.src(basedir + "/dashboard/css/style.css")
        .pipe(gulp.dest(distdir + "/dashboard/css"));
    gulp.src(basedir + "/dashboard/css/style.css")
        .pipe(gulp.dest(distdir + "/dashboard/css"));

   //copy v2 resources
    gulp.src(basedir + "/v2/css/**/*.*")
        .pipe(gulp.dest(distdir + "/v2/css"));
    gulp.src(basedir + "/v2/l10n/**/*.*")
        .pipe(gulp.dest(distdir + "/v2/l10n"));
    gulp.src(basedir + "/v2/views/**/*.*")
        .pipe(gulp.dest(distdir + "/v2/views"));

});

gulp.task("clean", function () {
    "use strict";
    gulp.src(distdir, {read: false})
        .pipe(clean({force: true}));

    gulp.src(basedirV2 + "/vendor/", {read: false})
     .pipe(clean({force: true}));

});

gulp.task('bower', function () {
    "use strict";
    bower()
        .pipe(gulp.dest(basedirV2 + '/js/vendor'));
});


gulp.task('rjs', ['bower'], function () {
    "use strict";
    rjs({
        baseUrl: basedirV2 + "/js",
        out: 'bootstrap.js',
        name: "bootstrap",
        mainConfigFile: basedirV2 + "/js/bootstrap.js",
        findNestedDependencies: true
    })
        .pipe(uglify({
            outSourceMap: true
        }))
        .pipe(gulp.dest(distdir + '/v2/js'));
});

gulp.task('lint', function () {
    "use strict";

    return gulp.src(basedirV2 + '/js/**/*.js')
        .pipe(jshint())
        .pipe(jshint.reporter('default'));
});


gulp.task('default', ['clean', 'bower', 'lint', 'resources', 'rjs']);
