define(['Console'], function (Console) {
  "use strict";
  Console.group("Entering HomeController module.");

  var controller = ['$scope',function ($scope) {
    Console.group("HomeController entered.");
    var rows = [[{
        name:"js/modules/newsstack",
        className:"col-md-6"
    },{
        name:"js/modules/draftkits",
        className:"col-md-6"
    }],[{
        name:"js/modules/bestlist",
        className:"col-md-8"
    },{
        name:"js/modules/challenges",
        className:"col-md-4"
    }]];
    $scope.rows = rows;
    Console.debug("Initializing:",rows);
    Console.groupEnd();
  }];
  //controller.$inject = [];

  Console.groupEnd();
  return controller;
});
