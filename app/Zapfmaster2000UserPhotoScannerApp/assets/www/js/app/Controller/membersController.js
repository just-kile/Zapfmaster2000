ZMUPSA.membersController = (function($, view, document) {
	var c = ZMUPSA.Constants;
	var eventsSetFlag = false;
	// Photos

	// Submits
	var onSubmit = function() {

		var userModel = new ZMUPSA.KegModel({
			brand : $("#ZMUPSA-newkeg-brand").val(),
			size : $("#ZMUPSA-newkeg-size").val(),
		});
		ZMUPSA.log("Created UserModel")
		ZMUPSA.log(userModel);

		ZMUPSA.postData(c.newUserPhpUrl, function() {
			alert("New Keg inserted!");
			reset();
		}, userModel, "newkeg")
		// upload image on server

	}
	var reset = function() {
		$("#ZMUPSA-newkeg-brand").val("")
		$("#ZMUPSA-newkeg-size").val("")
	}
	
	var onPageChange = function(event, data) {

		ZMUPSA.get(c.newUserPhpUrl,function(userModelArr){
			view.renderMembersTable(userModelArr)
		},{},"memberlist")
	};

	var init = function() {

	};

	var pub = {
		init : init,
		onPageChange : onPageChange,
		reset : reset
	};

	return pub;

}(jQuery, ZMUPSA.membersView, document));
