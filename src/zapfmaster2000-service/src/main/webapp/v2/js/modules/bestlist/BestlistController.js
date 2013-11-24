define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering Bestlist controller module.");

    var controller = ['$scope','CometService',function ($scope,CometService) {
        Console.group("Bestlist controller entered.");
        CometService.addPushListener(function(data){

        });
        Console.groupEnd();
    }];
    //controller.$inject = [];

    Console.groupEnd();
    return controller;
});
