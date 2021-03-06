define(['Console', 'Underscore'], function (Console, _) {
    "use strict";
    Console.group("Entering Callenge controller module.");

    var controller = ['$scope', '$timeout', "CometService", "DataService", "ZMConstants", "DateService", 'SplashScreenService',
        function ($scope, $timeout, CometService, ajax, c, DateService, splash) {
            Console.group("Challenge controller entered.");
            $scope.baseUrl = c.baseUrl;
            $scope.challengeMaxDuels = c.challengeMaxDuels;

            var calcTeamPercent = function (data) {
                _.each(data, function (challenge, key) {
                    var team1Percent = challenge.team1[0].amount / (challenge.team1[0].amount + challenge.team2[0].amount) * 100;
                    var team2Percent = challenge.team2[0].amount / (challenge.team1[0].amount + challenge.team2[0].amount) * 100;
                    challenge.team1Percent = !isNaN(team1Percent) ? team1Percent : 50;
                    challenge.team2Percent = !isNaN(team2Percent) ? team2Percent : 50;
                    challenge.team1Amount = data[key].team1[0].amount;
                    challenge.team2Amount = data[key].team2[0].amount;

                    challenge.countdown = 0;
                    challenge.diffAmount = Math.abs(challenge.team1[0].amount - challenge.team1[0].amount);
                });
                return data;

            };
            var clockInterval;
            $scope.letTheClockTick = function () {
                if (clockInterval) {
                    clearInterval(clockInterval);
                }

                _.each($scope.challenges, function (challenge, keys) {
                    var diff = DateService.calcDiffToNow(challenge.startDate, challenge.challengeDuration, "mm:ss");
                    if (diff > 0) {
                        $scope.challenges[keys].countdown = diff;
                    }

                    if (diff <= 0 && $scope.challenges[keys].type !== "CHALLENGE_DONE") {
                        $scope.challenges[keys].type = "CHALLENGE_DONE";
                    }

                });

                clockInterval = $timeout($scope.letTheClockTick, 1000);


            };
            var initScope = function (noMaxLength) {

                ajax.getChallenges().then(function (data) {
                    if (!noMaxLength && data.length > c.challengeMaxDuels) {
                        data.length = c.challengeMaxDuels;
                    }

                    calcTeamPercent(data);
                    $scope.challenges = data;
                    $scope.letTheClockTick();


                });
            };
            var updateAmount = function () {
                ajax.getChallenges().then(function (data) {
                    calcTeamPercent(data);
                    _.each($scope.challenges, function (challenge, key) {
                        challenge.team1Percent = data[key].team1Percent;
                        challenge.team2Percent = data[key].team2Percent;
                        challenge.team1Amount = data[key].team1[0].amount;
                        challenge.team2Amount = data[key].team2[0].amount;
                        challenge.diffAmount = data[key].diffAmount;
                    });
                });
            };
            var addNewChallenge = function (data) {
                calcTeamPercent([data]);
                if ($scope.challenges.length >= c.challengeMaxDuels) {
                    $scope.challenges.pop();
                }
                $scope.challenges.unshift(data);
            };


            var pushListener = function (data) {
                if (data) {
                    if (data.type === c.DRAWING) {
                        updateAmount();
                    } else if (data.type === c.CHALLENGE_STARTED) {
                        addNewChallenge(data);
                        splash.splashChallenge(data, c.CHALLENGE_STARTED);

                    } else if (data.type === c.CHALLENGE_DONE) {
                        splash.splashChallengeFinished(data, c.CHALLENGE_DONE);
                    }

                }

            };
            CometService.addPushListener(pushListener);
            $scope.$on("$destroy", function () {
                $timeout.cancel(clockInterval);
                CometService.removeNewsPushListener(pushListener);
            });
            $scope.init = initScope;
            Console.groupEnd();
        }];

    Console.groupEnd();
    return controller;
});
