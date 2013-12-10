define([
  // Standard Libs
  'Console',       // lib/console/console
   'Underscore',  // lib/underscore/underscore
  // Application Filters
   "filters/CountdownFilter",
    "filters/AmountFilter",
    "filters/AchievementFilter",
    "filters/ImageFilterBig"
], function (Console,_,CountdownFilter,AmountFilter,AchievementFilter,ImageFilterBig){
  "use strict";
  Console.group("Entering Filters module.");

  var filters = {
      countdown:CountdownFilter,
      amount:AmountFilter,
      achievement:AchievementFilter,
      big:  ImageFilterBig
  };
  Console.info("Registered filters: ", filters);

  var initialize = function (angModule) {
    _.each(filters,function(filter,name){
      angModule.filter(name,filter);
    });
    Console.debug("Custom filters initialized.");
  };

  Console.groupEnd();
  return {
    initialize: initialize
  };
});
