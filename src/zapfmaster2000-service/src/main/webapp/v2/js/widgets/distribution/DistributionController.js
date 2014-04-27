define(['Console', 'moment', 'Underscore'], function (Console, moment, _) {
    "use strict";
    Console.group("Entering Newsstack controller module.");

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
                    return val;
                };
            }
            $scope.yAxisTickFormatFunction = function () {
                return function (val) {
                    return val;
                };
            }

            $scope.colorFunction = function () {
                return function (d, i) {
                    return '#F5E400 '
                };
            }
            var initScope = function () {
                ajax.getDatas(c.progressUrl, function (data) {
                   // console.log(data);
                    var distData = [[-1,0],[0,0.0001],[1,0.001],[4,0.42],[7,0.44],[10,0.1],[12,0],[13,0]];

                    $scope.chartData = [
                        {
                            key: "Progress",
                            values: distData
                        }
                    ]
                    $scope.expectation = 4.2;
                    $scope.variance = 0.5;
                    $scope.degression = 0.3;
                    $scope.skew = -0.3;
                    $scope.kurtosis = 0;
                }, {
                    from: moment().subtract('minutes', c.PROGRESS_FROM_MINUTES).format(c.SERVER_TIME_FORMAT),
                    interval: c.PROGRESS_INTERVAL
                });


            };
            initScope();


            Console.groupEnd();
        }];

    Console.groupEnd();
    return controller;
});
