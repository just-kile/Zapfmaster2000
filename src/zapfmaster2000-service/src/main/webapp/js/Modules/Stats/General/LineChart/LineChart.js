/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.lineChart = (function($,view,ajax){
	var mC = ZMO.modules.Constants;
	var progressPointsNumber = mC.stats.lineChartPoints;
	var chartID = "ZMO-stats-linechart";
	var container = null,chartContainer = null,sliderContainer = null;
	var userId = null;
	var onDatasLoaded = function(statsModel,isProgress){
		if(userId){
			statsModel = new ZMO.modules.UserStatsModel(statsModel);
		}
		if(isProgress){
			statsModel = {
					progress:new ZMO.modules.ProgressModel(statsModel)
			}
		}
		var progressModel = statsModel.progress;
		view.init(chartContainer);
		
		var minusMinutes = -mC.stats.lineChartStartDateDays*24*60;
		var max = new ZMO.TimeParser(new Date());
		var min = max.getTimeParserAddMins(minusMinutes);
		
		var defaultMinusMinutes =  -mC.stats.lineChartMinBoundDays*24*60;
		var maxBound = new ZMO.TimeParser(new Date());
		var minBound = max.getTimeParserAddMins(defaultMinusMinutes);
		
		view.initSlider(sliderContainer,progressModel,sliderValueChanged,{
			min:minBound.getDate(),
			max:maxBound.getDate()
		},{
			min:min.getDate(),
			max:max.getDate()
		});
		view.createLineChart(progressModel);
		
	};
	var calcInterval = function(minTimestamp,maxTimestamp){
		return Math.round((maxTimestamp-minTimestamp)/(1000*60*progressPointsNumber));
	}
	var sliderValueChanged = function(e,data){
		var max = new ZMO.TimeParser(new Date(data.values.max));
		var min = new ZMO.TimeParser(new Date(data.values.min));
		var interval = calcInterval(min.getTimestamp(),max.getTimestamp());
		var data = {
				progressFrom:min.getServerTimeFormat(),
				progressTo:max.getServerTimeFormat(),
				progressInterval:interval
			}
//		if(!userId){
//			ajax.getDatas("rest/statistics/progress",function(data){
//				onDatasLoaded(data,true);
//			},{
//				from:min.getServerTimeFormat(),
//				to:max.getServerTimeFormat()
//			}
//			);
//		}else{
//			//user Line chart
//			ajax.enqueueDatas({
//				url:"rest/statistics/drinkProgress",
//				callback:onDatasLoaded,
//				data:{
//					user:userId,
//					
//				},
//				rawData:true,
//			});
//			ajax.startPull();
//		}
		if(!userId){
			ajax.updateEnqueueParams(mC.urls.STATS,data);
		}else{
			ajax.updateEnqueueParams(mC.urls.USERSTATS,data);
		}
		
		ajax.updateEnqueueDatas();
	}
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(hashParams){
		userId = hashParams.id;
		var minusMinutes = -mC.stats.lineChartStartDateDays*24*60;
		var max = new ZMO.TimeParser(new Date());
		var min = max.getTimeParserAddMins(minusMinutes);
		
		var	interval =  calcInterval(min.getTimestamp(),max.getTimestamp()) ;
		var data = {
				progressFrom:min.getServerTimeFormat(),
				progressTo:max.getServerTimeFormat(),
				progressInterval:interval
			}
		//container.text("Hello drinkers worldwide!");
		//register for dataupdate
		//@see Util/Net/Ajax.js --> enqueueData
		if(!userId){
			ajax.enqueueDatas({
				url:mC.urls.STATS,
				callback:onDatasLoaded,
				data:data
			});
			ajax.startPull();
		}else{
			//user Line chart
			ajax.enqueueDatas({
				url:mC.urls.USERSTATS,
				callback:onDatasLoaded,
				data:$.extend({
					user:userId,
				},data),
				rawData:true,
			});
			ajax.startPull();
		}
	};
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		container =  $("<div>").addClass("linechart");
		chartContainer =  $("<div>").attr("id",chartID).appendTo(container);
		sliderContainer =$("<div>").appendTo(container);
		return container;
	};
	var remove = function(){
		
		view.resetSlider();
	}
	var pub = {
			getInstance:getInstance,
			init:init,
			remove:remove
	};
	return pub;
}(jQuery,ZMO.modules.lineChartView,ZMO.ajax));
