define(['Console', 'Underscore'], function (Console, _) {
    "use strict";
    Console.group("Entering Newsstack controller module.");

    var controller = ['$scope', '$timeout', 'CometService', 'DataService', "ZMConstants",
        function ($scope, $timeout, CometService, ajax, c) {
            Console.group("Newsfeed controller entered.");


            function addNewsToScope(data) {
                $scope.items.unshift(data);
            }

            function loadNextItems() {
                $scope.busy = true;
                ajax.getNewsFeed($scope.items.length, c.newsFeedLength).then(function (data) {
                    _.each(data, function (item) {
                        $scope.items.push(item);
                    });
                    $scope.busy = false;
                    if (data.length === 0) {
                        $scope.noMoreData = true;
                    }
                });
            }

            var onNewsPush = function (data) {
                addNewsToScope(data);
            };

            function init() {
                loadNextItems();
                CometService.addPushListener(onNewsPush);
            }

            $scope.baseUrl = c.baseUrl;
            $scope.items = [];
            $scope.maxlength = c.newsFeedLength;
            $scope.busy = false;
            $scope.loadNextItems = loadNextItems;

            init();

            Console.groupEnd();
            $scope.$on("$destroy", function () {
                CometService.removeNewsPushListener(onNewsPush);
            });


        }];

    Console.groupEnd();
    return controller;
});
