define(['Console', "Underscore"], function (Console, _) {
    "use strict";
    Console.group("Entering CountdownFiler module.");

    var service = ['ZMConstants', '$translate', function (c, $translate) {
        var lang;
        var s = ["th", "st", "nd", "rd"], v;

        return function (input) {
            if (input) {

                lang = $translate.use();

                switch (lang) {
                    case 'en':
                        v = input % 100;
                        return input + (s[(v - 20) % 10] || s[v] || s[0]);
                    default :
                        return input + ".";

                }

            }
            return input;
        };
    }];

    Console.groupEnd();
    return service;
});
