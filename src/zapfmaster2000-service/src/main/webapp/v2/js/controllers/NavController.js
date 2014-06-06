define([
    'Console',
    'jQuery',
    'routes/routes'
], function (Console, $, routes) {
    "use strict";

    var appController = ['$scope', '$location', '$rootScope','$timeout', function ($scope, $location, $rootScope,$timeout) {
        function shouldShowNav() {
            return  !/presentation/.test($location.path().substring(1));
        }
        function tmp(){
            $timeout(function(){
                $scope.showNav = !$scope.showNav;
                tmp();
            },1500);

        }
        $rootScope.$on('$routeChangeSuccess', function (next, last) {
            if(shouldShowNav()){
                $timeout(function(){
                    $scope.showNav =true;
                },500);
            }else{
                $scope.showNav =false;
            }


        });
        $scope.navClass = function (page) {
            var currentRoute = $location.path().substring(1) || 'home';
            return page === currentRoute ? 'active' : '';
        };

        $scope.load = function (topic) {
            $("#zm-navbar").collapse("hide");
            $location.url('/' + topic);
        };

        $scope.logout = function () {
            $location.url('../');
        };

    }];


    return appController;
});
