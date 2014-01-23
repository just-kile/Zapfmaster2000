define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering HomeController module.");

    var controller = ['$scope', '$timeout', 'ZMConstants', function ($scope, $timeout, c) {
        Console.group("HomeController entered.");
        $scope.widgetBaseUrl = c.widgetBaseUrl;
        var widgetTimeouts = {};
        var firstWidgets = 0, widgetChangeEnabled=true;
        var rows = {
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
            center: [
                [
                    {
                        name: "bestlist",
                        className: "col-md-8",
                        interval: 5000
                    },
                    {
                        name: "challenges",
                        className: "col-md-4",
                        interval: 7000
                    }
                ],
                [
                    {
                        name: "challenges",
                        className: "col-md-8",
                        interval: 5000
                    },
                    {
                        name: "bestlist",
                        className: "col-md-4",
                        interval: 12000
                    }
                ]
            ]
        };
        var init = function (rows) {
            $scope.rows = {};
            $scope.rows.topLeft = rows.topLeft;
            $scope.rows.topRight = rows.topRight;
            $scope.rows.center = _.clone(rows.center[firstWidgets]);
            Console.debug("Initializing:", rows);
        };

        var updateWidget = function (widget, index,rows, actualRow) {
            actualRow = (actualRow+1) % rows.center.length;
            var newWidget = rows.center[actualRow] && rows.center[actualRow][index];
            $scope.rows.center.splice(index,1,newWidget);
            (function(newWidget, index,rows, actualRow){
                $timeout(function () {
                    updateWidget(newWidget, index,rows ,actualRow);
                }, newWidget.interval);

            })(newWidget,index,rows,actualRow);

        };
        var startUpdater = function (rows) {
            var startCenterWidgets = rows.center[0];
            _.each(startCenterWidgets, function (widget, index) {
                $timeout(function () {
                    updateWidget(widget, index, rows,0);
                }, widget.interval);
            });
        }

       if(widgetChangeEnabled)startUpdater(rows);


        //controller.$inject = [];
        init(rows);

    }];
    Console.groupEnd();
    return controller;
});
