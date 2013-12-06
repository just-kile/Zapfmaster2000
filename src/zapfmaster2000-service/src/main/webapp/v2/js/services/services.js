define([
  // Standard Libs
  'Console'       // lib/console/console
  , 'Underscore'  // lib/underscore/underscore

  // Custom Services
  , 'services/DataService',
    'services/CometService',
    'services/DateService',
    'services/SplashScreenService'
], function(Console, _, ds,cs,dateS,splash) {
  "use strict";
  Console.group("Entering Service module.");
  Console.info("DataService", ds);
  Console.info("CometService",cs);
    Console.info("DateService",dateS);
  var services = {
    DataService: ds,
    CometService:cs,
    DateService:dateS,
    SplashScreenService:splash
  };
  Console.info("Registered services: ", services);

  var initialize = function (angModule) {
    _.each(services,function(service,name){
      angModule.factory(name,service);
    });

    Console.debug("Custom services initialized.");
  }

  Console.groupEnd();
  return {
    initialize: initialize
  };
});
