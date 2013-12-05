define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering Draftkit controller module.");

    var controller = ['$scope',"CometService","DataService","ZMConstants",
        function ($scope,CometService,ajax,c) {
        Console.group("Draftkit controller entered.");

        ajax.getDatas(c.kegStatsUrl,function(kegs){
            $scope.kegs =  kegs;
        });

        CometService.addPushListener(function(data){

        });


        Console.groupEnd();
    }];
    //controller.$inject = [];

    Console.groupEnd();
    return controller;
});
