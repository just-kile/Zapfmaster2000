define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering Newsstack controller module.");

    var controller = ['$scope', '$timeout', 'CometService', 'DataService', "ZMConstants",
        function ($scope, $timeout, CometService, ajax, c) {
        Console.group("Newsstack controller entered.");
            $scope.baseUrl = c.baseUrl;

            CometService.addPushListener(function (data) {
              addToNewsQueue(data);
            });

            var addToNewsQueue = function(data){
                $scope.news.push(data);
                $timeout(function(){
                   $scope.news.shift();
                },5000);
            }
            var initScope = function(){
                $scope.news = [];

            }
            initScope();
            /*Test*/
            var DUMMY_DATAS = {"type":"DRAWING","image":"rest/image/user/1","date":"20140323-184633","userId":1,"userName":"Ben","amount":0.12798644841493814,"kegId":1,"brand":"Carlsberg","location":"Kölle"};
            addToNewsQueue(DUMMY_DATAS);

            $timeout(function(){


                DUMMY_DATAS = {"type":"DRAWING","image":"rest/image/user/2","date":"20140323-184633","userId":2,"userName":"Jenny","amount":0.12798644841493814,"kegId":1,"brand":"Carlsberg","location":"Kölle"};
                addToNewsQueue(DUMMY_DATAS);
            },1000);


            $timeout(function(){

                DUMMY_DATAS = {"type":"DRAWING","image":"rest/image/user/2","date":"20140323-184633","userId":2,"userName":"Peter","amount":0.12798644841493814,"kegId":1,"brand":"Carlsberg","location":"Kölle"};
                addToNewsQueue(DUMMY_DATAS);
            },2000);

            Console.groupEnd();
    }];
    //controller.$inject = [];

    Console.groupEnd();
    return controller;
});
