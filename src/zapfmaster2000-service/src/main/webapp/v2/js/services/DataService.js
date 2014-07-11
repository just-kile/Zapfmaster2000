define(['Console', 'Underscore', "Angular"], function (Console, _, angular) {
    "use strict";
    Console.group("Entering DataService module.");

    var service = ['$http', 'ZMConstants', '$q', function ($http, c, $q) {
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


        var getAchievementFeed = function (length) {
            var req = requestPromise(c.newsFeedUrl, {
                start: 0,
                length: length,
                filter: c.FILTER.ACHIEVEMENT
            });
            return req.then(handleSuccess, handleError);

        };

        var getDetailedAchievementStats = function (achievementId) {
            var req = requestPromise(c.achievementStatsDetailedUrl, {
                id: achievementId
            });
            return req.then(handleSuccess, handleError);
        };

        var getAmountStats = function () {
            var req = requestPromise(c.amountStatsUrl);
            return req.then(handleSuccess, handleError);
        };
        var getAchievementStats = function () {
            var req = requestPromise(c.achievementStatsUrl);
            return req.then(handleSuccess, handleError);
        };
        var getBestlist = function () {
            var req = requestPromise(c.bestlistUrl);
            return req.then(handleSuccess, handleError);
        };
        var getUserStats = function (userId) {
            var req = requestPromise(c.frontPageUserStatsUrl, {
                user: userId
            });
            return req.then(handleSuccess, handleError);

        };
        var getChallenges = function () {
            var req = requestPromise(c.challengeUrl);
            return req.then(handleSuccess, handleError);
        };
        var getZapfDistribution = function (from, interval) {
            var req = requestPromise(c.distributionUrl, {
                from: from,
                interval: interval
            });
            return req.then(handleSuccess, handleError);
        };
        var getKegStats = function(){

            var req = requestPromise(c.kegStatsUrl);
            return req.then(handleSuccess, handleError);
        };
        var getProgress = function(from,interval){

            var req = requestPromise(c.progressUrl,{
                from:from,
                interval:interval
            });
            return req.then(handleSuccess, handleError);
        };
        var getNewsFeed = function(start,interval){
            var req = requestPromise(c.newsFeedUrl,{
                start:start,
                length:interval
            });
            return req.then(handleSuccess, handleError);
        };
        var getMembers = function(){
            var req = requestPromise(c.membersUrl);
            return req.then(handleSuccess, handleError);
        };
        function handleError(response) {
            if (!angular.isObject(response.data) || !response.data.message
                ) {

                return( $q.reject("An unknown error occurred.") );

            }

            // Otherwise, use expected error message.
            return( $q.reject(response.data.message) );

        }


        function handleSuccess(response) {

            return( response.data );

        }

        return {
            getDatas: request,
            getAchievementFeed: getAchievementFeed,
            getDetailedAchievementStats: getDetailedAchievementStats,
            getAmountStats: getAmountStats,
            getAchievementStats: getAchievementStats,
            getBestlist: getBestlist,
            getUserStats: getUserStats,
            getChallenges: getChallenges,
            getZapfDistribution: getZapfDistribution,
            getKegStats:getKegStats,
            getProgress:getProgress,
            getNewsFeed:getNewsFeed,
            getMembers:getMembers
        };

    }];

    Console.groupEnd();
    return service;
});
