module.exports = function (config) {
    config.set({
            basePath: '../../',
            frameworks: ['jasmine', 'requirejs'
            ],
            files: [
                {pattern: 'main/v2/vendor/**/*.js', included: false},
                {pattern: 'main/v2/js/**/*.js', included: false},
                {pattern: 'test/javascript/**/*Spec.js', included: false},

                'test/test-main.js'
            ],

// list of files to exclude
            exclude: [
                'src/main.js'
            ],

            autoWatch: true,

            LogLevel: config.LOG_DEBUG,

            browsers: ['Chrome'],

            junitReporter: {
                outputFile: 'test_out/unit.xml',
                suite: 'unit'
            }
        }
    )
    ;
}
;