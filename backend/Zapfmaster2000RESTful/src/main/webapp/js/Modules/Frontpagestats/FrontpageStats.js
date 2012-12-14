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
		var add = function(kegModel,cont,chart){
			keyContainerMap[kegModel.keg_id] = {
					model :kegModel,
					container:cont,
					chart:chart
			};
		};
		var update = function(kegModel,cont,chart){
			if(kegModel){
				keyContainerMap[kegModel.keg_id]["model"] = kegModel;
				if(cont)keyContainerMap[kegModel.keg_id]["container"] = cont;
				if(chart)keyContainerMap[kegModel.keg_id]["chart"] = chart;
			}
		};
		var getChart = function(kegModel){
			return keyContainerMap[kegModel.keg_id]["chart"];
		};
		var getContainer = function(kegModel){
			return keyContainerMap[kegModel.keg_id]["container"];
		};
		var getModelToId = function(kegId){
			return keyContainerMap[kegId]["model"];
		};
		this.add = add;
		this.update = update;
		this.getChart = getChart;
		this.getContainer = getContainer;
		this.getModelToId = getModelToId;
	};
	
	/*****
	 * Keg chart overview creation/update
	 *****/
	var updateChart = function(kegModel){
		view.updateChart(kegModel,containerHandler.getChart(kegModel));
	};
	var updateNormalKegstats = function(kegModel){
		var kegStatsContainer = $(containerHandler.getContainer(kegModel)).find(".text");
		var newContent = $(ich["ZMO-frontpagestats-template"](kegModel));
		kegStatsContainer.replaceWith(newContent);
	};
	var updateKegStats = function(kegModel){
		updateNormalKegstats(kegModel);
		updateChart(kegModel);
	};
	
	var initContainer = function(kegModel){
		return $("<div>").addClass("news-drink-box");
		
	};
	var createChart = function(cont,kegModel){
		var chartContainer = $("<div>").addClass("chart").attr("id",chartIdPrefix+kegModel.keg_id);
		cont.append(chartContainer);
		return view.createBarChart(kegModel,chartContainer);
	};
	var initKegStats = function(kegModel){
		//create basic container
		var kegStatsContainer = initContainer(kegModel);
		container.append(kegStatsContainer);
		//Append Chart
		var chart = createChart(kegStatsContainer,kegModel);
		//Append text container
		kegStatsContainer.append($("<div>").addClass("text"));
		//Add Container to store
		containerHandler.add(kegModel,kegStatsContainer,chart);
		//Fill text container
		updateNormalKegstats(kegModel);
	};
	/*****
	 * User Draws chart creation
	 *****/
	
	
	
	
	
	
	/*****
	 * Switch functionality
	 *****/
	var switchContainer =function(kegId){
		
	}
	/*****
	 * Initialization
	 */
	var init = function(){
		ajax.getDatas("tmp/stats.json",function(kegDatas){
			containerHandler = new ContainerHandler();
			$.each(kegDatas["keg"],function(ind,kegData){
				var kegModel = new ZMO.modules.KegModel(kegData); 
				initKegStats(kegModel);
			});
		});
	};
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
			updateKegStats:updateKegStats
	};
	return pub;
}(jQuery,ZMO.modules.frontPageStatsView,ZMO.ajax));
