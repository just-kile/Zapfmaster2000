/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.lineChart = (function($,view,ajax){
	var mC = ZMO.modules.Constants;
	var chartID = "ZMO-progress-chart";
	var container = null;
	var onDatasLoaded = function(data){
		var statsModel = new ZMO.modules.StatsModel(data);
		var progressModel = statsModel.progress;
		view.init(container);
		view.createLineChart(progressModel);
		
	};
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(){
		//container.text("Hello drinkers worldwide!");
		//register for dataupdate
		ajax.enqueueDatas(mC.urls.STATS,onDatasLoaded);
		ajax.startPull();
	};
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		return (container =  $("<div>").addClass("linechart").attr("id",chartID));
	};
	var pub = {
			getInstance:getInstance,
			init:init
	};
	return pub;
}(jQuery,ZMO.modules.lineChartView,ZMO.ajax));
