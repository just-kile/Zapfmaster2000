define(['Console', 'Underscore'], function (Console, _) {

    "use strict";
    Console.group("Entering Draftkit controller module.");

    var controller = ['$scope', "CometService", "DataService", "ZMConstants",
        function ($scope, CometService, ajax, c) {
            Console.group("Draftkit controller entered.");
            $scope.baseUrl = c.baseUrl;
            var initScope = function (datas) {

                if (!datas) {
                    ajax.getDatas(c.kegStatsUrl, function (kegs) {
                        Console.log("Received Keg Data ",kegs)
                        $scope.kegs = kegs;
                    });
                } else {
                    Console.log("Update Keg Datas with ",datas);
                    $scope.kegs = datas;
                }
            }
            var updateScope = function () {
                ajax.getDatas(c.kegStatsUrl, function (kegs) {
                    Console.log("Received Keg Data ",kegs);
                    if ($scope.kegs && $scope.kegs.length != kegs.length) {
                        initScope();
                    } else {
                        _.find(kegs, function (keg) {
                            var scopeKeg = _.find($scope.kegs, function (scopeKeg) {
                                return keg.kegId == scopeKeg.kegId;
                            });
                            if (scopeKeg) {
                                scopeKeg.currentAmount = keg.currentAmount;
                            } else {
                                initScope(kegs);//some kegdata changed, render completely
                                return true;
                            }
                        })
                    }


                });
            }


            CometService.addPushListener(function (data) {
                if (c.DRAWING == data.type){
                    updateScope();
                }else if(c.NEWKEG== data.type){
                    initScope();
                }
            });

            initScope();
            Console.groupEnd();
        }];
    //controller.$inject = [];

    Console.groupEnd();
    return controller;
});
