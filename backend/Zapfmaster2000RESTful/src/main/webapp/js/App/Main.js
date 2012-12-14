
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
			"js/Util/Util.js",
			"js/Util/UtilConstants.js",
			"js/Util/Browser/Throbber.js",
			"js/Util/Browser/ChangePage.js",
			"js/Util/Net/Ajax.js",
			"js/Util/Net/Ajax.js",
			"js/Util/Object/Exists.js",
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
			//main
			"js/App/Model/Model.js",
			"js/App/View/View.js",
			"js/App/modules_properties.js",
			"js/App/Controller/Controller.js",
			function() {
				if(ZMO.controller)ZMO.controller.init();
		});
})();