define(['Console', 'Underscore'], function (Console) {
    "use strict";
    Console.group("Entering Newsstack controller module.");

    var controller = ['$scope', '$timeout', 'CometService', 'DataService', "ZMConstants",
        function ($scope, $timeout, CometService, ajax, c) {
            Console.group("Newsstack controller entered.");
            $scope.baseUrl = c.baseUrl;
            $scope.newsStackLength = c.newsStackLength;
            var waitingQueue = [];
            CometService.addPushListener(function (data) {
                addToNewsQueue(data);
            });
            var queueTimeout = false;
            var isRunning = false;
            var startUpdating = function (force) {
                if (waitingQueue.length > 0) {
                    if (isRunning && !force) {
                        return;
                    }
                    $scope.news.unshift(waitingQueue.pop());
                    queueTimeout = $timeout(function () {
                        startUpdating(true);
                    }, c.newsFeedTimeout);
                    isRunning = true;
                } else if (waitingQueue.length === 0) {
                    isRunning = false;
                }

            };
            var addToNewsQueue = function (data) {
                waitingQueue.push(data);
                startUpdating();

            };

            var initScope = function () {
                $scope.news = [];

            };
            initScope();
            ajax.getNewsFeed(0, c.newsStackLength).then( function (json) {
                if (json) {
                    _.each(json.reverse(), function (data) {
                        addToNewsQueue(data);
                    });
                }

            });

            Console.groupEnd();
        }];

    Console.groupEnd();
    return controller;
});
