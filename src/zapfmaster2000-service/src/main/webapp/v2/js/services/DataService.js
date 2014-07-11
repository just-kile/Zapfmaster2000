define(['Console', 'Underscore',"Angular"], function (Console, _,angular) {
    "use strict";
    Console.group("Entering DataService module.");

    var service = ['$http', 'ZMConstants','$q', function ($http, c,$q) {
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
        var requestPromise = function (url, data) {
            return $http({
                url: c.baseUrl + url,
                method: "GET",
                params: _.extend({
                    token: localStorage.getItem("token"),
                    _: new Date().getTime()
                }, data)
            });
        };
        var getAchievements = function () {
            var req = requestPromise(c.newsFeedUrl, {
                start: 0,
                length: c.newsFeedLength,
                filter: c.FILTER.ACHIEVEMENT
            });
            return req.then(handleSuccess,handleError);

        };

        function handleError( response ) {
            if (! angular.isObject( response.data ) || ! response.data.message
                ) {

                return( $q.reject( "An unknown error occurred." ) );

            }

            // Otherwise, use expected error message.
            return( $q.reject( response.data.message ) );

        }


        // I transform the successful response, unwrapping the application data
        // from the API response payload.
        function handleSuccess( response ) {

            return( response.data );

        }
        return {
            getDatas: request,
            getAchievements:getAchievements
        };

    }];

    Console.groupEnd();
    return service;
});
