define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering AchievementFilter module.");

    var service = ['ZMConstants', function (c) {
        return function (input) {
            return typeof input === "undefined" || input === null ? "" : "" + input;
        };

    }];

    Console.groupEnd();
    return service;
});
