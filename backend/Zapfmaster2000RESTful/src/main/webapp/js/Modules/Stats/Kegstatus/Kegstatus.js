ZMO.modules = ZMO.modules || {};

ZMO.modules.kegstatus = (function($,view,ajax){
	var mC = ZMO.modules.Constants,container=null,drinkstatsContainer=null,chartContainer=null,generalStatsContainer=null,barChartID ="ZMO-stats-barchart";
	
	var onDatasLoaded =function(statsModel){
		var keglistModel = statsModel.keg;
		view.init();
		view.createBarChart(keglistModel,statsModel.amount,chartContainer);
		drinkstatsContainer.empty();
		view.createDrinkstats(statsModel,drinkstatsContainer);
		generalStatsContainer.empty();
		view.createGeneralStats(statsModel,generalStatsContainer);
		
	};
	var init = function(){
		ajax.enqueueDatas({
			url:mC.urls.STATS,
			callback:onDatasLoaded
		});
		ajax.startPull();
	};
	var getInstance = function(){
		container = $("<div>").addClass("stats stats-kegstatus");
		//bar chart container
		chartContainer = $("<div>").addClass("statsDiv").attr("id",barChartID).appendTo(container);
		//bestlist container
		drinkstatsContainer = $("<div>").addClass("statsDiv").appendTo(container);
		//general stats
		generalStatsContainer = $("<div>").addClass("statsDiv").appendTo(container);
		return container;
	};
	var pub = {
			getInstance:getInstance,
			init:init
	};
	return pub;
}(jQuery,ZMO.modules.kegstatusView,ZMO.ajax));
