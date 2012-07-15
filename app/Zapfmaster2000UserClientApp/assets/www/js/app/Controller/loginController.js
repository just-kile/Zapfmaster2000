ZMUCA.loginController = (function ($, view, document) {
	var c = ZMUCA.Constants;
   var eventsSetFlag = false;
	
	function onFail(message) {
	    alert('Failed because: ' + message);
	}
      
   
   //Submits
   var onSubmit = function(){
	   ZMUCA.log("Logging in...");
	   var pin = $("#ZMUCA-login-pin").val();
	   ZMUCA.login(c.debugMode?"1163":pin,function(userModel){
		   ZMUCA.log("Logged in!");
		   ZMUCA.log("Hallo "+userModel.name+"!");
		  
//		   window.plugins.tts.speak("Hello Idiot!",function(){
//			   alert("Fertsch")
//		   },function(){
//			   alert("Fehler")
//		   });
		   $.mobile.changePage("#ZMUCA-newFrontpage")
	   },function(){
		   onFail("Pin nicht existent! Bitte erneut eingeben");
		   $("#ZMUCA-login-pin").val("");
	   });
	  

	   
	  
	   
   }
   var onScanQrCode = function(){
	   c.debugMode
	   		?onScanQrCodeSuccess({text:"1163",cancelled:false})
			:window.plugins.barcodeScanner.scan(onScanQrCodeSuccess, onFail);
		ZMUCA.log("Scanning QR Code")
	 
   }
   var onScanQrCodeSuccess = function(result){
	   if(!result.cancelled){
		   ZMUCA.log("Scanned QR Code: "+result.text)
		   qrCode = result.text
		   $("#ZMUCA-login-pin").val(qrCode);
		   onSubmit();
	   }
	   
   }
	var onPageChange = function (event, data) {
		ZMUCA.log("login onPageChange called")
		if(!eventsSetFlag){
			ZMUCA.log("Setting Tap Events for Login")
			$("#ZMUCA-login-submit").bind("tap",onSubmit);
			$("#ZMUCA-login-qr").live("tap",onScanQrCode)
			eventsSetFlag = true;
		}
    };

   
    
    
    var init = function () {
    	ZMUCA.log("login init called")
    	//for tts (text-to-speech)
//    	 document.addEventListener('deviceready', function() {
//    	        window.plugins.tts.startup(startupWin, fail);
//    	        populate();
//    	    }, false);
    };

    var pub = {
        init: init,
        onPageChange:onPageChange,
    };

    return pub;

} (jQuery,ZMUCA.newUserView, document));

