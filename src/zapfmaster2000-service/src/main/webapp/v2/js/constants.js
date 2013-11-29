define([
    // Standard Libs
    'Console'      // lib/console/console
], function(Console){
    "use strict";
    Console.group("Entering Constants module.");
    var constants = {
      baseUrl:"../",
      ajaxTimeout:120000,
      challengeUrl:"rest/challenge",
      bestlistUrl:"rest/statistics/rankings/bestUserList",
      challengeMaxDuels:5,
      bestlistMax:5,
      CHALLENGE_STARTED:"CHALLENGE_STARTED",
      CHALLENGE_DONE:"CHALLENGE_DONE",
      DRAWING:"DRAWING",
      SERVER_TIME_FORMAT:"YYYYMMDD-HHmmss",
      COUNTDOWN_TIME_FORMAT:"mm:ss"
    }

    Console.info("Register constants: ", constants);


    Console.groupEnd();
    return constants;
});
