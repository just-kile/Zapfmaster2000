/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.userachievements = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var container =null;
	var statsContainer = null;
	var generateObjAchievements = function(statsModel){
		return {
			achievementCount:statsModel.achievements.count,
			mostAchievementHour:statsModel.achievements.mostAchievementHour,
			};
	}
	var onDatasLoaded = function(data){
		container.empty();
		var userStatsModel = new ZMO.modules.UserStatsModel(data);
		var achievements = userStatsModel.achievements;
		 var globalStatsContainer = ich["ZMO-stats-mobile-user-achievement"](generateObjAchievements(userStatsModel));
		 $(globalStatsContainer).addClass("statsDiv");
		 var achievementsContainer = $("<div>").addClass("statsDiv");
		 
		 var content =container;
		 content.append(globalStatsContainer).append(achievementsContainer);
		 $.each(achievements.achievements,function(ind,achievement){
			 $("<img>").css("cursor","pointer").attr({
				 "src":achievement.achievementImage,
				 "title":achievement.achievementName
			 }).on(mC.clickEvent,function(){
				ZMO.changePage("#achievement?id="+achievement.achievementId);
			 }).appendTo(achievementsContainer);
		 });
		 //container.append(content);
		 
		
	};
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(hashParams,moduleParams){
		userId = hashParams.id;
		ajax.enqueueDatas({
			url:mC.urls.USERSTATS,
			callback:onDatasLoaded,
			data:{
				user:userId,
			},
			rawData:true,
		});
		ajax.startPull();
		//container.text("UserAchievements");
	};
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		container = $("<div class='stats stats-achievements'>");
		return container;
	};
	var pub = {
			getInstance:getInstance,
			init:init
	};
	return pub;
}(jQuery,ZMO.ajax));
