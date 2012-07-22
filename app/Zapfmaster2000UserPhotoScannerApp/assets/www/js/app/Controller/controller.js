ZMUPSA.controller = (function ($, view, document,subcontrollers) {
   var frontPageId = "index";
   var newuserId = "ZMUPSA-newuser";
   var newkegId = "ZMUPSA-nukeg";
   var membersId = "ZMUPSA-members";
   var initNewUser = function(){
	   
   }
   
   
	var onPageChange = function (event, data) {

        var toPageId = data.toPage.attr("id");
        var fromPageId = null;

        if (data.options.fromPage) {
            fromPageId = data.options.fromPage.attr("id");
        }

        switch (toPageId) {

            case frontPageId:
            	subcontrollers.frontPageController.onPageChange();
                break;
            case newuserId:
            	subcontrollers.newUserController.onPageChange();
                break;
            case newkegId:
            	subcontrollers.newKegController.onPageChange();
                break;
            case membersId:
            	subcontrollers.membersController.onPageChange();
                break;
 
        }
    };

    var onPageBeforeChange = function (event, data) {

        if (typeof data.toPage === "string") {

            var url = $.mobile.path.parseUrl(data.toPage);

            if ($.mobile.path.isEmbeddedPage(url)) {

                data.options.queryString = $.mobile.path.parseUrl(url.hash.replace(/^#/, "")).search.replace("?", "");
            }
        }
    };

 
 
    var init = function () {

        var d = $(document);
        d.bind("pagebeforechange", onPageBeforeChange);
        d.bind("pagechange", onPageChange);
        //init controllers
        $.each(subcontrollers,function(key,controller){
        	controller.init();
        })
       
    };

    var pub = {
        init: init
    };

    return pub;

} (jQuery,ZMUPSA.view, document,{
	frontPageController:ZMUPSA.frontPageController,
	newUserController:ZMUPSA.newUserController,
	newKegController:ZMUPSA.newKegController,
	membersController:ZMUPSA.membersController
	}
	));


$(document).bind("mobileinit", function () {
	ZMUPSA.controller.init();
});