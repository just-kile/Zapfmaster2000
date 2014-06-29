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
                    challenge.countdown = 0;
                });
                return data;

            };
            var clockInterval;
            $scope.letTheClockTick = function () {
                if (clockInterval){
                    clearInterval(clockInterval);
                }

                _.each($scope.challenges, function (challenge, keys) {
                    $scope.challenges[keys].countdown = DateService.calcDiffToNow(challenge.startDate, challenge.challengeDuration, "mm:ss");
                });

                clockInterval = $timeout($scope.letTheClockTick, 1000);


            };
            var initScope = function (noMaxLength) {

                ajax.getDatas(c.challengeUrl, function (data) {
                    if (!noMaxLength&&data.length > c.challengeMaxDuels) {
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
            });
            $scope.init = initScope;
            /*     var dummyChallengeData = {"type": "CHALLENGE_STARTED", "image": "images/others/challengeStarted.jpg", "date": "20131206-172849", "challengeDuration": 10, "startDate": "20131206-172849", "challengeId": 4, "team1": [
             {"userId": 1, "userName": "Ben", "userImage": "rest/image/user/1", "amount": 0.0, "won": true}
             ], "team2": [
             {"userId": 3, "userName": "Thomas", "userImage": "rest/image/user/3", "amount": 0.0, "won": false}
             ], "challengeFinished": false};
             $timeout(function(){
             splash.splashChallenge(dummyChallengeData, c.CHALLENGE_STARTED);
             },2000)
             $timeout(function(){
             splash.splashChallenge(dummyChallengeData, c.CHALLENGE_DONE);
             },5000)*/

            Console.groupEnd();
        }];
    //controller.$inject = [];

    Console.groupEnd();
    return controller;
});
