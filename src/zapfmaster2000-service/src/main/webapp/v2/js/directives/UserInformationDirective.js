define(['Console', 'jQuery'], function (Console, $) {
        "use strict";
        Console.group("Entering Textfill directive.");

        var directive = ['ZMConstants','DataService', function (c,ajax) {
                return {
                    restrict: 'E',

                    templateUrl: 'views/directives/UserInformationDirective.html',
                    link: function ($scope, ele, attrs) {
                        // console.log($scope);
                        $scope.baseUrl = c.baseUrl;
                        ajax.getDatas(c.userStatsUrl,function(userStats){
                            Console.log(userStats);
                            $scope.userName = userStats.user.userName;
                            $scope.userImage=userStats.user.userImage;

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
