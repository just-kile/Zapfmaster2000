ZMUCA.newFrontPageController = (function ($, view, document) {
		var onPageChange = function (event, data) {
		ZMUCA.log("newFrontPageController onPageChange called")
		//Check if connected to Node js Server Module
		ZMUCA.testConnection(function(){
			var news = ich["ZMUCA-news-template"](new ZMUCA.NewsModel({
				name:"Pete",
				amount:"20",
				duration:"39",
				date:"2012-07-22 00:24:35",
				place:"Bens Huette",
				keg:1,
				brand:"Staropramen",
				image:"http://server/beerometer/images/avatars/ben.jpg"
			}));
			news.appendTo("#ZMUCA-news-container");
		});
		if(!eventsSetFlag){
			$("#ZMUCA-newFrontpage .logout").live("tap",function(){
				ZMUCA.logout();
			})
			eventsSetFlag = true;
		}
		
    };

   
 
   


    var init = function () {
    	ZMUCA.log("newFrontPageController init called")
    }

    var pub = {
        init: init,
        onPageChange:onPageChange,
        
    };

    return pub;

} (jQuery,ZMUCA.newFrontPageView, document));

