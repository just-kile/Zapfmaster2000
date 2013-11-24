define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering Draftkit controller module.");

    var controller = ['$scope',"CometService",function ($scope,CometService) {
        Console.group("Draftkit controller entered.");
        CometService.addPushListener(function(data){

        });

        Console.groupEnd();
    }];
    //controller.$inject = [];

    Console.groupEnd();
    return controller;
});
