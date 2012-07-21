ZMUCA.frontPageController = (function ($, view, document) {


   
   
	var onPageChange = function (event, data) {
		var news = ich["ZMUCA-news-template"](new ZMUCA.NewsModel({
			name:"Pete",
			amount:"20",
			duration:"39",
			date:"2012-07-22 00:24:35",
			place:"Bens Hütte",
			keg:1,
			brand:"Staropramen",
			image:"http://server/beerometer/images/avatars/ben.jpg"
		}));
		news.appendTo("#ZMUCA-news-container");
      
    };

   
 
 
    var init = function () {

       
       
    };

    var pub = {
        init: init,
        onPageChange:onPageChange
    };

    return pub;

} (jQuery,ZMUCA.frontPageView, document));

