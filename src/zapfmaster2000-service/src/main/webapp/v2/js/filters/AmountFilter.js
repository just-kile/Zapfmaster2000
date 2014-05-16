define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering AmountFilter module.");

    var service = ['ZMConstants', function (c) {
        return function (input, type) {
            if (type === "full") {
                return Math.round(input);
            } else if (input && typeof input === "number") {
                return input.toFixed(type || 2);
            } else {
                return input;
            }
        };

    }];

    Console.groupEnd();
    return service;
});
