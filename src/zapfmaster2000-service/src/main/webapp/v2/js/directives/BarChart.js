define(['Console', 'd3'], function (Console, d3) {
        "use strict";
        Console.group("Entering BarChart directive.");

        var directive = ['ZMConstants','$window','$timeout', function (c,$window,$timeout) {
                return {
                    restrict: 'A',
                    scope: {
                        data: '=',
                        label: '@',
                        onClick: '&'
                    },
                  //  templateUrl: 'js/directives/BarChart/template.html',
                    link: function ($scope, ele, attrs) {
                        var renderTimeout;
                        var margin = parseInt(attrs.margin) || 20,
                            barHeight = parseInt(attrs.barHeight) || 20,
                            barPadding = parseInt(attrs.barPadding) || 5;

                        var svg = d3.select(ele[0])
                            .append('svg')
                            .style('width', '100%');

                        ele.bind("$destroy", function() {
                        //    svg.exit().remove();
                        });
                        $window.onresize = function () {
                            $scope.$apply();
                        };

                        $scope.$watch(function () {
                            return angular.element($window)[0].innerWidth;
                        }, function () {
                            $scope.render($scope.data);
                        });

                        $scope.$watch('data', function (newData) {
                            $scope.update(newData);
                        }, true);
                        $scope.update = function(data){
                            var text = svg.selectAll("text")
                                .data(data, function(d) { return d; });

                        }
                        $scope.render = function (data) {
                            svg.selectAll('*').remove();

                            if (!data) return;
                            //if (renderTimeout) clearTimeout(renderTimeout);

                           // renderTimeout = $timeout(function () {
                                var width = d3.select(ele[0])[0][0].offsetWidth - margin,
                                    height = $scope.data.length * (barHeight + barPadding),
                                    color = d3.scale.category20(),
                                    xScale = d3.scale.linear()
                                        .domain([0, d3.max(data, function (d) {
                                            return d.score;
                                        })])
                                        .range([0, width]);

                                svg.attr('height', height);

                                svg.selectAll('rect')
                                    .data(data)
                                    .enter()
                                    .append('rect')
                                    .attr('height', barHeight)
                                    .attr('width', 140)
                                    .attr('x', Math.round(margin / 2))
                                    .attr('y', function (d, i) {
                                        return i * (barHeight + barPadding);
                                    })
                                    .attr('fill', function (d) {
                                        return color(d.score);
                                    })
                                    .transition()
                                    .duration(1000)
                                    .attr('width', function (d) {
                                        return xScale(d.score);
                                    });
                                svg.selectAll('text')
                                    .data(data)
                                    .enter()
                                    .append('text')
                                    .attr('fill', '#fff')
                                    .attr('y', function (d, i) {
                                        return i * (barHeight + barPadding) + 15;
                                    })
                                    .attr('x', 15)
                                    .text(function (d) {
                                        return d.name + " (scored: " + d.score + ")";
                                    });

                        };
                    }
                }

            }
            ]
            ;

        Console.groupEnd();
        return directive;
    }
)
;
