ZMO.modules = ZMO.modules ||{}

ZMO.modules.Constants = {
		drawfeed:{
			types:{
				ACHIEVEMENT:"ACHIEVEMENT",
				DRAWING:"DRAWING",
				OTHER:"OTHER",
				CHALLENGE_STARTED:"CHALLENGE_STARTED",
				CHALLENGE_DONE :"CHALLENGE_DONE",
				CHALLENGE_DECLINED:"CHALLENGE_DECLINED",
				NEW_KEG:"NEW_KEG",
				NEW_USER:"NEW_USER"
			},
			listLength:10
		},
		urls:{
			ACHIEVEMENTS:"rest/achievements",
			ACHIEVEMENTSSTATS:"rest/achievementStats",
			FRONTPAGESTATS:"rest/statistics/frontpageStats",
			USERFRONTPAGESTATS:"rest/statistics/frontpageUserStats",

			NEWSLIST:"rest/news",
			//STATS:"tmp/stats.json",
			STATS:"rest/statistics/globalStats",
			MEMBERS:"rest/members",
			
			CHALLENGES:"rest/challenge",
			CHALLENGEEMEMBERS:"rest/challenge/users",
			STARTCHALLENGE:"rest/challenge/start/{0}",
			ACCEPTCHALLENGE:"rest/challenge/accept",
			DENYCHALLENGE:"rest/challenge/decline"
			// NEWSLIST:"//thomas-notebook-ubuntu/rest/news"
		},
		push:{
			NEWS:"rest/push/news",
			NEWSUPDATE:"rest/push/draftkit",
			CHALLENGE:"rest/push/challenge"
		},
		member:{
			MAX_ACHIEVEMENTS:7
		},
		challenges:{
			modes:{
				name:"Laenge",
				id:"length",
				params:[{
					name:"10min",
					id:"mode-tenMinutes",
					image:"images/challenges/10min.jpg",
					duration:10,
					type:"1v1"
				},{
					name:"30min",
					id:"mode-thirtyMinutes",
					image:"images/challenges/30min.jpg",
					duration:30,
					type:"1v1"
				},{
					name:"60min",
					id:"mode-60Minutes",
					image:"images/challenges/60min.jpg",
					duration:60,
					type:"1v1"
				}]
			}
		}
		,navbar :	[{
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
			link:"#stats",
			text:"Stats",
			title:"Stats",
			image:"images/icons/16-line-chart.png"
		
		},{
			link:"#settings",
			text:"Einstellungen",
			title:"Einstellungen",
			image:"images/icons/102-walk.png"
		
		},{
			link:"#logout",
			text:"Logout",
			title:"Logout",
			image:"images/icons/102-walk.png"
		
		}],
badExcuses:["1","2","und 3"]
	

};