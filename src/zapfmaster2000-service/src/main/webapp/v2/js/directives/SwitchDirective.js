define(['Console', 'd3'], function (Console, d3) {
        "use strict";
        Console.group("Entering Switch directive.");

        var directive = ['$animate','$timeout','ZMConstants', function ($animate,$timeout,c) {
                return {
                    restrict: 'A',
                    scope: {
                        switch:"&"

                    },
                    //  templateUrl: 'js/directives/BarChart/template.html',
                    link: function ($scope, ele, attrs) {
                       // console.log($scope);
                        console.log("Switch directive called!");
                        $animate.addClass(ele, "animated");
                        var animateIn = "fadeInUp"//c.WIDGETS.ANIMATION_IN;
                        var animateOut = "fadeOutDown"//c.WIDGETS.ANIMATION_OUT;
                    //    var animateIn = c.WIDGETS.ANIMATION_SWITCH_IN;
                    //    var animateOut = c.WIDGETS.ANIMATION_SWITCH_OUT;
                        function goOnWithUpdate(){

                                $animate.addClass(ele, animateIn, function () {

                                    $timeout(function(){

                                    },1000)
                                    activateTimeout();
                                });
                            $animate.removeClass(ele, animateOut);

                        }
                        function activateTimeout(){
                            $timeout(function () {

                                $animate.addClass(ele,  animateOut, function () {

                                    $timeout(function(){
                                        $scope.switch && $scope.switch({
                                            okCallback:goOnWithUpdate
                                        });
                                         },1000)

                                });
                                $animate.removeClass(ele, animateIn);


                            }, c.userProfileChangeTimeout);
                        }
                        activateTimeout();
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
