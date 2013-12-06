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
      frontPageUserStatsUrl:"rest/statistics/frontpageUserStats",
      kegStatsUrl:"rest/statistics/keg",
      newsPushUrl:"rest/push/news",
      updateAmountPushUrl:"rest/push/draftkit/{0}",
      boxesUrl:"rest/draftkit",
      challengeMaxDuels:5,
      bestlistMax:5,
      CHALLENGE_STARTED:"CHALLENGE_STARTED",
      CHALLENGE_DONE:"CHALLENGE_DONE",
      DRAWING:"DRAWING",
        NEWKEG:"NEW_KEG",
        LOGIN:"LOGIN",
        LOGOUT:"LOGOUT",
        DRAW:"DRAW",
      SERVER_TIME_FORMAT:"YYYYMMDD-HHmmss",
      COUNTDOWN_TIME_FORMAT:"mm:ss"
    }

    Console.info("Register constants: ", constants);


    Console.groupEnd();
    return constants;
});
