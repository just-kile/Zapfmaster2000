define(['Console','moment'], function (Console,moment) {
    "use strict";
    Console.group("Entering ClientDate  filter");

    var service = ['ZMConstants', function (c) {
        return function(input) {


            return moment(input, c.SERVER_TIME_FORMAT).format(c.CLIENT_TIME_FORMAT);
            // Console.debug("Filter ",input," with Output ", output);

        };

    }];

    Console.groupEnd();
    return service;
});
