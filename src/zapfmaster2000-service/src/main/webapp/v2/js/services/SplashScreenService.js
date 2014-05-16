define(['Console', 'Underscore'], function (Console, _) {
    "use strict";
    Console.group("Entering DataService module.");

    var service = ['$http', 'ZMConstants', '$timeout', function ($http, c, $timeout) {
        var scope = null;
        var register = function ($scope) {
            scope = $scope;
        };
        var splash = function (data, type) {
            scope.visible = true;
            if (type === c.CHALLENGE_STARTED) {
                scope.visible_challenge_started = true;
                $timeout(function () {
                    scope.visible_challenge_started = false;
                }, 5000);
            }
            if (type === c.CHALLENGE_DONE) {
                scope.visible_challenge_done = true;
                $timeout(function () {
                    scope.visible_challenge_done = false;
                }, 5000);
            }

            scope.data = data;

        };
        return {
            register: register,
            splashChallengeFinished: splash,
            splashChallenge: splash
        };

    }];

    Console.groupEnd();
    return service;
});
