define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering CountdownFiler module.");

    var service = ['ZMConstants', function (c) {
        return function (input) {
            if (typeof input === "string" &&
                 input.indexOf(".png") < 0) {
                return input + "/big";
            } else if(typeof input==="number"){
                return input+"/big";
            }else{
                return input;
            }

        };

    }];

    Console.groupEnd();
    return service;
});
