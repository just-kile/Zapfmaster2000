ZMO.modules.drawfeed = (function($,Ajax){
	var rfid ,newsfeed,container,scrollElement,counter=0,rfid;
	var mC = ZMO.modules.Constants;
	var c = mC.drawfeed;
	/**
	 * #######################################################
	 * Blank Container Creation
	 * #######################################################
	 */
	var initRfid =  function(){
		var container = $(document.createElement("div")).addClass("newsdiv");
		var newsText  =$(document.createElement("span")).text("News.");
		rfid = $(document.createElement("span")).attr("id","rfid").addClass("statstext");
		var newscut  =$(document.createElement("div")).text(" ").addClass("newscut");
		
		return container.append(newsText).append(rfid).append(newscut)
	}
	
	var createContainer =function(){
		var container = $(document.createElement("div")).addClass("news-backgrnd");
		var newsFeedContainer = $(document.createElement("div")).addClass("newsfeed");
		var rfidHeadline= initRfid();
		newsfeed  =  $(document.createElement("div")).attr("id","drawfeed-news")
		newsFeedContainer.append(rfidHeadline).append(newsfeed);
		container.append(newsFeedContainer);
		scrollElement = $("<div>").addClass("scrollElement").appendTo(container);
		return container;
	}
	/**
	 * ##########################################################
	 * Newsfeed creation
	 * ##########################################################
	 */
	/**
	 * Creates one news entry
	 * Evaluates which entry type we have
	 * @param {DOMElement} container the container where news should be rendered
	 * @param {Object} val the news entry from backend
	 * @param {Boolean} top if true inserts content at top, not at bottom 
	 */
	var lastContainer = null;
	 var fillContainer = function(cont,val,top){
		 	var news;
			 switch (val.type.toUpperCase()){
				case c.types.ACHIEVEMENT:
					 news = ich["ZMO-news-template-achievement"](new ZMO.AchievementModel(val))
					 break;
				case c.types.DRAWING:
					 news = ich["ZMO-news-template"](new ZMO.NewsModel(val));
					 break;
				case c.types.OTHER:
					 news = ich["ZMO-news-template-other"](new ZMO.OtherModel(val))
					 break;
				case c.types.CHALLENGE_STARTED:
					 news = parseChallengesStarted(val);
					 break;
				case c.types.CHALLENGE_DONE:
					 news = parseChallengesDone(val);
					 break;
			}
			if(typeof top!="undefined" && top ==true){
				if(val.refreshType!= "REFRESH"){
					cont.prepend(news);
					//news.fadeIn("slow");
					lastContainer = news;
				}else{
					lastContainer.replaceWith(news);
					lastContainer = news;
					
				}
			}else{
				cont.append(news);
			}
		 }
	 var parseChallengesStarted = function(val){
			var model = new ZMO.GlobalChallengeModel(val)
			return ich["ZMO-news-template-challenge-started"]({
				team1:model.team1.join(","),
				team2:model.team2.join(","),
				duration:model.duration,
				type:model.challenge_type,
				image:model.image,
				date:model.date
			})
		}
		var parseChallengesDone = function(val){
			var model = new ZMO.GlobalChallengeModel(val);
			var winner,loser;
			if(typeof model.team1!="undefined" &&typeof model.team1[0]!="undefined" && model.team1[0].won==1){
				winner=model.team1;
				loser=model.team2;
			}else{
				winner=model.team2;
				loser=model.team1;
			}
			return ich["ZMO-news-template-challenge-done"]({
				team1:model.team1.join(","),
				team2:model.team2.join(","),
				duration:model.duration,
				type:model.challenge_type,
				winner:model.team1[0].won?model.team1.join(","):model.team2.join(","),
				image:model.image,
				date:model.date
			})
		}
		var parseChallengesDeclined = function(val){
			var model = new ZMO.GlobalChallengeModel(val)
			return ich["ZMO-news-template-challenge-declined"]({
				team1:model.team1.join(","),
				team2:model.team2.join(","),
				duration:model.duration,
				type:model.challenge_type,
				reason:ZMO.Constants.badExcuses[Math.round(Math.random()*10)%(ZMO.Constants.badExcuses.length)],
			image:model.image,
			date:model.date
			})
		}
	 
	 var updateNewslist = function(startVal,length,callback){
			if(typeof startVal != "undefined")counter = startVal;
			Ajax.getDatas(mC.urls.NEWSLIST,function(datas){
				$.each(datas,function(ind,val){
					fillContainer(container,val);
				});
				if(callback)callback(datas);
			},{
				start:startVal,
				length:length,
				//random:new Date().getTime()
			});
		};
	
	var onMessageReceive =function(data){
		if(ZMO.exists(data)){
			if(data.refreshType=="REFRESH")data.kind="refresh";
			fillContainer(container,data,true);
		}else{
			ZMO.log("Warning: Drawfeed data empty!");
		}
	}
	
	 var fillInitialData = function(){
		 updateNewslist(0,mC.drawfeed.listLength);
		 Ajax.connectToNewsPush(onMessageReceive);

		 //Ajax.rfidLogin(onRfidLogin);
	 };
	 
	 var onRfidLogin = function(data){ 
		 var rfidModel = new ZMO.RfidModel(data);
		 if(rfidModel.type == "LOGOUT"){
			 rfid.text("");
		 }else{
			 rfid.text("Hallo "+rfidModel.userName+". Du kannst jetzt zapfen!");
		 }
	 };
	 var bindScrollHandler = function(scrollElement){
		 var windowHeight = $(window).height();
		 var actLoadingFlag = false;
		 $(window).bind("scroll",function(e){
			 var top = scrollElement.offset().top;
			 var windowTop = $(window).scrollTop()+windowHeight+200;
			 if(windowTop>top && top>windowHeight&& !actLoadingFlag){
				 actLoadingFlag = true;
				 var len = container.children().length;
				 updateNewslist(len,mC.drawfeed.listLength,function(datas){if(datas.length!=0)actLoadingFlag = false;});
				 ZMO.log("Loading newsfeed..."+len+"/"+mC.drawfeed.listLength);
			 }
			 
		 });
	 };
	 /**
	  * Gets called, when container is appended
	  */
	 var init = function(){
		 rfid = $("#rfid");
		 container =$("#drawfeed-news")
		 fillInitialData();
		 bindScrollHandler(scrollElement);
		
	}

	/**
	 * Gets called when instance is needed
	 */
	var getInstance = function(){
		return createContainer();
	}

	var pub = {
			getInstance:getInstance,
			init:init,
			updateNewslist:updateNewslist
	}
	return pub;
}(jQuery,ZMO.ajax))
