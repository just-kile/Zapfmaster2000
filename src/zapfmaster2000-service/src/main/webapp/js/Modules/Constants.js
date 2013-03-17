ZMO.modules = ZMO.modules ||{}
//var baseUrl = "";
//var baseUrl ="http://192.168.178.24:8080/zapfmaster2000-service/";
ZMO.modules.Constants = {
		clickEvent:(function(){
			if(/iphone|ipad|ipod|android|blackberry|mini|windows\sce|palm/i.test(navigator.userAgent.toLowerCase())){
				return "touchend";
			}else{
				return "mouseup touchend";
			}
		})(),
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
			listLength:15
		},
		urls:{
			ACHIEVEMENTS:"rest/achievements",
			ACHIEVEMENTSSTATS:"rest/achievementStats",
			FRONTPAGESTATS:"rest/statistics/frontpageStats",
			USERFRONTPAGESTATS:"rest/statistics/frontpageUserStats",
			USERSTATS:"rest/statistics/userStats",
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
		language:{
			deImg:"images/view/flagDE.png",
			enImg:"images/view/flagEN.png"
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
		navbarStats:[{
			link:"#stats",
			text:"Stats",
			title:"Stats",
			image:"images/icons/16-line-chart.png"
		},{
			link:"#userstats",
			text:"User",
			title:"User",
			image:"images/icons/111-user.png"
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
			image:"images/icons/115-bow-and-arrow.png"
		},{
			link:"#stats",
			text:"Stats",
			title:"Stats",
			image:"images/icons/16-line-chart.png"
		
		},{
			link:"#settings",
			text:"Settings",
			title:"Settings",
			image:"images/icons/19-gear.png"
		
		},{
			link:"#logout",
			text:"Logout",
			title:"Logout",
			image:"images/icons/63-runner.png"
		
		}],
badExcuses:["Muss mir morgen Muttis Haare kämmen","Mutti","und 3"]
	

};