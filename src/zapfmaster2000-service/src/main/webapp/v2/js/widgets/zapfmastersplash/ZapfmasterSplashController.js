define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering ZapfmasterSplash controller module.");

    var controller = ['$scope', '$timeout', 'CometService', 'DataService', "ZMConstants", "$animate",
        function ($scope, $timeout, CometService, ajax, c) {
            Console.group("ZapfmasterSplash controller entered.");
            $scope.baseUrl = c.baseUrl;
            var animationClassesIn =
                [
                    "flipInY",
                    "flipInX",
                    "bounceIn"
                ];
            var animationClassesOut =
                [
                    "fadeOutDown",
                    "flipOutY",
                    "flipOutX"
                ];
           $timeout(function () {
                $scope.visible=true;
               var randIn = Math.floor((Math.random()*animationClassesIn.length));
               $scope.animationClass = animationClassesIn[randIn];
               $timeout(function () {
                   $scope.animationClass = "";
               }, 1000);
            }, 1000);
            $timeout(function () {
                var randOut = Math.floor((Math.random()*animationClassesOut.length));

                $scope.animationClass = animationClassesOut[randOut];
            }, 6000);

        }];
    //controller.$inject = [];

    Console.groupEnd();
    return controller;
});
