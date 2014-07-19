define(['Console', 'jQuery'], function (Console, $) {
        "use strict";
        Console.group("Entering Number Interpolation directive.");

        var directive = ['$animate', '$timeout', "$filter", function ($animate, $timeout, $filter) {
                return {
                    restrict: 'A',
                    scope: {
                        value: "="
                    },
                    link: function ($scope, ele, attrs) {
                        var oldVal = 0;
                        var filter = attrs.filter || "amount";
                        function animate(finalVal, prevVal, steps) {
                            if (steps > 0) {
                                var val = prevVal + (finalVal - prevVal)/steps;
                                var filteredVal = $filter(filter)(val);
                                ele.text(filteredVal);
                                $timeout(function () {
                                    animate(finalVal, val, steps - 1);
                                }, 1);
                            }

                        }

                        attrs.$observe("zmNumberInterpolation", function (newVal) {
                            animate(Number.parseFloat(newVal), oldVal, 50);
                            oldVal = Number.parseFloat(newVal);

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
