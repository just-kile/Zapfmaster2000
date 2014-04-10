define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering HomeController module.");

    var controller = ['$scope', '$routeParams', '$timeout', 'ZMConstants', '$animate', function ($scope, $routeParams, $timeout, c, $animate) {
        Console.group("HomeController entered.");
        $scope.widgetBaseUrl = c.widgetBaseUrl;
        var widgetTimeouts = {};
        var firstWidget = $routeParams.widgetId || 0,
            widgetChangeEnabled = typeof $routeParams.widgetId == "undefined";

        var rowsDivider = 2;//rows.centerHalf.length;
        var activeSubWidget = firstWidget >= rowsDivider ? "centerHalf" : "centerThird";
        firstWidget = firstWidget % rowsDivider;
        function getActiveSubWidget() {
            return activeSubWidget;
        }

        var rows = {
            switchTime: 150000,
            switchingModule:{
                name: "zapfmastersplash",
                className: "col-md-12",
                interval: 10000
            },
            topLeft: [
                {
                    name: "newsstack",
                    className: "col-md-6"
                }
            ],
            topRight: [
                {
                    name: "draftkits",
                    className: "col-md-6"
                }
            ],
            centerThird: [
                [
                    {
                        name: "bestlist",
                        className: "col-md-8",
                        interval: 60000
                    },
                    {
                        name: "challenges",
                        className: "col-md-4",
                        interval: 60000
                    }
                ],
                [
                    {
                        name: "newsfeed",
                        className: "col-md-8",
                        interval: 60000
                    },
                    {
                        name: "achievementfeed",
                        className: "col-md-4",
                        interval: 60000
                    }

                ]
            ],
            centerHalf: [
                [
                    {
                        name: "linechart",
                        className: "col-md-6",
                        interval: 60000
                    },
                    {
                        name: "amountchart",
                        className: "col-md-6",
                        interval: 60000
                    }
                ],
                [
                    {
                        name: "achievementfeed",
                        className: "col-md-6",
                        interval: 60000
                    },
                    {
                        name: "newsfeed",
                        className: "col-md-6",
                        interval: 60000
                    }
                ]
            ]
        };
        var init = function (rows) {
            $scope.rows = {};
            $scope.rows.topLeft = rows.topLeft;
            $scope.rows.topRight = rows.topRight;

            $scope.rows.center = _.clone(rows[getActiveSubWidget()][firstWidget]);
            Console.debug("Initializing:", rows);
        };
        var changeInterval = {};

        var updateWidget = function (widget, index, rows, actualRow) {
            actualRow = (actualRow + 1) % rows[getActiveSubWidget()].length;
            var newWidget = rows[getActiveSubWidget()][actualRow] && rows[getActiveSubWidget()][actualRow][index];
            $scope.rows.center.splice(index, 1, newWidget);
            (function (newWidget, index, rows, actualRow) {
                changeInterval[getActiveSubWidget() + index] = $timeout(function () {
                    updateWidget(newWidget, index, rows, actualRow);
                }, newWidget.interval);

            })(newWidget, index, rows, actualRow);

        };

        var startUpdater = function (rows,instant) {
            var startCenterWidgets = rows[getActiveSubWidget()][0];
            _.each(startCenterWidgets, function (widget, index) {
                if(instant){
                    updateWidget(widget, index, rows, -1);
                }else{
                    changeInterval[getActiveSubWidget() + index] = $timeout(function () {
                        updateWidget(widget, index, rows, 0);
                    }, widget.interval);
                }

            });
        }

        var stopUpdater = function () {
            _.each(changeInterval, function (interval) {
                $timeout.cancel(interval);
            });
            changeInterval = {};

        }
        var widgetSwitchTimeout;
        var stopWidgetSwitch = function () {
            $timeout.cancel(widgetSwitchTimeout);
        }
        var startWidgetSwitch = function (rows) {
            widgetSwitchTimeout = $timeout(function () {
                stopUpdater();
                activeSubWidget = activeSubWidget == "centerHalf" ? "centerThird" : "centerHalf";
                $scope.rows.center.splice(0, 2,
                    rows.switchingModule);

                $timeout(function(){
                    startUpdater(rows,true);
                    startWidgetSwitch(rows);
                },rows.switchingModule.interval)

            }, rows.switchTime);


        }
        if (widgetChangeEnabled) {
            startUpdater(rows);
            startWidgetSwitch(rows);
        }
        init(rows);


        //controller.$inject = [];


    }];
    Console.groupEnd();
    return controller;
});
