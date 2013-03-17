
/**
* Main Initializer for App
*
* @class MyClass
* @constructor
*/
var ZMO = ZMO || {};
baseUrl = "";
/**
 * Loads all js and starts, when finished
 */
function appReady(){
	head.js(
			"js/App/Constants.js",
			//Util
			"js/Util/Util.js",
			"js/Util/UtilConstants.js",
			"js/Util/Browser/Throbber.js",
			"js/Util/Browser/ChangePage.js",
			"js/Util/Date/TimeParser.js",
			"js/Util/Net/Ajax.js",
			"js/Util/Net/Ajax.js",
			"js/Util/Object/Exists.js",
			"js/Util/Notifications/Notifications.js",
			"js/Util/Mobile/OnlineRecognizer.js",
			"js/Util/Mobile/Store.js",
			"js/Util/Mobile/Animator.js",
			"js/Util/Mobile/FastButton.js",
			"js/Util/Localization/Localization.js",
			"js/Util/Browser/Scroller.js",
			//"js/Util/Mobile/Scroller.js",
			
			
			"js/Util/Globalfunctions.js",

			//Modules
			"js/Modules/Model.js" ,
			"js/Modules/Constants.js",
			"js/Modules/Drawfeed/Drawfeed.js",
			"js/Modules/NavigationMobile/NavigationMobile.js",
			"js/Modules/Challenges/Challenges.js",
			//"js/Modules/CreateChallenge/CreateChallengeView.js",
			//"js/Modules/CreateChallenge/CreateChallenge.js",
			"js/Modules/ReceiveChallenge/ReceiveChallenge.js",
			"js/Modules/Logout/Logout.js" ,
			"js/Modules/Header/header.js" ,
			"js/Modules/SideNavigation/SideNavigation.js" ,
			"js/Modules/ChallengeUserList/ChallengeUserList.js" ,
			"js/Modules/Settings/Settings.js" ,
			"js/Modules/Stats/Mobile/StatsMobile.js" ,
			//main
			"js/Modules/Core/View.js",
			"js/App/modules_properties.js",
			"js/App/SwipeHandler.js",
			"js/Modules/Core/Controller.js",
			
			function() {
				//Get config url if app
				if(typeof device!="undefined" && device.cordova){
					
					console.log("ZM_Request");
					//localStorage.setItem("serverurl","http://192.168.178.24:8080/zapfmaster2000-service/");
					//baseUrl = "http://192.168.178.24:8080/zapfmaster2000-service/";
					//console.log("Base url:"+localStorage.getItem("zm-serverurl"));
					$.ajax({
						url:"http://zapfmaster2000.de/config.json?_="+new Date().getTime(),
						type:"GET",
						complete:function(res){
							console.log("StatusCode : "+res.status);
							console.log("responseText: "+res.responseText);
							if(res.status==200){
								
								baseUrl = $.parseJSON(res.responseText).serverurl;
								console.log("Parsed Base Url: "+baseUrl);
								localStorage.setItem("zm-serverurl",baseUrl);
								console.log("Saved Base url:"+localStorage.getItem("zm-serverurl"));
							}
							//init controller
							if(ZMO.controller)ZMO.controller.init();
							//init swipe control
							if(ZMO.swipeHandler)ZMO.swipeHandler.init();
							localStorage.setItem("zm-serverurl",baseUrl);
							console.log("afterwards Base url:"+localStorage.getItem("zm-serverurl"));
						}
					});
				}else{
					console.log("NO Request!");
					//init controller
					if(ZMO.controller)ZMO.controller.init();
					//init swipe control
					if(ZMO.swipeHandler)ZMO.swipeHandler.init();
				}
				
				
			}
		);
};
var app = document.URL.indexOf( 'http://' ) === -1 && document.URL.indexOf( 'https://' ) === -1;
if(app)document.addEventListener("deviceready", appReady, false);
else appReady();


