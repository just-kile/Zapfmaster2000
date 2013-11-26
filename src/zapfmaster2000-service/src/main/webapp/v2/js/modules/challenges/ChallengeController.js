define(['Console', 'Underscore'], function (Console, _) {
    "use strict";
    Console.group("Entering Callenge controller module.");

    var controller = ['$scope','$timeout', "CometService", "DataService", "ZMConstants", "DateService",
        function ($scope,$timeout, CometService, ajax, c, DateService) {
            Console.group("Challenge controller entered.");
            $scope.baseurl = c.baseUrl;
            var calcTeamPercent = function (data) {
                _.each(data, function (challenge, key) {
                    var team1Percent = challenge.team1[0].amount / (challenge.team1[0].amount + challenge.team2[0].amount) * 100;
                    var team2Percent = challenge.team2[0].amount / (challenge.team1[0].amount + challenge.team2[0].amount) * 100
                    challenge.team1Percent = !isNaN(team1Percent) ? team1Percent : 50;
                    challenge.team2Percent = !isNaN(team2Percent) ? team2Percent : 50;
                    challenge.countdown = "00:00";
                });
                return data;

            }
            Console.log(moment);
            var clockInterval;
            $scope.letTheClockTick = function () {
                if (clockInterval)clearInterval(clockInterval);

                _.each($scope.challenges, function (challenge, keys) {
                    var end = DateService.calcDiffToNow(challenge.startDate,challenge.challengeDuration, "mm:ss");
                    $scope.challenges[keys].countdown = end !="00:00"?end:"End";
                });

                clockInterval = $timeout($scope.letTheClockTick, 1000);


            }
            var initScope = function () {

                ajax.getDatas(c.challengeUrl, function (data) {
                    if (data.length > c.challengeMaxDuels) {
                        data.length = c.challengeMaxDuels;
                    }
                    calcTeamPercent(data);
                    $scope.challenges = data;
                    $scope.letTheClockTick();
                });
            };
            var updateAmount = function () {
                ajax.getDatas(c.challengeUrl, function (data) {
                    calcTeamPercent(data);
                    _.each($scope.challenges, function (challenge, key) {
                        challenge.team1Percent = data[key].team1Percent;
                        challenge.team2Percent = data[key].team2Percent;
                    });
                });
            };
            var addNewChallenge = function (data) {
                calcTeamPercent([data]);
                if ($scope.challenges.length >= c.challengeMaxDuels) {
                    $scope.challenges.pop();
                }
                $scope.challenges.unshift(data);
            }

            initScope();
            var pushListener = function (data) {
                if (data) {
                    if (data.type == c.DRAWING) {
                        updateAmount();
                    } else if (data.type == c.CHALLENGE_STARTED) {
                        addNewChallenge(data);
                    }

                }

            };
            CometService.addPushListener(pushListener);

            Console.groupEnd();
        }];
    //controller.$inject = [];

    Console.groupEnd();
    return controller;
});
