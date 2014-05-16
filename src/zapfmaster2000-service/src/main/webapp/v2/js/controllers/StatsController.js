define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering DataController module.");

    var controller = ['$scope', 'DataService', function ($scope, DataService) {
        Console.group("DataController entered.");


        Console.groupEnd();
    }];

    Console.groupEnd();
    return controller;
});
