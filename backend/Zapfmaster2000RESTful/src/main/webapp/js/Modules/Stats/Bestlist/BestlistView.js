/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.bestlistView = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var container = null,chart = null;
	var init = function(cont){
		container =cont;
	};
	var createPieChart = function(progressModel){
		chart = new Highcharts.Chart({
            chart: {
                renderTo: container.attr("id"),
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                height:200,
                marginTop:10
            },
            title: {
                text: ''
            },
            tooltip: {
        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
            	percentageDecimals: 1
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        color: '#fff',
                        connectorColor: '#fff',
                        //distance:2,
                        formatter: function() {
                            return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
                        }
                    }
                }
            },
            series: [{
                type: 'pie',
                name: 'Browser share',
                data: [
                    ['Firefox',   45.0],
                    ['IE',       26.8],
                    {
                        name: 'Chrome',
                        y: 12.8,
                        sliced: true,
                        selected: true
                    },
                    ['Safari',    8.5],
                    ['Opera',     6.2],
                    ['Others',   0.7]
                ]
            }]
        });
   
	};
	var updateChart = function(val){
		var series = chart.series[0];
		series.addPoint(val, true, true);
	};
	var pub = {
			updateChart:updateChart,
			init:init,
			createPieChart:createPieChart
	};
	return pub;
}(jQuery,ZMO.ajax));
