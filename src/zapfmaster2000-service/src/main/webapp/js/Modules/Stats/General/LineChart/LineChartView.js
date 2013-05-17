/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.lineChartView = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var container = null,chart = null,slideContainer = null;
	var init = function(cont){
		container =cont;
	};
	var plotOptions = {
			 grid: {
	        	    drawGridLines: true,        // wether to draw lines across
												// the grid or not.
	        	        gridLineColor: 'rgba(255,255,255,0.3)',   // CSS color
																	// spec of
																	// the grid
																	// lines.
	        	        background: 'rgba(255,255,255,0)',      // CSS color
																// spec for
																// background
																// color of
																// grid.
	        	        borderColor: 'rgba(255,255,255,0)',     // CSS color
																// spec for
																// border around
																// grid.
	        	        borderWidth: 2.0,           // pixel width of border
													// around grid.
	        	        shadow: true,               // draw a shadow for grid.
	        	        shadowAngle: 45,            // angle of the shadow.
													// Clockwise from x axis.
	        	        shadowOffset: 1.5,          // offset from the line of
													// the shadow.
	        	        shadowWidth: 3,             // width of the stroke for
													// the shadow.
	        	        shadowDepth: 3
	        	}, 
	        	seriesColors: ["#dddf0d","#90b1d8",  "#c5b47f", "#EAA228", "#579575", "#839557", "#958c12",
	        	                 "#953579", "#4b5de4", "#d8b83f", "#ff5800", "#0085cc"],
	};
	var calcProgress = function(timeParser,arr,interval,isTimestamp){
		var retArr = [];
		$.each(arr,function(ind,val){
			isTimestamp
					?retArr.push([timeParser.getChartTimeAddMins(ind*interval,true),val])
					:retArr.push([timeParser.getChartTimeAddMins(ind*interval),val]);
		});
		return retArr;
	};
	var createLineChart = function(progressModel){
		var id =  container.attr("id");
		container.css({
			height:"150px",
			
		});
		if(!container.children().length==0){
			updateChart(progressModel)
		}else{
		var progressStats= progressModel;
		var progress = progressStats.data;
		var interval = progressStats.interval;
		var startDate = new ZMO.TimeParser(progressStats.start_date);
		var datas = calcProgress(startDate,progress,interval);

		chart = $.jqplot(id, [datas],$.extend( {
				animateReplot: true,
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
// formatString:'%.2f'
		            	   formatString:'%.0f'
		                 },
// min:0,
		                 label:'Liter',
		                 labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
		                 labelOptions: {
		                     textColor: '#ccc',
		                     fontSize: '12pt'
		                   }
		             }
		           },
		          
		           cursor: {
		             show: false
		           },
		           highlighter: {
		               show: true, 
		               showLabel: true, 
		               tooltipAxes: 'y',
		               sizeAdjust: 7.5 ,
		               tooltipLocation : 'ne'
		           }
		       },plotOptions));
		}
	};
	var updateChart = function(progressModel){
		var progressStats= progressModel;
		var progress = progressStats.data;
		var interval = progressStats.interval;
		var startDate = new ZMO.TimeParser(progressStats.start_date);
		var datas = calcProgress(startDate,progress,interval,true);
		
		chart.series[0].data = datas;
		chart.replot( { 
			resetAxes: true, 
//			 axes:{
//				 yaxis:{min:0}
//			 }
		} );
	}

	var sliderSet = false;

	var initSlider = function(sliderContainer,progressModel,sliderValueChanged,timeBounds,defaultTimeBounds){
		
		if(!sliderSet){
			var timeParser = new ZMO.TimeParser(progressModel.start_date);
			sliderSet = true;
			slideContainer = sliderContainer
				.css("margin-top","15px")
				.dateRangeSlider({
					 valueLabels:"change",
					  delayOut: 1000,
					  arrows:false,
					 bounds:timeBounds,
					 defaultValues:defaultTimeBounds,
					 
					  formatter:function(val){
					        var value = Math.round(val * 5) / 5,
					          decimal = value - Math.round(val);
					        timeParser.setDate(new Date(val));
					       // console.log(val);
					        // return decimal == 0 ? value.toString() + ".0" :
							// value.toString();
					        return timeParser.getDefaultTime();
					      }
				}).on("valuesChanged",sliderValueChanged);
		}
	}
	var resetSlider = function(){
//		if(slideContainer){
//			slideContainer.rangeSlider("destroy");
			sliderSet = false;
//			container.children().remove();
//		}else{
//			ZMO.log("Remove Slider Container failed. Slider not set!");
//		}

	}
	var pub = {
			updateChart:updateChart,
			init:init,
			createLineChart:createLineChart,
			initSlider:initSlider,
			resetSlider:resetSlider
	};
	return pub;
}(jQuery,ZMO.ajax));
