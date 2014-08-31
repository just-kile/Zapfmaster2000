define(["Console", 'Underscore'], function (Console, _) {
    "use strict";
    Console.group("Entering HomeController module.");

    var controller = ['$scope', 'ZMConstants', "CometService", "ResizeService",
        function ($scope, c, CometService, resizeService) {
            CometService.startNewsPush().startChallengePush().stopBoxInstantPush();

            $scope.baseUrl = c.baseUrl;

        }];
    Console.groupEnd();
    return controller;
});
