define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering CountdownFiler module.");

    var service = ['ZMConstants', function (c) {
        return function (input) {
            if (input && input.indexOf("default") < 0) {
                return input + "/big";
            } else {
                return input;
            }

        };

    }];

    Console.groupEnd();
    return service;
});
