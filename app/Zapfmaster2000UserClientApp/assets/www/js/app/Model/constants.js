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
		actualUser:{},
		challenges:[{
			id:"challenge-normal",
			name:"1 vs 1",
			image:"css/images/challenges/1v1.png",//"images/avatars/felix.jpg",
			description:"Fordere einen Mittrinker zum Wettrinken auf! Gibt auch nen Achievement ;)"
		}
//		,{
//			id:"challenge-team",
//			name:"Team vs Team",
//			image:"images/avatars/felix.jpg"
//		}
		],
		modes:[{
			name:"Laenge",
			id:"length",
			params:[{
				name:"10 Minuten",
				id:"mode-tenMinutes",
				image:"css/images/challenges/10min.jpg",
				length:10
			},{
				name:"30 Minuten",
				id:"mode-thirtyMinutes",
				image:"css/images/challenges/30min.jpg",
				length:30
			},{
				name:"1 Stunde",
				id:"mode-60Minutes",
				image:"css/images/challenges/60min.jpg",
				length:60
			}]
		}],
		badExcuses :["Muss morgen früh raus, um in den Anden Kajak zu fahren",
		             "Trinke lieber nen Tee",
		             "Hab Mami versprochen weniger zu trinken." ]
}	


ZMUCA.showThrobber = function(show){
	if (show){
		$.mobile.showPageLoadingMsg(null, null, false);
	}
	else{
		$.mobile.hidePageLoadingMsg();
	}
};