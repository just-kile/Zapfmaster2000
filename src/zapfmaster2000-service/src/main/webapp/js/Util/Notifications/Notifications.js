ZMO.Notifications = (function($){
	var idCount = 1;
	var send = function(headline,ticker){
		console.log("Send local notification"+window.plugins);
	    if (typeof plugins !== "undefined") {
	        plugins.localNotification.add({
	            date : new Date(),
	            message : "Zapfmaster2000 \r\n"+headline,
	            ticker :ticker,
	            repeatDaily : false,
	            id : idCount++
	        });
	    }
	};
	var clear = function(){
		plugins.localNotification.cancelAll();
	};
	var pub = {
			send:send,
			clear:clear
	};
	return pub;
})(jQuery);      
