ZMO = ZMO || {};
ZMO.Utils = ZMO.Utils || {};

ZMO.Utils.animator = (function($,document){
	var callback = null,openPanel = false;
	var getCallback = function(){
		return callback;
	};
	var setCallback = function(c){
		callback = c;
	};
	var sCont=null;
	var getContainer = function(){
		return sCont;
	};
	var setContainer = function(container){
		return sCont = container;
	};
	var prepareContainer = function(sideContainer){
		sideContainer.addClass("animate");
		$(sideContainer)[0].addEventListener( 'webkitTransitionEnd', 
			    function( event ) {

			if(getCallback())getCallback()();
			}, false );
		
	};
	/**
	 * Animates the left open navigation
	 */
	var animateLeft = function(mainContainer,sideContainer,callback){
		setCallback(callback);
		setContainer(sideContainer);

		sideContainer.show();
		
		sideContainer.addClass("animateSide");
		openPanel = true;
	};
	var animateLeftRev = function(mainContainer,sideContainer,callback){
		setCallback(callback);
		sideContainer.removeClass("animateSide animateChall");
		openPanel = false;
	};

	var animateRight = function(mainContainer,sideContainer,callback){
		setCallback(callback);
		setContainer(sideContainer);

		sideContainer.addClass("animateChall");
		openPanel = true;
	};
	var animateRightRev = function(mainContainer,sideContainer,callback){
		setCallback(callback);
		sideContainer.removeClass("animateSide animateChall");
		openPanel = false;
	};
	var init = function(container){
		prepareContainer(container);
	}
	var isPanelOpen = function(){
		return openPanel;
	}
	var pub = {
			isPanelOpen:isPanelOpen,
			animateRight:animateRight,
			animateRightRev:animateRightRev,
			animateLeft:animateLeft,
			animateLeftRev:animateLeftRev,
			init:init
	};
	return pub;
})(jQuery,document);
