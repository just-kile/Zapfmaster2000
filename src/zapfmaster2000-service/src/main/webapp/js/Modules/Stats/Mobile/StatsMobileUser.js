/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.statsMobileUser = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var container =null;
	
	var refreshStats = function(isUserStats){
		if(isUserStats){
			var url = mC.urls.USERSTATS;
			ajax.getDatas(url,function(json){
				var globalStatsModel =new ZMO.modules.StatsModel(json);
				
			});
		}else{
			var url = mC.urls.STATS;
			ajax.getDatas(url,function(json){
				var globalStatsModel =new ZMO.modules.StatsModel(json);
				
			});
		}
	};
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(hashParams,moduleParams){
		container.text("Hello drinkers worldwide! User stats");
		refreshStats();
	};
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		return (container = $("<div class='statsDiv'>"));
	};
	var pub = {
			getInstance:getInstance,
			init:init
	};
	return pub;
}(jQuery,ZMO.ajax));
