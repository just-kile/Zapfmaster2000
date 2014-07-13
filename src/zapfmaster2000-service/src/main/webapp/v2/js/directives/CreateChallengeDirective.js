define(['Console', 'Underscore', "Angular", "jQuery"], function (Console, _, angular, $) {
        "use strict";
        Console.group("Entering BarChart directive.");
        var directive = ['ZMConstants', '$window', '$timeout', 'DataService',
            function (c, $window, $timeout, ajax) {
                return {
                    restrict: 'E',
                    templateUrl: 'views/directives/CreateChallengeDirective.html',
                    link: function ($scope, ele, attrs) {
                        $scope.baseUrl = c.baseUrl;
                        $scope.chooseOpponent = true;
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

                        $(ele).find("#challengeUserModal")
                            .on("show.bs.modal", function () {
                                ajax.getChallengeOpponents().then(function (opponents) {
                                    $scope.opponents = opponents;
                                });
                            }).on("hide.bs.modal",function(){
                                $scope.chooseOpponent = true;
                            });

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
                        $scope.chooseDuration = function (userId) {
                            $scope.chooseOpponent = false;
                            $scope.chosenUserId = userId;
                        };
                        $scope.requestUserForChallenge = function (userId, duration) {

                            ajax.requestUserForChallenge(userId, duration).then(function () {
                                $(ele).find("#challengeUserModal").modal("hide");
                                $scope.chooseOpponent = true;
                            });
                        };
                        $scope.backToOpponents = function(){
                            $scope.chooseOpponent = true;
                        };

                    }
                };

            }];

        Console.groupEnd();
        return directive;
    }
)
;
