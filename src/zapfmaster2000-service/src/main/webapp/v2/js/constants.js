define([
    // Standard Libs
    'Console'      // lib/console/console
], function(Console){
    "use strict";
    Console.group("Entering Constants module.");
    var constants = {
      baseUrl:"../",
      ajaxTimeout:120000
    }

    Console.info("Register constants: ", constants);


    Console.groupEnd();
    return constants;
});
