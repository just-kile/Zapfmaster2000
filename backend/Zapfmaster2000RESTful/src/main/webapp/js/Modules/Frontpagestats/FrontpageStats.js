/**
 * Wrapper for frontpage stats boxes
 * Contains the real stats boxes
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.frontpagestats = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var keyContainerMap = {};
	var container=null;
	var updateNormalKegstats = function(kegModel){
		var kegStatsContainer = $(keyContainerMap[kegModel.keg_id]);
		var newContent = ich["ZMO-frontpagestats-template"](kegModel);
		kegStatsContainer.replaceWith(newContent);
		keyContainerMap[kegModel.keg_id] = newContent;
	};
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var initContainer = function(kegModel){
		return keyContainerMap[kegModel.keg_id]=$("<div>");
		
	};
	var init = function(){
		//container.text("Hello drinkers worldwide!");
		keyContainerMap = {};
		ajax.getDatas("tmp/kegstatus.json",function(kegDatas){
			$.each(kegDatas["keg"],function(ind,kegData){
				var kegModel = new ZMO.modules.KegModel(kegData); 
				var kegStatsContainer = initContainer(kegModel);
				container.append(kegStatsContainer);
				updateNormalKegstats(kegModel);
			});
		});
	};
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		container = $("<div>").addClass("stats");
		//headline
		$("<div>").addClass("newsdiv").html("<span>Stats</span>").appendTo(container);
		//newscut
		$("<div>").addClass("newscut").appendTo(container);
		
		return container;
	};
	var pub = {
			getInstance:getInstance,
			init:init
	};
	return pub
}(jQuery,ZMO.ajax));
