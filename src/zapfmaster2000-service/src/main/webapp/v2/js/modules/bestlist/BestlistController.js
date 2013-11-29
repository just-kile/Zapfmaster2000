define(['Console', 'Underscore'], function (Console, _) {
    "use strict";
    Console.group("Entering Bestlist controller module.");

    var controller = ['$scope', 'CometService', 'DataService', "ZMConstants", function ($scope, CometService, ajax, c) {
        Console.group("Bestlist controller entered.");
        $scope.baseUrl = c.baseUrl;
        var calcPercentAmountAndOrder = function (data) {
            var sum = 0, sumAdd = 0, amount = 0, len = data.length;
            _.each(data, function (user, index) {
                sum += user.amount;
                user.order = index;
                user.amountPercent = 0;
            });

            _.each(data, function (user, index, userlist) {
                var userRev = userlist[len - 1 - index];
                sumAdd += userRev.amount;
                amount = sumAdd * 100 / sum;
                if (!isNaN(amount))userRev.amountPercent = amount;
            });
            return data;
        }
        var initScope = function () {
            ajax.getDatas(c.bestlistUrl, function (data) {
                if (data.length > c.bestlistMax) {
                    data.length = c.bestlistMax;
                }
                calcPercentAmountAndOrder(data);
                $scope.bestlist = data;
            });
        };

        CometService.addPushListener(function (data) {

        });
        initScope();
        Console.groupEnd();
    }];
    //controller.$inject = [];

    Console.groupEnd();
    return controller;
});
