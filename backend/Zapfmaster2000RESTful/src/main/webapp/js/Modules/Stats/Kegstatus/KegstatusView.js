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
		XBAR:"Liter"
	};
	
	var init = function(cont){
		
	};
	var convertToSeries = function(keglistModel,amountModel){
		var seriesObj =[];
		seriesObj.categories=[];
		var remaining =[],complete = [];
		try{
			$.each(keglistModel,function(ind,keg){
				//var kegName = keg.brand;
				var amount = parseFloat(amountModel.current);
				var complAmount = parseFloat(keg.size);
				
				seriesObj.categories.push( keg.keg_id);
				remaining.push(amount);
				complete.push(complAmount - amount);
			
			});
		}catch(e){
			ZMO.log("Parse Error Kegstatus!")
		}
		seriesObj.series=[{name:wording.COMPLETE,data:complete},{name:wording.REMAINING,data:remaining,showInLegend:false}];
		
		return seriesObj;
		
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
                         this.series.name +': '+ this.y +' Liter';
                	}else{
                		 return ''+
                         this.series.name +': '+ this.point.total +' Liter';
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
	var createDrinkstats = function(userlistModel,container){
		drinkerContainer =  container;
		var table = ich["ZMO-stats-drinker"]({
			
		});
		
		//drinkerContainer.append(table);
	};
	var updateChart = function(val){
		var series = chart.series[0];
		series.addPoint(val, true, true);
	};
	var pub = {
			updateChart:updateChart,
			init:init,
			createBarChart:createBarChart,
			createDrinkstats:createDrinkstats
	};
	return pub;
}(jQuery,ZMO.ajax));
