

ZMUCA.UserModel = function(config){
	this.userid = config.userid;
	this.password = config.password;
	this.name = config.name;
	this.gender = config.gender;
	this.weight = config.weight;
	this.image = config.image;
	this.rfid = config.rfid;
	this.qrcode = config.qrcode;
}


ZMUCA.NewsModel =function(config){
	this.name = config.name;
	this.amount = config.amount;
	this.duration = config.duration;
	this.date = config.date;
	this.place = config.place;
	this.keg =config.keg;
	this.brand = config.brand;
	this.image =config.image;
}

/**
 * challenge: 
 * 
 */
ZMUCA.ChallengeModel = function(config){
	this.challenge = config.challenge;
	this.challenger =config.challenger;
	this.victim = config.victim;
	this.mode = config.mode;
}