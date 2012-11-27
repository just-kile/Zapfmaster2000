/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.bestlist = (function($,view,ajax){
	var mC = ZMO.modules.Constants;
	var container =null,chart=null,pieChartID= "ZMO-stats-piechart";
	var onDatasLoaded = function(data){
		var statsModel = new ZMO.modules.StatsModel(data);
		var userlistModel = statsModel.bestUserList;
		view.init(chart);
		view.createPieChart(userlistModel);
		
	};
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(){
		//container.text("Hello drinkers worldwide!");
		ajax.enqueueDatas(mC.urls.STATS,onDatasLoaded);
		ajax.startPull();
	};
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		container = $("<div>").addClass("stats");
		//headline
		$("<div>").addClass("newsdiv").html("<span>Allgemeine Stats.</span>").appendTo(container);
		//newscut
		$("<div>").addClass("newscut").appendTo(container);
		chart = $("<div>").addClass("statsDiv").attr("id",pieChartID).appendTo(container);
		return container;
	};
	var pub = {
			getInstance:getInstance,
			init:init
	};
	return pub;
}(jQuery,ZMO.modules.bestlistView,ZMO.ajax));
