define(['Console', 'jQuery'], function (Console, $) {
        "use strict";
        Console.group("Entering BarChart directive.");

        var directive = ['$animate', '$timeout', function ($animate, $timeout) {
                return {
                    restrict: 'A',
                    scope: {
                        movingunderline: "="
                    },
                    //  templateUrl: 'js/directives/BarChart/template.html',
                    link: function ($scope, ele, attrs) {

                        var movingDiv = $("<div>").addClass("movingdiv").css({
                            left: 0

                        });
                        movingDiv.appendTo(ele);
                        $scope.$watch("movingunderline", function () {
                            $timeout(function () {
                                var activeLi = $(ele).find(".active");
                                movingDiv.css({
                                    left: activeLi.position().left,
                                    width: activeLi.outerWidth()
                                });
                            }, 500);

                            $(ele)
                                .find("li")
                                .on("mouseover", function (e) {
                                    movingDiv.css({
                                        left: $(e.currentTarget).position().left,
                                        width: $(e.currentTarget).outerWidth()
                                    });
                                    //$(e.currentTarget).addClass("active").siblings().removeClass("active");
                                })
                                .on("mouseout", function (e) {
                                    var currentTarget = $(e.currentTarget);
                                    if (!currentTarget.hasClass("active")) {
                                        movingDiv.css({
                                            left: currentTarget.siblings(".active").position().left,
                                            width: currentTarget.siblings(".active").outerWidth()
                                        });
                                    }
                                });

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
