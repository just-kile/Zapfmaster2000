define(['Console','text!../../rows.json'], function (Console,rowsResponse) {
    "use strict";
    Console.group("Entering HomeController module.");
    var rows = rowsResponse;
    if(typeof rows=="string"){
        rows = $.parseJSON(rowsResponse);
    }
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

        $scope.$on("$destroy", function () {
          stopUpdater();
          stopWidgetSwitch();
        });
        //controller.$inject = [];


    }];
    Console.groupEnd();
    return controller;
});
