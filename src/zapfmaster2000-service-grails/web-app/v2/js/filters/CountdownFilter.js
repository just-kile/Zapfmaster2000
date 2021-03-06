define(['Console', 'moment'], function (Console, moment) {
    "use strict";
    Console.group("Entering CountdownFiler module.");

    var service = ['ZMConstants', function (c) {
        return function (input) {
            if (typeof input === "string") {
                return input;
            }
            if(typeof input==="undefined"||input === null){
                return "";
            }
            var output = moment(input).format(c.COUNTDOWN_TIME_FORMAT);
            return output;
        };

    }];

    Console.groupEnd();
    return service;
});
