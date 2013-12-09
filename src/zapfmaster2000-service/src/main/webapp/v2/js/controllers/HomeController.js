define(['Console'], function (Console) {
  "use strict";
  Console.group("Entering HomeController module.");

  var controller = ['$scope',function ($scope) {
    Console.group("HomeController entered.");
    var rows = [[{
        name:"js/widgets/newsstack",
        className:"col-md-6"
    },{
        name:"js/widgets/draftkits",
        className:"col-md-6"
    }],[{
        name:"js/widgets/bestlist",
        className:"col-md-8"
    },{
        name:"js/widgets/challenges",
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
