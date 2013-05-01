/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.userstats = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var container =null;
	var statsContainer = null;
	var generateObjGlobalStats = function(statsModel){
		return {
			achievementRank:statsModel.rank.achievements,
			amountRank:statsModel.rank.amount,
			drawCountRank:statsModel.rank.drawCount,
			promille:statsModel.promille,
			drawCount:statsModel.drawCount.operations,
			drawCountAverage:statsModel.drawCount.average,
			amount:statsModel.amount.complete,
			amountGreatestDrawing:statsModel.amount.once,
			mostActiveHour:statsModel.amount.mostActivityHour,
			
			};
	};
	var onDatasLoaded = function(data){
		var userStatsModel = new ZMO.modules.UserStatsModel(data);
		var content = ich["ZMO-stats-mobile-user-general"](generateObjGlobalStats(userStatsModel));
		content.appendTo(statsContainer);
	}
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(hashParams,moduleParams){
		userId = hashParams.id;
		ajax.enqueueDatas({
			url:mC.urls.USERSTATS,
			callback:onDatasLoaded,
			data:{
				user:userId,
			},
			rawData:true,
		});
		ajax.startPull();
		//container.text("Userstats");
	};
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		container = $("<div class='stats stats-kegstatus'>");
		statsContainer = $("<div>").addClass("statsDiv").appendTo(container);
		return container;
	};
	var pub = {
			getInstance:getInstance,
			init:init
	};
	return pub;
}(jQuery,ZMO.ajax));
