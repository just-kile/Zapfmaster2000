ZMO.TimeParser = function(serverDateString){
	var serverTimeFormat = ZMO.UtilConstants.serverDateFormat;
	var clientTimeFormat = ZMO.UtilConstants.clientDateFormat;
	var date;
	if(typeof serverDateString == "undefined" || serverDateString == null){
		return null;
	}
	//decide if timestamp (number) or not (string)
	if(typeof serverDateString=="number"){
		date = new Date(serverDateString);
	}else if(typeof serverDateString =="object"){  
		date = serverDateString;
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
	var getServerTimeFormat = function(){
		return formatDate(date,serverTimeFormat);
	}
	var getHoursMinutes = function(){
		return formatDate(date,ZMO.UtilConstants.shortFormat);
	}
	var getTimestamp = function(){
		return date.getTime();
	};
	var getChartTime = function(){
		return formatDate(date,ZMO.UtilConstants.chartDateFormat);
	}
	var getChartTimeAddMins = function(mins,isTimestamp){
		var newTime = new Date(date.getTime()+ mins*60*1000);
		return isTimestamp?newTime.getTime():formatDate(newTime,ZMO.UtilConstants.chartDateFormat);
	}
	var getTimeParserAddMins = function(mins){
		return new ZMO.TimeParser(new Date(date.getTime()+ mins*60*1000));
	}

	var getDate = function(){
		return date;
	}
	var setDate = function(newDate){
		date = newDate;
		return this;
	}
	//public
	this.getDefaultTime = getDefaultTime;
	this.getTimestamp = getTimestamp;
	this.getHoursMinutes = getHoursMinutes;
	this.getChartTime = getChartTime;
	this.getChartTimeAddMins = getChartTimeAddMins;
	this.getDate = getDate;
	this.setDate = setDate;
	this.getServerTimeFormat = getServerTimeFormat;
	
	this.getTimeParserAddMins = getTimeParserAddMins;
};


