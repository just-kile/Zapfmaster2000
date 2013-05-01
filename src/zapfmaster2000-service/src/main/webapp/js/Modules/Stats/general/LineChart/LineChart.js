/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.lineChart = (function($,view,ajax){
	var mC = ZMO.modules.Constants;
	var chartID = "ZMO-stats-linechart";
	var container = null,chartContainer = null;
	var onDatasLoaded = function(statsModel){
		var progressModel = statsModel.progress;
		view.init(chartContainer);
		view.createLineChart(progressModel);
		
	};
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(){
		//container.text("Hello drinkers worldwide!");
		//register for dataupdate
		//@see Core/Controller/Util/Net/Ajax.js --> enqueueData
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
		container =  $("<div>").addClass("linechart");
		chartContainer =  $("<div>").attr("id",chartID).appendTo(container);
		return container;
	};
	var pub = {
			getInstance:getInstance,
			init:init
	};
	return pub;
}(jQuery,ZMO.modules.lineChartView,ZMO.ajax));
