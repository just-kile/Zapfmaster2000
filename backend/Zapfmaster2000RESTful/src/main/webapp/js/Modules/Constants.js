ZMO.modules = ZMO.modules ||{}

ZMO.modules.Constants = {
		navbar :	[{
			id:"topnav-1",
			link:"#",
			text:"Home",
			title:"Home"
		},{
			id:"topnav-2",
			link:"#stats",
			text:"Stats",
			title:"Stats"
		},{
			id:"topnav-3",
			link:"#members",
			text:"Members",
			title:"Members"
		},{
			id:"topnav-4",
			link:"#achievements",
			text:"Achievements",
			title:"Achievements"
		},{
			id:"topnav-5",
			link:"#challenges",
			text:"Challenges",
			title:"Challenges"
		}],
		navbarMobile :	[{
			link:"#",
			text:"News",
			title:"News",
			image:"images/icons/166-newspaper.png"
		},{
			link:"#duels",
			text:"Duels",
			title:"Duels",
			image:"images/icons/89-dumbell.png"
		},{
			link:"#dare",
			text:"Dare",
			title:"Dare",
			image:"images/icons/22-skull-n-bones.png"
		}],
		drawfeed:{
			types:{
				ACHIEVEMENT:"ACHIEVEMENT",
				DRAWING:"DRAWING",
				OTHER:"OTHER",
				CHALLENGE_STARTED:"CHALLENGE_STARTED",
				CHALLENGE_DONE :"CHALLENGE_DONE",
				CHALLENGE_DECLINED:"CHALLENGE_DECLINED"
			},
			listLength:10
		},
		urls:{
			FRONTPAGESTATS:"/tmp/stats_fp.json",
			//NEWSLIST:"tmp/news.json"
			NEWSLIST:"rest/news",
			//NEWSLIST:"//thomas-notebook-ubuntu/rest/news",
//			STATS:"rest/stats/getStatsAsJson"
			STATS:"tmp/stats.json",
			CHALLENGES:"tmp/challenges.json",
			MEMBERS:"rest/members"
			//NEWSLIST:"//thomas-notebook-ubuntu/rest/news"
		},
		push:{
			NEWS:"rest/push/news",
			RFID:"rest/push/login"
		},
		member:{
			MAX_ACHIEVEMENTS:7
		},
		
}