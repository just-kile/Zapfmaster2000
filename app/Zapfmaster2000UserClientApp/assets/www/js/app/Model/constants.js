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
			image:"images/avatars/felix.jpg"
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
				image:"images/avatars/felix.jpg",
				length:10
			},{
				name:"30 Minuten",
				id:"mode-thirtyMinutes",
				image:"images/avatars/felix.jpg",
				length:30
			},{
				name:"1 Stunde",
				id:"mode-60Minutes",
				image:"images/avatars/felix.jpg",
				length:60
			}]
		}]
}	