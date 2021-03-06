define(['Console', 'Underscore'], function (Console, _) {
    "use strict";
    Console.group("Entering AchievementFeed controller module.");

    var controller = ['$scope', '$timeout', 'CometService', 'DataService', "ZMConstants",
        function ($scope, $timeout, CometService, dataService, c) {
            Console.group("AchievementFeed controller entered.");
            $scope.baseUrl = c.baseUrl;
            $scope.items = [];
            $scope.maxlength = c.newsFeedLength;
            function addNewsToScope(data) {
                $scope.items.unshift(data);
            }

            function onNewsPush(data) {
                if (data.type === c.ACHIEVEMENT) {
                    addNewsToScope(data);
                }
            }

            dataService.getAchievementFeed(c.newsFeedLength).then(function (achievements) {
                _.each(achievements, function (achievement) {
                    $scope.items.push(achievement);
                });
                CometService.addPushListener(onNewsPush);
            });
            $scope.$on("$destroy", function () {
                CometService.removeNewsPushListener(onNewsPush);
            });
            Console.groupEnd();
        }];

    Console.groupEnd();
    return controller;
});
