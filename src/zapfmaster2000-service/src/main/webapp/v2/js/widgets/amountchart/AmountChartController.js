define(['Console', 'Underscore'], function (Console, _) {
    "use strict";
    Console.group("Entering Amount Chart controller module.");

    var controller = ['$scope', '$timeout', 'CometService', 'DataService', "ZMConstants",
        function ($scope, $timeout, CometService, ajax, c) {
            Console.group("Bestlist controller entered.");
            /*Bestlist*/
            $scope.baseUrl = c.baseUrl;
            $scope.currentAmount = 0.6;
            var updateAmount  =function(){
               // $scope.currentAmount =$scope.currentAmount-0.5<0?2:$scope.currentAmount-0.5;
                $scope.currentAmount =$scope.currentAmount+0.4;

                $timeout(updateAmount,3000);
            }
            updateAmount();
            Console.groupEnd();
        }];
    //controller.$inject = [];

    Console.groupEnd();
    return controller;
});
