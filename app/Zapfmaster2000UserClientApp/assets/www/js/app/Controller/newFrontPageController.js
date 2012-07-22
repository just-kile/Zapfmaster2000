ZMUCA.newFrontPageController = (function ($, view, document) {
	var userImageUri;
	var qrCode =1;
	var c = ZMUCA.Constants;
   var eventsSetFlag = false;
   //Photos
  
   

	
	function onFail(message) {
	    alert('Failed because: ' + message);
	}
      
   //QR Codes
   var onScanQrCode = function(){
	   c.debugMode
	   		?onScanQrCodeSuccess({text:"1",cancelled:false})
			:window.plugins.barcodeScanner.scan(onScanQrCodeSuccess, onFail);
		ZMUCA.log("Scanning QR Code")
	 
   }
   var onScanQrCodeSuccess = function(result){
	   if(!result.cancelled){
		   ZMUCA.log("Scanned QR Code: "+result.text)
		   qrCode = result.text
		   $("#ZMUCA-qrCode").text(qrCode)
	   }
	   
   }
   
   
  var channelHandlerSetFlag = false;
	var onPageChange = function (event, data) {
		ZMUCA.log("newFrontPageController onPageChange called")
		//Check if connected to Node js Server Module
		ZMUCA.testConnection();
		if(!eventsSetFlag){
			$("#ZMUCA-newFrontpage .logout").live("tap",function(){
				ZMUCA.logout();
			})
			eventsSetFlag = true;
		}
		
    };

   
 
    var onDraw = function(data){
		ZMUCA.log("onDraw "+data)
//		jQuery("#test").text(data) 
	}
	
	var onChallenge = function () {
	    //news.emit('woot');
	 }
	


    var init = function () {
    	ZMUCA.log("newFrontPageController init called")
    }

    var pub = {
        init: init,
        onPageChange:onPageChange,
        
    };

    return pub;

} (jQuery,ZMUCA.newFrontPageView, document));

