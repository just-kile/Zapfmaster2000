ZMO.modules = ZMO.modules || {};

ZMO.modules.kegstatus = (function($,view,ajax){
	var mC = ZMO.modules.Constants,container=null,chartContainer=null,barChartID ="ZMO-stats-barchart"; ;
	
	var onDatasLoaded =function(datas){
		var statsModel = new ZMO.modules.StatsModel(datas);
		var keglistModel = statsModel.keg;
		view.init();
		view.createBarChart(keglistModel,chartContainer);
		//view.createBestlist(keglistModel,bestlistContainer);
		
	};
	var init = function(){
		ajax.enqueueDatas(mC.urls.STATS,onDatasLoaded);
		ajax.startPull();
	};
	var getInstance = function(){
		container = $("<div>").addClass("stats");
		//bar chart container
		chartContainer = $("<div>").addClass("statsDiv").attr("id",barChartID).appendTo(container);
		//bestlist container
		//bestlistContainer = $("<div>").addClass("statsDiv").appendTo(container);
		
		return container;
	};
	var pub = {
			getInstance:getInstance,
			init:init
	};
	return pub;
}(jQuery,ZMO.modules.kegstatusView,ZMO.ajax));
