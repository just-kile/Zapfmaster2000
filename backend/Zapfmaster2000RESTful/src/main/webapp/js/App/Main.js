var ZMO = ZMO || {};
/**
 * Loads all js and starts, when finished
 */
(function(){
	head.js(
			"js/App/Model/Constants.js",
			"js/Modules/Util/Util.js",
			"js/Modules/Util/Browser/Throbber.js",
			"js/Modules/Util/Net/Ajax.js",
			"js/Modules/Util/Object/Exists.js",
			"js/Modules/Util/Globalfunctions.js",
			//main
			
			"js/App/Model/Model.js",
			"js/App/View/View.js",
			"js/App/Controller/Controller.js",
			function() {
				if(ZMO.controller)ZMO.controller.init();
		});
})();