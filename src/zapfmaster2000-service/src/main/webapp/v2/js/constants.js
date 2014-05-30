define([
    // Standard Libs
    'Console',
    'text!../constants.json'
], function (Console, constantsResponse) {
    "use strict";
    var constants = localStorage.getItem("zm-constants") || constantsResponse;
    if (typeof constants === "string") {
        constants = JSON.parse(constants);
    }

    //Console.group("Entering Constants module.");
    //Console.debug("Register constants: ", constants);
    //Console.groupEnd();
    return constants;
});
