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
                     //   var animateIn = "fadeInUp"//c.WIDGETS.ANIMATION_IN;
                     //   var animateOut = "fadeOutDown"//c.WIDGETS.ANIMATION_OUT;
                        var animateIn = c.WIDGETS.ANIMATION_SWITCH_IN;
                        var animateOut = c.WIDGETS.ANIMATION_SWITCH_OUT;
                        function goOnWithUpdate(){
                            $animate.removeClass(ele, 'animated ' + animateOut);
                            $animate.addClass(ele, 'animated ' +animateIn, function () {
                                $timeout(function(){

                                },1000)
                                activateTimeout();
                            });
                        }
                        function activateTimeout(){
                            $timeout(function () {
                                $animate.removeClass(ele, 'animated ' + animateIn);

                                $animate.addClass(ele, 'animated ' + animateOut, function () {
                                    $timeout(function(){
                                        $scope.switch && $scope.switch({
                                            okCallback:goOnWithUpdate
                                        });
                                         },1000)

                                });

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
