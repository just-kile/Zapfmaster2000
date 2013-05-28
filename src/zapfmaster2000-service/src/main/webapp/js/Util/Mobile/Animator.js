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
	
	var isWebkitAvailable = (function(){
		var enable = true;
		//Disable animation for android < sdk version 13
		if(typeof device!="undefined" && device.platform=="Android" &&  parseInt(device.version.split(".").join(""))<300 ){
			enable = false;
		}
		return typeof document.body.style.webkitTransition!="undefined" && enable;
	})();
	var prepareContainer = function(sideContainer,right){
		
		if(isWebkitAvailable){
			sideContainer.addClass(!right?"animate":"animateRight");
			$(sideContainer)[0].addEventListener( 'webkitTransitionEnd', 
				    function( event ) {
	
				if(getCallback())getCallback()();
				}, false );
		}else{
			sideContainer.addClass(!right?"animate-noWk":"animateRight-noWk");
		}
	};
	/**
	 * Animates the left open navigation
	 */
	var animateLeft = function(mainContainer,sideContainer,callback){
		setCallback(callback);
		setContainer(sideContainer);

		sideContainer.show();
		
		
		openPanel = true;
		if(isWebkitAvailable){
			sideContainer.addClass("animateSide");
		}else{
			if(getCallback())getCallback()();
			sideContainer.addClass("animateSide-noWk");
		}
	};
	var animateLeftRev = function(mainContainer,sideContainer,callback){
		setCallback(callback);
		
		openPanel = false;
		if(isWebkitAvailable){
			sideContainer.removeClass("animateSide animateChall");
		}else{
			if(getCallback())getCallback()();
			sideContainer.removeClass("animateSide-noWk animateChall-noWk");
		}
	};

	var animateRight = function(mainContainer,sideContainer,callback){
		setCallback(callback);
		setContainer(sideContainer);

		sideContainer.addClass("animateChall");
		openPanel = true;
		if(isWebkitAvailable){
			sideContainer.addClass("animateChall");
		}else{
			if(getCallback())getCallback()();
			sideContainer.addClass("animateChall-noWk");
		}
	};
	var animateRightRev = function(mainContainer,sideContainer,callback){
		setCallback(callback);
		sideContainer.removeClass("animateSide animateChall");
		openPanel = false;
		if(isWebkitAvailable){
			sideContainer.removeClass("animateSide animateChall");
		}else{
			if(getCallback())getCallback()();
			sideContainer.removeClass("animateSide-noWk animateChall-noWk");
		}
	};
	var init = function(container,right){
		prepareContainer(container,right);
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
