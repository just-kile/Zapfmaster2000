/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.statsMobile = (function($,ajax){
	var l = ZMO.Util.localization;
	var wording = {
			NOBODY:"nobody",
			PROGRESS:"progress",
			KEG:"kegOverview",
			RANK:"userRank",
			GENERAL:"generalStats"
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
	var mC = ZMO.modules.Constants;
	var container =null;
	var refreshStats = function(){
			var url = mC.urls.STATS;
			ajax.getDatas(url,function(json){
				ZMO.logger.log("Stats mobile get json datas"+JSON.stringify(json));
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
				
				
			}else if(ZMO.modules.Constants.stats.showOthersPieChart){
				othersAmount +=parseFloat(user.amount);
			}
		}catch(e){

			ZMO.logger.log("StatsMobile: User got no amount!");
			ZMO.logger.log(user);
		}
		});
		if(othersAmount>0){
			arr.push([ZMO.Util.localization.translateString("Rest"),othersAmount]);
		}
		return arr;
		
	}
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
			ticks.push(keg.brand+"("+keg.keg_id+")");
		});
		return [[s1,s2],ticks];
	}
var initBarChart = function(id,globalStatsModel){

	var barDatas = calcKeg(globalStatsModel.keg);
	var data = barDatas[0];
	var ticks =barDatas[1];
	$("#"+id).css("height","150px");
	 var plot2 = $.jqplot(id,data,$.extend({},plotOptions,{
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
             seriesColors: ["#dddf0d","rgba(0,0,0,0.3)",  "#c5b47f", "#EAA228", "#579575", "#839557", "#958c12",
       	                 "#953579", "#4b5de4", "#d8b83f", "#ff5800", "#0085cc"],
         }));
}
	var initPieChart = function(id,globalStatsModel){
		var rankList = globalStatsModel.bestUserList;
		var data = calcBestlist(rankList);
		$("#"+id).css("height","250px");
//		['Heavy Industry', 12],['Retail', 9], ['Light Industry', 14], 
//	    ['Out of home', 16],['Commuting', 7], ['Orientation', 9]
		jQuery.jqplot (id, [data], 
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
	}
	var initProgressChart = function(id,globalStatsModel){
		var progressStats= globalStatsModel.progress;
		var progress = progressStats.data;
		var interval = progressStats.interval;
		var startDate = new ZMO.TimeParser(progressStats.start_date);
		var datas = calcProgress(startDate,progress,interval)
		var plot1 = $.jqplot(id, [datas],$.extend( {
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
		          
		           cursor: {
		             show: false
		           }
		       },plotOptions));
	}
	var initBasicContainer = function(chartid,headlineText,image){
		 var container = $("<li>").addClass("statsMobile keg");
		 var headline = $("<div>").addClass("headline ZMO-sideNavigation-entry").data("chart",chartid);
		 var icon = $("<img>").attr("src",image).appendTo(headline).addClass("small");
		 $("<div>").css({
			 "margin-left":"2em"
		 }).text(headlineText).appendTo(headline);
		 
		 var body = $("<div>").addClass("content statsDiv").hide();
		 if(chartid){
			 
			 var chartContainer  =$("<div>").addClass("chart").attr("id",chartid).css({
				 width:"100%",
				 height:"200px"
			 }).appendTo(body);
			 //container.append(headline).append(chartContainer).append(body);
		 }else{
			
		 }
		 container.append(headline).append(body);
		
		 
		 return container;
	}
	var initKegContainer = function(globalStatsModel){
		var CHARTID = "zmo-keg";
		var kegContainer = initBasicContainer(CHARTID,l.translateString(wording.KEG),"images/icons/47-fuel.png");
//		var content = ich["ZMO-stats-mobile-kegstatus"](globalStatsModel);
//		kegContainer.find(".content").append(content);
		
		return kegContainer;
	}
	var initRankContainer = function(globalStatsModel){
		 var CHARTID ="zmo-rank";
		 var statsContainer = initBasicContainer(CHARTID,l.translateString(wording.RANK),"images/icons/85-trophy.png");
		
		 //Appendix of rank table
		 var userlistModel = globalStatsModel.bestUserList;
		var template = ich["ZMO-stats-bestlist-item"];
		var table = $("<table>").addClass("bestlist-table");
		$.each(userlistModel,function(ind,val){
			val.rank = ind+1;
			val.unit = "l";
			table.append(template(val));
		});
		 statsContainer.find(".content").append(table);
		 return statsContainer;
	}
	var initProgressContainer = function(globalStatsModel){
		 var CHARTID ="zmo-progress";
		 var statsContainer = initBasicContainer(CHARTID,l.translateString(wording.PROGRESS),"images/icons/122-stats.png");

		 return statsContainer;
	}

	var parseTableObject = function(statsList){
		var obj = {};
		
		if(statsList && ZMO.exists(statsList[0])){
			obj.userName = statsList[0].userName;
			obj.userId = statsList[0].userId;
			obj.amount  = (statsList[0].amount || statsList[0].drawCount ||statsList[0].achievementCount);
		}else{
			obj.userName = ZMO.Util.localization.translateString(wording.NOBODY);
			obj.userId = "";
		}
		
		return obj;
	};
		var generateObj = function(statsModel){
			return {
				bestUser:parseTableObject(statsModel.bestUserList),
				bestUserHour:parseTableObject(statsModel.bestUserListHour),
				bestDrawCount:parseTableObject(statsModel.drawCountUserList),
				bestAchievement:parseTableObject(statsModel.achievementUserList),
				promille:statsModel.promille,
				amountComplete:statsModel.amount.complete,
				amountAtMost:statsModel.amount.once,
				amountAverage:statsModel.drawCount.average,
				drawCount:statsModel.drawCount.operations,
				//drawCountMobilestatsModel.drawCount.operations,
				mostActivityHour:statsModel.amount.mostActivityHour,
				achievementCount:statsModel.achievements.count,
				mostAchievementHour:statsModel.achievements.mostAchievementHour
			};
		};
	var initGeneralStatsContainer = function(globalStatsModel){
		 var statsContainer = initBasicContainer(null,l.translateString(wording.GENERAL),"images/icons/112-group.png");
		 var content = ich["ZMO-frontpagestats-general-template"](generateObj(globalStatsModel));
		 statsContainer.find(".content").append(content);
		 return statsContainer;
	}
	
	var	toggleContainer = function(e,globalStatsModel){
		if(!ZMO.scrolling.isActuallyScrolling()){
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
	}
	var initAccordion = function(globalStatsModel){
		var accordionContainer =$("<div>");
		var ul =$("<ul>").addClass("statsMobileList");
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
		ZMO.logger.log("Stats mobile init accordion");
		
	}
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(hashParams,moduleParams){
		refreshStats();
		chartFcts = {
				"zmo-progress": initProgressChart,
				"zmo-rank":initPieChart,
				"zmo-keg":initBarChart
		};
		ZMO.logger.log("Stats mobile init");
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
