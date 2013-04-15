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
	var plotOptions = {
			 grid: {
	        	    drawGridLines: true,        // wether to draw lines across the grid or not.
	        	        gridLineColor: 'rgba(255,255,255,0.3)',   // CSS color spec of the grid lines.
	        	        background: 'rgba(255,255,255,0)',      // CSS color spec for background color of grid.
	        	        borderColor: 'rgba(255,255,255,0)',     // CSS color spec for border around grid.
	        	        borderWidth: 2.0,           // pixel width of border around grid.
	        	        shadow: true,               // draw a shadow for grid.
	        	        shadowAngle: 45,            // angle of the shadow.  Clockwise from x axis.
	        	        shadowOffset: 1.5,          // offset from the line of the shadow.
	        	        shadowWidth: 3,             // width of the stroke for the shadow.
	        	        shadowDepth: 3
	        	}, 
	        	seriesColors: ["#dddf0d","#90b1d8",  "#c5b47f", "#EAA228", "#579575", "#839557", "#958c12",
	        	                 "#953579", "#4b5de4", "#d8b83f", "#ff5800", "#0085cc"],
	};
	var calcProgress = function(timeParser,arr,interval){
		var retArr = [];
		$.each(arr,function(ind,val){
			retArr.push([timeParser.getChartTimeAddMins(ind*interval),val]);
		});
		return retArr;
	}
	var createLineChart = function(progressModel){
		var id =  container.attr("id");
		container.css({
			height:"150px",
			
		});
		var progressStats= progressModel;
		var progress = progressStats.data;
		var interval = progressStats.interval;
		var startDate = new ZMO.TimeParser(progressStats.start_date);
		var datas = calcProgress(startDate,progress,interval);
//		var line1=[['23-May-08', 578.55], ['20-Jun-08', 566.5], ['25-Jul-08', 480.88], ['22-Aug-08', 509.84],
//		           ['26-Sep-08', 454.13], ['24-Oct-08', 379.75], ['21-Nov-08', 303], ['26-Dec-08', 308.56],
//		           ['23-Jan-09', 299.14], ['20-Feb-09', 346.51], ['20-Mar-09', 325.99], ['24-Apr-09', 386.15]];
		chart = $.jqplot(id, [datas],$.extend( {
		           title:'Draw Analytics',
		           axes:{
		             xaxis:{
		               renderer:$.jqplot.DateAxisRenderer,
		               tickOptions:{
		                 formatString:'%d.%m&nbsp;%H:%M'
		               } 
		             },
		             yaxis:{
		               tickOptions:{
//		            	   formatString:'%.2f'
		            	   formatString:'%.0f'
		                 },
		                 min:0
		             }
		           },
		          
		           cursor: {
		             show: false
		           }
		       },plotOptions));
//		chart = new Highcharts.Chart({
//            chart: {
//                renderTo: container.attr("id"),
//                type: 'line',
//                marginRight: 130,
//                marginBottom: 25,
//                height:200
//            },
//            title: {
//                text: 'Draw Analytics',
//                x: -20 //center
//            },
//
//            xAxis: {
//                type: 'datetime',
//                tickInterval:  3600 * 1000*5, //  five hours
//                tickWidth: 0,
//                gridLineWidth: 1,
//                labels: {
//                    align: 'left',
//                    x: 3,
//                    y: -3
//                }
//            },
//            yAxis: {
//                title: {
//                    text: 'Liter'
//                },
//                plotLines: [{
//                    value: 0,
//                    width: 1,
//                    color: '#808080'
//                }]
//            },
//            tooltip: {
//                formatter: function() {
//                	   var date = new Date(this.x); //in ms not s
//                        return '<b>'+ this.series.name +'</b><br/>'+
//                         +date.getHours()+":"+date.getMinutes()+"Uhr: "+this.y.toFixed(2) +'L';
//                }
//            },
//            legend: {
//                layout: 'vertical',
//                align: 'right',
//                verticalAlign: 'top',
//                x: -10,
//                y: 100,
//                borderWidth: 0
//            },
//            series: [{
//                name: 'Draw Amount',
//                pointInterval:  progressModel.interval*60*1000,//60*60 * 1000*0.5, //half an hour
//                pointStart: progressModel.start_date,//Date.UTC(2013, 0, 01),
//                data: progressModel.data
//            }]
//        });
   
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
