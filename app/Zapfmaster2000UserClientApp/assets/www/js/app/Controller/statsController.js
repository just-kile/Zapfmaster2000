ZMUCA.statsController = (function ($, view, document) {
	var onPageChange = function (event, data) {
		ZMUCA.log("statsController onPageChange called")
		//Check if connected to Node js Server Module
		ZMUCA.testConnection();
    };
 


    var init = function () {
    	ZMUCA.log("cheersController init called")
    }

    var pub = {
        init: init,
        onPageChange:onPageChange,
        
    };

    return pub;

} (jQuery,ZMUCA.statsView, document));
