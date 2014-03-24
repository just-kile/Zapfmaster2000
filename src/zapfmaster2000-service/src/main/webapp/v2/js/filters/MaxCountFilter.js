define(['Console',"Underscore"], function (Console,_) {
    "use strict";
    Console.group("Entering CountdownFiler module.");

    var service = ['ZMConstants', function (c) {
        return function(input) {
            if(input.length> c.newsFeedLength){
                input.length=c.newsFeedLength;
            }

              return input;
          /* return _.filter(input,function(val,index){
               return index<2;
           });   */

        };

    }];

    Console.groupEnd();
    return service;
});
