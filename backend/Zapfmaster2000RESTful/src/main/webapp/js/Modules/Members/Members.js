/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.members = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var container;
	var onMembersReceive = function(members){
		
	};
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(){
		container.text("Hello drinkers worldwide!");
		container.append("<div>");
		ajax.getDatas(mC.urls.MEMBERS,onMembersReceive)
	}
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		return (container = $("<div class='news-backgrnd'>"));
	}
	var pub = {
			getInstance:getInstance,
			init:init
	}
	return pub
}(jQuery,ZMO.ajax))
