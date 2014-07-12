define(['Console', 'd3', "Angular"], function (Console, d3, angular) {
        "use strict";
        Console.group("Entering BarChart directive.");
        var directive = ['ZMConstants', '$window', '$timeout', 'DataService',
            function (c, $window, $timeout,ajax) {
                return {
                    restrict: 'E',
                    templateUrl: 'views/directives/CreateChallengeDirective.html',
                    link: function ($scope, ele, attrs) {
                        $scope.baseUrl = c.baseUrl;
                        function addParamsToScope(challenges){
                            $scope.pendingChallenges = challenges;
                        }
                        ajax.getPendingChallenges().then(addParamsToScope);
                    }
                };

            }];

        Console.groupEnd();
        return directive;
    }
)
;
