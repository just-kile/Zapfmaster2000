define(['Console', 'moment', 'Underscore','text!../../../fake_cite.json'], function (Console, moment, _,citeString) {
    "use strict";
    Console.group("Entering UserProfile controller module.");

    var controller = ['$scope', '$timeout', 'CometService', 'DataService', "ZMConstants", 'DateService',
        function ($scope, $timeout, CometService, ajax, c, DateService) {
            Console.group("UserProfile controller entered.");
            var users = {};
            var cites = JSON.parse(citeString);
            $scope.baseUrl = c.baseUrl;
            function generateRandomId(){
                var index = Math.floor((Math.random()*users.length));
                return users[index].userId;
            }
            function update(userId,callback){
                ajax.getDatas(c.frontPageUserStatsUrl, function (data) {
                    $scope.user=data.user;

                    $scope.amount = data.amount;
                    $scope.achievement = data.achievement;
                    $scope.drawCount = data.drawCount;
                    $scope.rank = data.rank;

                    var index = Math.floor((Math.random()*cites.length));
                    $scope.quotation = cites[index];
                    callback && callback();
                }, {user: userId});
                ajax.getDatas(c.alcoholLevelUrl, function (data) {
                    $scope.alcoholLevel=data.alcoholLevel;
                }, {user: userId});
            }
            $scope.updateFn = function(ok){
                update(generateRandomId(),ok);
            }
            function init(){
                ajax.getDatas(c.membersUrl,function(data){
                    users = data;
                    update(generateRandomId());
                });

            }

            init();

            Console.groupEnd();
        }];

    Console.groupEnd();
    return controller;
});
