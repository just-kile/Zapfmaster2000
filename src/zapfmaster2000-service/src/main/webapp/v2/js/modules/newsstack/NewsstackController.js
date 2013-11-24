define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering Newsstack controller module.");

    var controller = ['$scope',function ($scope) {
        Console.group("Newsstack controller entered.");

        Console.groupEnd();
    }];
    //controller.$inject = [];

    Console.groupEnd();
    return controller;
});
