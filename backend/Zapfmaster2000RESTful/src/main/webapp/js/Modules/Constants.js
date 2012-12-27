ZMO.modules = ZMO.modules ||{}

ZMO.modules.Constants = {
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
			FRONTPAGESTATS:"tmp/stats_fp.json",
			USERFRONTPAGESTATS:"tmp/kegstatus.json",

			NEWSLIST:"rest/news",
			STATS:"tmp/stats.json",
			
			MEMBERS:"rest/members",
			
			CHALLENGES:"tmp/challenges.json",
			CHALLENGEEMEMBERS:"rest/challenge/users",
			STARTCHALLENGE:"rest/challenge/start/{0}",
			ACCEPTCHALLENGE:"rest/challenge/accept",
			DENYCHALLENGE:"rest/challenge/deny"
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
					name:"10 Minuten",
					id:"mode-tenMinutes",
					image:"images/challenges/10min.jpg",
					duration:10
				},{
					name:"30 Minuten",
					id:"mode-thirtyMinutes",
					image:"images/challenges/30min.jpg",
					duration:30
				},{
					name:"1 Stunde",
					id:"mode-60Minutes",
					image:"images/challenges/60min.jpg",
					duration:60
				}]
			},
			types:[{
				id:"1v1",
				name:"1 vs 1",
				image:"images/challenges/1v1.png",//"images/avatars/felix.jpg",
				description:"Fordere einen Mitzapfer zum Wettzapfen auf!"
			}]
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
			link:"#dare",
			text:"Dare",
			title:"Dare",
			image:"images/icons/22-skull-n-bones.png"
		
}]
};