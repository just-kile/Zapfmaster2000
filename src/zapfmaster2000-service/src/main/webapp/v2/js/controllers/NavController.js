define([
    'Console',
    'jQuery',
    'Underscore',
    'routes/routes',
    'text!../../nav.json'
], function (Console, $, _, routes, navItems) {
    "use strict";
    if (typeof navItems === "string") {
        navItems = JSON.parse(navItems);
    }
    var appController = ['$scope', '$location', '$rootScope', '$timeout', 'CometService',
        function ($scope, $location, $rootScope, $timeout, cometService) {
            function shouldShowNav() {
                return  !/presentation/.test($location.path().substring(1));
            }

            function prepareNavItems(items) {
                _.each(items, function (item) {
                    item.onClick = load(item.url);
                    item.navClass = navClass(item.url);
                });
                return items;
            }

            $scope.navItems = prepareNavItems(navItems);


            $rootScope.$on('$routeChangeSuccess', function (next, last) {
                if (shouldShowNav()) {
                    $timeout(function () {
                        $scope.showNav = true;
                    }, 500);
                } else {
                    $scope.showNav = false;
                }


            });
            function navClass(page) {
                return function () {
                    var currentRoute = $location.path().substring(1) || 'home';
                    return page === currentRoute ? 'active' : '';
                };

            }

            function load(topic) {
                return function () {
                    // $("#zm-navbar").collapse("hide");
                    $location.url('/' + topic);
                };
            }

            function onChallengeReceive(challenge) {
                $rootScope.$broadcast("zm-challenge.receive",challenge);
            }

            cometService.addChallengePushListener(onChallengeReceive);
        }];


    return appController;
});
