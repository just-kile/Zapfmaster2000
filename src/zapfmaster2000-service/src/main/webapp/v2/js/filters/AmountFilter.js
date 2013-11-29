define(['Console'], function (Console) {
    "use strict";
    Console.group("Entering AmountFilter module.");

    var service = ['ZMConstants', function (c) {
        return function(input) {
            if(input && typeof input =="number"){
                return input.toFixed(2)
            }else{
                return input;
            }
        };

    }];

    Console.groupEnd();
    return service;
});
