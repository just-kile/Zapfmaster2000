/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.lineChartView = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var container = null,chart = null;
	var init = function(cont){
		container =cont;
	};
	var createLineChart = function(progressModel){
		chart = new Highcharts.Chart({
            chart: {
                renderTo: container.attr("id"),
                type: 'line',
                marginRight: 130,
                marginBottom: 25,
                height:200
            },
            title: {
                text: 'Draw Analytics',
                x: -20 //center
            },

            xAxis: {
                type: 'datetime',
                tickInterval:  3600 * 1000*5, //  five hours
                tickWidth: 0,
                gridLineWidth: 1,
                labels: {
                    align: 'left',
                    x: 3,
                    y: -3
                },title: {
                    text: 'Datum'
                },
            },
            yAxis: {
                title: {
                    text: 'Liter'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function() {
                	   var date = new Date(this.x); //in ms not s
                        return '<b>'+ this.series.name +'</b><br/>'+
                         +date.getHours()+":"+date.getMinutes()+"Uhr: "+this.y +'L';
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -10,
                y: 100,
                borderWidth: 0
            },
            series: [{
                name: 'Draw Amount',
                pointInterval:  3600 * 1000*0.5, //half an hour
                pointStart: Date.UTC(2013, 0, 01),
                data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6,7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6,7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
            }]
        });
   
	};
	var updateChart = function(val){
		var series = chart.series[0];
		series.addPoint(val, true, true);
	}
	var pub = {
			updateChart:updateChart,
			init:init,
			createLineChart:createLineChart
	};
	return pub;
}(jQuery,ZMO.ajax));
