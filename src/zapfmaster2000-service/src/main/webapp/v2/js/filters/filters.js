define([
  // Standard Libs
  'Console',       // lib/console/console
   'Underscore',  // lib/underscore/underscore
  // Application Filters
   "filters/CountdownFilter",
    "filters/AmountFilter",
    "filters/AchievementFilter"
], function (Console,_,CountdownFilter,AmountFilter,AchievementFilter){
  "use strict";
  Console.group("Entering Filters module.");

  var filters = {
      countdown:CountdownFilter,
      amount:AmountFilter,
      achievement:AchievementFilter
  };
  Console.info("Registered filters: ", filters);

  var initialize = function (angModule) {
    _.each(filters,function(filter,name){
      angModule.filter(name,filter);
    })
    Console.debug("Custom filters initialized.");
  }

  Console.groupEnd();
  return {
    initialize: initialize
  };
});
