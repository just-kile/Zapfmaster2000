define(['Console', "Underscore"], function (Console, _) {
    "use strict";
    Console.group("Entering CountdownFiler module.");

    var service = ['ZMConstants', function (c) {
        return function (input, expression) {
            if (input && expression && _.isArray(input) && input.length > (expression)) {
                input.length = expression;
            }
            return input;

        };

    }];

    Console.groupEnd();
    return service;
});
