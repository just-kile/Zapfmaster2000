define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering AboutUs controller module.");

    var controller = ['$scope', '$timeout', 'CometService', 'DataService', "ZMConstants",
        function ($scope, $timeout, CometService, ajax, c) {
            Console.group("About Us controller entered.");

        }];

    Console.groupEnd();
    return controller;
});
