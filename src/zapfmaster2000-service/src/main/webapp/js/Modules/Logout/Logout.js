/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.logout = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var container =null;
	
	var logout = function(){
		var baseUrl = localStorage.getItem("zm-serverurl");
		localStorage.clear();
		if(baseUrl!=null)localStorage.setItem("zm-serverurl",baseUrl);
		//var baseUrl = window.location.href.replace(new RegExp("(/[a-zA-Z]*.html)"),"");
		if(mC.unregisterPushService && window.device &&( window.device.platform == 'android' || window.device.platform == 'Android')){
			window.plugins.pushNotification.unregister(function(){
				ZMO.logger.log("App unregister success");
				window.location.replace("index.html");
			}, function(){
				ZMO.logger.log("App unregister failed");
				window.location.replace("index.html");
			});
		}else{
			window.location.replace("index.html");
		}
		
	};
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(hashParams,moduleParams){
		//container.text("Logout").on("mouseup",logout);
		logout();
		if(ZMO.throbber)ZMO.throbber.hide();
		
	};
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		return (container = $("<div>").addClass("zmo-clickbutton").css("margin-top","3em"));
	};
	var pub = {
			getInstance:getInstance,
			init:init,
			logout:logout
	};
	return pub;
}(jQuery,ZMO.ajax));
