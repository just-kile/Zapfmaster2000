ZMO = ZMO || {};
ZMO.onlineRecognizer = function($,document){
	var offlineFlag = false;
	var onOffline = function(){
		offlineFlag = true;
	}
	var onOnline = function(){
		offlineFlag = false;
	}
	var isOffline = function(){
		return offlineFlag;
	}
	var init = function(){
		document.addEventListener("pause", onOffline, false);
		document.addEventListener("resume", onOnline, false);
	}
	init();

	var pub = {
			isOffline:isOffline
	};
	return pub;
}(jQuery,document);
