ZMUCA.duelsController = (function ($, view, document) {
	var onPageChange = function (event, data) {
		ZMUCA.log("duelsController onPageChange called")
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

} (jQuery,ZMUCA.duelsView, document));

