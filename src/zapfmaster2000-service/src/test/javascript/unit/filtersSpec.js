define(["Console", "Underscore", "Angular", "angular-mocks", "filters/filters", "constants"],
    function (Console, _, angular, mocks, filters, c) {
        "use strict";

        describe("filters testing: ", function () {
            var mod;

            beforeEach(function () {
                Console.setLevel(0);
                mod = angular.module("myApp", []);
                mod.constant("ZMConstants", c);
                filters.initialize(mod);
                module("myApp");
            });

            describe("achievement filter", function () {
                var filter;
                beforeEach(function () {
                    inject(function ($filter) {
                        //scope = $rootScope;
                        filter = $filter("achievement");
                    });
                });
                it("should be defined", function () {
                    expect(filter).toBeDefined();
                    Console.log("test");
                });

                it("should return a string", function () {
                    expect(filter(undefined)).toEqual("");
                    expect(filter(0)).toEqual("0");
                    expect(filter(3)).toEqual("3");
                    expect(filter("foo")).toEqual("foo");

                });
            });
            describe("amount filter", function () {
                var filter, FULL = "full";
                beforeEach(function () {
                    inject(function ($filter) {
                        //scope = $rootScope;
                        filter = $filter("amount");
                    });
                });
                it("should be defined", function () {
                    expect(filter).toBeDefined();
                });

                it("should round to integer number for full mode", function () {
                    expect(filter(undefined, FULL)).toEqual(0);
                    expect(filter(null, FULL)).toEqual(0);
                    expect(filter("foo", FULL)).toEqual(0);
                    expect(filter("2.4m²", FULL)).toEqual(2);

                    expect(filter(0.3, FULL)).toEqual(0);
                    expect(filter(0.51, FULL)).toEqual(1);

                });
                it("should cut number to fixed 2 by default", function () {
                    expect(filter()).toEqual("0.00");
                    expect(filter(null)).toEqual("0.00");
                    expect(filter("foo")).toEqual("0.00");
                    expect(filter("2.404214m²")).toEqual("2.40");

                    expect(filter(0.333333333)).toEqual("0.33");
                    expect(filter(1.566666)).toEqual("1.57");

                });
                it("should cut number to fixed n by given n", function () {
                    var n = 1;
                    expect(filter(undefined, n)).toEqual("0.0");
                    expect(filter(null, n)).toEqual("0.0");
                    expect(filter("foo", n)).toEqual("0.0");
                    expect(filter("2.404214m²", n)).toEqual("2.4");

                    expect(filter(0.333333333, n)).toEqual("0.3");
                    expect(filter(1.566666, n)).toEqual("1.6");

                });
                it("should not cut number for false n", function () {
                    var n = "foo";
                    expect(filter(undefined, n)).toEqual("0");
                    expect(filter(null, n)).toEqual("0");
                    expect(filter("foo", n)).toEqual("0");
                    expect(filter("2.404214m²", n)).toEqual("2");

                    expect(filter(0.333333333, n)).toEqual("0");
                    expect(filter(1.566666, n)).toEqual("2");

                });

            });
            describe("client date filter", function () {
                var filter;
                beforeEach(function () {
                    inject(function ($filter) {
                        //scope = $rootScope;
                        filter = $filter("clientdate");
                    });
                });
                it("should be defined", function () {
                    expect(filter).toBeDefined();
                });

                it("should return client date", function () {
                    expect(filter(undefined)).toEqual("");
                    expect(filter(0)).toEqual("");
                    expect(filter("")).toEqual("");
                    expect(filter("foo")).toEqual("Invalid date");

                    expect(filter("20140505 22:22")).toEqual("22:22");
                    expect(filter("12", "hour")).toEqual("12:00");
                    expect(filter("20140505 12:12", "full")).toEqual("12:12");


                });
            });
            describe("countdown filter", function () {
                var filter;
                beforeEach(function () {
                    inject(function ($filter) {
                        //scope = $rootScope;
                        filter = $filter("countdown");
                    });
                });
                it("should be defined", function () {
                    expect(filter).toBeDefined();
                });

                it("should return countdown date in format mm:ss with input in ms", function () {
                    expect(filter(undefined)).toEqual("");
                    expect(filter("")).toEqual("");
                    expect(filter(0)).toEqual("00:00");
                    expect(filter("foo")).toEqual("foo");
                    expect(filter(600000)).toEqual("10:00");

                });
            });
            describe("image big filter", function () {
                var filter;
                beforeEach(function () {
                    inject(function ($filter) {
                        //scope = $rootScope;
                        filter = $filter("big");
                    });
                });
                it("should be defined", function () {
                    expect(filter).toBeDefined();
                });

                it("should return big image path", function () {
                    expect(filter()).toEqual(undefined);
                    expect(filter("")).toEqual("/big");
                    expect(filter(0)).toEqual("0/big");
                    expect(filter("0")).toEqual("0/big");
                    expect(filter("foo/1/image")).toEqual("foo/1/image/big");
                    expect(filter("images/wuff.png")).toEqual("images/wuff.png");
                });
            });
            describe("max count filter", function () {
                var filter;
                beforeEach(function () {
                    inject(function ($filter) {
                        //scope = $rootScope;
                        filter = $filter("maxcount");
                    });
                });
                it("should be defined", function () {
                    expect(filter).toBeDefined();
                });

                it("should cut length of array", function () {
                    expect(filter()).toEqual(undefined);
                    expect(filter("")).toEqual("");
                    expect(filter(0)).toEqual(0);
                    expect(filter("0")).toEqual("0");
                    expect(filter([])).toEqual([]);
                    expect(filter([1, 2, 3])).toEqual([1, 2, 3]);
                    expect(filter([1, 2, 3], 2)).toEqual([1, 2]);
                    expect(filter([1, 2, 3], 4)).toEqual([1, 2, 3]);
                    // expect(filter("images/wuff.png")).toEqual("images/wuff.png");
                });
            });

            describe("rank filter", function () {
                var filter, $translate;
                beforeEach(function () {
                    module(function ($provide) {
                        $translate = {use:function(){return "en";}};
                        $provide.value('$translate', $translate);
                    });

                    inject(function ($filter) {
                        //scope = $rootScope;
                        filter = $filter("rank");

                    });
                });
                it("should be defined", function () {
                    expect(filter).toBeDefined();
                });

                it("should show right rank string for lang de", function () {
                    spyOn($translate,"use").andReturn("de");

                    expect(filter()).toEqual(undefined);
                    expect(filter("")).toEqual("");
                    expect(filter("-1")).toEqual("-1.");
                    expect(filter(2)).toEqual("2.");
                    expect(filter("12")).toEqual("12.");
                });
                it("should show right rank string for lang de", function () {
                    spyOn($translate,"use").andReturn("en");

                    expect(filter()).toEqual(undefined);
                    expect(filter("")).toEqual("");
                    expect(filter("1")).toEqual("1st");
                    expect(filter(2)).toEqual("2nd");
                    expect(filter("3")).toEqual("3rd");
                    expect(filter("4")).toEqual("4th");
                    expect(filter("44")).toEqual("44th");
                });
            });


        });

    });

