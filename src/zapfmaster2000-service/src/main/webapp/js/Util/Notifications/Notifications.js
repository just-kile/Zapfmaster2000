ZMO.Notifications = (function($){
	var idCount = 1;
	var send = function(headline,ticker){
		
	    if (typeof plugins !== "undefined" ) {
	    	if(ZMO.onlineRecognizer.isOffline()){
	    		window.plugins.statusBarNotification.notify( ticker,headline);
	    	}
	    	
//	        plugins.localNotification.add({
//	            date : new Date(),
//	            message : "Zapfmaster2000 \r\n"+headline,
//	            ticker :ticker,
//	            repeatDaily : false,
//	            id : idCount++
//	        });
	    	 if(navigator.notification && navigator.notification.vibrate) navigator.notification.vibrate(1000);     
	    }
	};
	var clear = function(){
		//plugins.localNotification.cancelAll();
	};
	var pub = {
			send:send,
			clear:clear
	};
	return pub;
})(jQuery);      
