define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering Newsstack controller module.");

    var controller = ['$scope','SplashScreenService',function ($scope,splash) {
        Console.group("Newsstack controller entered.");

        Console.groupEnd();
        splash.register($scope);

    }];
    //controller.$inject = [];

    Console.groupEnd();
    return controller;
});
