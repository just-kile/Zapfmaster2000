/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.dummy = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var container =null;
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(hashParams,moduleParams){
		container.text("Hello drinkers worldwide!");
	};
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		return (container = $("<div class='statsDiv'>"));
	};
	/**
	 * @optional
	 * Gets called when module is removed from DOM
	 */
	var remove = function(){
		alert("Module removed!")
	}
	var pub = {
			getInstance:getInstance,
			init:init,
			remove:remove
	};
	return pub;
}(jQuery,ZMO.ajax));
