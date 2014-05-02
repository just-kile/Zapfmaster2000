define(['Console', 'moment', 'Underscore'], function (Console, moment, _) {
    "use strict";
    Console.group("Entering Newsstack controller module.");

    var controller = ['$scope', '$timeout', 'CometService', 'DataService', "ZMConstants", 'DateService',
        function ($scope, $timeout, CometService, ajax, c, DateService) {
            Console.group("LineChart controller entered.");
            var transformData = function (curveValues) {
                var result = [];
                 result.push([0,0]);
                _.each(curveValues, function (data, index) {
                    result.push([data.amount, data.userCount]);
                });
                var lastAmount = result[result.length -1];
                result.push([lastAmount[0]+1,0])
                return result;
            };
            $scope.width = 750;
            $scope.height = 250;

            $scope.xAxisTickFormatFunction = function () {
                return function (val) {
                    return val.toFixed(2);
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
                ajax.getDatas(c.distributionUrl, function (data) {
                   // console.log(data);
                   // var distData = [[-1,0],[0,0.0001],[1,0.001],[4,0.42],[7,0.44],[10,0.1],[12,0],[13,0]];
                    var distData = transformData(data.normalCurveValues)
                    $scope.chartData = [
                        {
                            key: "Progress",
                            values: distData
                        }
                    ]
                    $scope.expectation = data.expectation;
                    $scope.variance = data.variance;
                    $scope.degression = data.degression;
                   // $scope.skew = -0.3;
                    //$scope.kurtosis = 0;
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
