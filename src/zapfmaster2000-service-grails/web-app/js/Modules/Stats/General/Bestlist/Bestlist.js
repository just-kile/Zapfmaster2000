/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.bestlist = (function($,view,ajax){
	var mC = ZMO.modules.Constants;
	var container =null,chartContainer=null,bestlistContainer=null,pieChartID= "ZMO-stats-piechart";
	var onDatasLoaded = function(statsModel){
		var userlistModel = statsModel.bestUserList;
		view.init();
		view.createPieChart(userlistModel,chartContainer);
		bestlistContainer.empty();
		view.createBestlist(userlistModel,bestlistContainer);
	};
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(){
		//enqueue datas, so that after 30s datas will be updated
		ajax.enqueueDatas({
			url:mC.urls.STATS,
			callback:onDatasLoaded
		});
		ajax.startPull();
	};
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		container = $("<div>").addClass("stats").addClass("stats-bestlist");
		//headline
		var headlineText = ZMO.Util.localization.translateString("generalStats")
		$("<div>").addClass("newsdiv").html("<span>"+headlineText+"</span>").appendTo(container);
		//newscut
		$("<div>").addClass("newscut").appendTo(container);
		//chart container
		chartContainer = $("<div>").addClass("statsDiv").attr("id",pieChartID).appendTo(container);
		//bestlist container
		bestlistContainer = $("<div>").addClass("statsDiv").appendTo(container);
		return container;
	};
	var remove = function(){
		view.clearCharts();
	}
	var pub = {
			getInstance:getInstance,
			init:init,
			remove:remove
	};
	return pub;
}(jQuery,ZMO.modules.bestlistView,ZMO.ajax));
