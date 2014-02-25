define(['Console', 'Underscore'], function (Console, _) {
    "use strict";
    Console.group("Entering Amount Chart controller module.");

    var controller = ['$scope', '$timeout', 'CometService', 'DataService', "ZMConstants",
        function ($scope, $timeout, CometService, ajax, c) {
            Console.group("Bestlist controller entered.");
            /*Bestlist*/
            $scope.baseUrl = c.baseUrl;
            $scope.currentAmount = 0;
            var updateAmount  =function(){
                $scope.currentAmount =Math.random()*2;
                $timeout(updateAmount,2000);
            }
            updateAmount();
            Console.groupEnd();
        }];
    //controller.$inject = [];

    Console.groupEnd();
    return controller;
});
