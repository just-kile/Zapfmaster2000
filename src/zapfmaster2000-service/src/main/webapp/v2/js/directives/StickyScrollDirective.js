define(['Console', 'jQuery'], function (Console, $) {
        "use strict";
        Console.group("Entering StickyScroll directive.");

        var directive = ['$animate', '$timeout', 'ZMConstants', 'ResizeService' ,
                function ($animate, $timeout, c, resizeService) {
                    return {
                        restrict: 'A',
                        link: function ($scope, ele, attrs) {
                            var initTop, newTop, initWidth, height, EPS = 10,
                                lastScrollTop,
                                scrollTop, windowHeight, eleOffsetTop,betweenFlag = false,absTop;

                            function onScroll(e) {
                                if (!height || height < EPS) {
                                    height = ele.height();
                                }
                                windowHeight = $(window).height();
                                scrollTop = $(window).scrollTop();
                                eleOffsetTop = ele.offset().top;
                                newTop = initTop>eleOffsetTop?initTop:eleOffsetTop;
                                var isNearDocumentTop = newTop <= initTop;
                                var isOverTop = newTop < scrollTop;
                                var isOverBottom = newTop + height < scrollTop + windowHeight;
                                var isBackward = lastScrollTop > scrollTop;
                                if (isOverTop && !isOverBottom && isBackward) {
                                    if(!betweenFlag){
                                        absTop =(scrollTop +windowHeight - height-initTop);
                                    }
                                    ele.css({
                                        position: "absolute",
                                        top: absTop+"px",
                                        width: initWidth + "px"
                                    });
                                    betweenFlag =true;
                                } else if ((isOverTop && isBackward) ||((isOverBottom || isOverTop)&& !isNearDocumentTop )) {
                                    ele.css({
                                        "position": "fixed",
                                        "top": windowHeight - height,
                                        width: initWidth + "px"
                                    });
                                    betweenFlag = false;
                                } else {
                                    ele.css({
                                        "position": "static",
                                        "width": "auto",
                                        "top": "auto"
                                    });
                                    initWidth = ele.width();
                                    betweenFlag = false;
                                }
                                lastScrollTop = scrollTop;
                            }

                            function activate() {
                                initTop = ele.offset().top;
                                height = ele.height();
                                initWidth = ele.width();
                                $(window).on("scroll", onScroll);
                            }

                            function deactivate() {
                                $(window).off("scroll", onScroll);
                            }


                            function onResize() {
                                if (resizeService.isBreakpoint("xs")) {
                                    deactivate();
                                } else {
                                    activate();
                                }
                            }

                            resizeService.onResize(onResize);
                            if (!resizeService.isBreakpoint("xs")) {
                                activate();
                            }
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
