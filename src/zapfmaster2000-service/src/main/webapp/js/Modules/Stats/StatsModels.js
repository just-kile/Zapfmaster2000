ZMO.modules = ZMO.modules || {};
/**
 * Set of Submodels
 * @param config
 * @return {ZMO.modules.StatsModel}
 */
ZMO.modules.StatsModel = function(config){
	//TODO
	if("undefined"!=typeof config){
		this.keg =new ZMO.modules.kegModel(config.keg);
		
		this.amount = new ZMO.modules.AmountStatsModel(config.amount);
		this.achievements = new ZMO.modules.AchievementStatsModel(config.achievements);
		this.drawCount  =new ZMO.modules.DrawCountModel (config.drawCount);
		this.progress = new ZMO.modules.ProgressModel(config.progress||config.drinkProgress);
	
		this.promille = config.promille?config.promille.average||config.promille.alcoholLevel:(config.alcoholLevel?config.alcoholLevel.alcoholLevel:null);
		this.promille = this.promille?this.promille.toFixed(2):null;
		
		this.bestUserList = new ZMO.modules.BestUserListModel(config.bestUserList||config.amountUserList);
		this.bestUserListHour = new ZMO.modules.BestUserListModel(config.amountUserListLastHour);
		this.achievementUserList = new ZMO.modules.AchievementUserListModel(config.achievementUserList);
		this.drawCountUserList = new ZMO.modules.DrawCountUserListModel(config.drawCountUserList);
		this.rank = new ZMO.modules.RankModel(config.rank);
	}

};
ZMO.modules.RankModel =function(config){
	if("undefined"!=typeof config){
		this.amount = config.amount||config.rankAmount;
		this.achievements = config.achievements||config.rankAchievements;
		this.drawCount = config.drawCount||config.rankDrawCount;
	}
};
ZMO.modules.BestUserListModel = function(config){
	var arr = [];
	if("undefined"!=typeof config){
		jQuery.each(config,function(ind,user){
			arr.push({
				userId:user.userId||user.id,
				userName:user.userName||user.name,
				userImage:user.userImage||user.image,
				amount:user.amount.toFixed(2)
			});
		});
	}
	return arr;
};
ZMO.modules.AchievementUserListModel = function(config){
	var arr = [];
	if("undefined"!=typeof config){
	jQuery.each(config,function(ind,user){
		arr.push({
			userId:user.userId||user.id,
			userName:user.userName||user.name,
			userImage:user.userImage||user.image,
			achievementCount:user.count||user.achievementCount
		});
	});
	}
	return arr;
};
ZMO.modules.DrawCountUserListModel = function(config){
	
	var arr = [];
	if("undefined"!=typeof config){
	jQuery.each(config,function(ind,user){
		arr.push({
			userId:user.userId||user.id,
			userName:user.userName||user.name,
			userImage:user.userImage||user.image,
			drawCount:user.drawCount
		});
	});
	}
	return arr;
};
/**
 * The Kegmodel
 * @param config
 * @returns {ZMO.modules.kegModel}
 */

ZMO.modules.kegModel = function(config){
	var kegArr = [];
	if("undefined"!=typeof config){
	jQuery.each(config,function(ind,keg){
		var date = new ZMO.TimeParser(keg.lastsUntil);
		kegArr.push({
			 keg_id: keg.keg_id||keg.kegId,
		      brand:keg.brand,
		      size : keg.size,
		      start_date:new ZMO.TimeParser(keg.start_date||keg.startDate).getDefaultTime(),
		      keg_numbers:keg.keg_numbers||keg.kegNumber,
		      current_amount : keg.currentAmount.toFixed(2),
		      lastsUntil :date.getDefaultTime(),
		      lastsUntilShort:date.getHoursMinutes(),
		      boxId:keg.boxId
		});
	});

	}
	return kegArr;

};
/**
 * Amount model
 * @param config
 * @returns {ZMO.modules.amount}
 */
ZMO.modules.AmountStatsModel = function(config){
	if("undefined"!=typeof config){
        this.complete = config.amountTotal.toFixed(2);
        this.once=config.greatestDrawing;
        this.mostActivityHour=config.mostActivityHour||config.amountMostActivityHour;
	}
};
/**
 * Achievementstats
 * @param config
 * @returns {ZMO.modules.achievementStatsModel}
 */
ZMO.modules.AchievementStatsModel=function(config){
	if("undefined"!=typeof config){
        this.count=config.count||config.achievementCount,
        //this.achievementspeed = config.achievementspeed;
        this.mostAchievementHour=config.mostAchievementHour||config.achievementMostActivityHour; 
	}
};
/**
 * 
 * @param config
 * @returns {ZMO.modules.progressModel}
 */
ZMO.modules.DrawCountModel =function(config){
	if("undefined"!=typeof config){
		this.operations = config.count||config.drawCount;
	
	this.average = config.averageOperationsPerHour||config.drawCountPerHour;
	this.average = this.average.toFixed(2)
	}
};
/**
 * 
 * @param config
 * @returns {ZMO.modules.progressModel}
 */
ZMO.modules.ProgressModel = function(config){
	if("undefined"!=typeof config){
	this.data = config.data||config.amount;
	//this.start_date = Date.UTC(2012, 12, 31);// config.start_date;
	var date = config.from||config.startDate;
	this.start_date =new ZMO.TimeParser(date).getTimestamp();
	
	this.interval = config.interval;
	}
};
ZMO.modules.AchievementStatisticsModel = function(config){
	this.achievementId = config.achievementId;
	this.achievementName = config.achievementName;
	this.achievementImage = config.achievementImage;
	this.description = config.description;
	this.users = (function(){
		var arr = [];
		$.each(config.users,function(ind,user){
			arr.push({
				userName:user.userName,
				userImage:user.userImage,
				userId:user.userId,
				date:new ZMO.TimeParser(user.date).getDefaultTime()
			});
		});
		return arr;
	})();
};
