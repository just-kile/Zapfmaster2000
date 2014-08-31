define(['Console', 'moment', 'Underscore'], function (Console, moment, _) {
    "use strict";
    Console.group("Entering AchievementStats controller module.");

    var controller = ['$scope', '$timeout', 'CometService', 'DataService', "ZMConstants", 'DateService',
        function ($scope, $timeout, CometService, ajax, c, DateService) {
            Console.group("AchievementStats controller entered.");
            var achievements = {};
            $scope.baseUrl = c.baseUrl;
            function generateRandomId() {
                var index = Math.floor((Math.random() * achievements.length));
                return achievements[index].achievementId;
            }

            function update(achievementId, callback) {
                ajax.getDetailedAchievementStats(achievementId).then( function (achievementStats) {
                    $scope.achievementName = achievementStats.achievementName;
                    $scope.image = achievementStats.achievementImage;
                    $scope.description = achievementStats.description;
                    $scope.users = achievementStats.users;
                    if (callback) {
                        callback();
                    }
                });
            }

            $scope.updateFn = function (ok) {
                update(generateRandomId(), ok);
            };
            function init() {
                ajax.getDatas(c.achievementsUrl, function (data) {
                    achievements = data;
                    update(generateRandomId());
                });

            }

            init();

            Console.groupEnd();
        }];

    Console.groupEnd();
    return controller;
});
