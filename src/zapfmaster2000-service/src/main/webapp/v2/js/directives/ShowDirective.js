define(['Console', 'd3'], function (Console, d3) {
        "use strict";
        Console.group("Entering BarChart directive.");

        var directive = ['$animate','$timeout','ZMConstants', function ($animate,$timeout,c) {
                return {
                    restrict: 'A',
                    scope: {
                        widget: '='

                    },
                    //  templateUrl: 'js/directives/BarChart/template.html',
                    link: function ($scope, ele, attrs) {
                        console.log($scope);
                        $animate.addClass(ele, 'animated ' + c.WIDGETS.ANIMATION_IN, function () {
                            $timeout(function () {

                                $animate.removeClass(ele, 'animated ' + c.WIDGETS.ANIMATION_IN, function () {

                                })
                            },1000);
                        });
                        $scope.$on("$destroy", function () {
                            $animate.addClass(ele, 'animated ' + c.WIDGETS.ANIMATION_OUT, function () {
                                $timeout(function () {

                                    $animate.removeClass(ele, 'animated ' + c.WIDGETS.ANIMATION_OUT, function () {

                                    })
                                }, 1000);
                            });

                        });
                     /*   $scope.$watch("rows.center", function (newArray, oldArray) {
                            var els = $element.find(".zm-widget");

                            $animate.addClass($(els[0]), 'animated ' + c.FIGURE_CHART.ANIMATION_OUT, function () {
                                $timeout(function () {
                                    var currEl = $(els[offset - index]);
                                    $animate.removeClass(currEl, 'animated ' + c.FIGURE_CHART.ANIMATION_OUT, function () {
                                        currEl.remove();
                                    })
                                }, 2000);
                            });

                        }, true);
                                      */
                    }
                }

            }
            ]
            ;

        Console.groupEnd();
        return directive;
    }
)
;
