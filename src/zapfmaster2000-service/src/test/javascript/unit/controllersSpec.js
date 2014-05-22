define(["Underscore", "Angular", "angular-mocks", "filters/filters"],
    function (_, angular, mocks, filters) {
        describe('achievement Filter', function () {
            var scope,subject,mod;

            beforeEach(function(){

            });
            beforeEach(function(){
                mod =   angular.module("myApp",[]);
                mod.constant('ZMConstants', {});
                filters.initialize(mod);

                module("myApp");
                inject(function($filter){
                    //scope = $rootScope;
                    subject = $filter("achievement");
                });

            });

            it('works for app', function () {

                expect(subject(3)).toEqual('3');


            });


            it('works for underscore', function () {
                // just checking that _ works
                expect(_.size([1, 2, 3])).toEqual(3);
            });

        });

    });

