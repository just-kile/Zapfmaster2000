/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.challenges = (function($,ajax){
	var c = ZMO.modules.Constants;
	var l = ZMO.Util.localization;
	var sH = ZMO.Util.scrollHandler;
	var container = null,duelsContainerUl= null;
	var wording = {
			REMAINING:"challengeRemaining",
			S_REMAINING:"challengeSRemaining",
			DONE:"challengeDone",
			
	};
	var stretchLengthStatus = function(newsRow,globalChallengeModel){
    	var team1Div =newsRow.find(".team1_amount");
    	var team2Div =newsRow.find(".team2_amount");
    	var team1Amount = globalChallengeModel.team1Amount;
    	var team2Amount = globalChallengeModel.team2Amount;
    	if(team1Amount>0 || team2Amount>0){
    		var sum = team1Amount+team2Amount;
    		var percent1 = team1Amount/sum;
    		var percent2 = team2Amount/sum;
    		team1Div.css("width",(percent2*100)+"%");
    		team2Div.css("width",(percent1*100)+"%");
    	}
    	return newsRow;
	};
	var countdownArr = [];
	var Countdown = function(container,challengeModel){
		var oneMinute = 1000*60;
		var oneSecond = 1000;
		var dateParser =  challengeModel.dateParser;
		var duration =   parseInt(challengeModel.duration)*oneMinute*1;
		var text = l.translateString(wording.S_REMAINING);
		var timeContainer = $(container).find(".time");
		var startDate = dateParser.getTimestamp();
		var interval = setInterval(function(){
			
			time = Math.ceil((startDate+duration-(new Date()).getTime())/oneSecond); 
			
			var newText = text.replace("{{time}}",time);
			
			if(time<=0){
				newText = l.translateString(wording.DONE);
				clearInterval(interval);
			}
			timeContainer.html(newText);
		},1000);
		this.destroy = function(){
			clearInterval(interval);
		};
	};
	var parseChallengesOverview = function(model){
		var duelsRow = null;
		return duelsRow = ich["ZMO-duelsRow"]({
			team1:model.team1,
			team2:model.team2,
			type:function(){
				//model.challenge_type,
				var am1 = model.team1Amount.toFixed(2);
				var am2 = model.team2Amount.toFixed(2);
				return am1+"l -- "+am2+"l";
			}(),
			active:model.finished==0?"Fertig":"Aktiv",
			time:(function(){
				var oneMinute = 1000*60;
				var duration = parseInt(model.duration)*oneMinute*1;
				var startDate = ZMO.exists(model.dateParser)?model.dateParser.getTimestamp():0;
				var time =Math.ceil((startDate+duration-(new Date()).getTime())/oneMinute);
				if(time>0 && time<=2){
					setTimeout(function(){
						countdownArr.push(new Countdown(duelsRow,model));
					},0);
					return l.translateString(wording.REMAINING).replace("{{time}}",time);
				}else if(time>0){
					return l.translateString(wording.REMAINING).replace("{{time}}",time);
				}else{
					return l.translateString(wording.DONE);
				}
//				return time>0?time +"min verbleibend":"Challenge beendet!";
			})()
		});
	};
	var fillContainer = function(globalChallengeModel){
		var news = $(parseChallengesOverview(globalChallengeModel));
		duelsContainerUl.append(stretchLengthStatus(news,globalChallengeModel));
		
	};
	var onChallengesReceive = function(datas){
		ZMO.logger.log("challenges datas received!");
		duelsContainerUl.empty();
		clearCountdowns();
		$.each(datas,function(ind,val){
			fillContainer(new ZMO.GlobalChallengeModel(val));
		});
		sH.refresh();
	};
	
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(queryparams,data){
//		ajax.getDatas(c.urls.CHALLENGES,onChallengesReceive);
		ajax.enqueueDatas({
			url:c.urls.CHALLENGES,
			callback:onChallengesReceive,
			rawData:true
		});
		ajax.startPull();
		
		sH.initScrolling({
			element:container,
			isMobile:false//data.isMobile,
//			pullDownCallback:function(){
//				ajax.getDatas(c.urls.CHALLENGES,onChallengesReceive);
//			}
		});
		
	};
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		container = $("<div>").addClass("newsfeed");
		var ulWrapper =$("<div>");
		duelsContainerUl = $("<ul>").attr("id","ZMO-duels-container");
		ulWrapper.append(duelsContainerUl);
		container.append(ulWrapper);
		return container;
	};
	var clearCountdowns = function(){
		$.each(countdownArr,function(ind,countdown){
			countdown.destroy();
		});
		countdownArr =[];
	};
	var remove = function(){
		clearCountdowns();
	};
	var pub = {
			getInstance:getInstance,
			init:init,
			remove:remove
	};
	return pub;
}(jQuery,ZMO.ajax));
