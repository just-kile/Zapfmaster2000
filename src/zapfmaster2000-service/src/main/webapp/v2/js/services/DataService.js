define(['Console'], function (Console) {
  "use strict";
  Console.group("Entering DataService module.");

  var service = ['$http','ZMConstants', function ($http,c) {
    var request = function(url,success,data){
          $http({
              url: c.baseUrl+url,
              method:"GET",
              params: {
                  token: localStorage.getItem("token"),
                  _: new Date().getTime()
              }
          }).success(success);
    };
    return {
        getDatas:request
    }

  }];

  Console.groupEnd();
  return service;
});
