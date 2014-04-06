define(['Console',"Underscore"], function (Console,_) {
    "use strict";
    Console.group("Entering CountdownFiler module.");

    var service = ['ZMConstants', function (c) {
        return function(input,expression) {
            if(input && (input.length> (expression||c.newsStackLength))){
                input.length=expression||c.newsStackLength;
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
