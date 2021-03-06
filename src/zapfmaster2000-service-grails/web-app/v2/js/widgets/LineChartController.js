define(['Console', 'moment', 'Underscore'], function (Console, moment, _) {
    "use strict";
    Console.group("Entering Linechart controller module.");

    var controller = ['$scope', '$timeout', 'CometService', 'DataService', "ZMConstants", 'DateService',
        function ($scope, $timeout, CometService, ajax, c, DateService) {
            Console.group("LineChart controller entered.");
            var chartData = {};
            var transformData = function (data) {
                var result = [];
                var startDate = DateService.parseClientDate(data.from);
                var interval = data.interval;
                _.each(data.amount, function (amount, index) {
                    result.push([startDate.add("minutes", interval).format("X"), amount]);
                });
                chartData = result;
                return result;
            };
            $scope.width = 750;
            $scope.height = 250;

            $scope.xAxisTickFormatFunction = function () {
                return function (val) {
                    return moment.unix(val).format(c.CLIENT_TIME_FORMAT);
                };
            };
            $scope.yAxisTickFormatFunction = function () {
                return function (val) {
                    return val;
                };
            };

            $scope.colorFunction = function () {
                return function (d, i) {
                    return '#F5E400';
                };
            };
            /*Pie chart helper functions*/
            $scope.xFunction = function () {
                return function (val) {
                    return val.key;
                };
            };

            $scope.yFunction = function () {
                return function (val) {
                    return val.y;
                };
            };
            $scope.colorFunctionPie = function () {
                var colorArray = ["#ED71A8", "#FFF143"];
                return function (d, i) {
                    return colorArray[i];
                };
            };
            var transformDataPie = function (bestlist) {

                var sum = 0;
                _.each(bestlist, function (user, index) {
                    if (index > 0) {
                        sum += user.amount;
                    }
                });
                if (bestlist.length > 0) {
                    return [
                        {
                            key: bestlist[0].name,
                            y: bestlist[0].amount
                        },
                        {
                            key: "The World",
                            y: sum
                        }
                    ];
                } else {
                    return null;
                }

            };
            var initScope = function () {
                var from = moment().subtract('minutes', c.PROGRESS_FROM_MINUTES).format(c.SERVER_TIME_FORMAT);
                var interval = c.PROGRESS_INTERVAL;
                ajax.getProgress(from, interval).then(function (data) {
                    Console.log(data);
                    $scope.chartData = [
                        {
                            key: "Progress",
                            values: data.amount.length > 1 ? transformData(data) : []
                        }
                    ];
                });
                ajax.getBestlist().then(function (bestlist) {
                    $scope.pieChartData = transformDataPie(bestlist);
                });

            };
            initScope();


            Console.groupEnd();
        }];

    Console.groupEnd();
    return controller;
});
