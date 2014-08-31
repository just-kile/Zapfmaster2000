define(['Console', 'd3'], function (Console, d3) {
        "use strict";
        Console.group("Entering Switch directive.");

        var directive = ['$animate', '$timeout', 'ZMConstants', function ($animate, $timeout, c) {
                return {
                    restrict: 'A',
                    scope: {
                        switch: "&"

                    },
                    link: function ($scope, ele, attrs) {
                        Console.log("Switch directive called!");
                        $animate.addClass(ele, "animated");

                        var animateIn = c.WIDGETS.ANIMATION_SWITCH_IN;
                        var animateOut = c.WIDGETS.ANIMATION_SWITCH_OUT;

                        function goOnWithUpdate() {

                            $animate.addClass(ele, animateIn, function () {
                                activateTimeout();
                            });
                            $animate.removeClass(ele, animateOut);

                        }

                        function activateTimeout() {
                            $timeout(function () {
                                $animate.addClass(ele, animateOut, function () {
                                    $timeout(function () {
                                        if($scope.switch){
                                            $scope.switch({
                                                ok: goOnWithUpdate
                                            });
                                        }
                                    }, 1000);

                                });
                                $animate.removeClass(ele, animateIn);


                            }, c.userProfileChangeTimeout);
                        }

                        activateTimeout();
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
