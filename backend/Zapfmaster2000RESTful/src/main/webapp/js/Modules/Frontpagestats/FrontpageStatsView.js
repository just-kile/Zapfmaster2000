/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.frontPageStatsView = (function($,ajax){
	var mC = ZMO.modules.Constants;
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
	var init = function(cont){
		
	};
	/**
	 * Provides further information about keg
	 */
	var keglistMod ={};
	var keglist = function(keglistModelOrId){
		if(typeof keglistModelOrId =="string" ){
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
			ZMO.log("Parse Error Kegstatus!");
		}
		seriesObj.series=[{name:wording.COMPLETE,data:complete,showInLegend:false},{name:wording.REMAINING,data:remaining,showInLegend:false}];
		
		return seriesObj;
		
	};
	var convertAmountModelToSeries = function(amountModel){
		
	};
	var updateAmountChart = function(){
		
	};
	var updateChart = function(kegModel,chart,titleText){
		var actAmount =  parseFloat(kegModel.current_amount);
		var completeDiff =  parseFloat(kegModel.size)-actAmount;
		chart.series[0].data[0].update(actAmount,false);
		chart.series[1].data[0].update(completeDiff);
		if(titleText)chart.setTitle({
			text:titleText
		});
	};
	var updateChartUser = function(kegModel,chart,titleText){
		var actAmount =  parseFloat(kegModel.current_amount);
		var completeDiff =  parseFloat(kegModel.size)-actAmount;
		chart.series[0].data[0].update(completeDiff,false);
		chart.series[1].data[0].update(actAmount);
		if(titleText)chart.setTitle({
			text:titleText
		});
	}
	
	var createBarChart = function(keglistModel,container){
		barContainer =container;
		var series = convertToSeries(keglistModel);

        var chart = new Highcharts.Chart({
            chart: {
                renderTo: container.attr("id"),
                type: 'bar',
                height:130
            },
            title: {
                text: "Fass "+keglistModel.keg_id//wording.TITLE
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
        return chart;
   
	};
	var pub = {
			init:init,
			createBarChart:createBarChart,
			updateChart:updateChart,
			updateChartUser:updateChartUser
	};
	return pub;
}(jQuery,ZMO.ajax));
