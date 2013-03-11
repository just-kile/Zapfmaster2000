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
	function isConnected() {
	   if(navigator.connection){
			var networkState = navigator.connection.type;
		    var states = {};
		    states[Connection.UNKNOWN]  = false;
		    states[Connection.ETHERNET] = true;
		    states[Connection.WIFI]     = true;
		    states[Connection.CELL_2G]  = true;
		    states[Connection.CELL_3G]  = true;
		    states[Connection.CELL_4G]  = true;
		    states[Connection.NONE]     = false;
	    return bool;
	   }
	}
	var init = function(){
		document.addEventListener("pause", onOffline, false);
		document.addEventListener("resume", onOnline, false);
		
	}
	init();

	var pub = {
			isOffline:isOffline,
			isConnected:isConnected
	};
	return pub;
}(jQuery,document);
