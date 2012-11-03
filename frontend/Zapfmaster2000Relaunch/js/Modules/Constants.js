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
		drawfeed:{
			types:{
				ACHIEVEMENT:"ACHIEVEMENT",
				DRAWING:"DRAWING",
				OTHER:"OTHER",
				CHALLENGE_STARTED:"CHALLENGE_STARTED",
				CHALLENGE_DONE :"CHALLENGE_DONE",
				CHALLENGE_DECLINED:"CHALLENGE_DECLINED"
			}
		},
		urls:{
			FRONTPAGESTATS:"/tmp/stats_fp.json",
			NEWSLIST:"/tmp/news.json"
		}

}