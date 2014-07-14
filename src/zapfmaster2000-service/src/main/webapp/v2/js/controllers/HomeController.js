define(["Console", 'Underscore'], function (Console, _) {
    "use strict";
    Console.group("Entering HomeController module.");

    var controller = ['$scope', 'ZMConstants', "CometService", "ResizeService",
        function ($scope, c, cometService, resizeService) {
            cometService.stopCometService();
            cometService.startChallengePush();
            $scope.baseUrl = c.baseUrl;

        }];
    Console.groupEnd();
    return controller;
});
