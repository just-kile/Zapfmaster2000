ZMO.TimeParser = function(serverDateString){
	var serverTimeFormat = ZMO.UtilConstants.serverDateFormat;
	var clientTimeFormat = ZMO.UtilConstants.clientDateFormat;
	var date;
	//decide if timestamp (number) or not (string)
	if(typeof serverDateString=="number"){
		date = new Date(serverDateString);
	}else{
		date = new Date(getDateFromFormat(serverDateString,serverTimeFormat));
		
	}
	
	var getDefaultTime = function(){
		return formatDate(date,clientTimeFormat);
 
	};
	var getHoursMinutes = function(){
		return formatDate(date,ZMO.UtilConstants.shortFormat);
	}
	var getTimestamp = function(){
		return date.getTime();
	};
	//public
	this.getDefaultTime = getDefaultTime;
	this.getTimestamp = getTimestamp;
	this.getHoursMinutes = getHoursMinutes;
};


