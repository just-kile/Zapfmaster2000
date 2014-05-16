define(['Console', 'Underscore'], function (Console, _) {
    "use strict";
    Console.group("Entering CometService module.");

    var service = ['$http', "ZMConstants",'$q', function ($http, c,$q) {
        var callbacks = {
            newspush: []
        };
        var ajaxCalls = {};

        var startCometService = function (url, cbKey) {
            ajaxCalls[cbKey] = $q.defer();
            $http({method: 'GET', url: c.baseUrl + url, params: {
                token: localStorage.getItem("token"),
                _: new Date().getTime()
            }, timeout:  ajaxCalls[cbKey].promise}).
                success(function (data, status, headers, config) {

                    Console.group("Received Push datas");
                    if (status == 200 && callbacks[cbKey].length>0) {
                        Console.debug("Datas", data);
                        _.each(callbacks[cbKey], function (callback, index) {
                            if (callback)callback(data);
                        });
                        startCometService(url, cbKey);

                    }
                    Console.groupEnd();


                }).
                error(function (data, status, headers, config) {
                    Console.log("Error Status: ", status);
                    if ([503,0,504].indexOf(status)>-1) {
                        Console.debug("Timeout. Reconnect Newspush");
                        startCometService(url, cbKey);

                    } else {
                        Console.debug("Reconnect in 5s...");
                        window.setTimeout(function(){
                            startCometService(url, cbKey);
                        }, 5000);
                    }
                });
        }
        startCometService(c.newsPushUrl, 'newspush');

        return {
            addPushListener: function (callback) {
                if (callback)callbacks.newspush.push(callback);
                Console.debug("Added Push Listener");
                Console.log("All Push Listeners: ", callbacks);
            },
            addInstantUpdateListener: function (boxId,callback) {
                if (callback){
                    if(!callbacks[boxId]){
                        startCometService(c.updateAmountPushUrl.replace("{0}",boxId),boxId);
                    }
                    if(!callbacks[boxId])callbacks[boxId] = [];
                    callbacks[boxId].push(callback);
                    Console.debug("Added Amount Push Listener");

                }
                // Console.debug("Push Listener", callback, "added");
                Console.log("All Push Listeners: ", callbacks);
            },
            resetPush:function(id){
                if(callbacks && callbacks[id]){
                    callbacks[id].length = 0;
                    //if( ajaxCalls[id])ajaxCalls[id].resolve();
                }
            },
            reset: function () {
                var me = this;
                _.each(callbacks,function(push,boxId){
                    me.resetPush(boxId);
                });

                Console.log("Reset callbacks.");
            }
        }

    }];

    Console.groupEnd();
    return service;
});