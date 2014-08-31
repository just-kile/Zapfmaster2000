var ZMO = ZMO || {};
/**
 * Loads all js and starts, when finished
 */
baseUrl = "";
(function(){
	head.js(//core models
			"js/Website/Constants.js" ,
			"js/Modules/Model.js" ,
			//utils
			"js/Util/Util.js",
			"js/Util/UtilConstants.js",
			"js/Util/Browser/Throbber.js",
			"js/Util/Browser/ChangePage.js",
			"js/Util/Date/TimeParser.js",
			"js/Util/Net/Ajax.js",
			"js/Util/Object/Exists.js",
			"js/Util/Localization/Localization.js",
			"js/Util/Globalfunctions.js",
			"js/Util/Browser/Scroller.js",
			//modules
			"js/Modules/Stats/StatsModels.js",
			"js/Modules/Constants.js",
			"js/Modules/Drawfeed/Drawfeed.js",
			"js/Modules/Navigation/Navigation.js",
			"js/Modules/Stats/General/Kegstatus/KegstatusView.js",
			"js/Modules/Stats/General/Kegstatus/Kegstatus.js",
			"js/Modules/Stats/General/LineChart/LineChartView.js",
			"js/Modules/Stats/General/LineChart/LineChart.js" ,
			"js/Modules/Stats/General/Bestlist/BestlistView.js",
			"js/Modules/Stats/General/Bestlist/Bestlist.js",
			"js/Modules/Stats/General/AchievementBestlist/AchievementBestlist.js",
			"js/Modules/Stats/User/UserStats/UserStats.js",
			"js/Modules/Stats/User/UserInfo/UserInfo.js",
			"js/Modules/Stats/User/UserAchievements/UserAchievements.js",
			"js/Modules/Challenges/Challenges.js",
			"js/Modules/Frontpagestats/FrontpageStatsView.js",
			"js/Modules/Frontpagestats/FrontpageStats.js",
			"js/Modules/Members/Members.js",
			"js/Modules/Achievements/Achievements.js",
			"js/Modules/Achievementstats/Achievementstats.js",
			"js/Modules/Achievementlist/Achievementlist.js",
			"js/Modules/Logout/Logout.js",
			//page definition properties
			"js/Website/modules_properties.js",
			//Core2
			"js/Modules/Core/View.js" ,
			"js/Modules/Core/Controller.js",
			function() {
				//execute core controller
				if(ZMO.controller)ZMO.controller.init();
				else alert("Problems with fileloading!");
				
		});
})();


var Changer = Changer || {};
Changer.hashs = ["#","#stats","#challenges"];
Changer.times = [30000,30000,15000];
//Changer.times = [6000,6000,1500];
Changer.count = 0;
Changer.offset = 322;
Changer.startChangeAnimation = function(){
	
	
	setTimeout(function(){
		Changer.count++;
		window.location.href=Changer.hashs[Changer.count%Changer.hashs.length];
		setTimeout(function(){
			$('body').animate({scrollTop: Changer.offset}, 500,function(){
				
			});
		},200);
		Changer.startChangeAnimation();
	},Changer.times[Changer.count%Changer.hashs.length]);
}
