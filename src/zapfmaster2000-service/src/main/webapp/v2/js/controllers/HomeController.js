define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering HomeController module.");

    var controller = ['$scope','$routeParams', '$timeout', 'ZMConstants','$animate', function ($scope,$routeParams, $timeout, c,$animate) {
        Console.group("HomeController entered.");
        $scope.widgetBaseUrl = c.widgetBaseUrl;
        var widgetTimeouts = {};
        var firstWidgets = $routeParams.widgetId || 0,
            widgetChangeEnabled=typeof $routeParams.widgetId =="undefined";
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
                        interval: 50000
                    },
                    {
                        name: "challenges",
                        className: "col-md-4",
                        interval: 5000
                    }
                ],[
                    {
                        name: "bestlist",
                        className: "col-md-8",
                        interval: 50000
                    },
                    {
                        name: "zapfmastersplash",
                        className: "col-md-4",
                        interval: 10000
                    }
                ],[
                    {
                        name: "bestlist",
                        className: "col-md-8",
                        interval: 50000
                    },
                    {
                        name: "newsfeed",
                        className: "col-md-4",
                        interval: 50000
                    }
                ],[
                    {
                        name: "bestlist",
                        className: "col-md-8",
                        interval: 50000
                    },
                    {
                        name: "achievementfeed",
                        className: "col-md-4",
                        interval: 50000
                    }
                ],
                [
                    {
                        name: "bestlist",
                        className: "col-md-6",
                        interval: 50000
                    },
                    {
                        name: "amountchart",
                        className: "col-md-6",
                        interval: 50000
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
        var changeInterval;
        var startUpdater = function (rows) {
            var startCenterWidgets = rows.center[0];
            _.each(startCenterWidgets, function (widget, index) {
                changeInterval =   $timeout(function () {
                    updateWidget(widget, index, rows,0);
                }, widget.interval);
            });
        }
       var stopUpdater = function(){
           $timeout.cancel(changeInterval);
       }
       if(widgetChangeEnabled)startUpdater(rows);
       init(rows);




        //controller.$inject = [];


    }];
    Console.groupEnd();
    return controller;
});
