/**
 * Wrapper for frontpage stats boxes
 * Contains the real stats boxes
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.frontpagestats = (function($,view,ajax){
	var mC = ZMO.modules.Constants;
	var containerHandler = null;
	var container=null;
	var chartIdPrefix = "ZMO-frontpageStats-barchart-1";
	/**
	 * Some kind of Data Handler
	 */
	var ContainerHandler = function(){
		var keyContainerMap = {};
		var add = function(kegModel,cont,chart,kegCont,userCont){
			keyContainerMap[kegModel.boxId] = {
					model :kegModel,
					container:cont,
					chart:chart,
			};
		};
		var update = function(kegModel,cont,chart){
			if(kegModel){
				keyContainerMap[kegModel.boxId]["model"] = kegModel;
				if(cont)keyContainerMap[kegModel.boxId]["container"] = cont;
				if(chart)keyContainerMap[kegModel.boxId]["chart"] = chart;
			}
		};
		var removeKeg = function(boxId){
			var kegModel =getModelToId(boxId);
			if(keyContainerMap[kegModel.boxId]["container"])
				keyContainerMap[kegModel.boxId]["container"].remove();
			//chart.destroy()
		};
		var isUneccessary = function(boxId,kegModelArr){
			var idArr = [];
			$.each(kegModelArr,function(ind,kegModel){
				idArr.push(kegModel.boxId);
			});
			return !$.inArray(keg.model.boxId,idArr);
		};
		var removeUneccessaryKegs = function(kegModelArr){
			var idArr = [];
			$.each(kegModelArr,function(ind,kegModel){
				idArr.push(kegModel.boxId);
			});
			$.each(keyContainerMap,function(ind,keg){
				if(!$.inArray(keg.model.boxId,idArr)){
					removeKeg(kegModel);
				}
			});
		};
		var getChart = function(kegModel){
			return keyContainerMap[getModelToId(kegModel).boxId]["chart"];
		};
		var getObj = function(kegModel,param){
			if(typeof kegModel=="string"||typeof kegModel=="number"){
				return keyContainerMap[kegModel][param]; 
			}else{
				return keyContainerMap[kegModel.boxId][param];
			}
		};
		var getContainer = function(kegModel){
			return getObj(kegModel,"container");
			
		};
		var getUserContainer = function(kegModel){
			return getObj(kegModel,"container").find(".ZMO-userText");
		};
		var getKegContainer = function(kegModel){
			return getObj(kegModel,"container").find(".ZMO-kegText");
		};
		var getModelToId = function(boxId){
			return typeof boxId=="object"?boxId:keyContainerMap[boxId]["model"];
		};
		var switchContainerToUser =function(boxId){
			var kegModel = getModelToId(boxId);
			getKegContainer(kegModel).hide();
			getUserContainer(kegModel).show();
		} ;
		var switchContainerToKeg =function(boxId){
			var kegModel = getModelToId(boxId);
			getKegContainer(kegModel).show();
			getUserContainer(kegModel).hide();
		} ;
		this.add = add;
		this.update = update;
		this.getChart = getChart;
		this.getContainer = getContainer;
		this.getUserContainer = getUserContainer;
		this.getKegContainer = getKegContainer;
		this.switchContainerToUser = switchContainerToUser;
		this.switchContainerToKeg = switchContainerToKeg;
		this.getModelToId = getModelToId;
		this.isUneccessary = isUneccessary;
		this.removeUneccessaryKegs = removeUneccessaryKegs;
	};
	var initContainer = function(){
		return $("<div>").addClass("news-drink-box");
	};
	/*****
	 * General Stats
	 *****/
	var initGeneralStats = function(fpData){
		var cont = initContainer().addClass("ZMO-generalNews");
		container.append(cont);
		updateGeneralStats(fpData);
	};
	var updateGeneralStats = function(fpData){
		var cont = container.find(".ZMO-generalNews");
		var fpStats = new ZMO.modules.StatsModel(fpData);
		var bestUser = fpStats.bestUserList;
		var bestUserHour = fpStats.bestUserListHour;
		var bestAchievementUser = fpStats.achievementUserList;
		var bestDrawCount = fpStats.drawCountUserList;
		
		var content = ich["ZMO-frontpagestats-general-template"]({
			bestUser:bestUser.length>0?bestUser[0]:"Niemand",
			bestUserHour:bestUserHour.length>0?bestUserHour[0]:"Niemand",
			bestAchievement:bestAchievementUser.length>0?bestAchievementUser[0]:"Niemand",
			bestDrawCount:bestDrawCount.length>0?bestDrawCount[0]:"Niemand",
			promille:fpStats.promille
		});
		cont.empty().append(content);
	};
	/*****
	 * Keg chart overview creation/update
	 *****/
	var updateChart = function(kegModel){
		view.updateChart(kegModel,containerHandler.getChart(kegModel),"Fass "+kegModel.boxId);
	};
	var updateNormalKegstats = function(kegModel){
		var kegStatsContainer = $(containerHandler.getKegContainer(kegModel));
		var newContent = $(ich["ZMO-frontpagestats-template"](kegModel));
		kegStatsContainer.empty().append(newContent);
	};
	var updateKegStats = function(kegModel){
		updateNormalKegstats(kegModel);
		updateChart(kegModel);
	};
	

	var createChart = function(cont,kegModel){
		var chartContainer = $("<div>").addClass("chart").attr("id",chartIdPrefix+kegModel.boxId);
		cont.append(chartContainer);
		return view.createBarChart(kegModel,chartContainer);
	};
	var initBox = function(kegModel){
		//create basic container
		var kegStatsContainer = initContainer(kegModel);
		container.append(kegStatsContainer);
		//Append Chart
		var chart = createChart(kegStatsContainer,kegModel);
		//Append text container
		var kegContainer =$("<div>").addClass("ZMO-kegText");
		var userContainer = $("<div>").addClass("ZMO-userText").hide();
		kegStatsContainer.append(kegContainer).append(userContainer);
		
		//Add Container to store
		containerHandler.add(kegModel,kegStatsContainer,chart,kegContainer,userContainer);
		//Fill text container
		updateNormalKegstats(kegModel);
	};
	/*****
	 * User Draws Stats creation
	 *****/
	var initUserStats =  function(newsModel){
		var boxId = newsModel.boxId;
		ajax.getDatas(ZMO.modules.Constants.urls.USERFRONTPAGESTATS,function(userRank){
			var userContainer = containerHandler.getUserContainer(boxId);
			var content = ich["ZMO-frontpagestats-general-template"]({
				news:newsModel,
				userRank:userRank.rank
			});
			
			userContainer.empty().append(content);
		},{
			userid:newsModel.userId
		});
	};
	function roundAHalf(val) { 
		  if((val - Math.floor(val)) >= 0.5) {
		    maxSize = Math.ceil(val);
		  } else {
			 maxSize = Math.floor(val) + 0.5;
		  }
		return maxSize;
		}

	var updateUserStats = function(newsModel){
		var boxId =  newsModel.boxId;
		//Update chart
		var amount = newsModel.amount;
		var chart= containerHandler.getChart(boxId);
		var maxSize = roundAHalf(amount);
		view.updateChartUser({
			current_amount:amount,
			size:maxSize
		},chart,newsModel.name+": "+amount +"l");
		
	};
	/*****
	 * Switch functionality
	 *****/
	var switchContainerToUser =function(newsModel){
		var boxId =  newsModel.boxId;
		var chart= containerHandler.getChart(boxId);
		view.updateChart({
			current_amount:0.5,
			size:0
		},chart,newsModel.name+", Zapf los!");
		initUserStats(newsModel);
		containerHandler.switchContainerToUser(newsModel);
	};
	var switchContainerToKeg =function(boxId){
		ajax.getDatas(ZMO.modules.Constants.urls.FRONTPAGESTATS,function(fpStats){
			updateGeneralStats(fpStats);
			var kegModelArr = new ZMO.modules.kegModel(fpStats.keg);
			$.each(kegModelArr,function(ind,kegData){
				if(kegData.boxId==boxId)
					updateKegStats(kegData);
			});
			containerHandler.switchContainerToKeg(boxId);
		});
	};
	/*****
	 * Init push functionality
	 *****/
	var initPush = function(kegModel){
		ajax.connectToNewsUpdate(kegModel.boxId,function(pushResponse){
			var newsModel =  new ZMO.NewsModel(pushResponse);
			var type = newsModel.type;
			if(type.toLowerCase() == "login"){
				switchContainerToUser(newsModel);
			}else if(type.toLowerCase() == "draw"){
				updateUserStats(newsModel);
			}else if(type.toLowerCase() == "logout"){
				switchContainerToKeg(newsModel.boxId);
			}
		});
	};
	/*****
	 * Initialization
	 */
	var init = function(){
		ajax.getDatas(ZMO.modules.Constants.urls.FRONTPAGESTATS,function(fpStats){
			//general stats
			initGeneralStats(fpStats);
			//Kegcharts
			containerHandler = new ContainerHandler();
			var kegModelArr = new ZMO.modules.kegModel(fpStats.keg);
			$.each(kegModelArr,function(ind,kegData){
				initBox(kegData);
				initPush(kegData);
			});
		});
	};
/****
 * DEBUG
 */
	var count;
	var dummyInit = function(){
		count = 0;
		switchContainerToUser(new ZMO.NewsModel( {
	        "type": "DRAWING",
	        "image": "images/achievements/davidhasselhoff.png",
	        "date": 1355687887000,
	        "userId": 3,
	        "userName": "user-3",
	        "amount": count,
	        "kegId": 1,
	        "brand": "keg-brand-1",
	        "boxId":2
	    }));
	};
	
	var dummyUserStatsUpdate = function(){
		count = count + 0.2;
		updateUserStats(new ZMO.NewsModel( {
	        "type": "DRAWING",
	        "image": "images/achievements/davidhasselhoff.png",
	        "date": 1355687887000,
	        "userId": 3,
	        "userName": "user-3",
	        "amount": count,
	        "kegId": 1,
	        "brand": "keg-brand-1",
	        "boxId":2
	    }));
	}
	


	
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		container = $("<div>").addClass("stats frontPageStats");
		//headline
		$("<div>").addClass("newsdiv").html("<span>Stats</span>").appendTo(container);
		//newscut
		$("<div>").addClass("newscut").appendTo(container);
		
		return container;
	};
	var pub = {
			getInstance:getInstance,
			init:init,
			updateKegStats:updateKegStats,
			switchContainerToKeg:switchContainerToKeg,
			switchContainerToUser:switchContainerToUser,
			dummyInit:dummyInit,
			dummyUserStatsUpdate:dummyUserStatsUpdate
	};
	return pub;
}(jQuery,ZMO.modules.frontPageStatsView,ZMO.ajax));
