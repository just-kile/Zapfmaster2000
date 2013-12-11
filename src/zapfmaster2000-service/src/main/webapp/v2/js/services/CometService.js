define(['Console', 'Underscore'], function (Console, _) {
    "use strict";
    Console.group("Entering CometService module.");

    var service = ['$http', "ZMConstants", function ($http, c) {
        var callbacks = {
            newspush: [],
        };

        var startCometService = function (url, cbKey) {
            $http({method: 'GET', url: c.baseUrl + url, params: {
                token: localStorage.getItem("token"),
                _: new Date().getTime()
            }, timeout: c.ajaxTimeout}).
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
                    if(!callbacks[boxId])callbacks[boxId] = [];
                    callbacks[boxId].push(callback);
                    Console.debug("Added Amount Push Listener");
                    if(callbacks[boxId].length==1){
                        startCometService(c.updateAmountPushUrl.replace("{0}",boxId),boxId);
                    }
                }
                // Console.debug("Push Listener", callback, "added");
                Console.log("All Push Listeners: ", callbacks);
            },
            resetPush:function(id){
                if(callbacks && callbacks[id]){
                    callbacks[id].length = 0;
                }
            },
            reset: function () {
                _.each(callbacks,function(push){
                    push.length=0;
                });
                Console.log("Reset callbacks.");
            }
        }

    }];

    Console.groupEnd();
    return service;
});
