/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.statsMobile = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var container =null;
	var chartsFcts = {};
	var refreshStats = function(){
			var url = mC.urls.STATS;
			ajax.getDatas(url,function(json){
				var globalStatsModel =new ZMO.modules.StatsModel(json);
				initAccordion(globalStatsModel);
				
			});
	};
	var calcProgress = function(timeParser,arr,interval){
		var retArr = [];
		$.each(arr,function(ind,val){
			retArr.push([timeParser.getChartTimeAddMins(ind*interval),val]);
		});
		return retArr;
	}
	var initProgressChart = function(container,globalStatsModel){
		var progressStats= globalStatsModel.progress;
		var progress = progressStats.data;
		var interval = progressStats.interval;
		var startDate = new ZMO.TimeParser(progressStats.start_date);
		var datas = calcProgress(startDate,progress,interval)
//		var line1=[['23-May-08', 578.55], ['20-Jun-08', 566.5], ['25-Jul-08', 480.88], ['22-Aug-08', 509.84],
//		           ['26-Sep-08', 454.13], ['24-Oct-08', 379.75], ['21-Nov-08', 303], ['26-Dec-08', 308.56],
//		           ['23-Jan-09', 299.14], ['20-Feb-09', 346.51], ['20-Mar-09', 325.99], ['24-Apr-09', 386.15]];
		var plot1 = $.jqplot(container, [datas], {
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
//		            	   formatString:'%.2f'
		            	   formatString:'%.0f'
		                 },
		                 min:0
		             }
		           },
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
		        	seriesColors: [ "#dddf0d", "#c5b47f", "#EAA228", "#579575", "#839557", "#958c12",
		        	                 "#953579", "#4b5de4", "#d8b83f", "#ff5800", "#0085cc"],
		           cursor: {
		             show: false
		           }
		       });
	}
	var initBasicContainer = function(chartid){
		 var container = $("<li>").addClass("statsMobile keg statsDiv");
		 var headline = $("<div>").addClass("headline");
		 var body = $("<div>").addClass("content").hide();
		 if(chartid){
			 var chartContainer  =$("<div>").addClass("chart").attr("id",chartid).css({
				 width:"100%",
				 height:"200px"
			 }).hide();
			 container.append(headline).append(chartContainer).append(body);
		 }else{
			 container.append(headline).append(body);
		 }
		
		 
		 return container;
	}
	var initKegContainer = function(globalStatsModel){
		var kegContainer = initBasicContainer();
		var content = ich["ZMO-stats-mobile-kegstatus"](globalStatsModel);
		kegContainer.find(".content").append(content);
		kegContainer.find(".headline").text("Keg");
		
		return kegContainer;
	}
	var initRankContainer = function(globalStatsModel){
		 var statsContainer = initBasicContainer();
		 var content = ich["ZMO-stats-mobile-kegstatus"](globalStatsModel);
		 statsContainer.find(".content").append(content);
		 statsContainer.find(".headline").text("Rank");
		 
		 return statsContainer;
	}
	var initProgressContainer = function(globalStatsModel){
		var CHARTID ="zmo-progress";
		 var statsContainer = initBasicContainer(CHARTID);
		 statsContainer.find(".headline").text("Progress").data("chart",CHARTID);
		 var content = $(ich["ZMO-stats-mobile-kegstatus"](globalStatsModel));
		 
		
		 statsContainer.find(".content").append(content);

		 return statsContainer;
	}
	var initGeneralStatsContainer = function(globalStatsModel){
		 var statsContainer = initBasicContainer();
		 var content = ich["ZMO-stats-mobile-kegstatus"](globalStatsModel);
		 statsContainer.find(".content").append(content);
		 statsContainer.find(".headline").text("General");
		 return statsContainer;
	}
	
	var	toggleContainer = function(e,globalStatsModel){
		var el = $(e.currentTarget);
		var content = el.siblings(".content");
		if(!content.is(":visible")){
			el.siblings().show();
		}else{
			el.siblings().hide();
		}
		var chartId = el.data("chart");
		if(chartId &&$("#"+chartId).children().length==0){
			chartFcts[chartId](chartId,globalStatsModel);	
		}
	}
	var initAccordion = function(globalStatsModel){
		var accordionContainer =$("<div>");
		var ul =$("<ul>").addClass("statsMobileList")
		var kegContainer = initKegContainer(globalStatsModel);
		var rankContainer = initRankContainer(globalStatsModel);
		var generalStatsContainer = initGeneralStatsContainer(globalStatsModel);
		var progress = initProgressContainer(globalStatsModel);
		
		
		
		ul	.on(mC.clickEvent,".headline",function(e){
			
			toggleContainer(e,globalStatsModel);
		})
			.append(kegContainer)
			.append(progress)
			.append(generalStatsContainer)
			.append(rankContainer)
			;

		accordionContainer.append(ul).appendTo(container);
		
	}
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(hashParams,moduleParams){
		refreshStats();
		chartFcts = {
				"zmo-progress": initProgressChart
		}
	};
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		return (container = $("<div>"));
	};
	var pub = {
			getInstance:getInstance,
			init:init
	};
	return pub;
}(jQuery,ZMO.ajax));
