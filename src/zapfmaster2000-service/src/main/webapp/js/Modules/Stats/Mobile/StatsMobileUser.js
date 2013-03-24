/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.statsMobileUser = (function($,ajax){
	var wording = {
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
	var mC = ZMO.modules.Constants;
	var container =null;
	var chartsFcts = {};
	var refreshStats = function(userId){
		var datas;
		if(userId){
		 datas={
				user:userId	
			};
		}	

			
		var url = mC.urls.USERSTATS;
		ajax.getDatas(url,function(json){
			var globalStatsModel =new ZMO.modules.UserStatsModel(json);
			initAccordion(globalStatsModel);
			
		},datas);
	};
	var calcProgress = function(timeParser,arr,interval){
		var retArr = [];
		$.each(arr,function(ind,val){
			retArr.push([timeParser.getChartTimeAddMins(ind*interval),val]);
		});
		return retArr;
	}
	var calcBestlist = function(list){
		var arr = [];
		$.each(list,function(ind,user){
			arr.push([user.userName,user.amount]);
		});
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
//		var line1=[['23-May-08', 578.55], ['20-Jun-08', 566.5], ['25-Jul-08', 480.88], ['22-Aug-08', 509.84],
//		           ['26-Sep-08', 454.13], ['24-Oct-08', 379.75], ['21-Nov-08', 303], ['26-Dec-08', 308.56],
//		           ['23-Jan-09', 299.14], ['20-Feb-09', 346.51], ['20-Mar-09', 325.99], ['24-Apr-09', 386.15]];
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

	var initRankContainer = function(globalStatsModel){
		 var CHARTID ="zmo-rank";
		 var statsContainer = initBasicContainer(CHARTID,"Rank","images/icons/85-trophy.png");
		
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
		 var statsContainer = initBasicContainer(CHARTID,"Progress","images/icons/122-stats.png");

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
		var generateObjGlobalStats = function(statsModel){
			return {
				achievementRank:statsModel.rank.achievements,
				amountRank:statsModel.rank.amount,
				drawCountRank:statsModel.rank.drawCount,
				promille:statsModel.promille,
				drawCount:statsModel.drawCount.operations,
				drawCountAverage:statsModel.drawCount.average,
				amount:statsModel.amount.complete,
				amountGreatestDrawing:statsModel.amount.once,
				mostActiveHour:statsModel.amount.mostActivityHour,
				
				};
		};
		var generateObjAchievements = function(statsModel){
			return {
				achievementCount:statsModel.achievements.count,
				mostAchievementHour:statsModel.achievements.mostAchievementHour,
				
				
				
				
				};
		}
	var initGeneralStatsContainer = function(userStatsModel){
		 var statsContainer = initBasicContainer(null,"General","images/icons/112-group.png");
		 var content = ich["ZMO-stats-mobile-user-general"](generateObjGlobalStats(userStatsModel));
		 statsContainer.find(".content").append(content);
		 return statsContainer;
	}
	var initAchievementStatsContainer = function(userStatsModel){
		var achievements = userStatsModel.achievements;
		 var statsContainer = initBasicContainer(null,"Achievements","images/icons/112-group.png");
		 var content =$("<div>");
		 var globalStatsContainer = ich["ZMO-stats-mobile-user-achievement"](generateObjAchievements(userStatsModel));
		 var achievementsContainer = $("<div>");
		 content.append(globalStatsContainer).append(achievementsContainer);
		 $.each(achievements.achievements,function(ind,achievement){
			 $("<img>").attr("src",achievement.achievementImage).on(mC.clickEvent,function(){
				 var aContainer =$(ich["ZMO-stats-mobile-user-popup"](achievement));
				 ZMO.Util.Popup.open(aContainer);
			 }).appendTo(achievementsContainer);
		 })
		 statsContainer.find(".content").append(content);
		 return statsContainer;
	}
	var initUserInfoContainer = function(userStatsModel){
		 var statsContainer = $("<div>").addClass("statsDiv zmo-user");//initBasicContainer(null,"Achievements","images/icons/112-group.png");
		 var user = userStatsModel.user;
		 var img = $("<img>").attr("src",user.userImage);
		 var userName = $("<div>").text("#"+userStatsModel.rank.amount+". "+user.userName);
		 statsContainer.append(img).append(userName);
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

	var initAccordion = function(userStatsModel){
		var accordionContainer =$("<div>");
		var ul =$("<ul>").addClass("statsMobileList");
		var userInfoContainer  =initUserInfoContainer(userStatsModel);
		var achievementContainer = initAchievementStatsContainer(userStatsModel);
		var generalStatsContainer = initGeneralStatsContainer(userStatsModel);
		var progress = initProgressContainer(userStatsModel);
		
		
		
		ul	.on(mC.clickEvent,".headline",function(e){
			
			toggleContainer(e,userStatsModel);
		})	.append(userInfoContainer)
			.append(generalStatsContainer)
			.append(achievementContainer)
			.append(progress)
//			
			//.append(rankContainer)
			;

		accordionContainer.append(ul).appendTo(container);
		
	}
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(hashParams,moduleParams){
		var userId = hashParams.id;
		refreshStats(userId);
		chartFcts = {
				"zmo-progress": initProgressChart,
				"zmo-rank":initPieChart,
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
