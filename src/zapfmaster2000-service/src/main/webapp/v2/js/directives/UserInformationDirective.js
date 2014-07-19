define(['Console', 'jQuery'], function (Console, $) {
        "use strict";
        Console.group("Entering User information directive.");

        var directive = ['ZMConstants','DataService', function (c,ajax) {
                return {
                    restrict: 'E',

                    templateUrl: 'views/directives/UserInformationDirective.html',
                    link: function ($scope, ele, attrs) {
                        // console.log($scope);
                        $scope.baseUrl = c.baseUrl;
                        ajax.getLoggedInUserStats().then(function(userStats){
                            $scope.userName = userStats.user.userName;
                            $scope.userImage=userStats.user.userImage;
                            $scope.isUserLoggedIn=true;
                        },function(){
                            $scope.isUserLoggedIn=false;
                        });

                    }
                };

            }
            ]
            ;

        Console.groupEnd();
        return directive;
    }
)
;
