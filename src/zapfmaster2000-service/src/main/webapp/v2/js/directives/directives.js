define([
    // Standard Libs
    'Console',
    'jQuery',
    'Underscore',
    'Angular',

    // Application Widgets
    'directives/BarChart',
    'directives/FigureChart',
    'directives/FeedDirective',

    'directives/ShowDirective',
    'directives/SwitchDirective',
    'directives/TextfillDirective',
    'directives/MovingUnderline',
    'directives/AccordionDirective',
    'directives/UserInformationDirective',
    'directives/StickyScrollDirective'

//    ,'directives/LineChart'

], function (Console, $, _, angular, BarChart, FigureChart, FeedDirective, ShowDirective, SwitchDirective,
             TextfillDirective, MovingUnderline,AccordionDirective,UserInformationDirective,StickyScroll) {
    "use strict";
    Console.group("Entering Widgets module.");

    var directives = {
        d3bars: BarChart,
        switch: SwitchDirective,
        // d3line:LineChart,
        figurechart: FigureChart,
        animatewidget: ShowDirective,
        feed: FeedDirective,
        textfill: TextfillDirective,
        movingunderline:MovingUnderline,
        zmAccordion:AccordionDirective,
        userInfo:UserInformationDirective,
        zmStickyScroll:StickyScroll

    };

    Console.info("Registered directives: ", directives);

    var initialize = function (angModule) {
        _.each(directives, function (filter, name) {
            angModule.directive(name, filter);
        });
        Console.info("Registered Directives: ", directives);

    };

    Console.groupEnd();
    return {
        initialize: initialize
    };
});
