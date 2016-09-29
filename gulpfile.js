var gulp = require('gulp'),
    bower = require('gulp-bower'),
    usemin = require('gulp-usemin'),
    wrap = require('gulp-wrap'),
    connect = require('gulp-connect'),
    watch = require('gulp-watch'),
    cleanCSS = require('gulp-clean-css'),
    minifyJs = require('gulp-uglify'),
    concat = require('gulp-concat'),
    less = require('gulp-less'),
    rename = require('gulp-rename'),
    minifyHTML = require('gulp-minify-html');

var paths = {
    src:{
        index: 'src/main/ng-app/index.html',
        js: 'src/main/ng-app/js/**/*.*',
        css: 'src/main/ng-app/css/**/*.*',
        images: 'src/main/ng-app/images/**/*.*',
        templates: 'src/main/ng-app/templates/**/*.html',
        bower_libs: 'src/bower_components/**/*.*'
    },
    dist: {
        index: 'src/main/webapp',
        js: 'src/main/webapp/resources/js/',
        images: 'src/main/webapp/resources/img/',
        css: 'src/main/webapp/resources/css',
        templates: 'src/main/webapp/templates',
        lib: 'src/main/webapp/resources/lib'
    }
};


/**
 * Handle bower components from index
 */
gulp.task('usemin', function() {
    return gulp.src(paths.src.index)
        .pipe(usemin({
            js: [minifyJs(), 'concat'],
            css: [cleanCSS({keepSpecialComments: 0}), 'concat']
        }))
        .pipe(gulp.dest(paths.dist.index));
});


//build assets
gulp.task('build-assets', ['bower-install']);

/**
 * install bower components on the app directory
 **/
gulp.task('bower-install', function() {
      return bower()
           .pipe(gulp.dest(paths.dist.lib));
});

/**
 * Handle custom files
 */
gulp.task('build-custom', ['custom-images', 'custom-js', 'custom-less', 'custom-templates']);

gulp.task('custom-images', function() {
    return gulp.src(paths.src.images)
        .pipe(gulp.dest(paths.dist.images));
});

/*
 * copy and minify javascript
 **/
gulp.task('custom-js', function() {
    return gulp.src(paths.src.js)
        .pipe(minifyJs())
        .pipe(concat('ng-app.min.js'))
        .pipe(gulp.dest(paths.dist.js));
});

gulp.task('custom-less', function() {
    return gulp.src(paths.src.css)
            .pipe(cleanCSS({compatibility: 'ie8'}))
            .pipe(concat('ng-app.min.css'))
            .pipe(gulp.dest(paths.dist.css));
});

gulp.task('custom-templates', function() {
    return gulp.src(paths.src.templates)
        .pipe(minifyHTML())
        .pipe(gulp.dest(paths.dist.templates));
});

/**
 * Watch custom files
 */
gulp.task('watch', function() {
    gulp.watch([paths.src.images], ['custom-images']);
    gulp.watch([paths.src.css], ['custom-less']);
    gulp.watch([paths.src.js], ['custom-js']);
    gulp.watch([paths.src.templates], ['custom-templates']);
    gulp.watch([paths.src.index], ['usemin']);
});

/**
 * Live reload server
 */
gulp.task('webserver', function() {
    connect.server({
        root: paths.dist.index,
        livereload: true,
        port: 8888
    });
});

gulp.task('livereload', function() {
    gulp.src([paths.dist.index +'/*.html',
              paths.dist.js +'**/*.js',
              paths.dist.css +'**/*.css',
              paths.dist.images +'**/*.*',
              paths.dist.templates +'**/*.*'])
        .pipe(watch())
        .pipe(connect.reload());
});

/**
 * Gulp tasks
 */
gulp.task('build', ['usemin', 'build-assets', 'build-custom']);
gulp.task('default', ['build', 'webserver', 'livereload', 'watch']);
