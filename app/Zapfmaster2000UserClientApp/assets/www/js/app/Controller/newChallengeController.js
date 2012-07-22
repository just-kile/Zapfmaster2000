ZMUCA.newChallengeController = (function($, view, document) {
	var c = ZMUCA.Constants;
	var firstCallFlag = false;
	var id = "ZMUCA-newchallenge";
	var challengeTab,modeTab,opponentTab;
	var actualChallenge = new ZMUCA.ChallengeModel({
			challenge:"",
			mode:"",
			users:{}
	});
	// Submits
	var onSubmit = function() {
	}
	var onChallengeClick = function(challenge){
		//alert(challengeId.name);
		//Goto next tab
		initMode(challenge);
		actualChallenge.challenge = challenge;
		challengeTab.fadeOut(function(){
			modeTab.fadeIn();
		});
		
		//opponentTab.hide();
		
//		$("#" + id + " [data-role=content] .zm_challenge").hide();
//		$("#" + id + " [data-role=content] .mode").show();
	}
	var onModeClick = function(mode){
		initOpponents(mode);
		actualChallenge.mode = mode;
		//alert(challengeId.name);
		//Goto next tab
		modeTab.fadeOut(function(){
			opponentTab.fadeIn();
		});
		
	}
	var onUserClick = function(event,userModel){
		actualChallenge.users = [userModel];
		ZMUCA.log(actualChallenge);
		ZMUCA.actionsChannel.emit("challenge offered",actualChallenge)
		//alert(actualChallenge.challenge+"\n"+actualChallenge.mode+"\n"+actualChallenge.user)
	}
	var initChallenges = function() {
		view.renderChallenges(challengeTab,c.challenges,onChallengeClick);
	}
	var initMode = function() {
		view.renderModes(modeTab,c.modes,onModeClick)
	}
	var initOpponents = function(){
		ZMUCA.actionsChannel.emit("getAllUsers", function(userModelArr) {
			view.renderTable(opponentTab, userModelArr,onUserClick);
		})
		if (!firstCallFlag) {
			ZMUCA.log("Setting Tab Events for new Challenge");
			ZMUCA.log("Setting Push events for new Challenge");
			// IF new User connected refresh the listview
			ZMUCA.actionsChannel.on("newUserConnected", function(
					userModelArr) {
				view.renderTable(opponentTab, userModelArr,onUserClick);
			}) 

			firstCallFlag = true;
		}
	}
	var firstVisitFlag=false;
	var onPageChange = function(event, data) {
		ZMUCA.log("newChallengeCOntroller onPageChange called")
		if(!firstVisitFlag){
			challengeTab = $("#" + id + " .challenge");
			modeTab = $("#" + id + " .mode");
			opponentTab = $("#" + id + " .opponent");
		}
		modeTab.hide();
		opponentTab.hide();
		challengeTab.show();

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

}(jQuery, ZMUCA.newChallengeView, document));
