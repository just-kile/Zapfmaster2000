ZMUCA.controller = (function($, view, document, subcontrollers) {
	var frontPageId = "index";
	var newFrontPageId = "ZMUCA-newFrontpage"
	var loginId = "ZMUCA-login";
	var newChallenge = "ZMUCA-newchallenge";
	var duelsId = "ZMUCA-duels";
	var cheersId = "ZMUCA-newcheers";
	var statsId = "ZMUCA-stats";
	var acceptChallengeId ="ZMUCA-acceptChallenge";
	var onPageChange = function(event, data) {

		var toPageId = data.toPage.attr("id");
		var fromPageId = null;

		if (data.options.fromPage) {
			fromPageId = data.options.fromPage.attr("id");
		}
		var user = ZMUCA.getUser();
		if (typeof user == "undefined" && ( toPageId != frontPageId
				&& toPageId != loginId)) {
			$.mobile.changePage("#" + frontPageId)
		} 
//		else {

//			loginTest(user,function() {

				switch (toPageId) {

				case frontPageId:
					subcontrollers.frontPageController.onPageChange(event, data);
					break;
				case loginId:
					subcontrollers.loginController.onPageChange(event, data);
					break;
				case acceptChallengeId:
					subcontrollers.acceptChallengeController.onPageChange(event, data);
					break;
				case newFrontPageId:
					subcontrollers.newFrontPageController.onPageChange(event, data);
					break;
				case newChallenge:
					subcontrollers.newChallengeController.onPageChange(event, data);
					break;
				case cheersId:
					subcontrollers.cheersController.onPageChange(event, data);
					break;
				case duelsId:
					subcontrollers.duelsController.onPageChange(event, data);
					break;	
				case statsId:
					subcontrollers.statsController.onPageChange(event, data);
					break;	
				}
//			})
//		}
	};
//	var loginTest = function(user,callback) {
//		if (typeof ZMUCA.drawChannel == "undefined") {
//			ZMUCA.login(user.password, function(userModel) {
//				ZMUCA.log("Logged in!");
//				ZMUCA.log("Hallo " + userModel.name + "!");
//
//				// window.plugins.tts.speak("Hello Idiot!",function(){
//				// alert("Fertsch")
//				// },function(){
//				// alert("Fehler")
//				// });
//				// $.mobile.changePage("#ZMUCA-newFrontpage")
//				callback();
//			}, function() {
//				onFail("Pin nicht existent! Bitte erneut eingeben");
//				$("#ZMUCA-login-pin").val("");
//			});
//		} else {
//			callback()
//		}
//	}
	var onPageBeforeChange = function(event, data) {

		if (typeof data.toPage === "string") {

			var url = $.mobile.path.parseUrl(data.toPage);

			if ($.mobile.path.isEmbeddedPage(url)) {

				data.options.queryString = $.mobile.path.parseUrl(url.hash
						.replace(/^#/, "")).search.replace("?", "");
			}
		}
	};

	var init = function() {

		var d = $(document);
		d.bind("pagebeforechange", onPageBeforeChange);
		d.bind("pagechange", onPageChange);
		// init controllers
		$.each(subcontrollers, function(key, controller) {
			controller.init();
		});
		

	};

	var pub = {
		init : init
	};

	return pub;

}(jQuery, ZMUCA.view, document, {
	frontPageController : ZMUCA.frontPageController,
	newChallengeController : ZMUCA.newChallengeController,
	loginController : ZMUCA.loginController,
	acceptChallengeController:ZMUCA.acceptChallengeController,
	newFrontPageController : ZMUCA.newFrontPageController,
	newChallengeController : ZMUCA.newChallengeController,
	statsController : ZMUCA.statsController,
	duelsController : ZMUCA.duelsController,
	cheersController : ZMUCA.cheersController
	
	
}));

$(document).bind("mobileinit", function() {
	ZMUCA.controller.init();
});