/**
 * Wrapper for frontpage stats boxes
 * Contains the real stats boxes
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.frontpagestats = (function($,ajax){
	var mC = ZMO.modules.Constants;
	
	var container;
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(){
		//container.text("Hello drinkers worldwide!");
	}
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		container = $("<div>").addClass("stats");
		//headline
		$("<div>").addClass("newsdiv").html("<span>Stats</span>").appendTo(container);
		//newscut
		$("<div>").addClass("newscut").appendTo(container);
		
		return container;
	}
	var pub = {
			getInstance:getInstance,
			init:init
	}
	return pub
}(jQuery,ZMO.ajax))
