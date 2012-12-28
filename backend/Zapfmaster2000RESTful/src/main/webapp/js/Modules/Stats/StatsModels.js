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
		this.progress = new ZMO.modules.ProgressModel(config.progress);
	
		this.promille = config.promille?config.promille.average||config.promille.alcoholLevel.toFixed(2):null;
		
		this.bestUserList = new ZMO.modules.BestUserListModel(config.bestUserList);
		this.bestUserListHour = new ZMO.modules.BestUserListModel(config.bestUserListHour);
		this.achievementUserList = new ZMO.modules.AchievementUserListModel(config.achievementUserList);
		this.drawCountUserList = new ZMO.modules.DrawCountUserListModel(config.drawCountUserList);
		this.rank = new ZMO.modules.RankModel(config.rank);
	}

};
ZMO.modules.RankModel =function(config){
	if("undefined"!=typeof config){
		this.amount = config.amount;
		this.achievements = config.achievements;
		this.drawCount = config.drawCount;
	}
}
ZMO.modules.BestUserListModel = function(config){
	var arr = [];
	if("undefined"!=typeof config){
		jQuery.each(config,function(ind,user){
			arr.push({
				userId:user.userId||user.id,
				userName:user.userName||user.name,
				userImage:user.userImage||user.image,
				amount:user.amount
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
			achievementCount:user.count
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
		      current_amount : keg.current_amount||keg.currentAmount,
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
        this.complete = config.amountTotal;
        this.once=config.greatestDrawing;
        this.mostActivityHour=config.mostActivityHour;
	}
};
/**
 * Achievementstats
 * @param config
 * @returns {ZMO.modules.achievementStatsModel}
 */
ZMO.modules.AchievementStatsModel=function(config){
	if("undefined"!=typeof config){
        this.count=config.count,
        //this.achievementspeed = config.achievementspeed;
        this.mostAchievementHour=config.mostAchievementHour; 
	}
};
/**
 * 
 * @param config
 * @returns {ZMO.modules.progressModel}
 */
ZMO.modules.DrawCountModel =function(config){
	if("undefined"!=typeof config){
		this.operations = config.count;
	
	this.average = config.averageOperationsPerHour.toFixed(2);
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
	this.start_date =new ZMO.TimeParser(config.from).getTimestamp();
	
	this.interval = config.interval;
	}
};

