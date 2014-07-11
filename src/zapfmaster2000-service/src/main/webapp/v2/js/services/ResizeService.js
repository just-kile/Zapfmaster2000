/**
 * Add the following html tags anywhere to the html document in order to use this service!
 <div class="device-xs visible-xs"></div>
 <div class="device-sm visible-sm"></div>
 <div class="device-md visible-md"></div>
 <div class="device-lg visible-lg"></div>

 The idea is inspired from:

 * Responsive Bootstrap Toolkit
 * Author:    Maciej Gurban
 * License:   MIT
 * Version:   1.5.0 (2014-06-04)
 * Origin:    https://github.com/maciej-gurban/responsive-bootstrap-toolkit

 */
define(['Console', 'jQuery', 'Underscore'], function (Console, $, _) {
    "use strict";
    Console.group("Entering Resize Service module.");

    var service = ['ZMConstants', function (c) {
        var callbacks = [], timeString = new Date();
        var waitForFinalEvent = (function () {
            var timers = {};
            return function (callback, ms, uID) {
                //
                var uuID = (!uID) ? "I'm a banana!" : null;
                if (timers[uuID]) {
                    clearTimeout(timers[uuID]);
                }
                timers[uuID] = setTimeout(callback, ms);
            };
        })();

        var isBreakpoint = function (alias) {
            return $('.device-' + alias).is(':visible');
        };
        $(window).bind('resize', function () {
            waitForFinalEvent(function () {
                _.each(callbacks, function (cb) {
                    if (cb){
                        cb();
                    }
                });

            }, 300, timeString.getTime());
        });
        return {
            onResize: function (cb) {
                callbacks.push(cb);
            },
            isBreakpoint: isBreakpoint
        };

    }];

    Console.groupEnd();
    return service;
});
