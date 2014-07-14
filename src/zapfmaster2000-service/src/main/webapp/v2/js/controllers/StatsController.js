define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering DataController module.");

    var controller = ['$scope', 'DataService','CometService', function ($scope, DataService,cometService) {
        Console.group("DataController entered.");
        cometService.stopCometService();
        cometService.startChallengePush();

        Console.groupEnd();
    }];

    Console.groupEnd();
    return controller;
});
