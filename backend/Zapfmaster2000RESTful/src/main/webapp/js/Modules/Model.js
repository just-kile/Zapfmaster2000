/**
 * The Position model for rendering the modules in view
 * @see js/Core/View/view.js
 * @param {Object} attr
 * 	with attributes
 * 		 	{String} key: can be navigation,main,footer
 * 			{int} col: the wanted column
 * 			{int} row: the wanted row
 */
ZMO.PositionModel =function(key,arr){
	this.key =key;
	this.col = arr[1];
	this.row = arr[0];
	/**
	 * Compares two ZMO.PositionModels
	 * 
	 */
	this.equals = function(newPos){
		return 	typeof newPos !="undefined" 
				&& this.col ==newPos.col 
				&& this.key==newPos.key
				&& this.row==newPos.row;
	};
};

ZMO.PageModel = function(conf){
	this.navigation = conf.navigation;
	this.main = conf.main;
	this.footer = conf.footer;
	
};



/**
 * Not used?
 * @param config
 * @returns {ZMO.DrawfeedModel}
 */
ZMO.DrawfeedModel = function(config){
	this.name = config.name;
	this.id = config.id;
	this.imageurl =config.imageurl;
	this.duration = config.duration;
	this.amount = config.amount;
	this.timestamp = new Date(config.timestamp);
	this.place = "Harzj";
	this.keg = config.keg;
};
ZMO.NewsModel =function(config){
	this.name = config.userName;
	this.amount = Math.round(config.amount*100)/100;
	this.duration = config.duration;
	this.date = config.date?new ZMO.TimeParser(config.date).getDefaultTime():"",//config.date?new Date(config.date).toGMTString():(new Date()). toGMTString();
	this.place = config.place;
	this.kegId =config.kegId;
	this.brand = config.brand;
	this.image =config.image;
	this.userid = config.userid||config.userId;
	this.type = config.type;
	this.place = "Alter Markt";
	this.boxId = config.boxId;
};
ZMO.AchievementModel = function(config){
	this.userid = config.userId;
	this.username = config.userName;
	this.date = config.date?new ZMO.TimeParser(config.date).getDefaultTime():"",//config.date?new Date(config.date).toGMTString():(new Date()). toGMTString();
			
	this.type = config.type;
	this.achievement_id = config.achievementId;
	this.name = config.achievementName;
	this.description = config.description;
	this.image =config.image;
};
ZMO.OtherModel = function(config){
	this.text = config.text;
	this.image = config.image||config.IMAGE_PATH||config.imagepath;
	this.type = config.type;
	this.date = config.date?new ZMO.TimeParser(config.date).getDefaultTime():"";//config.date?new Date(config.date).toGMTString():(new Date()). toGMTString();
			
	
};

ZMO.ChallengeStartedModel = function(config){
	this.userid = config.userid||config.userId;
	this.username = config.username||config.userName;
	this.userimage = config.userimage||config.userImage;

	this.won =config.won;
	this.amount =config.amount;
};

ZMO.GlobalChallengeModel = function(config){
	this.type = config.type;
	this.challenge_type = config.challenge_type||config.challengeType;
	this.duration = config.duration||config.challengeDuration;
	this.challengeFinished = config.challengeFinished;
	this.challengeId = config.challenge_id||config.challengeId;
	var parseTeam = function(teamArr,key){
		var arr = [];
		$.each(teamArr,function(ind,val){
//			arr.push(new ZMO.ChallengeStartedModel(val))
			arr.push(val[key]);
			
		});
		return arr;
	};
	var sumArr =function(arr){
		var count = 0;
		for (var i = 0; i < arr.length; i++ )
		{
			count += arr[i];
		}
		return count;
	};
	this.team1 = parseTeam(config.team1,"userName");
	this.team2 = parseTeam(config.team2,"userName");
	this.team1Images = parseTeam(config.team1,"userImage");
	this.team2Images = parseTeam(config.team2,"userImage");
	this.team1Amount = sumArr(parseTeam(config.team1,"amount"));
	this.team2Amount = sumArr(parseTeam(config.team2,"amount"));
	this.image = config.image||config.challengeImage;
	this.date = config.startDate?new ZMO.TimeParser(config.startDate).getDefaultTime():"";//config.date?new Date(config.date).toGMTString():(new Date()). toGMTString();
			
	//this.date = config.start_time||config.startDate;

};

ZMO.ModuleModel  =function(conf,modulePosKey){
	this.moduleId= conf.moduleId;
	this.element= ZMO.modules[conf.moduleId];
	this.ratio= conf.ratio||"100%";
	this.position = new ZMO.PositionModel(modulePosKey,conf.position);
	this.nocache = !!conf.nocache;
	this.params = conf.params||{};
};
ZMO.RfidModel = function(conf){
	this.userName = conf.userName;
	this.userId = conf.userId;
	this.type = conf.type;
};

ZMO.MemberModel = function(config) {
	this.userName = config.userName;
	this.userId = config.userId;
	this.achievements = config.achievements;
	this.userImage = config.imagePath||config.userImage;
	this.totalAmount = config.totalAmount != undefined?config.totalAmount.toFixed(2):0;
	
	// ignore all achievements if there are way to many
	var max = ZMO.modules.Constants.member.MAX_ACHIEVEMENTS;
	if (this.achievements != undefined && this.achievements.length > max) {
		this.additional = true;
		this.additionalCount = config.achievements.length - max;
		this.achievements.splice(max, this.achievements.length - max);
	}
	
};

//ZMO.GlobalChallengeModel = function(config){
//	this.type = config.type;
//	this.challenge_type = config.challenge_type;
//	this.duration = config.duration;
//	this.challengeId = config.challenge_id;
//	var parseTeam = function(teamArr,key){
//		var arr = [];
//		$.each(teamArr,function(ind,val){
////			arr.push(new ZMUCA.ChallengeStartedModel(val))
//			arr.push(val[key]);
//			
//		});
//		return arr;
//	}
//	var sumArr =function(arr){
//		var count = 0;
//		for (var i = 0; i < arr.length; i++ )
//		{
//			count += arr[i];
//		}
//		return count;
//	}
//	this.team1 = parseTeam(config.team1,"username")
//	this.team2 = parseTeam(config.team2,"username")
//	this.team1Images = parseTeam(config.team1,"userimage")
//	this.team2Images = parseTeam(config.team2,"userimage")
//	this.team1Amount = sumArr(parseTeam(config.team1,"amount"));
//	this.team2Amount = sumArr(parseTeam(config.team2,"amount"));
//	this.image = config.image;
//	this.date = config.start_time;
//
//}

