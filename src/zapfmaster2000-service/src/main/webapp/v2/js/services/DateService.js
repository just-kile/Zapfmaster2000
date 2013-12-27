define(['Console','moment'], function (Console,moment) {
    "use strict";
    Console.group("Entering DateService module.");

    var service = ['ZMConstants', function (c) {
        var parseClientDate = function(dateString){
             if(typeof dateString ==="number"){
                 return moment(dateString);
             }else{
                 return moment(dateString, c.SERVER_TIME_FORMAT);
             }
        }
        var calcDiffToNow = function(string,duration,format){
            var endDate = parseClientDate(string).add("m",duration);
            var ms = endDate.diff(moment());
            if(ms>0){
                return ms;
            } else{
                return 0;
            }

        }

        return {
            parseClientDate:parseClientDate,
            calcDiffToNow:calcDiffToNow
        }

    }];

    Console.groupEnd();
    return service;
});
