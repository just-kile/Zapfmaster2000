define(["Console", 'Underscore'], function (Console, _) {
    "use strict";
    Console.group("Entering HomeController module.");

    var controller = ['$scope','ZMConstants', "CometService", "ResizeService",
        function ($scope, c, cometService, resizeService) {
            localStorage.removeItem("token");
            window.location.href = c.baseUrl;
        }];
    Console.groupEnd();
    return controller;
});
