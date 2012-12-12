var ZMO = ZMO || {};
/**
 * Loads all js and starts, when finished
 */
(function(){
	head.js("js/App/Controller/Controller.js",
			"js/App/Model/Model.js",
			"js/App/View/View.js",
			function() {
				if(ZMO.controller)ZMO.controller.init();
		});
})();