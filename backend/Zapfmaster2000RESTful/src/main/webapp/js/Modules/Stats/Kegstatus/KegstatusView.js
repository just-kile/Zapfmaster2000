/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.kegstatusView = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var barContainer = null,chart = null,drinkerContainer = null;
	var wording = {
		REMAINING:"Verbleibend",
		COMPLETE:"Komplett",
		TITLE:"Fass Uebersicht",
		XBAR:"Liter",
		PROMILLE:"Ungef&auml;hrer Blutalkohol",
		
		ONCE_DRAFT:"Gr&ouml;&szlig;te auf einmal gezapfte Menge",
		COMPLETE_AMOUNT:"Gesamtliteranzahl",
		COMPLETE_DRAFTS:"Gesamtanzahl Zapfvorg&auml;nge",
		MID_DRAFTS:"Durchschnittliche Zapfvorg&auml;nge",
		GAINED_ACHIEVEMENTS:"Bisher erreichte Achievements",
		MOST_ACTIVITIY_HOUR:"Most Activity&copy; Hour",
		MOST_ACHIEVEMENT_HOUR:"Most Achievement&copy; Hour"
	};
	var drinkersWording= {
		ALLTIME:"All Time",
		DRINKER_OF_HOUR:"Trinker der Stunde",
		MOST_LOYAL:"Treuester Kunde",
	};
	
	var init = function(cont){
		
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
				remaining.push(amount);
				complete.push(complAmount - amount);
			
			});
		}catch(e){
			ZMO.log("Parse Error Kegstatus!");
		}
		seriesObj.series=[{name:wording.COMPLETE,data:complete},{name:wording.REMAINING,data:remaining,showInLegend:false}];
		
		return seriesObj;
		
	};
	/**
	 * Provides further information about keg
	 */
	var keglistMod ={};
	var keglist = function(keglistModelOrId){
		if(typeof keglistModelOrId =="string" ){
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
                         this.series.name +': '+ this.y +' Liter '+keglist(this.key).brand;
                	}else{
                		 return ''+
                         this.series.name +': '+ this.point.total +' Liter '+keglist(this.key).brand;
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
	var parseTableObject = function(description,statsList,unit){
		var obj = {description:description,isLink:true};
		
		if(statsList && ZMO.exists(statsList[0])){
			obj.user_name = statsList[0].user_name;
			obj.user_id = statsList[0].user_id;
			obj.amount  = (statsList[0].amount || statsList[0].draw_count ||statsList[0].achievement_count)+" "+unit;
		}else{
			obj.user_name = "Noch niemand";
			obj.user_id = "";
		}
		
		return obj;
	};
	var createDrinkstats = function(statsModel,container){
		
		drinkerContainer =  container;
		var table = $("<table>").addClass("stats-drinker");
		var template = ich["ZMO-stats-drinker"];
		$.each(drinkersWording,function(ind,val){
			var obj={};
			switch(val){
			case drinkersWording.ALLTIME:
				obj = parseTableObject(val,statsModel.bestUserList,"l");
				break;
			case drinkersWording.DRINKER_OF_HOUR:
				obj = parseTableObject(val,statsModel.bestUserListHour,"l");
				break;
			case drinkersWording.MOST_LOYAL:
				obj = parseTableObject(val,statsModel.drawCountUserList,"x");
				break;
			}
			var row = template(obj);
			table.append(row);
		});
		table.append(template({
			description:wording.PROMILLE,
			isText:true,
			text:statsModel.promille,
			unit:"&permil;"
		}));
		drinkerContainer.append(table);
	};
	/******
	 * General stats
	 ******/
	var generateObj = function(description,text,unit){
		return {
			description:description,
			isText:true,
			text:text,
			unit:unit
		};
	};
	var createGeneralStats = function(statsModel,container){
		var table = $("<table>").addClass("stats-drinker");
		var tp = ich["ZMO-stats-drinker"];
		table.append(tp(generateObj(wording.COMPLETE_AMOUNT,statsModel.amount.complete,"l")))
			.append(tp(generateObj(wording.ONCE_DRAFT,statsModel.amount.once,"l")))
			.append(tp(generateObj(wording.MID_DRAFTS,statsModel.drawCount.average,"x/h")))
			.append(tp(generateObj(wording.COMPLETE_DRAFTS,statsModel.drawCount.operations,"x")))
			.append(tp(generateObj(wording.MOST_ACTIVITIY_HOUR,statsModel.amount.mostActivityHour," Uhr")))
			.append(tp(generateObj(wording.GAINED_ACHIEVEMENTS,statsModel.achievements.count,"")))
			.append(tp(generateObj(wording.MOST_ACHIEVEMENT_HOUR,statsModel.achievements.mostAchievementHour," Uhr")));
			
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
