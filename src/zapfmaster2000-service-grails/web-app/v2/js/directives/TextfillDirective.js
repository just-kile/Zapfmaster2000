define(['Console', 'jQuery'], function (Console, $) {
        "use strict";
        Console.group("Entering Textfill directive.");

        var directive = ['ZMConstants', function (c) {
                return {
                    restrict: 'A',
                    scope: {
                        textfill: "="

                    },
                    link: function ($scope, ele, attrs) {
                        Console.log("Textfill directive called!");
                        function update() {
                            var fontSize = 50;
                            var ourText = $('span:visible:first', ele);
                            var maxHeight = $(ele).height();
                            var maxWidth = $(ele).width();
                            var textHeight;
                            var textWidth;
                            do {
                                ourText.css('font-size', fontSize);
                                textHeight = ourText.height();
                                textWidth = ourText.width();
                                fontSize = fontSize - 1;
                            } while ((textHeight > maxHeight || textWidth > maxWidth) && fontSize > 3);
                        }

                        $scope.$watch("textfill", update);


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
