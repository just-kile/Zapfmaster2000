ZMUCA.acceptChallengeController = (function ($, view, document) {
	var c = ZMUCA.Constants;
	var eventsSetFlag = false;
	
	function onFail(message) {
	    alert('Failed because: ' + message);
	}
      
   
   //Submits
   var onSubmit = function(){
	   ZMUCA.log("Sending Challenge ...");
	   alert("Sende Daten:"  )
	  

	   
	  
	   
   }

  var onCancel = function(){
	  window.history.back();
  }
	var onPageChange = function (event, data) {
		ZMUCA.log("acceptChallenge onPageChange called")
		if(!eventsSetFlag){
			ZMUCA.log("Setting Tap Events for Login")
			$("#ZMUCA-acceptChallenge-submit").bind("tap",onSubmit);
			$("#ZMUCA-acceptChallenge-cancel").live("tap",onCancel)
			eventsSetFlag = true;
		}
    };

   var initChallenge = function(challengeModel){
	   ZMUCA.log(challengeModel);
	   
   }
    
    
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
        initChallenge:initChallenge
    };

    return pub;

} (jQuery,ZMUCA.newUserView, document));

