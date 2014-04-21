define(['Console', 'moment', 'Underscore'], function (Console, moment, _) {
    "use strict";
    Console.group("Entering UserProfile controller module.");

    var controller = ['$scope', '$timeout', 'CometService', 'DataService', "ZMConstants", 'DateService',
        function ($scope, $timeout, CometService, ajax, c, DateService) {
            Console.group("UserProfile controller entered.");
            var userId=1;
            $scope.baseUrl = c.baseUrl;
            function init(){
                ajax.getDatas(c.frontPageUserStatsUrl, function (data) {
                    $scope.userName=data.user.userName;
                    $scope.userImage = data.user.userImage;
                }, {user: userId});


            }

            init();

            Console.groupEnd();
        }];

    Console.groupEnd();
    return controller;
});
