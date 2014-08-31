define(['Console', 'd3'], function (Console, d3) {
        "use strict";
        Console.group("Entering BarChart directive.");

        var directive = ['$animate', '$timeout', 'ZMConstants', function ($animate, $timeout, c) {
                return {
                    restrict: 'A',
                    scope: {
                        widget: '='

                    },
                    link: function ($scope, ele, attrs) {
                        $animate.addClass(ele, 'animated ' + c.WIDGETS.ANIMATION_IN, function () {
                            $timeout(function () {

                                $animate.removeClass(ele, 'animated ' + c.WIDGETS.ANIMATION_IN, function () {

                                });
                            }, 1000);
                        });
                    }
                };

            }
            ]
            ;

        Console.groupEnd();
        return directive;
    }
)
;
