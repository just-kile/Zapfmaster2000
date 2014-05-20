/*define([
 'angular-mocks',
 'Source/modules/home/home-ctrl'
 ], function () {
 describe('HomeController in app.home', function () {

 var scope, subject;

 beforeEach(function () {

 module('app.home');

 inject(function ($rootScope, $controller) {
 scope = $rootScope.$new();
 subject = $controller('HomeController', { $scope: scope });
 });
 });

 describe('check if controller is on it\'s place', function () {
 it('should have loaded the subject', function () {
 expect(subject).toBeDefined();
 });
 });

 describe('check if scope is also on it\'s place', function () {
 it('should test scope to be defined', function () {
 expect(scope).toBeDefined();
 });
 });

 });
 });*/

describe('just checking', function () {
    beforeEach(function () {

    });
    describe('a test suite', function () {
        it('works for app', function () {

            expect(_.size([1, 2, 3])).toEqual(3);

        });


        it('works for underscore', function () {
            // just checking that _ works
            expect(_.size([1, 2, 3])).toEqual(3);
        });
    });


});
