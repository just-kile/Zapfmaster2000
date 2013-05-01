/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.userinfo = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var container =null;
	var onDatasLoaded = function(data){
		var statsModel = new ZMO.modules.UserStatsModel(data);
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
		container.text("UserInfo");
	};
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		container = $("<div class='stats stats-bestlist'>");
		statsContainer = $("<div>").addClass("statsDiv").appendTo(container);
		return container;
	};
	var pub = {
			getInstance:getInstance,
			init:init
	};
	return pub;
}(jQuery,ZMO.ajax));
