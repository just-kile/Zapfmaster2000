define([
    // Standard Libs
    'Console',
    'Underscore',
    // Application Filters
    "filters/CountdownFilter",
    "filters/AmountFilter",
    "filters/AchievementFilter",
    "filters/ImageFilterBig",
    "filters/ClientDateFilter",
    "filters/MaxCountFilter",
    "filters/RankFilter"
], function (Console, _, CountdownFilter, AmountFilter, AchievementFilter, ImageFilterBig, ClientDateFilter, MaxCountFilter, RankFilter) {
    "use strict";
    Console.group("Entering Filters module.");

    var filters = {
        countdown: CountdownFilter,
        amount: AmountFilter,
        achievement: AchievementFilter,
        big: ImageFilterBig,
        clientdate: ClientDateFilter,
        maxcount: MaxCountFilter,
        rank: RankFilter
    };
   // Console.debug("Filters to be registered: ", filters);

    var initialize = function (angModule) {
        _.each(filters, function (filter, name) {
            angModule.filter(name, filter);
        });
        Console.debug("Registered filters: ", filters);

    };

    Console.groupEnd();
    return {
        initialize: initialize
    };
});
