
/**
* Main Initializer for App
*
* @class MyClass
* @constructor
*/
var ZMO = ZMO || {};
/**
 * Loads all js and starts, when finished
 */
(function(){
	head.js(
			"js/App/Model/Constants.js",
			//Util
			"js/Modules/Util/Util.js",
			"js/Modules/Util/Browser/Throbber.js",
			"js/Modules/Util/Browser/ChangePage.js",
			"js/Modules/Util/Net/Ajax.js",
			"js/Modules/Util/Net/Ajax.js",
			"js/Modules/Util/Object/Exists.js",
			"js/Modules/Util/Globalfunctions.js",
			//Modules
			"js/Modules/Model.js" ,
			"js/Modules/Stats/StatsModels.js",
			"js/Modules/Constants.js",
			"js/Modules/Drawfeed/Drawfeed.js",
			"js/Modules/NavigationMobile/NavigationMobile.js",
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
			"js/Modules/CreateChallenge/CreateChallengeView.js",
			"js/Modules/CreateChallenge/CreateChallenge.js",

			//main
			"js/App/Model/Model.js",
			"js/App/View/View.js",
			"js/App/modules_properties.js",
			"js/App/Controller/Controller.js",
			function() {
				if(ZMO.controller)ZMO.controller.init();
		});
})();