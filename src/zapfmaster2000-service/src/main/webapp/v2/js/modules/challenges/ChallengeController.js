define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering Callenge controller module.");

    var controller = ['$scope',"CometService","DataService", "ZMConstants",
        function ($scope,CometService,ajax,c) {
        Console.group("Challenge controller entered.");
        var updateScope = function(){
            ajax.getDatas(c.challengeUrl,function(data){
                $scope.baseurl = c.baseUrl;

                /*var sum = team1Amount+team2Amount;
                var percent1 = team1Amount/sum;
                var percent2 = team2Amount/sum;
                team1Div.css("width",(percent2*100)+"%");
                team2Div.css("width",(percent1*100)+"%");  */

                $scope.challenges = data;

            });
        }
        updateScope();
        var pushListener = function(data){
              updateScope();
        };
        CometService.addPushListener(pushListener);

        Console.groupEnd();
    }];
    //controller.$inject = [];

    Console.groupEnd();
    return controller;
});
