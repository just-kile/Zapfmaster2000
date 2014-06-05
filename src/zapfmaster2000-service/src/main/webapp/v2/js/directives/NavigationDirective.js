define(['Console', 'jQuery'], function (Console, $) {
        "use strict";
        Console.group("Entering Navigation directive.");

        var directive = ['ZMConstants', function (c) {
                return {
                    restrict: 'E',
                    scope: {
                        topic: '='
                    },
                    templateUrl: 'views/directives/NavigationDirective.html',
                    link: function ($scope, element, attrs) {
                        Console.log("Feed Directive called");

                        $scope.topic = attrs.topic;
                        //   $scope.items = attrs.items;

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
