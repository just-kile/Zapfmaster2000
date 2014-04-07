define(['Console', 'Underscore'], function (Console, _) {
    "use strict";
    Console.group("Entering Amount Chart controller module.");

    var controller = ['$scope', '$timeout', 'CometService', 'DataService', "ZMConstants",
        function ($scope, $timeout, CometService, ajax, c) {
            Console.group("Bestlist controller entered.");
            /*Bestlist*/
            $scope.baseUrl = c.baseUrl;
            $scope.currentAmount = 0;
            $scope.achievementCount = 0;
            var updateView  =function(amount){
               // $scope.currentAmount =$scope.currentAmount-0.5<0?2:$scope.currentAmount-0.5;
                $scope.currentAmount =amount.amountTotal;

            }

            var updateAchievements = function(achievementsCount){
                $scope.achievementCount =achievementsCount.count;
            }
            var retrieveAmount  =function(callback){
                ajax.getDatas(c.amountStatsUrl,function(responseJson){
                     callback(responseJson);
                });
            }
            var retrieveAchievement = function(callback){
                ajax.getDatas(c.achievementStatsUrl,function(achievementStats){
                      callback(achievementStats);
                });
            }

            CometService.addPushListener(function (data) {
                if (c.DRAWING == data.type ) {
                    retrieveAmount(updateView);
                }
                else if(c.ACHIEVEMENT == data.type){
                    retrieveAchievement(updateAchievements);
                }

            });
            retrieveAmount(updateView);
            retrieveAchievement(updateAchievements);
           // updateAmount();
            Console.groupEnd();
        }];
    //controller.$inject = [];

    Console.groupEnd();
    return controller;
});
