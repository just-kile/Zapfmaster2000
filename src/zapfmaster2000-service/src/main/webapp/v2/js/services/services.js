define([
  // Standard Libs
  'Console'       // lib/console/console
  , 'Underscore'  // lib/underscore/underscore

  // Custom Services
  , 'services/DataService',
    'services/CometService'
], function(Console, _, ds,cs) {
  "use strict";
  Console.group("Entering Service module.");
  Console.info("DataService", ds);
  Console.info("CometService",cs);

  var services = {
    DataService: ds,
    CometService:cs
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
