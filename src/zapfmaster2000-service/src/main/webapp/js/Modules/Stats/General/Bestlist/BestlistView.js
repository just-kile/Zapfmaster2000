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
	        	        gridLineColor: 'rgba(255,255,255,0.3)',   // CSS color spec of the grid lines.
	        	        background: 'rgba(255,255,255,0)',      // CSS color spec for background color of grid.
	        	        borderColor: 'rgba(0,0,0,0)',     // CSS color spec for border around grid.
	        	        borderWidth: 2.0,           // pixel width of border around grid.
	        	        shadow: false,               // draw a shadow for grid.
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
	var filterAmountArray = function(list){

		return list.sort(function(a, b){
			try{
				 var aName = parseFloat(a.amount);
				 var bName = parseFloat(b.amount); 
			}catch(e){
				return 0;
			}
				 
				  return ((aName > bName) ? -1 : ((aName < bName) ? 1 : 0));
		});
		
		
		
	}
	var calcBestlist = function(list){
		var max = mC.stats.pieChartMaxUsers;
		var arr = [];
		var othersAmount  = 0;
		list = filterAmountArray(list);
		
		$.each(list,function(ind,user){
			try{
			if(ind<max){
				
					arr.push([user.userName,parseFloat(user.amount)]);
				
				
			}else{
				othersAmount += parseFloat(user.amount);
			}
		}catch(e){

			ZMO.log("StatsMobile: User got no amount!");
			ZMO.log(user);
		}
		});
		if(othersAmount>0){
			arr.push([ZMO.Util.localization.translateString("Rest"),othersAmount]);
		}
		return arr;
		
	}
	var createPieChart = function(userlistModel,container){
		var id = container.attr("id");
		var rankList = userlistModel;
		var data = calcBestlist(rankList);
		$("#"+id).css("height","250px");
//		['Heavy Industry', 12],['Retail', 9], ['Light Industry', 14], 
//	    ['Out of home', 16],['Commuting', 7], ['Orientation', 9]
		if(chart)$("#"+id).empty();
			
		chart = jQuery.jqplot (id, [data], 
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
	var clearCharts = function(){
		chart = null;
	}
	var pub = {
			updateChart:updateChart,
			init:init,
			createPieChart:createPieChart,
			createBestlist:createBestlist,
			clearCharts:clearCharts
	};
	return pub;
}(jQuery,ZMO.ajax));
