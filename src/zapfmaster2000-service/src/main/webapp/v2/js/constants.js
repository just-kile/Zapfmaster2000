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
        amountStatsUrl:"rest/statistics/amount",
        achievementStatsUrl:"rest/statistics/achievements",
        kegStatsUrl: "rest/statistics/keg",
        newsPushUrl: "rest/push/news",
        updateAmountPushUrl: "rest/push/draftkit/{0}",
        boxesUrl: "rest/draftkit",
        newsFeedUrl:"rest/news",
        webWorkers:4,
        newsFeedLength:6,
        newsStackLength:2,
        newsFeedTimeout:2000,
        progressUrl: "rest/statistics/progress",
        challengeMaxDuels: 6,
        bestlistMax: 6,
        CHALLENGE_STARTED: "CHALLENGE_STARTED",
        CHALLENGE_DONE: "CHALLENGE_DONE",
        DRAWING: "DRAWING",
        ACHIEVEMENT:"ACHIEVEMENT",
        NEWKEG: "NEW_KEG",
        LOGIN: "LOGIN",
        LOGOUT: "LOGOUT",
        DRAW: "DRAW",
        SERVER_TIME_FORMAT: "YYYYMMDD-HHmmss",
        CLIENT_TIME_FORMAT:"HH:mm",
        COUNTDOWN_TIME_FORMAT: "mm:ss",
        MUG_SIZE: 0.4,
        FIGURE_CHART:{
            MAX_IMAGES_PER_LINE:5,
            //MIN_IMAGES:5,
            //MAX_LINES:3,
            ANIMATION_IN:"bounceIn",
            ANIMATION_OUT:"flipOutY",
            INITIAL_HEIGHT:100,
            INITIAL_WIDTH:90

        },
        WIDGETS:{
            ANIMATION_IN:"fadeIn",
            ANIMATION_OUT:"fadeOut"
        }
    }

    Console.info("Register constants: ", constants);


    Console.groupEnd();
    return constants;
});
