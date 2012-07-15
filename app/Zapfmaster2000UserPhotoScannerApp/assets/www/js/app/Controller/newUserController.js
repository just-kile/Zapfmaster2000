ZMUPSA.newUserController = (function ($, view, document) {
	var userImageUri;
	var qrCode =1;
	var c = ZMUPSA.Constants;
   var eventsSetFlag = false;
   //Photos
   var onTakeAPhotoClick = function(){
//	   if(typeof navigator.camera != "undefined"){
		   ZMUPSA.log("Taking Photo")
		  !c.debugMode
		  	? navigator.camera.getPicture(onPhotoTaken, onFail, { quality: 100,
			   destinationType: Camera.DestinationType.DATA_URL,
			   targetWidth: 64,
			   targetHeight: 48,
			   
			   
		   })
		   :onPhotoTaken(c.dummyImage);
//	   }else{
//		onFail("No Cam")
//	}
   }
   

	var onPhotoTaken = function (imageUri) {
		ZMUPSA.log("Photo Taken with Uri: "+imageUri)
		var image = $('#ZMUPSA-prevImage').attr("src","data:image/jpeg;base64,"+imageUri);
		userImageUri = imageUri;

		
	}
	
	function onFail(message) {
	    alert('Failed because: ' + message);
	}
      
   //QR Codes
   var onScanQrCode = function(){
	   c.debugMode
	   		?onScanQrCodeSuccess({text:"1",cancelled:false})
			:window.plugins.barcodeScanner.scan(onScanQrCodeSuccess, onFail);
		ZMUPSA.log("Scanning QR Code")
	 
   }
   var onScanQrCodeSuccess = function(result){
	   if(!result.cancelled){
		   ZMUPSA.log("Scanned QR Code: "+result.text)
		   qrCode = result.text
		   $("#ZMUPSA-qrCode").text(qrCode)
	   }
	   
   }
   
   //Submits
   var onSubmit = function(){
	   
	   var userModel = new ZMUPSA.UserModel({
		   name:$("#ZMUPSA-newuser-name").val(),
		   gender:$("#ZMUPSA-newuser-gender").val(),
		   weight:$("#ZMUPSA-newuser-weight").val(),
			  
		   image:userImageUri,
		   rfid:ZMUPSA.getRfidByQrCode(qrCode)
		   
	   });
	   ZMUPSA.log("Created UserModel")
	   ZMUPSA.log(userModel);
	   

	   ZMUPSA.postData(c.newUserPhpUrl,function(resp){
		   alert("User inserted!\n"+"Pin: "+resp);
		   reset();
	   },userModel,"adduser")
	   //upload image on server
	  
	   
   }
   var reset = function(){
	   userImageUri = null;
	   qrCode =1;
	   $("#ZMUPSA-newuser-name").val("")
	   $("#ZMUPSA-newuser-weight").val("")
	   $("#ZMUPSA-prevImage").attr("src",c.defaultImage);
	   $("#ZMUPSA-qrCode").html("");
	   
	   
   }
	var onPageChange = function (event, data) {
		
		if(!eventsSetFlag){
			ZMUPSA.log("Setting Tab Events for New User")
			$("#ZMUPSA-newuser-camera").bind("tap",onTakeAPhotoClick);
	    	$("#ZMUPSA-newuser-qrscan").bind("tap",onScanQrCode);
	    	$("#ZMUPSA-newuser-submit").bind("tap",onSubmit);
	    	eventsSetFlag = true;
		}
    };

   
 
 
    var init = function () {
    	
    };

    var pub = {
        init: init,
        onPageChange:onPageChange,
        reset:reset
    };

    return pub;

} (jQuery,ZMUPSA.newUserView, document));

