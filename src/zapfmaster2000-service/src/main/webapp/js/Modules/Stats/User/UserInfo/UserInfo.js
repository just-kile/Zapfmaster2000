/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.userinfo = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var container =null;
	var onDatasLoaded = function(data){
		container.empty();
		var statsModel = new ZMO.modules.UserStatsModel(data);
	
		var user = statsModel.user;
		var nameContainer = $("<div>").addClass("statsDiv username").text(user.userName+" ("+user.userId+")");
		
		var imageContainer = $("<div>").addClass("statsDiv").appendTo(container);
		var img = $("<img>").attr("src",baseUrl+user.userImage+"/big");
		img.css({
			height:"160px",
			width:"160px"
		});
		img.appendTo(imageContainer);
		
		container.append(nameContainer).append(imageContainer);
		
	}
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(hashParams,moduleParams){
		if(ZMO.throbber)ZMO.throbber.show();
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
	};
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		container = $("<div class='stats stats-bestlist'>");
		
		return container;
	};
	var pub = {
			getInstance:getInstance,
			init:init
	};
	return pub;
}(jQuery,ZMO.ajax));
