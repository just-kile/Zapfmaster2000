define(['Console', 'Underscore','jQuery'], function (Console, _,$) {
    "use strict";
    Console.group("Entering CometService module.");

    var service = ['$http', "ZMConstants", '$q', 'webWorkerPoolFactory', function ($http, c, $q, webWorkerPoolFactory) {
        var callbacks = {
            newspush: []
        };
        var baseUrl =  document.location.origin+ document.location.pathname;
        var ajaxCalls = {};
        var webWorkerPool = webWorkerPoolFactory.createPool('js/services/CometWorker.js', c.webWorkers);
       // webWorkerPool.postMessage({init:true,baseUrl:document.location.origin+ document.location.pathname});
        var startCometService = function (url, cbKey) {
            ajaxCalls[cbKey] = $q.defer();
            webWorkerPool
                .postMessage({url:baseUrl+ c.baseUrl+ url, token: localStorage.getItem("token")})
                .then(function (event) {
                    console.log(event.data);
                    var e = event.data;
                    var status = e.status;
                    Console.group("Received Push datas");
                    if (status == 200 && callbacks[cbKey].length > 0) {
                        var data = $.parseJSON(e.responseText);
                        Console.debug("Datas", data);
                        _.each(callbacks[cbKey], function (callback, index) {
                            if (callback)callback(data);
                        });
                        startCometService(url, cbKey);

                    } else {
                        if ([503, 0, 504].indexOf(status) > -1) {
                            Console.debug("Timeout. Reconnect Newspush");
                            startCometService(url, cbKey);

                        } else {
                            Console.debug("Reconnect in 5s...");
                            window.setTimeout(function () {
                                startCometService(url, cbKey);
                            }, 5000);
                        }
                    }
                    Console.groupEnd();
                });
        }
        startCometService(c.newsPushUrl, 'newspush');

        return {
            addPushListener: function (callback) {
                if (callback)callbacks.newspush.push(callback);
                Console.debug("Added Push Listener");
                Console.log("All Push Listeners: ", callbacks);
            },
            addInstantUpdateListener: function (boxId, callback) {
                if (callback) {
                    if (!callbacks[boxId]) {
                        startCometService(c.updateAmountPushUrl.replace("{0}", boxId), boxId);
                    }
                    if (!callbacks[boxId])callbacks[boxId] = [];
                    callbacks[boxId].push(callback);
                    Console.debug("Added Amount Push Listener");

                }
                // Console.debug("Push Listener", callback, "added");
                Console.log("All Push Listeners: ", callbacks);
            },
            resetPush: function (id) {
                if (callbacks && callbacks[id]) {
                    callbacks[id].length = 0;
                    //if( ajaxCalls[id])ajaxCalls[id].resolve();
                }
            },
            reset: function () {
                var me = this;
                _.each(callbacks, function (push, boxId) {
                    me.resetPush(boxId);
                });

                Console.log("Reset callbacks.");
            }
        }

    }];

    Console.groupEnd();
    return service;
});
