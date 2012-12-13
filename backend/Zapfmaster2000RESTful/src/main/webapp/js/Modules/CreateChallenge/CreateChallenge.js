/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.createChallenge = (function($,Ajax){
	var c = ZMO.modules.Constants;
	var container;
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(){
		Ajax.getDatas("tmp/challenges.json",function(resp){
			
		});
	}
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		return container = $("<div>").addClass("newsfeed");
	}
	var pub = {
			getInstance:getInstance,
			init:init
	}
	return pub
}(jQuery,ZMO.ajax))
