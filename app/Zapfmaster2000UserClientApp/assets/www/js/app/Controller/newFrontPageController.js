ZMUCA.newFrontPageController = (function($, view, document) {
	var eventsSetFlag = false;
	var counter = 0;
	var onPageChange = function(event, data) {
		ZMUCA.log("newFrontPageController onPageChange called")
		// Check if connected to Node js Server Module
		ZMUCA.testConnection(function() {
			ZMUCA.showThrobber(true)
			ZMUCA.get("http://server/beerometer/zapfmasterUserClientApp.php",function(datas){
				var container = $("#ZMUCA-news-container");
				$.each(datas,function(ind,val){
					var news = ich["ZMUCA-news-template"](new ZMUCA.NewsModel(val))
					news.appendTo(container);
				});
				ZMUCA.showThrobber(false);
				new iScroll("ZMUCA-news-container")
				// $("[data-role=header]").fixedtoolbar("updatePagePadding")
			},{
				newslist:counter++
			})
			
		
	});

	};

	var init = function() {
		ZMUCA.log("newFrontPageController init called")
	}

	var pub = {
		init : init,
		onPageChange : onPageChange,

	};

	return pub;

}(jQuery, ZMUCA.newFrontPageView, document));
