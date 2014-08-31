define([
    // Standard Libs
    'Console' ,
    'Underscore',
    // Custom Services
    'services/DataService',
    'services/CometService',
    'services/DateService',
    'services/SplashScreenService',
    'services/ResizeService'
], function (Console, _, ds, cs, dateS, splash,resize) {
    "use strict";
    Console.group("Entering Service module.");
    Console.info("DataService", ds);
    Console.info("CometService", cs);
    Console.info("DateService", dateS);
    var services = {
        DataService: ds,
        CometService: cs,
        DateService: dateS,
        SplashScreenService: splash,
        ResizeService:resize
    };
    Console.info("Registered services: ", services);

    var initialize = function (angModule) {
        _.each(services, function (service, name) {
            angModule.factory(name, service);
        });

        Console.info("Registered Services: ", services);

    };

    Console.groupEnd();
    return {
        initialize: initialize
    };
});
