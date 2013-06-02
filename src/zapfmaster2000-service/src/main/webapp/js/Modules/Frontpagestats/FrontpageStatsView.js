/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.frontPageStatsView = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var wording = {
			REMAINING:"remaining",
			COMPLETE:"complete",
			TITLE:"kegOverview",
			XBAR:"Liter",
			KEG:"kegNumber"
			
		};
	var plotOptions = {
			 grid: {
	        	    drawGridLines: true,        // wether to draw lines across the grid or not.
	        	        gridLineColor: 'rgba(255,255,255,0.3)',   // CSS color spec of the grid lines.
	        	        background: 'rgba(255,255,255,0)',      // CSS color spec for background color of grid.
	        	        borderColor: 'rgba(0,0,0,0)',     // CSS color spec for border around grid.
	        	        borderWidth: 2.0,           // pixel width of border around grid.
	        	        shadow: true,               // draw a shadow for grid.
	        	        shadowAngle: 0,            // angle of the shadow.  Clockwise from x axis.
	        	        shadowOffset: 1,          // offset from the line of the shadow.
	        	        shadowWidth: 1,             // width of the stroke for the shadow.
	        	        shadowDepth: 1
	        	}, 
	        	seriesColors: ["#90b1d8","#dddf0d",  "#c5b47f", "#EAA228", "#579575", "#839557", "#958c12",
	        	                 "#953579", "#4b5de4", "#d8b83f", "#ff5800", "#0085cc"],
	};
	
	var init = function(cont){
		$.each(wording,function(ind,word){
			wording[ind] = ZMO.Util.localization.translateString(word);
		});
	};
	/**
	 * Provides further information about keg
	 */
	var keglistMod ={};
	var keglist = function(keglistModelOrId){
		if(typeof keglistModelOrId =="string" ||typeof keglistModelOrId =="number" ){
			return keglistMod[keglistModelOrId];
		}else if(typeof keglistModelOrId == "object"){
			keglistMod[keglistModelOrId.boxId] = keglistModelOrId;
		}else{
			return keglistMod;
		}
	};
	var convertToSeries = function(keg,amountModel){
		var seriesObj =[];
		seriesObj.categories=[];
		seriesObj.stack= [];
		var remaining =[],complete = [];
		//set keglist
		keglist(keg);
		try{
				//var kegName = keg.brand;
				var amount = parseFloat(keg.current_amount);
				var complAmount = parseFloat(keg.size);
				seriesObj.categories.push( keg.boxId);
				//seriesObj.stack.push(keg.brand);
				remaining.push(complAmount-amount);
				complete.push( amount);
			
		}catch(e){
			ZMO.logger.error("Parse Error Kegstatus!");
		}
		seriesObj.series=[{name:wording.COMPLETE,data:complete,showInLegend:false},{name:wording.REMAINING,data:remaining,showInLegend:false}];
		
		return seriesObj;
		
	};
	var convertAmountModelToSeries = function(amountModel){
		
	};
	var updateAmountChart = function(){
		
	};
	var updateChart = function(kegModel,chart,titleText,isGeneral){
		var actAmount =  parseFloat(kegModel.current_amount);
		var completeDiff =  parseFloat(kegModel.size)-actAmount;
		if(isGeneral){
			chart.series[1].data = [[actAmount,1]];
			chart.series[0].data = [[completeDiff,1]];
		}else{
			chart.series[0].data = [[actAmount,1]];
			chart.series[1].data = [[completeDiff,1]];
		}
		
		chart.replot( { resetAxes: true } );    
//		chart.series[0].data[0].update(actAmount,false);
//		chart.series[1].data[0].update(completeDiff);

//		if(titleText)chart.setTitle({
//			text:titleText
//		});
	};
	var timeout = null;
	var times = 20;// = animation steps
	var animationTimeout = 10;
	var oldAmount = 0;
	var count = 0;
	var updateChartUser = function(kegModel,chart,titleText){
		count = 0;
		var actAmount =  parseFloat(kegModel.current_amount);
		//
		clearTimeout(timeout);
		
		animateChart(actAmount,parseFloat(kegModel.size),chart);
	}
	var animateChart = function(finalAmount,kegSize,chart){
		timeout = setTimeout(function(){
			count++;
			var finalAmountDiff = finalAmount - oldAmount;
			var newAmount = oldAmount + count/times*finalAmountDiff;
			var completeDiff = kegSize-newAmount;
			chart.series[0].data = [[newAmount,1]];
			chart.series[1].data = [[completeDiff,1]];
			console.log(" cD: "+completeDiff+" new Amount "+newAmount);
			chart.replot( { resetAxes: true } );   
			oldAmount = newAmount;
			if(count<=times){
				animateChart(finalAmount,kegSize,chart);
			}
			
		},animationTimeout);
	};
	var calcKeg = function(list){
		var s1 = [];
		var s2 = [];
		var ticks = [];
		$.each(list,function(ind,keg){
			//arr.push([[keg.size-keg.current_amount,ind]]);
			//amount bar
			
			var size =keg.size;
			var amount =parseFloat(keg.current_amount);
			s1.push(size-amount);
			s2.push(amount);
			ticks.push(keg.keg_id);
		});
		return [[s1,s2],ticks];
	}
	var createBarChart = function(keglistModel,container){
		barContainer =container;
		var barDatas =  calcKeg([keglistModel]);
		var data = barDatas[0];
		var ticks =barDatas[1];
		var id = container.attr("id");
		container.css("height","100px");
		var chart = $.jqplot(id,data,$.extend({},plotOptions,{
		 	stackSeries: true,
		 	title:keglistModel.brand,
             seriesDefaults: {
                 renderer:$.jqplot.BarRenderer,
                 // Show point labels to the right ('e'ast) of each bar.
                 // edgeTolerance of -15 allows labels flow outside the grid
                 // up to 15 pixels.  If they flow out more than that, they 
                 // will be hidden.
                 pointLabels: { show: false, location: 'e', edgeTolerance: -15,formatString:"%#.2f" },
                 // Rotate the bar shadow as if bar is lit from top right.
                 shadowAngle: 135,
                 // Here's where we tell the chart it is oriented horizontally.
                 rendererOptions: {
                     barDirection: 'horizontal',
                         // Put a 30 pixel margin between bars.
                         barMargin: 30,
                 },
             },
             axes: {
                 yaxis: {
                     renderer: $.jqplot.CategoryAxisRenderer,
                     ticks: ticks
                 },
                 xaxis:{
                	// min:50,
                 }
             },

             seriesColors: ["#dddf0d","rgba(0,0,0,0.3)",  "#c5b47f", "#EAA228", "#579575", "#839557", "#958c12",
       	                 "#953579", "#4b5de4", "#d8b83f", "#ff5800", "#0085cc"],
         })); 
//		var series = convertToSeries(keglistModel);
//        var chart = new Highcharts.Chart({
//            chart: {
//                renderTo: container.attr("id"),
//                type: 'bar',
//                height:130
//            },
//            title: {
//                text: wording.KEG+keglistModel.keg_id//wording.TITLE
//            },
//            xAxis: {
//                categories: series.categories
//            },
//            yAxis: {
//                min: 0,
//                title: {
//                    text: wording.XBAR
//                }
//            },
//            legend: {
//                //backgroundColor: '#FFFFFF',
//                reversed: false
//            },
//            tooltip: {
//                formatter: function() {
//                	if(this.series.name == wording.REMAINING){
//                		 return ''+
//                         this.series.name +': '+ this.y.toFixed(2) +' Liter '+keglist(this.key).brand;
//                	}else{
//                		 return ''+
//                         this.series.name +': '+ this.point.total.toFixed(2) +' Liter '+keglist(this.key).brand;
//                	}
//                   
//                }
//            },
//            plotOptions: {
//                series: {
//                    stacking: 'normal'
//                }
//            },
//            series: series.series
//        });
        return chart;
   
	};
	var clearCharts = function(){
		
	}
	var pub = {
			init:init,
			createBarChart:createBarChart,
			updateChart:updateChart,
			updateChartUser:updateChartUser,
			clearCharts:clearCharts
	};
	return pub;
}(jQuery,ZMO.ajax));
