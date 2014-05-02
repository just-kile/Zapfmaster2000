define(['Console', 'moment', 'Underscore'], function (Console, moment, _) {
    "use strict";
    Console.group("Entering AchievementStats controller module.");

    var controller = ['$scope', '$timeout', 'CometService', 'DataService', "ZMConstants", 'DateService',
        function ($scope, $timeout, CometService, ajax, c, DateService) {
            Console.group("AchievementStats controller entered.");
            var achievements = {};
            $scope.baseUrl = c.baseUrl;
            function generateRandomId(){
                var index = Math.floor((Math.random()*achievements.length));
               return achievements[index].achievementId;
            }
            function update(achievementId,callback){
               // achievementId = 1;
                ajax.getDatas(c.achievementStatsDetailedUrl, function (data) {
                    $scope.achievementName=data.achievementName;
                    // $scope.userImage = data.user.userImage;
                    $scope.image = data.achievementImage;
                    $scope.description = data.description;
                    $scope.users = data.users;
                    callback && callback();
                }, {id: achievementId});
            }
            $scope.updateFn = function(ok){
                update(generateRandomId(),ok);
            }
            function init(){
                ajax.getDatas(c.achievementsUrl,function(data){
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
