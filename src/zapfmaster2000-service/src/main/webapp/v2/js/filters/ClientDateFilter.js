define(['Console', 'moment'], function (Console, moment) {
    "use strict";
    Console.group("Entering ClientDate  filter");

    var service = ['ZMConstants', function (c) {
        return function (input, expression) {
            if (input) {
                if (expression === "full") {
                    return moment(input, c.SERVER_TIME_FORMAT).format(c.FULL_CLIENT_TIME_FORMAT);
                } else if (typeof expression !== "undefined") {
                    return moment(0).set(expression, input).format(c.CLIENT_TIME_FORMAT);

                } else {
                    return moment(input, c.SERVER_TIME_FORMAT).format(c.CLIENT_TIME_FORMAT);

                }
            } else {
               // Console.error("Invalid date for client date filter!");
                return "";

            }
            // Console.debug("Filter ",input," with Output ", output);

        };

    }];

    Console.groupEnd();
    return service;
});
