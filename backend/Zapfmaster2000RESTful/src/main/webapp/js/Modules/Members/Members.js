/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.members = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var container;
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(){
		container.text("Hello drinkers worldwide!")
	}
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		return (container = $("<div>"));
	}
	var pub = {
			getInstance:getInstance,
			init:init
	}
	return pub
}(jQuery,ZMO.ajax))
