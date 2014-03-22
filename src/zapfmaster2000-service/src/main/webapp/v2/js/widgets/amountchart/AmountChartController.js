define(['Console', 'Underscore'], function (Console, _) {
    "use strict";
    Console.group("Entering Amount Chart controller module.");

    var controller = ['$scope', '$timeout', 'CometService', 'DataService', "ZMConstants",
        function ($scope, $timeout, CometService, ajax, c) {
            Console.group("Bestlist controller entered.");
            /*Bestlist*/
            $scope.baseUrl = c.baseUrl;
            $scope.currentAmount = 0;

            var updateAmount  =function(amount){
               // $scope.currentAmount =$scope.currentAmount-0.5<0?2:$scope.currentAmount-0.5;
                $scope.currentAmount =amount;


            }

            var retrieveAmount  =function(callback){
                ajax.getDatas(c.amountStatsUrl,function(responseJson){
                     callback(responseJson.amountTotal);
                });
            }
            CometService.addPushListener(function (data) {
                if (c.DRAWING == data.type) {
                    retrieveAmount(updateAmount);
                }

            });
            retrieveAmount(updateAmount);

           // updateAmount();
            Console.groupEnd();
        }];
    //controller.$inject = [];

    Console.groupEnd();
    return controller;
});
