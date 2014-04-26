define([
    // Standard Libs
    'Console',
    'text!../constants.json'
], function (Console,constantsResponse) {
    "use strict";
    var constants = localStorage.getItem("zm-constants")||constantsResponse;
    if(typeof constants=="string"){
        constants = $.parseJSON(constants);
    }
    Console.group("Entering Constants module.");
    Console.info("Register constants: ", constants);
    Console.groupEnd();
    return constants;
});
