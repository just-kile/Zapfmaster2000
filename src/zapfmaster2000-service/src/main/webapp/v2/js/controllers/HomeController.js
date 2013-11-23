define(['Console'], function (Console) {
  "use strict";
  Console.group("Entering HomeController module.");

  var controller = ['$scope',function ($scope) {
    Console.group("HomeController entered.");
    var rows = [{
        name:"js/modules/newsstack",
        controller:"newsstack"
    },{
        name:"js/modules/bestlist",
        controller:"bestlist"
    },{
        name:"js/modules/challenges",
        controller:"challenges"
    }];
    $scope.rows = rows;
    Console.log("Initializing:",rows);
    Console.groupEnd();
  }];
  //controller.$inject = [];

  Console.groupEnd();
  return controller;
});
