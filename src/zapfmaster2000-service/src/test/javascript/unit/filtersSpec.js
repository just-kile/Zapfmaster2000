define(["Underscore", "Angular", "angular-mocks", "filters/filters"],
    function (_, angular, mocks, filters) {
        describe("filters testing", function () {
            var subject, mod;

            beforeEach(function () {
                window.debug.setLevel(0);
                mod = angular.module("myApp", []);
                mod.constant('ZMConstants', {});
                filters.initialize(mod);
                module("myApp");
            });

            describe('achievement filter tests', function () {
                var subject;
                beforeEach(function () {
                    inject(function ($filter) {
                        //scope = $rootScope;
                        subject = $filter("achievement");
                    });
                });
                it('should be defined', function () {
                    expect(subject).toBeDefined();
                });

                it('should return a string', function () {
                    expect(subject(undefined)).toEqual('');
                    expect(subject(0)).toEqual('0');
                    expect(subject(3)).toEqual('3');
                    expect(subject("foo")).toEqual('foo');

                });
            });
            describe('amount filter tests', function () {
                var subject, FULL = "full";
                beforeEach(function () {
                    inject(function ($filter) {
                        //scope = $rootScope;
                        subject = $filter("amount");
                    });
                });
                it('should be defined', function () {
                    expect(subject).toBeDefined();
                });

                it('should round to integer number for full mode', function () {
                    expect(subject(undefined, FULL)).toEqual(0);
                    expect(subject(null, FULL)).toEqual(0);
                    expect(subject("foo", FULL)).toEqual(0);
                    expect(subject("2.4m²", FULL)).toEqual(2);

                    expect(subject(0.3, FULL)).toEqual(0);
                    expect(subject(0.51, FULL)).toEqual(1);

                });
                it('should cut number to fixed 2 by default', function () {
                    expect(subject()).toEqual("0.00");
                    expect(subject(null)).toEqual("0.00");
                    expect(subject("foo")).toEqual("0.00");
                    expect(subject("2.404214m²")).toEqual("2.40");

                    expect(subject(0.333333333)).toEqual("0.33");
                    expect(subject(1.566666)).toEqual("1.57");

                });
                it('should cut number to fixed n by given n', function () {
                    var n = 1;
                    expect(subject(undefined, n)).toEqual("0.0");
                    expect(subject(null, n)).toEqual("0.0");
                    expect(subject("foo", n)).toEqual("0.0");
                    expect(subject("2.404214m²", n)).toEqual("2.4");

                    expect(subject(0.333333333, n)).toEqual("0.3");
                    expect(subject(1.566666, n)).toEqual("1.6");

                });

            });

        });

    });

