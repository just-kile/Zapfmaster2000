define([
    // Standard Libs
    'Console'      // lib/console/console
], function (Console) {
    "use strict";
    Console.group("Entering Constants module.");
    var constants = {
        baseUrl: "../",
        widgetBaseUrl: "js/widgets/",
        ajaxTimeout: 120000,
        challengeUrl: "rest/challenge",
        bestlistUrl: "rest/statistics/rankings/bestUserList",
        frontPageUserStatsUrl: "rest/statistics/frontpageUserStats",
        kegStatsUrl: "rest/statistics/keg",
        newsPushUrl: "rest/push/news",
        updateAmountPushUrl: "rest/push/draftkit/{0}",
        boxesUrl: "rest/draftkit",
        progressUrl: "rest/statistics/progress",
        challengeMaxDuels: 6,
        bestlistMax: 6,
        CHALLENGE_STARTED: "CHALLENGE_STARTED",
        CHALLENGE_DONE: "CHALLENGE_DONE",
        DRAWING: "DRAWING",
        NEWKEG: "NEW_KEG",
        LOGIN: "LOGIN",
        LOGOUT: "LOGOUT",
        DRAW: "DRAW",
        SERVER_TIME_FORMAT: "YYYYMMDD-HHmmss",
        COUNTDOWN_TIME_FORMAT: "mm:ss",
        MUG_SIZE: 0.4,
        FIGURE_CHART:{
            MAX_IMAGES_PER_LINE:10,
            MIN_IMAGES:5,
            MAX_LINES:3,

            INITIAL_HEIGHT:75,
            INITIAL_WIDTH:60

        }
    }

    Console.info("Register constants: ", constants);


    Console.groupEnd();
    return constants;
});
