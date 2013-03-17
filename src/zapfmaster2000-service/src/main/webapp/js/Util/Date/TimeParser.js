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
		 if(serverDateString!=0){
			 return formatDate(date,clientTimeFormat);
		 }else{
			return false;
		 }
 
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


