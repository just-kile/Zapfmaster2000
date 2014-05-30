define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering AmountFilter module.");

    var service = ['ZMConstants', function (c) {
        return function (input, type) {

            input = parseFloat(input);
            input = isNaN(input) ? 0 : input;

            if (type === "full") {
                return Math.round(input);
            } else if (input || input === 0) {
                return input.toFixed(type || 2);
            }

        };

    }];

    Console.groupEnd();
    return service;
});
