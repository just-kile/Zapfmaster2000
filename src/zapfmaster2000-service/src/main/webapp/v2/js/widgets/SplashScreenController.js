define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering Challenge Splash Screen controller module.");

    var controller = ['$scope', 'SplashScreenService', function ($scope, splash) {
        Console.group("Challenge Splash Screen controller entered.");

        Console.groupEnd();
        splash.register($scope);
        Console.groupEnd();
    }];
    //controller.$inject = [];

    Console.groupEnd();
    return controller;
});
