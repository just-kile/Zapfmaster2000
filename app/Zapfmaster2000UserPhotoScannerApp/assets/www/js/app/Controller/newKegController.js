ZMUPSA.newKegController = (function($, view, document) {
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

		if (!eventsSetFlag) {
			ZMUPSA.log("Setting Tab Events for New Keg")
			$("#ZMUPSA-newkeg-submit").bind("tap", onSubmit);
		}
	};

	var init = function() {

	};

	var pub = {
		init : init,
		onPageChange : onPageChange,
		reset : reset
	};

	return pub;

}(jQuery, ZMUPSA.newKegView, document));
