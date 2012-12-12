ZMO.modules = ZMO.modules || {};
/**
 * Set of Submodels
 * @param config
 * @return {ZMO.modules.StatsModel}
 */
ZMO.modules.StatsModel = function(config){
	//TODO
	if("undefined"!=typeof config){
		this.keg = config.keg;//new ZMO.modules.kegModel(config.keg);
		
		this.amount = new ZMO.modules.amountStatsModel(config.amount);
		this.achievements = new ZMO.modules.achievementStatsModel(config.achievements);
		this.drawCount  =new ZMO.modules.drawCountModel (config.drawCount);
		this.progress = new ZMO.modules.progressModel(config.progress);
	
		this.promille = config.promille?config.promille.average:null;
		this.bestUserList = config.bestUserList;
		this.bestUserListHour = config.bestUserListHour;
		this.achievementUserList = config.achievementUserList;
		this.drawCountUserList = config.drawCountUserList;
	}

};
/**
 * The Kegmodel
 * @param config
 * @returns {ZMO.modules.kegModel}
 */
ZMO.modules.kegModel = function(config){
	if("undefined"!=typeof config){
      this.keg_id= config.keg_id;
      this.brand=config.brand;
      this.size = config.size;
      this.start_date=new Date();//config.start_date;
      this.keg_numbers=config.keg_numbers;
      this.current_amount = config.current_amount;
	}
};
/**
 * Amount model
 * @param config
 * @returns {ZMO.modules.amount}
 */
ZMO.modules.amountStatsModel = function(config){
	if("undefined"!=typeof config){
        this.current=config.current;
        this.complete = config.complete;
        this.once=config.once;
        this.mostActivityHour=config.mostActivityHour;
	}
};
/**
 * Achievementstats
 * @param config
 * @returns {ZMO.modules.achievementStatsModel}
 */
ZMO.modules.achievementStatsModel=function(config){
	if("undefined"!=typeof config){
        this.count=config.count,
//        "achievementspeed": "50",
        this.mostAchievementHour=config.mostAchievementHour; 
	}
};
/**
 * 
 * @param config
 * @returns {ZMO.modules.progressModel}
 */
ZMO.modules.drawCountModel =function(config){
	if("undefined"!=typeof config){
		this.operations = config.operations;
	
	this.average = config.average;
	}
};
/**
 * 
 * @param config
 * @returns {ZMO.modules.progressModel}
 */
ZMO.modules.progressModel = function(config){
	if("undefined"!=typeof config){
	this.data = config.data;
	this.start_date = Date.UTC(2012, 12, 31);// config.start_date;
	this.interval = config.interval;
	}
};

