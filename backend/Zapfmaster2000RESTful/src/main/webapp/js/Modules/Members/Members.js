/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.members = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var container;
	var onMembersReceive = function(members){
		$.each(members,function(ind,val){
			var m = ich["ZMO-members-template-box"](new ZMO.MemberModel(val));
			container.append(m);
		});
	};
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(){
		container.text("Hello drinkers worldwide! foo!");
		ajax.getDatas(mC.urls.MEMBERS,onMembersReceive)
	}
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		return (container = $("<div>").addClass("newsfeed"));
	}
	var pub = {
			getInstance:getInstance,
			init:init
	}
	return pub
}(jQuery,ZMO.ajax))
