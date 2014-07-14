define(["Console", 'Underscore'], function (Console, _) {
    "use strict";
    Console.group("Entering HomeController module.");

    var controller = ['$scope', '$routeParams', '$timeout', 'ZMConstants', '$animate', '$translate', 'CometService',
        function ($scope, $routeParams, $timeout, c, $animate, $translate, cometService) {
            cometService.stopCometService();
            cometService.startChallengePush();

        }];
    Console.groupEnd();
    return controller;
});
