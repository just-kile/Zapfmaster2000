define([
  // Standard Libs
  'Console'      // lib/console/console
  , 'jQuery'     // lib/jquery/jquery
  , 'Underscore' // lib/underscore/underscore
  , 'Angular'    // lib/angular/angular

  // Application Widgets
  ,'directives/BarChart'
], function(Console, $, _, angular,BarChart){
  "use strict";
  Console.group("Entering Widgets module.");

  var directives = {
      d3bars:BarChart
  };

  Console.info("Registered directives: ", directives);

  var initialize = function (angModule) {
    _.each(directives,function(filter,name){
       angModule.directive(name,filter);
    })
    Console.debug("Custom widgets initialized.");
  }

  Console.groupEnd();
  return {
    initialize: initialize
  };
});
