define(['Console', 'Underscore', "Angular"], function (Console, _, angular) {
        "use strict";
        Console.group("Entering BarChart directive.");
        var directive = ['ZMConstants', '$window', '$timeout', 'DataService',
            function (c, $window, $timeout, ajax) {
                return {
                    restrict: 'E',
                    templateUrl: 'views/directives/CreateChallengeDirective.html',
                    link: function ($scope, ele, attrs) {
                        $scope.baseUrl = c.baseUrl;
                        function removeChallengeId(id) {
                            var index = -1;
                            _.find($scope.pendingChallenges, function (challenge, ind) {
                                if (challenge.challengeId === id) {
                                    index = ind;
                                    return true;
                                }
                                return false;
                            });
                            $scope.pendingChallenges.splice(index, 1);

                        }

                        function addParamsToScope(challenges) {
                            $scope.pendingChallenges = challenges;
                        }

                        ajax.getPendingChallenges().then(addParamsToScope);
                        $scope.acceptChallenge = function (id) {
                            ajax.acceptChallenge(id).then(function () {
                                removeChallengeId(id);
                            });
                        };
                        $scope.denyChallenge = function (id) {

                            ajax.denyChallenge(id).then(function () {
                                removeChallengeId(id);
                            });
                        };
                    }
                };

            }];

        Console.groupEnd();
        return directive;
    }
)
;
