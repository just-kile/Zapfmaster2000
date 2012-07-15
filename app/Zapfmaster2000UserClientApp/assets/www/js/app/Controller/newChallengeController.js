ZMUCA.newChallengeController = (function($, view, document) {
	var c = ZMUCA.Constants;
	var firstCallFlag = false;

	// Submits
	var onSubmit = function() {
	}

	var onPageChange = function(event, data) {
		ZMUCA.testConnection(function() {
			ZMUCA.actionsChannel.emit("getAllUsers", function(userModelArr) {
				view.renderTable("#ZMUCA-newchallenge-tableContainer",userModelArr);
			})
			if (!firstCallFlag) {
				ZMUCA.log("Setting Tab Events for new Challenge");

				ZMUCA.log("Setting Push events for new Challenge");
				//IF new User connected refresh the listview
				ZMUCA.actionsChannel.on("newUserConnected",
						function(userModelArr) {
							view.renderTable("#ZMUCA-newchallenge-tableContainer",userModelArr);
						})

				firstCallFlag = true;
			}
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
