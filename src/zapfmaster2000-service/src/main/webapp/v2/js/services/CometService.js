define(['Console','Underscore'], function (Console,_) {
    "use strict";
    Console.group("Entering CometService module.");

    var service = ['$http',"ZMConstants", function ($http, c) {
        var callbacks = [];
        var startCometService = function () {
            $http({method: 'GET', url: c.baseUrl + 'rest/push/news', params: {
                token: localStorage.getItem("token"),
                _: new Date().getTime()
            }, timeout: c.ajaxTimeout}).
                success(function (data, status, headers, config) {
                    startCometService();
                    Console.group("Received Push datas");
                    if(status == 200){
                        Console.debug("Datas",data);
                        _.each(callbacks,function(callback,index){
                            if(callback)callback(data);
                        });

                    }
                    Console.groupEnd();


                }).
                error(function (data, status, headers, config) {
                    Console.log("Error Status: ",status);
                    if(status==503 || status == 0){
                        Console.debug("Timeout. Reconnect Newspush");
                        startCometService();

                    }else{
                        Console.debug("Reconnect in 5s...");
                        window.setTimeout(startCometService,5000);
                    }
                });
        }
        startCometService();
        return {
            addPushListener: function (callback) {
                if (callback)callbacks.push(callback);
                Console.debug("Push Listener", callback, "added");
                Console.log("All Push Listeners: ", callbacks);
            },
            reset: function () {
                callbacks.length = 0;
                Console.log("Reset callbacks.");
            }
        }

    }];

    Console.groupEnd();
    return service;
});
