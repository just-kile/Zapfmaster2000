/**
 *Achievement Stats Module
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.achievementlist = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var container;
	var onMembersReceive = function(members){
		container.append("<div class=\"member-headline\">Members: " + members.length + "</div>");
		$.each(members,function(ind,val){
			var m = ich["ZMO-members-template-box"](new ZMO.MemberModel(val));
			container.append(m);
		});
	};
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 * in jQuery Mobile it is called: onPageChange
	 */
	var init = function(data){
		//you can access with data.id to the wanted id
		var text = JSON.stringify(data);
		container.append(text);
		ajax.enqueueDatas(mC.urls.MEMBERS,onMembersReceive,data,true,true);
		ajax.startPull();
	};
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		return (container = $("<div>").addClass("newsfeed"));
	};
	var pub = {
			getInstance:getInstance,
			init:init
	};
	return pub;
}(jQuery,ZMO.ajax));
