define(['Console','moment'], function (Console,moment) {
    "use strict";
    Console.group("Entering CountdownFiler module.");

    var service = ['ZMConstants', function (c) {
        return function(input) {
            if(typeof input=="string"){
                return input;
            }
            var output = moment(input).format(c.COUNTDOWN_TIME_FORMAT);
           // Console.debug("Filter ",input," with Output ", output);
            return output;
        };

    }];

    Console.groupEnd();
    return service;
});
