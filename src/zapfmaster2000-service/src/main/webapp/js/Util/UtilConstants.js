ZMO.UtilConstants ={
		debugMode :true,
		langToken:"zmo-lang",
		defaultLang:(function(){
			if(navigator.language == "de"){
				return "de";
			}else{
				return "en";
			}
		})(),
		templateUrl : "js/Templates/templates.html",
		ajax:{
//			pullTimeout:60000,//ms
			pullTimeout:5000,
			NO_DATA_RECEIVED_CODE:503
		},
		throbberUrl:"images/view/throbber.gif",
		tokenName:"token",
		serverDateFormat:"yyyyMMdd-HHmmss",
		clientDateFormat:"dd.MM.yyyy HH:mm:ss",
		chartDateFormat:"MM-dd-yyyy HH:mm",
		shortFormat:"E, HH:mm",
		linkUrls:{
			user:"#user",
			achievement:"#achievement",
			team1:"#user",
			team2:"#user",
			winner:"#user"
		}
};