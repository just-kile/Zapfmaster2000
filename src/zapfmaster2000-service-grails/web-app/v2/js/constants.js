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

    Console.debug("Register constants: ", constants);
    return constants;
});
