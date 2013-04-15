/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.kegstatusView = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var barContainer = null,chart = null,drinkerContainer = null;
	var wording = {
		REMAINING:"remaining",
		COMPLETE:"complete",
		TITLE:"kegOverview",
		XBAR:"Liter",
		NOBODY:"nobody"
		
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
	        	seriesColors: ["#dddf0d","#90b1d8",  "#c5b47f", "#EAA228", "#579575", "#839557", "#958c12",
	        	                 "#953579", "#4b5de4", "#d8b83f", "#ff5800", "#0085cc"],
	};
	
	var init = function(cont){
		$.each(wording,function(ind,word){
			wording[ind] = ZMO.Util.localization.translateString(word);
		});
	};
	/******
	 * Create Bar chart
	 ******/

	/**
	 * Provides further information about keg
	 */
	var keglistMod ={};
	var keglist = function(keglistModelOrId){
		if(typeof keglistModelOrId =="string"||typeof keglistModelOrId =="number" ){
			var model;
			$.each(keglistMod,function(ind,keg){
				if(keg.keg_id==keglistModelOrId){
					model  = keg;
					return false;
				}
			});
			return model;
		}else if(typeof keglistModelOrId == "object"){
			keglistMod = keglistModelOrId
		}else{
			return keglistMod;
		}
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
			ticks.push(keg.brand+" ("+keg.keg_id+")");
		});
		return [[s1,s2],ticks];
	}
	var createBarChart = function(keglistModel,amountModel,container){
		var barDatas = calcKeg(keglistModel);
		var data = barDatas[0];
		var ticks =barDatas[1];
		var id= container.attr("id");
		$("#"+id).css("height","120px");
		chart = $.jqplot(id,data,$.extend({},plotOptions,{
			 	stackSeries: true,
	             seriesDefaults: {
	                 renderer:$.jqplot.BarRenderer,
	                 // Show point labels to the right ('e'ast) of each bar.
	                 // edgeTolerance of -15 allows labels flow outside the grid
	                 // up to 15 pixels.  If they flow out more than that, they 
	                 // will be hidden.
	                 pointLabels: { show: true, location: 'e', edgeTolerance: -15 },
	                 // Rotate the bar shadow as if bar is lit from top right.
	                 shadowAngle: 135,
	                 // Here's where we tell the chart it is oriented horizontally.
	                 rendererOptions: {
	                     barDirection: 'horizontal',
	                         // Put a 30 pixel margin between bars.
	                         barMargin: 30,
	                 }
	             },
	             axes: {
	                 yaxis: {
	                     renderer: $.jqplot.CategoryAxisRenderer,
	                     ticks: ticks
	                 },
	                 xaxis:{
	                	 min:50,
	                 }
	             },
	             seriesColors: ["#90b1d8","#dddf0d",  "#c5b47f", "#EAA228", "#579575", "#839557", "#958c12",
	       	                 "#953579", "#4b5de4", "#d8b83f", "#ff5800", "#0085cc"],
	         })); 
	};
	var updateChart = function(val){
		var series = chart.series[0];
		series.addPoint(val, true, true);
	};
	/******
	 * Drinker stats
	 ******/
	var parseTableObject = function(statsList){
		var obj = {};
		
		if(statsList && ZMO.exists(statsList[0])){
			obj.userName = statsList[0].userName;
			obj.userId = statsList[0].userId;
			obj.amount  = (statsList[0].amount || statsList[0].drawCount ||statsList[0].achievementCount);
		}else{
			obj.userName = wording.NOBODY;
			obj.userId = "";
		}
		
		return obj;
	};
	var generateObjAlltime = function(statsModel){
		return {
			bestUser:parseTableObject(statsModel.bestUserList),
			bestUserHour:parseTableObject(statsModel.bestUserListHour),
			bestDrawCount:parseTableObject(statsModel.drawCountUserList),
			bestAchievement:parseTableObject(statsModel.achievementUserList),
			promille:statsModel.promille
			
		};
	};
	var createDrinkstats = function(statsModel,container){
		
		drinkerContainer =  container;
		var obj = generateObjAlltime(statsModel);
		var table =$( ich["ZMO-frontpagestats-general-template"](obj));
		drinkerContainer.append(table);
	};
	/******
	 * General stats
	 ******/
	var generateObj = function(statsModel){
		return {
			amountComplete:statsModel.amount.complete,
			amountAtMost:statsModel.amount.once,
			amountAverage:statsModel.drawCount.average,
			drawCount:statsModel.drawCount.operations,
			mostActivityHour:statsModel.amount.mostActivityHour,
			achievementCount:statsModel.achievements.count,
			mostAchievementHour:statsModel.achievements.mostAchievementHour
		};
	};
	var createGeneralStats = function(statsModel,container){
		
		var obj = generateObj(statsModel);
		var table =$( ich["ZMO-frontpagestats-general-template"](obj));

		table.appendTo(container);
	};
	var pub = {
			updateChart:updateChart,
			init:init,
			createBarChart:createBarChart,
			createDrinkstats:createDrinkstats,
			createGeneralStats:createGeneralStats
	};
	return pub;
}(jQuery,ZMO.ajax));
