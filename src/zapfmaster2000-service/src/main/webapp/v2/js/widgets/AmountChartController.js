define(['Console', 'Underscore'], function (Console, _) {
    "use strict";
    Console.group("Entering Amount Chart controller module.");

    var controller = ['$scope', '$timeout', 'CometService', 'DataService', "ZMConstants",
        function ($scope, $timeout, CometService, ajax, c) {
            Console.group("Amount Chart controller entered.");
            /*Bestlist*/
            $scope.baseUrl = c.baseUrl;
            $scope.currentAmount = 0;
            $scope.achievementCount = 0;
            $scope.unitMugs = c.MUG_SIZE;
            $scope.unitAchievements = 1;
            var updateView = function (amount) {
                $scope.currentAmount = amount.amountTotal;

            };

            var updateAchievements = function (achievementsCount) {
                $scope.achievementCount = achievementsCount.count;
            };
            var retrieveAmount = function (callback) {
                ajax.getAmountStats().then(function (responseJson) {
                    callback(responseJson);
                });
            };
            var retrieveAchievement = function (callback) {
                ajax.getAchievementStats().then(function (achievementStats) {
                    callback(achievementStats);
                });
            };
            var onNewsPush = function (data) {
                if (c.DRAWING === data.type) {
                    retrieveAmount(updateView);
                }
                else if (c.ACHIEVEMENT === data.type) {
                    retrieveAchievement(updateAchievements);
                }

            };
            CometService.addPushListener(onNewsPush);
            retrieveAmount(updateView);
            retrieveAchievement(updateAchievements);
            $scope.$on("$destroy", function () {
                CometService.removeNewsPushListener(onNewsPush);
            });
            Console.groupEnd();
        }];

    Console.groupEnd();
    return controller;
});
