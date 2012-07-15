var ZMUCA = ZMUCA || {};

ZMUCA.Constants ={
		debugMode:false,
		defaultImage:"css/images/default.png",
		phpUrl:"http://server/beerometer/zapfmasterUserClientApp.php",
		securityToken:"16d7a4fca7442dda3ad93c9a726597e4",
		node:{
//			drawUrl:"http://server:1337/draw",
//			newsUrl:'http://server:1337/news',
//			datasUrl:'http://server:1337/getdatas'
//			drawUrl:"http://127.0.0.1:1337/draw",
//			actionsUrl:'http://127.0.0.1:1337/actions',
//			datasUrl:'http://127.0.0.1:1337/getdatas'
				drawUrl:"http://192.168.178.31:1337/draw",
				actionsUrl:'http://192.168.178.31:1337/actions',
				datasUrl:'http://192.168.178.31:1337/getdatas'
		},
		actualUser:{}
}	