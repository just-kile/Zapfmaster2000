
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
function appReady(){
	head.js(
			"js/App/Constants.js",
			//Util
			"js/Util/Util.js",
			"js/Util/UtilConstants.js",
			"js/Util/Browser/Throbber.js",
			"js/Util/Browser/ChangePage.js",
			"js/Util/Date/TimeParser.js",
			"js/Util/Net/Ajax.js",
			"js/Util/Net/Ajax.js",
			"js/Util/Object/Exists.js",
			"js/Util/Notifications/Notifications.js",
			"js/Util/Mobile/OnlineRecognizer.js",
			"js/Util/Mobile/Store.js",
			"js/Util/Mobile/Animator.js",
			"js/Util/Mobile/FastButton.js",
			"js/Util/Localization/Localization.js",
			//"js/Util/Mobile/Scroller.js",
			
			
			"js/Util/Globalfunctions.js",

			//Modules
			"js/Modules/Model.js" ,
			"js/Modules/Constants.js",
			"js/Modules/Drawfeed/Drawfeed.js",
			"js/Modules/NavigationMobile/NavigationMobile.js",
			"js/Modules/Challenges/Challenges.js",
			"js/Modules/CreateChallenge/CreateChallengeView.js",
			"js/Modules/CreateChallenge/CreateChallenge.js",
			"js/Modules/ReceiveChallenge/ReceiveChallenge.js",
			"js/Modules/Logout/Logout.js" ,
			"js/Modules/Header/header.js" ,
			"js/Modules/SideNavigation/SideNavigation.js" ,
			"js/Modules/ChallengeUserList/ChallengeUserList.js" ,
			"js/Modules/Settings/Settings.js" ,
			//main
			"js/Modules/Core/View.js",
			"js/App/modules_properties.js",
			"js/App/SwipeHandler.js",
			"js/Modules/Core/Controller.js",
			
			function() {
				//init controller
				if(ZMO.controller)ZMO.controller.init();
				//init swipe control
				if(ZMO.swipeHandler)ZMO.swipeHandler.init();
			}
		);
};
if(typeof plugins!="undefined")document.addEventListener("deviceready", appReady, false);
else appReady();