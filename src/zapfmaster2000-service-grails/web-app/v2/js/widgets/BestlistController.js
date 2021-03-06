define(['Console', 'Underscore'], function (Console, _) {
    "use strict";
    Console.group("Entering Bestlist controller module.");

    var controller = ['$scope', '$timeout', 'CometService', 'DataService', "ZMConstants",
        function ($scope, $timeout, CometService, ajax, c) {
            Console.group("Bestlist controller entered.");
            /*Bestlist*/
            $scope.bestlistMax = c.bestlistMax;
            $scope.baseUrl = c.baseUrl;
            var calcPercentAmount = function (data) {
                var sum = 0, sumAdd = 0, amount = 0, len = data.length;
                _.each(data, function (user, index) {
                    sum += user.amount;

                    user.amountPercent = 0;
                });

                _.each(data, function (user, index, userlist) {
                    var userRev = userlist[len - 1 - index];
                    sumAdd += userRev.amount;
                    amount = sumAdd * 100 / sum;
                    if (!isNaN(amount)) {
                        userRev.amountPercent = amount;
                    }
                });
                return data;
            };
            var initOrder = function (data) {
                _.each(data, function (user, index) {
                    user.order = index;
                });
                return data;
            };
            var initScope = function (isOnlyBestlist,showAll) {
                ajax.getBestlist().then(function (data) {
                    if (data.length > c.bestlistMax && !showAll) {
                        data.length = c.bestlistMax;
                    }
                    initOrder(data);
                    calcPercentAmount(data);
                    $scope.bestlist = data;

                    if (!isOnlyBestlist && $scope.bestlist && $scope.bestlist.length > 0) {
                        $timeout(function () {
                            $scope.arrow.visible = true;
                            updateStatsArrow();
                        }, 3000);
                    }
                });
            };
            var sortDatas = function () {
                var len = $scope.bestlist.length, tmp = 0;
                for (var i = 0; i < len; i++) {
                    for (var j = 0; j < len; j++) {
                        if ($scope.bestlist[i].amount > $scope.bestlist[j].amount) {
                            tmp = $scope.bestlist[i].order;
                            $scope.bestlist[j].order = j;
                            $scope.bestlist[i].order = tmp;
                        }
                    }
                }
            };
            var updateScope = function (data) {
                ajax.getBestlist().then(function (data) {
                    if (data.length > c.bestlistMax) {
                        data.length = c.bestlistMax;
                    }
                    if ($scope.bestlist.length > 0) {

                        calcPercentAmount(data);
                        var bestlist = $scope.bestlist;

                        _.each(data, function (user, index) {
                            var userId = user.id;
                            var isUserInList = false;
                            _.each(bestlist, function (oldUser, oldIndex) {
                                if (oldUser.id === userId) {
                                    $scope.bestlist[oldIndex].order = index;
                                    $scope.bestlist[oldIndex].amount = user.amount;
                                    $scope.bestlist[oldIndex].amountPercent = user.amountPercent;
                                    isUserInList = true;
                                }
                            });
                            if (!isUserInList) {
                                var lowestOrderUser = {order: 0};
                                var addedFlag = false;
                                var lowestIndex = 0;
                                _.each(bestlist, function (oldUser, oldIndex) {
                                    if (oldUser.order === index) {
                                        user.order = index;
                                        $scope.bestlist.push(user);
                                        addedFlag = true;
                                    }
                                    if (oldUser.order > lowestOrderUser.order) {
                                        lowestOrderUser = oldUser;
                                        lowestIndex = oldIndex;
                                    }
                                });
                                if ($scope.bestlist.length > c.bestlistMax) {
                                    $scope.bestlist.splice(lowestIndex, 1);
                                } else if (!addedFlag) {
                                    user.order = $scope.bestlist.length;
                                    $scope.bestlist.push(user);
                                }

                            }
                        });
                    } else {
                        initScope();
                        changeInterval =  $timeout(function () {
                            $scope.arrow.visible = true;
                            updateStatsArrow();
                        }, 3000);
                    }

                });
            };
            var onNewsPush = function (data) {
                if (c.DRAWING === data.type) {
                    updateScope();
                }

            };
            CometService.addPushListener(onNewsPush);


            var counter = 0, changeInterval;

            /*Stats*/
            var initArrow = function () {

                $scope.arrow = {
                    pointer: -9999,
                    visible: false
                };


            };
            var getUserIdFromPointer = function (order) {
                if ($scope.bestlist) {
                    var list = $scope.bestlist;
                    for (var i = 0; i < list.length; i++) {
                        if (list[i] && (list[i].order === order)) {
                            return list[i].id;
                        }
                    }
                }
                return -1;
            };
            var activeStats = 0;
            var updateStatsArrow = function () {
                var len;
                if (!$scope.bestlist) {
                    len = 1;
                } else {
                    len = $scope.bestlist.length;
                }

                $scope.arrow.pointer = counter;

                if (changeInterval) {
                    $timeout.cancel(changeInterval);
                }

                ajax.getUserStats(getUserIdFromPointer(counter)).then(function (data) {
                    counter = ++counter % len;
                    activeStats = ++activeStats % 2;
                    $scope.activeStats = activeStats;
                    if (activeStats % 2 === 1) {
                        $scope.statsodd = data;
                    } else {
                        $scope.statseven = data;
                    }


                    changeInterval = $timeout(function () {
                        updateStatsArrow();
                    }, 5000);
                });


            };

            var resizeMethod = function () {
                if (document.body.clientWidth < 768) {
                    Console.log('mobile');

                    if (changeInterval) {
                        $timeout.cancel(changeInterval);
                        changeInterval = null;
                    }

                }
            };
            var initResizing = function () {
                window.addEventListener("resize", resizeMethod, true);
            };
            $scope.init = function (isOnlyBestlist,showAll) {
                if (!isOnlyBestlist) {
                    initResizing();

                    initArrow();
                }
                initScope(isOnlyBestlist,showAll);
            };

            $scope.$on("$destroy", function () {
                $timeout.cancel(changeInterval);
                window.removeEventListener("resize", resizeMethod, true);
                CometService.removeNewsPushListener(onNewsPush);
            });
            Console.groupEnd();
        }];

    Console.groupEnd();
    return controller;
});
