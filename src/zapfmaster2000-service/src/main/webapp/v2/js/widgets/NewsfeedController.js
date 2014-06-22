define(['Console','Underscore'], function (Console,_) {
    "use strict";
    Console.group("Entering Newsstack controller module.");

    var controller = ['$scope', '$timeout', 'CometService', 'DataService', "ZMConstants",
    //controller.$inject = [];
        function ($scope, $timeout, CometService, ajax, c) {
            Console.group("Newsfeed controller entered.");
            $scope.baseUrl = c.baseUrl;
            $scope.items = [];
            $scope.maxlength = c.newsFeedLength;

            function addNewsToScope(data) {
                $scope.items.unshift(data);
            }
            $scope.loadNextItems =loadNextItems;
            function loadNextItems(){
                ajax.getDatas(c.newsFeedUrl, function (data) {
                    _.each(data, function (item) {
                        $scope.items.push(item);
                    });
                }, {
                    start: $scope.items.length,
                    length: c.newsFeedLength
                });
            }
            function init(){
                loadNextItems();
                CometService.addPushListener(function (data) {
                    addNewsToScope(data);

                });
            }
            init();

            Console.groupEnd();


        }];

    Console.groupEnd();
    return controller;
});
