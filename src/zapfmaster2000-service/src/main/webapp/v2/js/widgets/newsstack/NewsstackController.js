define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering Newsstack controller module.");

    var controller = ['$scope', '$timeout', 'CometService', 'DataService', "ZMConstants",
        function ($scope, $timeout, CometService, ajax, c) {
        Console.group("Newsstack controller entered.");
            CometService.addPushListener(function (data) {
                if (c.DRAWING == data.type){
                    addToNewsQueue(data);
                }
            });
            var addToNewsQueue = function(data){
                $scope.news.push(data);
                /*$timeout(function(){
                   $scope.shift();
                },5000);   */
            }
            var initScope = function(){
                $scope.news = [];

            }
                initScope();


            Console.groupEnd();
    }];
    //controller.$inject = [];

    Console.groupEnd();
    return controller;
});
