define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering DataController module.");

    var controller = ['$scope', 'DataService','CometService', function ($scope, DataService,CometService) {
        Console.group("DataController entered.");
        CometService.startNewsPush().startChallengePush().stopBoxInstantPush();


        Console.groupEnd();
    }];

    Console.groupEnd();
    return controller;
});
