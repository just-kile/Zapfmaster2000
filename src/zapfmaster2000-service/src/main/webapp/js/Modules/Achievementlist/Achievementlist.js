/**
 *Achievement Stats Module
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.achievementlist = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var container;
	var onMembersReceive = function(achievement){
		container.empty();
		container.append("<div class=\"member-headline\">Achievement: " + achievement.achievementName + "</div>");
		var listDiv = $("<div>").addClass("statsdiv").appendTo(container);
		var table= $("<table>").addClass("stats-drinker").appendTo(listDiv);
		$.each(achievement.users,function(ind,val){
			var m = ich["ZMO-achievementsstats-list-template"](val);
			
			table.append(m);
		});
	};
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 * in jQuery Mobile it is called: onPageChange
	 */
	var init = function(data){
		//you can access with data.id to the wanted id
		ajax.enqueueDatas({
			url:mC.urls.ACHIEVEMENTSSTATS,
			callback:onMembersReceive,
			data:data,
			onlyOnce:true,
			rawData:true
		});
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
