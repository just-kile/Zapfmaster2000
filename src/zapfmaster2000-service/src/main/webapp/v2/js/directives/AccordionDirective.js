define(['Console', 'd3', "Angular",'jQuery'], function (Console, d3, angular,$) {
        "use strict";
        Console.group("Entering BarChart directive.");

        var directive = [ function () {
            return {
                transclude:true,
                restrict: 'E',
                scope:{
                    title:"@"
                },
                templateUrl: 'views/directives/AccordionDirective.html',
                link:function($scope,ele,attrs){

                    $scope.toggle=function(){
                        // alert("Toggle");
                        var container = $(ele).find(".zm-accordion-container");
                        container.toggleClass("zm-hidden");

                    };
                    $scope.$watch("title",function(){
                        $scope.title = attrs.title;

                    });
                }
            };

        }];

        Console.groupEnd();
        return directive;
    }
)
;
