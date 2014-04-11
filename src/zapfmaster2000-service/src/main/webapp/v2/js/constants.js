define([
    // Standard Libs
    'Console',
    'text!../constants.json'
], function (Console,constantsResponse) {
    "use strict";
    var constants = constantsResponse;
    if(typeof constants=="string"){
        constants = $.parseJSON(constantsResponse);
    }
    Console.group("Entering Constants module.");
    Console.info("Register constants: ", constants);
    Console.groupEnd();
    return constants;
});
