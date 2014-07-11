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


        var getAchievementFeed = function () {
            var req = requestPromise(c.newsFeedUrl, {
                start: 0,
                length: c.newsFeedLength,
                filter: c.FILTER.ACHIEVEMENT
            });
            return req.then(handleSuccess,handleError);

        };

        var getDetailedAchievementStats = function(achievementId){
            var req = requestPromise(c.achievementStatsDetailedUrl,{
                id:achievementId
            });
            return req.then(handleSuccess,handleError);
        };

        var getAmountStats = function(){
            var req = requestPromise(c.amountStatsUrl);
            return req.then(handleSuccess,handleError);
        };
        var getAchievementStats = function(){
            var req = requestPromise(c.achievementStatsUrl);
            return req.then(handleSuccess,handleError);
        };
        var getBestlist = function(){
            var req = requestPromise(c.bestlistUrl);
            return req.then(handleSuccess,handleError);
        };
        var getUserStats = function(userId){
            var req = requestPromise(c.frontPageUserStatsUrl,{
                user:userId
            });
            return req.then(handleSuccess,handleError);

        }
        function handleError( response ) {
            if (! angular.isObject( response.data ) || ! response.data.message
                ) {

                return( $q.reject( "An unknown error occurred." ) );

            }

            // Otherwise, use expected error message.
            return( $q.reject( response.data.message ) );

        }


        function handleSuccess( response ) {

            return( response.data );

        }
        return {
            getDatas: request,
            getAchievementFeed:getAchievementFeed,
            getDetailedAchievementStats:getDetailedAchievementStats,
            getAmountStats:getAmountStats,
            getAchievementStats:getAchievementStats,
            getBestlist:getBestlist,
            getUserStats:getUserStats
        };

    }];

    Console.groupEnd();
    return service;
});
