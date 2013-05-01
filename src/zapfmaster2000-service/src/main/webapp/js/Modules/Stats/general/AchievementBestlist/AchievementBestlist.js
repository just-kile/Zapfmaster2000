/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.achievementBestlist = (function($,view,ajax){
	var mC = ZMO.modules.Constants;
	var bestlistContainer = null,drawCountContainer = null;
	var container =null;
	var onDatasLoaded = function(statsModel){
		var userlistModel = statsModel.achievementUserList;
		//view.createPieChart(userlistModel,chartContainer);
		bestlistContainer.empty();
		view.createBestlist(userlistModel,bestlistContainer," Achievements","Achievement Bestlist");
		drawCountContainer.empty();
		view.createBestlist(statsModel.drawCountUserList,drawCountContainer," x","Counts Bestlist");
		
	};
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(){
		//container.text("Hello drinkers worldwide!");
		ajax.enqueueDatas({
			url:mC.urls.STATS,
			callback:onDatasLoaded
		})
		ajax.startPull();
	};
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		container = $("<div class='stats stats-achievements'>");
		//Achievements bestlist
		bestlistContainer = $("<div>").addClass("statsDiv").appendTo(container);
		//DrawCount bestlist
		drawCountContainer = $("<div>").addClass("statsDiv").appendTo(container);
		
		return container;
	};
	var pub = {
			getInstance:getInstance,
			init:init
	};
	return pub;
}(jQuery,ZMO.modules.bestlistView,ZMO.ajax));
