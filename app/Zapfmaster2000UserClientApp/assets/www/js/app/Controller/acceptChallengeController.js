ZMUCA.acceptChallengeController = (function ($, view, document) {
	var c = ZMUCA.Constants;
	var eventsSetFlag = false;
	var challenges = [];
	
	var submitBtn,cancelBtn;
	function onFail(message) {
	    alert('Failed because: ' + message);
	}
      
   
   //Submits
   var onSubmit = function(){
	   ZMUCA.log("Sending Challenge ...");
	   var challenge =challenges[challenges.length-1];
	   ZMUCA.log(challenge);
	   ZMUCA.actionsChannel.emit("challengeAccepted",challenge)
	   window.history.back();
   }
   var onCancel = function(){
	   ZMUCA.log("Sending Challenge ...");
	   var challenge =challenges[challenges.length-1];
	   ZMUCA.log(challenge);
	   ZMUCA.actionsChannel.emit("challengeDeclined",challenge)
	   window.history.back();
   }

	var onPageChange = function (event, data) {
		ZMUCA.log("acceptChallenge onPageChange called")
		if(!eventsSetFlag){
			ZMUCA.log("Setting Tap Events for Login")
			submitBtn = 	$("#ZMUCA-acceptChallenge-submit");
			cancelBtn = $("#ZMUCA-acceptChallenge-cancel");
			submitBtn.bind("tap",onSubmit);
			cancelBtn.live("tap",onCancel)
			eventsSetFlag = true;
		}
    };

   var initChallenge = function(challengeModel){
	   ZMUCA.log("with challenge")
	   ZMUCA.log(challengeModel);
	   challenges.push(challengeModel);
	   
	  
		$("#ZMUCA-acceptChallenge .headline").html(view.renderText(challengeModel))
   }
    
    var init = function () {
    	ZMUCA.log("login init called");
    	$("#ZMUCA-acceptChallenge-submit")
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

} (jQuery,ZMUCA.acceptChallengesView, document));

