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
                }
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
                pointInterval:  progressModel.interval*60*1000,//60*60 * 1000*0.5, //half an hour
                pointStart: progressModel.start_date,//Date.UTC(2013, 0, 01),
                data: progressModel.data
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
			createLineChart:createLineChart
	};
	return pub;
}(jQuery,ZMO.ajax));
