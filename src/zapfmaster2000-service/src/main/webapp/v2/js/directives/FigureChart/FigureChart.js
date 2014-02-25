define(['Console', 'd3'], function (Console, d3) {
        "use strict";
        Console.group("Entering BarChart directive.");

        var directive = ['ZMConstants', '$window', '$timeout', function (c, $window, $timeout) {
                return {
                    restrict: 'A',
                    scope: {
                        currentAmount: '='
                    },
                    templateUrl: 'js/directives/FigureChart/template.html',
                    link: function ($scope, ele, attrs) {
                        Console.log("Amount Directive called");
                        var calcAmountPieces = function (amount) {
                            var result = [];
                            if (amount) {
                                var times = amount / c.MUG_SIZE;
                                var amount;
                                while (times > 0) {
                                    amount = times > 1 ? 100 : Math.ceil(times * 100);
                                    times = times - 1;
                                    result.push({
                                        img_src_full: attrs.imgFull,
                                        img_src_empty: attrs.imgEmpty,
                                        amount: 100 - amount,
                                        height: 100
                                    });
                                }
                            }
                            return result;
                        }
                        $scope.images = calcAmountPieces(attrs.currentAmount);
                        $scope.$watch(attrs.currentAmount, function (value) {
                            // alert("Amount Changes!");
                            console.log(value);
                            var newAmountPieces = calcAmountPieces(value);
                            var diff = newAmountPieces.length - $scope.images.length;
                            //Add new mug
                            if (diff > 0) {
                                if ($scope.images.length > 0) {
                                    $scope.images[$scope.images.length - 1].amount = 0;
                                }


                                for (var i = diff; i > 0; i--) {
                                    $scope.images.push(newAmountPieces[newAmountPieces.length - i]);
                                }

                            } else if (diff === 0) {       //change only amount
                                if ($scope.images.length > 0) {
                                    $scope.images[$scope.images.length - 1].amount = newAmountPieces[newAmountPieces.length - 1].amount;
                                }
                            } else if (diff < 0) {   //remove mugs
                                if ($scope.images.length > 0) {
                                    $scope.images.splice(diff,-diff);
                                    $scope.images[$scope.images.length - 1].amount = newAmountPieces[newAmountPieces.length - 1].amount;
                                }
                            }
                        });


                    }
                }

            }
            ]
            ;

        Console.groupEnd();
        return directive;
    }
)
;
