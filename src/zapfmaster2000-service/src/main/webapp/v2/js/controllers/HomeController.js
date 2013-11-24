define(['Console'], function (Console) {
  "use strict";
  Console.group("Entering HomeController module.");

  var controller = ['$scope',function ($scope) {
    Console.group("HomeController entered.");
    var rows = [{
        name:"js/modules/newsstack"
    },{
        name:"js/modules/draftkits"
    },{
        name:"js/modules/bestlist"
    },{
        name:"js/modules/challenges"
    }];
    $scope.rows = rows;
    Console.debug("Initializing:",rows);
    Console.groupEnd();
  }];
  //controller.$inject = [];

  Console.groupEnd();
  return controller;
});
