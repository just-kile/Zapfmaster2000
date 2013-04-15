/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.bestlistView = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var chartContainer = null,chart = null,bestlistContainer = null;
	var wording = {
			amount:"amount"
	};
	var plotOptions = {
			 grid: {
	        	    drawGridLines: true,        // wether to draw lines across the grid or not.
	        	        gridLineColor: 'rgba(0,0,0,0.2)',   // CSS color spec of the grid lines.
	        	        background: 'rgba(255,255,255,0.2)',      // CSS color spec for background color of grid.
	        	        borderColor: 'rgba(0,0,0,0.2)',     // CSS color spec for border around grid.
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
	var init = function(cont){
		$.each(wording,function(ind,word){
			wording[ind] = ZMO.Util.localization.translateString(word);
		});
	};
	var convertToSeries = function(userlistModel){
		var seriesArr =[];
		try{
			$.each(userlistModel,function(ind,user){
				var username = user.userName;
				var amount = parseFloat(user.amount);
				seriesArr.push([username,amount]);
			});
		}catch(e){
			ZMO.logger.error("Parse Error Beslist!")
		}
		return seriesArr;
		
	};
	var calcBestlist = function(list){
		var arr = [];
		$.each(list,function(ind,user){
			arr.push([user.userName,user.amount]);
		});
		return arr;
		
	}
	var createPieChart = function(userlistModel,container){
		var id = container.attr("id");
		var rankList = userlistModel;
		var data = calcBestlist(rankList);
		$("#"+id).css("height","250px");
//		['Heavy Industry', 12],['Retail', 9], ['Light Industry', 14], 
//	    ['Out of home', 16],['Commuting', 7], ['Orientation', 9]
		jQuery.jqplot (id, [data], 
			    $.extend({ 
			      seriesDefaults: {
			        // Make this a pie chart.
			        renderer: jQuery.jqplot.PieRenderer, 
			        rendererOptions: {
			          // Put data labels on the pie slices.
			          // By default, labels show the percentage of the slice.
			          showDataLabels: true,
			          dataLabels: 'label',
//			          dataLabelFormatString:'%.4f'
			        }
			      }, 
		          
			      legend: { show:false, location: 'e' }
			    }
			  ,plotOptions));

		
		//		chartContainer =container;
//		var datas = convertToSeries(userlistModel);
//		chart = new Highcharts.Chart({
//            chart: {
//                renderTo: chartContainer.attr("id"),
//                plotBackgroundColor: null,
//                plotBorderWidth: null,
//                plotShadow: false,
//                height:200,
//                marginTop:10
//            },
//            title: {
//                text: ''
//            },
//            tooltip: {
//        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
//            	percentageDecimals: 1
//            },
//            plotOptions: {
//                pie: {
//                    allowPointSelect: true,
//                    cursor: 'pointer',
//                    dataLabels: {
//                        enabled: true,
//                        color: '#fff',
//                        connectorColor: '#fff',
//                        distance:0,
//                        formatter: function() {
//                            return '<b>'+ this.point.name +'</b>';//+this.percentage.toFixed(1) +' %';
//                        }
//                    }
//                }
//            },
//            series: [{
//                type: 'pie',
//                name: wording.amount,
//                data: datas
//            }]
//        });
   
	};
	var createBestlist = function(userlistModel,container,unit,headlineText){
		var template = ich["ZMO-stats-bestlist-item"];
		var table = $("<table>").addClass("bestlist-table");
		$.each(userlistModel,function(ind,val){
			val.rank = ind+1;
			val.unit = unit?unit:"l";
			table.append(template(val));
		});
		var headline  =$("<span>").text(headlineText?headlineText:"Bestlist");
		container.append(headline).append(table);
	};
	var updateChart = function(val){
		var series = chart.series[0];
		series.addPoint(val, true, true);
	};
	var pub = {
			updateChart:updateChart,
			init:init,
			createPieChart:createPieChart,
			createBestlist:createBestlist
	};
	return pub;
}(jQuery,ZMO.ajax));
