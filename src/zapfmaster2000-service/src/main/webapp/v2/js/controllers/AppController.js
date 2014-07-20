define([
    'Console',
    'routes/routes'
], function (Console, routes) {
    "use strict";

    var appController = ['$scope', function ($scope) {
        Console.group("AppController entered");
        $scope.navigation = routes;
        Console.groupEnd();
    }];

    return appController;
});
