define(['Console', 'Underscore'], function (Console, _) {
    "use strict";
    Console.group("Entering DataService module.");

    var service = ['$http', 'ZMConstants', '$timeout', function ($http, c, $timeout) {
        var scope = null;
        var register = function ($scope) {
            scope = $scope;
        };
        var splash = function (data) {
            scope.visible = true;

            scope.data = data;
            $timeout(function () {
                scope.visible = false;
            }, 5000);
        }
        return {
            register: register,
            splashChallengeFinished: splash,
            splashChallenge: splash
        }

    }];

    Console.groupEnd();
    return service;
});
