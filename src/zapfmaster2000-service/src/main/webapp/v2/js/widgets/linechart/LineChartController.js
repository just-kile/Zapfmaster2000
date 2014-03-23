define(['Console','d3'], function (Console,d3) {
    "use strict";
    Console.group("Entering Newsstack controller module.");

    var controller = ['$scope', '$timeout', 'CometService', 'DataService', "ZMConstants",
        function ($scope, $timeout, CometService, ajax, c) {
            Console.group("LineChart controller entered.");
            /*CometService.addPushListener(function (data) {
                if (c.DRAWING == data.type){
                    //   addToNewsQueue(data);
                }
            }); */
            var initScope = function(){
                $scope.title = "DemoCtrl";
                $scope.d3Data = [
                    {name: "Greg", score:98},
                    {name: "Ari", score:96},
                    {name:"Pete", score:102},
                    {name: "Loser", score: 48}
                ];
                $scope.d3OnClick = function(item){
                    alert(item.name);
                };
                ajax.getDatas(c.bestlistUrl, function (data) {

                });

            };

            $timeout(function(){
              $scope.d3Data[0].score = 30;
            },5000)
             initScope();


            Console.groupEnd();
        }];

    Console.groupEnd();
    return controller;
});
