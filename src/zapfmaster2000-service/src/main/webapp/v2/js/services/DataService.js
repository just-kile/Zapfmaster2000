define(['Console', 'Underscore'], function (Console, _) {
    "use strict";
    Console.group("Entering DataService module.");

    var service = ['$http', 'ZMConstants', function ($http, c) {
        var request = function (url, success, data) {
            $http({
                url: c.baseUrl + url,
                method: "GET",
                params: _.extend({
                    token: localStorage.getItem("token"),
                    _: new Date().getTime()
                }, data)
            }).success(success);
        };
        return {
            getDatas: request
        };

    }];

    Console.groupEnd();
    return service;
});
