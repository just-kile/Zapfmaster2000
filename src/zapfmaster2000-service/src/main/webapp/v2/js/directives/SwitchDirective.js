define(['Console', 'd3'], function (Console, d3) {
        "use strict";
        Console.group("Entering Switch directive.");

        var directive = ['$animate', '$timeout', 'ZMConstants', function ($animate, $timeout, c) {
                return {
                    restrict: 'A',
                    scope: {
                        switch: "&"

                    },
                    //  templateUrl: 'js/directives/BarChart/template.html',
                    link: function ($scope, ele, attrs) {
                        // console.log($scope);
                        Console.log("Switch directive called!");
                        $animate.addClass(ele, "animated");
                        //  var animateIn = "fadeInUpBig"//c.WIDGETS.ANIMATION_IN;
                        // var animateOut = "fadeOutDownBig"//c.WIDGETS.ANIMATION_OUT;
                        // var animateIn = "rotateInDownRight"//c.WIDGETS.ANIMATION_IN;
                        //var animateOut = "rotateOutDownRight"//c.WIDGETS.ANIMATION_OUT;

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
                                        $scope.switch && $scope.switch({
                                            ok: goOnWithUpdate
                                        });
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
