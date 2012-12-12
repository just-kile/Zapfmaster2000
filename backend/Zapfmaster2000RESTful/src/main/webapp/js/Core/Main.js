var ZMO = ZMO || {};
/**
 * Loads all js and starts, when finished
 */
(function(){
	head.js(//core models
			"js/Core/Model/Constants.js" ,
			"js/Core/Model/Model.js",
			"js/Modules/Model.js" ,
			//utils
			"js/Core/Controller/Util/Util.js",
			"js/Core/Controller/Util/Browser/Throbber.js",
			"js/Core/Controller/Util/Net/Ajax.js",
			"js/Core/Controller/Util/Object/Exists.js",
			"js/Core/Controller/Globalfunctions.js",
			//modules
			"js/Modules/Stats/StatsModels.js",
			"js/Modules/Constants.js",
			"js/Modules/Drawfeed/Drawfeed.js",
			"js/Modules/Navigation/Navigation.js",
			"js/Modules/Stats/Kegstatus/KegstatusView.js",
			"js/Modules/Stats/Kegstatus/Kegstatus.js",
			"js/Modules/Stats/LineChart/LineChartView.js",
			"js/Modules/Stats/LineChart/LineChart.js" ,
			"js/Modules/Stats/Bestlist/BestlistView.js",
			"js/Modules/Stats/Bestlist/Bestlist.js",
			"js/Modules/Stats/AchievementBestlist/AchievementBestlist.js",
			"js/Modules/Challenges/Challenges.js",
			"js/Modules/Frontpagestats/FrontpageStats.js",
			"js/Modules/Members/Members.js",
			"js/Modules/Achievements/Achievements.js",
			"js/Modules/Achievementstats/Achievementstats.js",
			"js/Modules/Achievementlist/Achievementlist.js",
			//page definition properties
			"js/Core/modules_properties.js",
			//Core2
			"js/Core/View/View.js" ,
			"js/Core/Controller/Controller.js",
			function() {
				if(ZMO.controller)ZMO.controller.init();
		});
})();