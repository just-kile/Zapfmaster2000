ZMO.DrawfeedModel = function(config){
	this.name = config.name;
	this.id = config.id;
	this.imageurl =config.imageurl;
	this.duration = config.duration;
	this.amount = config.amount;
	this.timestamp = config.timestamp;
	this.place = "Harzj";
	this.keg = config.keg;
}
ZMO.NewsModel =function(config){
	this.name = config.name||config.user;
	this.amount = Math.round(config.amount*100)/100;
	this.duration = config.duration;
	this.date = config.date||(new Date()). toGMTString()
	this.place = config.place;
	this.keg =config.keg;
	this.brand = config.brand||config.BRAND;
	this.image =config.image||config.IMAGE_PATH;
	this.userid = config.userid;
}
ZMO.AchievementModel = function(config){
	this.name = config.name||config.ACHIEVEMENT_NAME;
	this.userid = config.userid;
	this.username = config.username|| config.user;
	this.date = config.date||(new Date()). toGMTString();
	this.type = config.type;
	this.achievement_id = config.achievement_id||config.ACHIEVEMENT_ID;
	this.description = config.description;
	this.image =config.image||config.ACHIEVEMENT_IMAGE_PATH;
	this.public = config.public;
	
}
ZMO.OtherModel = function(config){
	this.text = config.text;
	this.image = config.image||config.IMAGE_PATH||config.imagepath;
	this.type = config.type;
	this.date = new Date();
	
}
ZMO.ChallengeStartedModel = function(config){
	this.userid = config.userid;
	this.username = config.username;
	this.userimage = config.userimage
	this.start_time = config.start_time;
	this.type = config.type;
	this.challenge_type = config.challenge_type;
	this.won =config.won;
	this.team = config.team;
	this.image =config.image;
	this.duration = config.duration;
}
ZMO.GlobalChallengeModel = function(config){
	this.type = config.type;
	this.challenge_type = config.challenge_type;
	this.duration = config.duration;
	var parseTeam = function(teamArr,key){
		var arr = [];
		$.each(teamArr,function(ind,val){
//			arr.push(new ZMO.ChallengeStartedModel(val))
			arr.push(val[key]);
			
		});
		return arr;
	}
	var sumArr =function(arr){
		var count = 0;
		for (var i = 0; i < arr.length; i++ )
		{
			count += arr[i];
		}
		return count;
	}
	this.team1 = parseTeam(config.team1,"username")
	this.team2 = parseTeam(config.team2,"username")
	this.team1Images = parseTeam(config.team1,"userimage")
	this.team2Images = parseTeam(config.team2,"userimage")
	this.team1Amount = sumArr(parseTeam(config.team1,"amount"));
	this.team2Amount = sumArr(parseTeam(config.team2,"amount"));
	this.image = config.image;
	this.date = config.start_time;
	this.challengeId = config.challenge_id;
}

ZMO.ModuleModel  =function(id,element,ratio){
	this.id= id;
	this.element= element;
	this.ratio= ratio;
}

