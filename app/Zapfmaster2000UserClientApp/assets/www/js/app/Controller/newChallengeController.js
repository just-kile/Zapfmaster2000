ZMUCA.newChallengeController = (function($, view, document) {
	var c = ZMUCA.Constants;
	var firstCallFlag = false;
	var id = "ZMUCA-newchallenge";
	var actualChallenge = {
			challenge:"",
			mode:"",
			users:{}
	}
	// Submits
	var onSubmit = function() {
	}
	var onChallengeClick = function(challenge){
		//alert(challengeId.name);
		//Goto next tab
		initMode(challenge);
		actualChallenge.challenge = challenge;
		$("#" + id + " [data-role=content] .zm_challenge").hide();
		$("#" + id + " [data-role=content] .mode").show();
	}
	var onModeClick = function(mode){
		initOpponents(mode);
		actualChallenge.mode = mode;
		//alert(challengeId.name);
		//Goto next tab
		$("#" + id + " [data-role=content] .zm_challenge").hide();
		$("#" + id + " [data-role=content] .opponent").show();
		
	}
	var onUserClick = function(user){
		actualChallenge.user = user;
	}
	var initChallenges = function() {
		view.renderChallenges("#" + id + " .challenge",c.challenges,onChallengeClick);
	}
	var initMode = function() {
		view.renderModes("#" + id + " .mode",c.modes,onModeClick)
	}
	var initOpponents = function(){
		ZMUCA.actionsChannel.emit("getAllUsers", function(userModelArr) {
			view.renderTable("#" + id + " .opponent", userModelArr);
		})
		if (!firstCallFlag) {
			ZMUCA.log("Setting Tab Events for new Challenge");
			ZMUCA.log("Setting Push events for new Challenge");
			// IF new User connected refresh the listview
			ZMUCA.actionsChannel.on("newUserConnected", function(
					userModelArr) {
				view.renderTable("#" + id + " .opponent", userModelArr);
			})

			firstCallFlag = true;
		}
	}
	var onPageChange = function(event, data) {
		$("#" + id + " [data-role=content] .zm_challenge").hide();
		$("#" + id + " [data-role=content] :first").show();

		ZMUCA.testConnection(function() {
			initChallenges();
			
		})
	};

	var init = function() {

	};

	var pub = {
		init : init,
		onPageChange : onPageChange,

	};

	return pub;

}(jQuery, ZMUCA.newUserView, document));
