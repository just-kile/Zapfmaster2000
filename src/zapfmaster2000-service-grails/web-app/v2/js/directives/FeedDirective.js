define(['Console', 'jQuery'], function (Console, $) {
        "use strict";
        Console.group("Entering Feed directive.");

        var directive = ['ZMConstants', '$window', '$timeout', '$animate', function (c, $window, $timeout, $animate) {
                return {
                    restrict: 'E',
                    scope: {
                        items: '=',
                        noAnimation: '='
                    },
                    templateUrl: 'views/directives/FeedDirective.html',
                    link: function ($scope, element, attrs) {
                        Console.log("Feed Directive called");

                        $scope.baseUrl = c.baseUrl;
                        $scope.$watch("items", function (newVal, oldVal) {
                            if (oldVal && oldVal.length === 0 && newVal && newVal.length > 0) {
                                $animate.addClass(element.find("li"), 'animated fadeIn', function () {
                                    $timeout(function () {
                                        $animate.removeClass(element.find("li"), 'animated fadeIn');
                                    }, 1000);
                                });
                            } else if (newVal && newVal.length > 0) {
                                var el = element.find("li");
                                var first = $(el.first());
                                var last = $(el.last());

                                if (attrs.noAnimation!="true") {
                                    $animate.addClass(first, 'animate-height', function () {
                                        $timeout(function () {
                                            $animate.removeClass(first, 'animate-height');
                                        }, 1000);
                                    });
                                    $animate.addClass(last, 'animate-height-reverse', function () {
                                        $timeout(function () {
                                            $animate.removeClass(last, 'animate-height-reverse');
                                        }, 1000);
                                    });
                                }
                            }

                        }, true);


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
