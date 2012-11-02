/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.dummy = (function($,Ajax){
	var c = ZMO.modules.Constants;
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(){
		
	}
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		return $("<div>");
	}
	var pub = {
			getInstance:getInstance,
			init:init
	}
	return pub
}(jQuery,ZMO.ajax))
