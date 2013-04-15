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
	/******
	 * Create Bar chart
	 ******/
	var convertToSeries = function(keglistModel,amountModel){
		var seriesObj =[];
		seriesObj.categories=[];
		seriesObj.stack= [];
		var remaining =[],complete = [];
		//set keglist
		keglist(keglistModel);
		try{
			$.each(keglistModel,function(ind,keg){
				//var kegName = keg.brand;
				var amount = parseFloat(keg.current_amount);
				var complAmount = parseFloat(keg.size);
				seriesObj.categories.push( keg.keg_id);
				//seriesObj.stack.push(keg.brand);
				remaining.push(complAmount-amount);
				complete.push( amount);
			
			});
		}catch(e){
			ZMO.logger.error("Parse Error Kegstatus!");
		}
		seriesObj.series=[{name:wording.COMPLETE,data:complete},{name:wording.REMAINING,data:remaining,showInLegend:false}];
		
		return seriesObj;
		
	};
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
	var createBarChart = function(keglistModel,amountModel,container){
		barContainer =container;
		var series = convertToSeries(keglistModel,amountModel);

        chart = new Highcharts.Chart({
            chart: {
                renderTo: container.attr("id"),
                type: 'bar',
                height:200
            },
            title: {
                text: wording.TITLE
            },
            xAxis: {
                categories: series.categories
            },
            yAxis: {
                min: 0,
                title: {
                    text: wording.XBAR
                }
            },
            legend: {
                //backgroundColor: '#FFFFFF',
                reversed: false
            },
            tooltip: {
                formatter: function() {
                	if(this.series.name == wording.REMAINING){
                		 return ''+
                         this.series.name +': '+ this.y.toFixed(2) +' Liter '+keglist(this.key).brand;
                	}else{
                		 return ''+
                         this.series.name +': '+ this.point.total.toFixed(2) +' Liter '+keglist(this.key).brand;
                	}
                   
                }
            },
            plotOptions: {
                series: {
                    stacking: 'normal'
                }
            },
            series: series.series
        });
   
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
		
		
//		$.each(drinkersWording,function(ind,val){
//			var obj={};
//			switch(val){
//			case drinkersWording.ALLTIME:
//				obj = parseTableObject(val,statsModel.bestUserList,"l");
//				break;
//			case drinkersWording.DRINKER_OF_HOUR:
//				obj = parseTableObject(val,statsModel.bestUserListHour,"l");
//				break;
//			case drinkersWording.MOST_LOYAL:
//				obj = parseTableObject(val,statsModel.drawCountUserList,"x");
//				break;
//			}
//			var row = template(obj);
//			table.append(row);
//		});
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
