// Karma configuration file

module.exports = function (config) {
  config.set({
    basePath: '',
    frameworks: ['jasmine', '@angular-devkit/build-angular'],
    plugins: [
      require('karma-jasmine'),
      require('karma-chrome-launcher'),
      require('karma-jasmine-html-reporter'),
      require('karma-coverage'),
      require('istanbul-instrumenter-loader'),
      require('@angular-devkit/build-angular/plugins/karma')
    ],
    client: {
      jasmine: {
        // You can add configuration options for Jasmine here
        // The possible options are listed at https://jasmine.github.io/api/edge/Configuration.html
        // For example, you can disable the random execution with `random: false`
        // Or set a specific seed with `seed: 4321`
      },
      clearContext: false // Leave Jasmine Spec Runner output visible in the browser
    },
    jasmineHtmlReporter: {
      suppressAll: true // Removes duplicated traces
    },
    coverageReporter: {
      dir: require('path').join(__dirname, './coverage/my-new-login-app'),
      subdir: '.',
      reporters: [
        { type: 'html', subdir: 'report-html' },
        { type: 'text-summary' }
      ],
      check: {
        global: {
          statements: 75,
          branches: 75,
          functions: 75,
          lines: 75
        }
      }
    },
    reporters: ['progress', 'kjhtml', 'coverage'],
    preprocessors: {
      'src/**/*.ts': ['coverage']
    },
    coverageIstanbulReporter: {
      reports: ['html', 'text-summary'],
      dir: require('path').join(__dirname, './coverage/my-new-login-app'),
      fixWebpackSourcePaths: true
    },
    browsers: ['Chrome'],
    singleRun: false,
    restartOnFileChange: true
  });
};
