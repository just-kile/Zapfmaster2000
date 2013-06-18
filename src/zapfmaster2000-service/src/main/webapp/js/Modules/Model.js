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


ZMO.NewsModel =function(config){
	this.userName = config.userName;
	this.amount = Math.round(config.amount*100)/100;
	//this.duration = config.duration;
	this.date = config.date?new ZMO.TimeParser(config.date).getDefaultTime():"",//config.date?new Date(config.date).toGMTString():(new Date()). toGMTString();
	this.place = config.place;
	this.kegId =config.kegId;
	this.brand = config.brand;
	this.userImage =baseUrl +(config.image||config.imagePath||config.userImage);
	this.userId = config.userid||config.userId;
	this.type = config.type;
	this.place = config.location || "";
	this.boxId = config.boxId;
	this.drawId =config.drawId;
	this.boxName = config.boxName || "Theke";
};
ZMO.NewKegNewsModel =function(config){
	this.brand = config.brand;
	this.size = config.size;
	this.location = config.location;
	this.kegId = config.kegId;
	this.boxId = config.boxId;
	this.image = baseUrl +config.image;
	this.date = config.date?new ZMO.TimeParser(config.date).getDefaultTime():"";
};
ZMO.NewUserNewsModel =function(config){
	this.image = baseUrl +config.image;
	this.date = config.date?new ZMO.TimeParser(config.date).getDefaultTime():"";
	this.userId = config.userId;
	this.userName = config.userName;
	this.place = config.location || "";
};
ZMO.AchievementModel = function(config){
	this.userId = config.userId;
	this.userName = config.userName;
	this.date = config.date?new ZMO.TimeParser(config.date).getDefaultTime():"",//config.date?new Date(config.date).toGMTString():(new Date()). toGMTString();
			
	this.type = config.type;
	this.achievementId = config.achievementId;
	this.achievementName = config.achievementName;
	this.description = config.description;
	this.image =baseUrl +config.image;
	this.place = config.location || "";
};
ZMO.OtherModel = function(config){
	this.text = config.text;
	this.image = baseUrl +config.image||config.IMAGE_PATH||config.imagepath;
	this.type = config.type;
	this.date = config.date?new ZMO.TimeParser(config.date).getDefaultTime():"";//config.date?new Date(config.date).toGMTString():(new Date()). toGMTString();
	
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
	var parseTeamModel = function(team){
		var arr = [];
		$.each(team,function(ind,member){
			arr.push({
				userName:member.userName,
				userId:member.userId,
				userImage:baseUrl+member.userImage,
				won:member.won,
				amount:member.amount
			});
		});
		return arr;
		
	}
	this.team1 = parseTeamModel(config.team1);
	this.team2 = parseTeamModel(config.team2);
//	this.team1 = parseTeam(config.team1,"userName");
//	this.team2 = parseTeam(config.team2,"userName");
//	this.team1Images = parseTeam(config.team1,"userImage");
//	this.team2Images = parseTeam(config.team2,"userImage");
	this.team1Amount = sumArr(parseTeam(config.team1,"amount"));
	this.team2Amount = sumArr(parseTeam(config.team2,"amount"));
	this.team1Won =   parseTeam(config.team1,"won");
	this.team2Won = parseTeam(config.team2,"won");
	var that = this;
	
	this.winner =(function(){
		if(typeof that.team1Won!="undefined" &&typeof that.team1Won[0]!="undefined" && that.team1Won[0]){
			return config.team1;
		}else{
			return config.team2;
		}
	})();
	
	this.image =baseUrl + config.image||config.challengeImage;
	this.dateParser = config.startDate?new ZMO.TimeParser(config.startDate):null;
	this.date = config.startDate?new ZMO.TimeParser(config.startDate).getDefaultTime():"";//config.date?new Date(config.date).toGMTString():(new Date()). toGMTString();
	this.place = config.location || "";
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
	this.userImage = baseUrl +(config.imagePath||config.userImage);
	this.totalAmount = config.totalAmount != undefined?config.totalAmount.toFixed(2):0.00;
	this.rfidTag = config.rfidTag;
	// ignore all achievements if there are way to many
	var max = ZMO.modules.Constants.member.MAX_ACHIEVEMENTS;
	if (this.achievements != undefined && this.achievements.length > max) {
		this.additional = true;
		this.additionalCount = config.achievements.length - max;
		this.achievements.splice(max, this.achievements.length - max);
	}
	
};
ZMO.AchievementsModel = function(config) {
	this.achievementName = config.achievementName;
	this.achievementId = config.achievementId;
	this.users = config.users;
	this.achievementImage =baseUrl + config.imagePath||config.achievementImage;
	this.count = config.users ?config.users.length :0;
	
	// ignore all achievements if there are way to many
	var max = ZMO.modules.Constants.member.MAX_ACHIEVEMENTS;
	if (this.users != undefined && this.users.length > max) {
		this.additional = true;
		this.additionalCount = config.users.length - max;
		this.users.splice(max, this.users.length - max);
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

