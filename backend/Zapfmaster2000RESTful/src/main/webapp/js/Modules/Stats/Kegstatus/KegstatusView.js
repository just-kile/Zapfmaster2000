/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.kegstatusView = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var barContainer = null,chart = null,bestlistContainer = null;
	var wording = {
		REMAINING:"Verbleibend",
		COMPLETE:"Komplett",
		TITLE:"Fass Uebersicht",
		XBAR:"Getrunkene Menge"
	};
	
	var init = function(cont){
		
	};
	var convertToSeries = function(keglistModel){
		var seriesObj =[];
		seriesObj.categories=[];
		var remaining =[],complete = [];
		try{
			$.each(keglistModel,function(ind,keg){
				//var kegName = keg.brand;
				var amount = parseFloat(keg.amount.actual);
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
	var createBarChart = function(keglistModel,container){
		barContainer =container;
		var series = convertToSeries(keglistModel);

        chart = new Highcharts.Chart({
            chart: {
                renderTo: container.attr("id"),
                type: 'bar',
                height:300
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
	var createBestlist = function(userlistModel,container){
		bestlistContainer =  container;
		var template = ich["ZMO-stats-bestlist-item"];
		var table = $("<table>").addClass("bestlist-table");
		$.each(userlistModel,function(ind,val){
			val.rank = ind+1;
			table.append(template(val));
		});
		bestlistContainer.append(table);
	};
	var updateChart = function(val){
		var series = chart.series[0];
		series.addPoint(val, true, true);
	};
	var pub = {
			updateChart:updateChart,
			init:init,
			createBarChart:createBarChart,
			createBestlist:createBestlist
	};
	return pub;
}(jQuery,ZMO.ajax));
