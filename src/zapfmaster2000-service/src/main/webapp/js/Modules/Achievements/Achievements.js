/**
 *Achievement Overview Module
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.achievements = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var container;
	var onAchievementsReceive = function(members){
		container.append("<div class=\"member-headline\">Achievements: " + members.length + "</div>");
		$.each(members,function(ind,val){
			var m = ich["ZMO-achievements-template-box"](new ZMO.AchievementsModel(val));
			container.append(m);
		});
	};
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(){
		if(ZMO.throbber)ZMO.throbber.show();
		ajax.getDatas(mC.urls.ACHIEVEMENTS,onAchievementsReceive)
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
