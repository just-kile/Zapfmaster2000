define(['Console', 'jQuery'], function (Console, $) {
        "use strict";
        Console.group("Entering FigureChart directive.");

        var directive = ['ZMConstants', '$window', '$timeout', '$animate', function (c, $window, $timeout, $animate) {
                return {
                    restrict: 'A',
                    scope: {
                        currentAmount: '='
                    },
                    templateUrl: 'js/directives/FigureChart/template.html',
                    link: function ($scope, element, attrs) {
                        Console.log("Amount Directive called");
                        $scope.initialWidth = c.FIGURE_CHART.INITIAL_WIDTH;
                        var calcAmountPieces = function (amount) {
                            var result = [];
                            if (amount) {
                                var times = (amount / c.MUG_SIZE ) % c.FIGURE_CHART.MAX_IMAGES_PER_LINE;
                                var amount;
                                while (times > 0) {
                                    amount = times > 1 ? 100 : Math.ceil(times * 100);
                                    times = times - 1;
                                    result.push({
                                        img_src_full: attrs.imgFull,
                                        img_src_empty: attrs.imgEmpty,
                                        amount: 100 - amount,
                                        height: c.FIGURE_CHART.INITIAL_HEIGHT,
                                        width: c.FIGURE_CHART.INITIAL_WIDTH
                                    });
                                }
                            }
                            return result;
                        }
                        $scope.images = [];
                        $scope.countimage = {
                            height: c.FIGURE_CHART.INITIAL_HEIGHT + 30,
                            imgsrc: attrs.imgBig,
                            mugcount:0
                        };
                        $scope.mug_size = c.MUG_SIZE;
                        //Animation
                        $scope.$watch("images", function (newArray, oldArray) {
                            var diff = newArray.length - oldArray.length;
                            var els = element.children().children();
                            if (diff > 0) {
                                // $timeout(function () {//workaround: image width is not set fast enough
                                var offset = oldArray.length + 1;
                                for (var i = diff - 1; i >= 0; i--) {

                                    (function (index) {
                                        $animate.addClass($(els[offset + index]), 'animated ' + c.FIGURE_CHART.ANIMATION_IN, function () {
                                            $timeout(function () {
                                                $animate.removeClass($(els[offset + index]), 'animated ' + c.FIGURE_CHART.ANIMATION_IN);

                                            }, 1000);
                                        });
                                    })(i);

                                }
                                //  }, 0)

                            } else if (diff < 0) {
                                var offset = oldArray.length + 1;
                                for (var i = -diff; i > 0; i--) {
                                    (function (index) {
                                        $animate.addClass($(els[offset - index]), 'animated ' + c.FIGURE_CHART.ANIMATION_OUT, function () {
                                            $timeout(function () {
                                                var currEl = $(els[offset - index]);
                                                $animate.removeClass(currEl, 'animated ' + c.FIGURE_CHART.ANIMATION_OUT, function () {
                                                    currEl.remove();
                                                })
                                            }, 2000);
                                        });
                                    })(i);

                                }
                            }

                        }, true);
                        //real data changing

                        $scope.$watch(attrs.currentAmount, function (value) {
                            // alert("Amount Changes!");
                            console.log(value);
                            $scope.countimage.mugcount = Math.floor(value/c.MUG_SIZE - (value/ c.MUG_SIZE) % c.FIGURE_CHART.MAX_IMAGES_PER_LINE);
                            $scope.complete_mug_count=(Math.round(value/ c.MUG_SIZE*100)*0.01).toFixed(2);
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
                                var newWidthPerItem = width / newLength;
                                if (newWidthPerItem < c.FIGURE_CHART.INITIAL_WIDTH) {
                                    _.each($scope.images, function (item, index) {
                                        item.height = item.height * (newWidthPerItem / item.width);
                                        item.width = newWidthPerItem;
                                    });
                                }
                            }
                            //change height
                            /* var width = element.width();
                             var height = element.height();
                             if(newLength> c.FIGURE_CHART.MIN_IMAGES && newLength< c.FIGURE_CHART.MAX_IMAGES_PER_LINE){
                             var newWidthPerItem = width/newLength;
                             _.each($scope.images, function (item,index) {
                             item.height = item.height*(newWidthPerItem/item.width);
                             item.width =newWidthPerItem;


                             });
                             }else if(newLength>= c.FIGURE_CHART.MAX_IMAGES_PER_LINE
                             && newLength- c.FIGURE_CHART.MAX_LINES*c.FIGURE_CHART.MAX_IMAGES_PER_LINE<0){

                             var newWidthPerItem = width/c.FIGURE_CHART.MAX_IMAGES_PER_LINE;
                             _.each($scope.images, function (item,index) {
                             item.height = item.height*(newWidthPerItem/item.width);
                             item.width =newWidthPerItem;
                             });
                             }else if(newLength>= c.FIGURE_CHART.MAX_IMAGES_PER_LINE){
                             var newWidthPerItem = width/newLength*c.FIGURE_CHART.MAX_LINES;
                             _.each($scope.images, function (item,index) {
                             item.height = item.height*(newWidthPerItem/item.width);
                             item.width =newWidthPerItem;
                             });
                             }   */

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
