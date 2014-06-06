define([
    'Console',
    'jQuery',
    'routes/routes'
], function (Console, $, routes) {
    "use strict";

    var appController = ['$scope', '$location', '$rootScope', function ($scope, $location, $rootScope) {
        function shouldShowNav() {
            return  !/presentation/.test($location.path().substring(1));
        }

        $rootScope.$on('$routeChangeSuccess', function (next, last) {
            $scope.showNav = shouldShowNav();
            //$(".navbar.navbar-inverse.navbar-fixed-top.zm-navbar").collapse("hide");
        });
        $scope.navClass = function (page) {
            var currentRoute = $location.path().substring(1) || 'home';
            return page === currentRoute ? 'active' : '';
        };

        $scope.load = function (topic) {
            $location.url('/' + topic);
        };

        $scope.logout = function () {
            $location.url('../');
        };

    }];


    return appController;
});
