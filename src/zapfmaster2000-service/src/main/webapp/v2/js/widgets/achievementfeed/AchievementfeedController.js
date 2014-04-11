define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering AchievementFeed controller module.");

    var controller = ['$scope', '$timeout', 'CometService', 'DataService', "ZMConstants",
        function ($scope, $timeout, CometService, ajax, c) {
            Console.group("Newsfeed controller entered.");
            $scope.baseUrl = c.baseUrl;
            $scope.items = [];
            $scope.maxlength= c.newsFeedLength;
            function addNewsToScope(data){
                $scope.items.unshift(data);
            }
            ajax.getDatas(c.newsFeedUrl, function (data) {
                _.each(data, function (item) {
                    $scope.items.push(item);
                })
                //  $scope.items = data;
                //$scope.items = data;
                //  $scope.maxlength=c.newsFeedLength;
                CometService.addPushListener(function (data) {
                    addNewsToScope(data);

                });
            }, {
                start: 0,
                length: c.newsFeedLength,
                filter: c.ACHIEVEMENT
            });



        }];

    Console.groupEnd();
    return controller;
});
