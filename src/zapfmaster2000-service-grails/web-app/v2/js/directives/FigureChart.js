define(['Console', 'jQuery', 'Underscore'], function (Console, $, _) {
        "use strict";
        Console.group("Entering FigureChart directive.");

        var directive = ['ZMConstants', '$window', '$timeout', '$animate', function (c, $window, $timeout, $animate) {
                return {
                    restrict: 'A',
                    scope: {
                    },
                    templateUrl: 'views/directives/FigureChart.html',
                    link: function ($scope, element, attrs) {
                        Console.log("Amount Directive called");
                        $scope.initialWidth = c.FIGURE_CHART.INITIAL_WIDTH;
                        $scope.unittitle = attrs.unittitle;
                        $scope.unittext = attrs.unittext;
                        $scope.tofixed = attrs.tofixed;
                        var unitSize = attrs.unit;
                        var calcAmountPieces = function (amount) {
                            var result = [];
                            if (amount) {
                                var times = (amount / unitSize ) % c.FIGURE_CHART.MAX_IMAGES_PER_LINE;
                                var newAmount;
                                while (times > 0) {
                                    newAmount = times > 1 ? 100 : Math.ceil(times * 100);
                                    times = times - 1;
                                    result.push({
                                        img_src_full: attrs.imgFull,
                                        img_src_empty: attrs.imgEmpty,
                                        amount: 100 - newAmount,
                                        height: c.FIGURE_CHART.INITIAL_HEIGHT,
                                        width: c.FIGURE_CHART.INITIAL_WIDTH
                                    });
                                }
                            }
                            return result;
                        };

                        function animateElementIn(els, offset, index) {
                            $animate.addClass($(els[offset + index]), 'animated ' + c.FIGURE_CHART.ANIMATION_IN, function () {
                                $timeout(function () {
                                    $animate.removeClass($(els[offset + index]), 'animated ' + c.FIGURE_CHART.ANIMATION_IN);

                                }, 1000);
                            });
                        }

                        function animateElementOut(els, offset, index) {
                            $animate.addClass($(els[offset - index]), 'animated ' + c.FIGURE_CHART.ANIMATION_OUT, function () {
                                $timeout(function () {
                                    var currEl = $(els[offset - index]);
                                    $animate.removeClass(currEl, 'animated ' + c.FIGURE_CHART.ANIMATION_OUT, function () {
                                        currEl.remove();
                                    });
                                }, 2000);
                            });
                        }

                        $scope.images = [];
                        $scope.countimage = {
                            height: c.FIGURE_CHART.INITIAL_HEIGHT + 30,
                            imgsrc: attrs.imgBig,
                            mugcount: 0
                        };
                        $scope.mug_size = unitSize;
                        //Animation
                        $scope.$watch("images", function (newArray, oldArray) {
                            var diff = newArray.length - oldArray.length;
                            var els = element.children().children();
                            var offset, i;
                            if (diff > 0) {
                                offset = oldArray.length + 1;
                                for (i = diff - 1; i >= 0; i--) {
                                    animateElementIn.call(this, els, offset, i);
                                }

                            } else if (diff < 0) {
                                offset = oldArray.length + 1;
                                for (i = -diff; i > 0; i--) {
                                    animateElementOut.call(this, els, offset, i);
                                }
                            }

                        }, true);
                        //real data changing
                        attrs.$observe('amount', function (val) {
                            var value = parseFloat(val);
                            Console.log(' type:', attrs.createControl);
                            $scope.countimage.mugcount = Math.floor(value / unitSize - (value / unitSize) % c.FIGURE_CHART.MAX_IMAGES_PER_LINE);
                            $scope.complete_mug_count = value / unitSize;
                            var newAmountPieces = calcAmountPieces(value);
                            var diff = newAmountPieces.length - $scope.images.length;
                            var newLength = newAmountPieces.length;
                            var oldLength = $scope.images.length;
                            //Add new mug
                            if (diff > 0) {
                                if ($scope.images.length > 0) {
                                    $scope.images[$scope.images.length - 1].amount = 0;
                                }
                                for (var i = diff; i > 0; i--) {
                                    $scope.images.push(newAmountPieces[newLength - i]);
                                }

                            } else if (diff === 0) {       //change only amount
                                if ($scope.images.length > 0) {
                                    $scope.images[$scope.images.length - 1].amount = newAmountPieces[newLength - 1].amount;
                                }
                            } else if (diff < 0) {   //remove mugs
                                if ($scope.images.length > 0 && newAmountPieces.length > 0) {
                                    $scope.images.splice(diff, -diff);
                                    $scope.images[$scope.images.length - 1].amount = newAmountPieces[newLength - 1].amount;
                                    $scope.images[$scope.images.length - 1].height = newAmountPieces[newLength - 1].height;
                                }
                            }


                            //recalculate width
                            if (element) {
                                var width = element.width() - element.children().children(":first").width() - 20;
                                var newWidthPerItem = width / c.FIGURE_CHART.MAX_IMAGES_PER_LINE;

                                if (true || newWidthPerItem < c.FIGURE_CHART.INITIAL_WIDTH) {
                                    _.each($scope.images, function (item, index) {
                                        // item.height = item.height * (newWidthPerItem / item.width);
                                        item.width = newWidthPerItem;
                                    });
                                }
                            }



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
