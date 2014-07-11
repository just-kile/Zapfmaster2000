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
            /*Testing, delete if not necessary*/
            //addToNewsQueue(DUMMY_DATAS);
            /*  var stopFlag = false;
             var dummydata = function(){

             var DUMMY_DATAS = {"type":"DRAWING","image":"rest/image/user/1","date":"20140323-184633","userId":1,"userName":"Ben","amount":Math.random()*10,"kegId":1,"brand":"Carlsberg","location":"Kölle"};

             addToNewsQueue(DUMMY_DATAS);

             if(!stopFlag)$timeout(dummydata,4000);
             //stopFlag = true;
             }

             dummydata();
             stopFlag = false;   */
            ajax.getNewsFeed(0, c.newsStackLength).then( function (json) {
                if (json) {
                    _.each(json.reverse(), function (data) {
                        addToNewsQueue(data);
                    });
                }

            });

            Console.groupEnd();
        }];
    //controller.$inject = [];

    Console.groupEnd();
    return controller;
});
