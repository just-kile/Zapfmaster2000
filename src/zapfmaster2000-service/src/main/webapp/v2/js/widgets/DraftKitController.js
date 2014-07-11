define(['Console', 'Underscore'], function (Console, _) {

    "use strict";
    Console.group("Entering Draftkit controller module.");
    /* boxId: 1
     imagePath: "rest/image/user/2"
     type: "LOGIN"
     userId: 2
     userName: "Felix" */
    var controller = ['$scope', "CometService", "DataService", "ZMConstants",
        function ($scope, CometService, ajax, c) {
            Console.group("Draftkit controller entered.");
            $scope.baseUrl = c.baseUrl;
            var firstInit = true;
            var updateAmount = function (data) {
                Console.log("Update Amount with ", data);
                var scopeKeg = _.find($scope.kegs, function (keg) {
                    return data.boxId === keg.boxId;
                });
                if (scopeKeg) {
                    if (data.type === c.LOGIN || data.type === c.DRAW) {
                        if (!scopeKeg.user) {
                            scopeKeg.user = {};
                        }
                        scopeKeg.user.loggedin = true;
                        scopeKeg.user.image = c.baseUrl + data.imagePath;
                        scopeKeg.user.userName = data.userName;
                        scopeKeg.user.amount = data.amount || 0;
                    } else {
                        if (!scopeKeg.user) {
                            scopeKeg.user = {};
                        }
                        scopeKeg.user.loggedin = false;
                    }

                }

            };
            var initScope = function (datas) {

                if (!datas) {
                    ajax.getKegStats().then(function (kegs) {
                        Console.log("Received Keg Data ", kegs);
                        if (firstInit) {
                            _.each(kegs, function (keg) {
                                CometService.addInstantUpdateListener(keg.boxId, updateAmount);
                            });
                            firstInit = false;
                        }

                        $scope.kegs = kegs;
                    });
                } else {
                    Console.log("Update Keg Datas with ", datas);
                    $scope.kegs = datas;
                }

            };
            var updateScope = function () {
                ajax.getKegStats().then(function (kegs) {
                    Console.log("Received Keg Data ", kegs);
                    if ($scope.kegs && $scope.kegs.length !== kegs.length) {
                        initScope(kegs);
                    } else {
                        _.find(kegs, function (keg) {
                            var scopeKeg = _.find($scope.kegs, function (scopeKeg) {
                                return keg.kegId === scopeKeg.kegId;
                            });
                            if (scopeKeg) {
                                scopeKeg.currentAmount = keg.currentAmount;
                            } else {
                                initScope(kegs);//some kegdata changed, render completely
                                return true;
                            }
                        });
                    }


                });
            };


            CometService.addPushListener(function (data) {
                if (c.DRAWING === data.type) {
                    updateScope();
                } else if (c.NEWKEG === data.type) {
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
