/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.logout = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var container =null;
	
	var logout = function(){
		localStorage.clear();
		//var baseUrl = window.location.href.replace(new RegExp("(/[a-zA-Z]*.html)"),"");
		window.location.href="index.html";
	};
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(hashParams,moduleParams){
		container.text("Logout").on("mouseup",logout);
	};
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		return (container = $("<div>").addClass("ZMO-logout"));
	};
	var pub = {
			getInstance:getInstance,
			init:init
	};
	return pub;
}(jQuery,ZMO.ajax));
