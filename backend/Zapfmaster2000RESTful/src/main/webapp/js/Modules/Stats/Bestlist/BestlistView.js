/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.bestlistView = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var chartContainer = null,chart = null,bestlistContainer = null;
	var init = function(cont){
		
	};
	var convertToSeries = function(userlistModel){
		var seriesArr =[];
		try{
			$.each(userlistModel,function(ind,user){
				var username = user.user_name;
				var amount = parseFloat(user.amount);
				seriesArr.push([username,amount]);
			});
		}catch(e){
			ZMO.log("Parse Error Beslist!")
		}
		return seriesArr;
		
	};
	var createPieChart = function(userlistModel,container){
		chartContainer =container;
		var datas = convertToSeries(userlistModel);
		chart = new Highcharts.Chart({
            chart: {
                renderTo: chartContainer.attr("id"),
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                height:200,
                marginTop:10
            },
            title: {
                text: ''
            },
            tooltip: {
        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
            	percentageDecimals: 1
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        color: '#fff',
                        connectorColor: '#fff',
                        distance:0,
                        formatter: function() {
                            return '<b>'+ this.point.name +'</b>: '+this.percentage.toFixed(1) +' %';
                        }
                    }
                }
            },
            series: [{
                type: 'pie',
                name: 'Menge',
                data: datas
            }]
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
		var headline  =$("<span>").text("Bestlist");
		bestlistContainer.append(headline).append(table);
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
