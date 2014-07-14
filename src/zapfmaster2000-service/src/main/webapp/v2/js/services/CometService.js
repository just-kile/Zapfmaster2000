define(['Console', 'Underscore'], function (Console, _) {
    "use strict";
    Console.group("Entering CometService module.");

    var service = ['$http', "ZMConstants", '$q', '$rootScope', function ($http, c, $q, $rootScope) {
        var callbacks = {
            newspush: [],
            challenges:[]
        };
        var newspushStopped = false;
        //var ajaxCalls = {};
        var cometRunningMap = {};
        var startCometServiceFor = function (url, cbKey, stopManual) {
            //ajaxCalls[cbKey] = $q.defer();
            cometRunningMap[cbKey] = {
                running: true, // flag if comet service is already running
                url: url,
                stop: $q.defer(), //promise to abort request
                stopManual: !!stopManual //true to start/stop this push manually
            };
            $http({method: 'GET', url: c.baseUrl + url, params: {
                token: localStorage.getItem("token"),
                _: new Date().getTime()
            }, timeout: cometRunningMap[cbKey].stop.promise}).
                success(function (data, status, headers, config) {

                    Console.group("Received Push datas");
                    if (status === 200 && callbacks[cbKey].length > 0) {
                        Console.debug("Datas", data);
                        _.each(callbacks[cbKey], function (callback, index) {
                            if (callback) {
                                callback(data);
                            }
                        });
                        startCometServiceFor(url, cbKey);

                    }
                    Console.groupEnd();


                }).
                error(function (data, status, headers, config) {
                    Console.log("Error Status: ", status);
                    if (!cometRunningMap[cbKey].running) {
                        Console.debug("Comet service manually stopped!");
                    } else if ([503, 0, 504].indexOf(status) > -1) {
                        Console.debug("Timeout. Reconnect Newspush");
                        startCometServiceFor(url, cbKey);

                    } else {
                        Console.debug("Reconnect in 5s...");
                        window.setTimeout(function () {
                            startCometServiceFor(url, cbKey);
                        }, 5000);
                    }
                });
        };
        var resetPush = function (id) {
            if (callbacks && callbacks[id]) {
                callbacks[id].length = 0;
                //if( ajaxCalls[id])ajaxCalls[id].resolve();
            }
        };
        var reset = function () {
            _.each(callbacks, function (push, boxId) {
                resetPush(boxId);
            });

            Console.log("Reset callbacks.");
        };
        var startNewsPush = function () {
            if (!cometRunningMap.newspush || !cometRunningMap.newspush.running) {
                startCometServiceFor(c.newsPushUrl, 'newspush');
            }
        };
        var stopCometServiceFor = function (cbKey, force) {
            if (cometRunningMap[cbKey] && cometRunningMap[cbKey].running && (!cometRunningMap[cbKey].stopManual || force)) {
                cometRunningMap[cbKey].running = false;
                cometRunningMap[cbKey].stop.resolve();
            }
        };
        var stopCometService = function (cbKey) {
            _.each(cometRunningMap, function (req, cbKey) {
                stopCometServiceFor(cbKey);
            });
        };
        var startCometService = function () {
            _.each(cometRunningMap, function (req, cbKey) {
                if (!req.running) {
                    startCometServiceFor(req.url, cbKey);
                }
            });
        };
        var startChallengePush = function () {
            if (!cometRunningMap.challenges || !cometRunningMap.challenges.running) {
                startCometServiceFor(c.challengePushUrl, 'challenges', true);
            }
        };
        var stopChallengePush = function () {
            stopCometServiceFor("challenges", true);
        };
        var addChallengePushListener = function(){

        }

      /*  $rootScope.$on('$routeChangeSuccess', function (next, last) {
            reset();
        });*/
        var removeNewsPushListener = function (fn) {
            var fnString = fn.toString();
            var index = -1;
            _.find(callbacks.newspush, function (callback, ind) {
                if (fnString === callback.toString()) {
                    index = ind;
                    return true;
                }
                return false;
            });
            if(index>-1){
                callbacks.newspush.splice(index,1);
            }
        };
        return {
            addPushListener: function (callback) {
                if (callback) {
                    callbacks.newspush.push(callback);
                }
                Console.debug("Added Push Listener");
                Console.log("All Push Listeners: ", callbacks);
            },
            addInstantUpdateListener: function (boxId, callback) {
                if (callback) {
                    if (!callbacks[boxId]) {
                        startCometServiceFor(c.updateAmountPushUrl.replace("{0}", boxId), boxId);
                        callbacks[boxId] = [];
                    }
                    callbacks[boxId].push(callback);
                    Console.debug("Added Amount Push Listener");

                }
                // Console.debug("Push Listener", callback, "added");
                Console.log("All Push Listeners: ", callbacks);
            },
            addChallengePushListener:function(callback){
                if (callback) {
                    callbacks.challenges.push(callback);
                }
                Console.debug("Added Push Listener");
                Console.log("All Push Listeners: ", callbacks);
            },
            resetPush: resetPush,
            reset: reset,
            removeNewsPushListener: removeNewsPushListener,
            startCometService: startCometService,
            stopCometService: stopCometService,
            startNewsPush: startNewsPush,
            startChallengePush: startChallengePush,
            stopChallengePush: stopChallengePush

        };

    }];

    Console.groupEnd();
    return service;
});