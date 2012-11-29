/**
 * position:[row,column] 
 */
ZMO.modules_properties ={
		front:{
			sections:{
				navigation:[{
					moduleId:"navigation",
					ratio:"100%",
					position:[0,0]
				}],
				main:[{
					moduleId:"drawfeed",
					ratio:"70%",
					position:[0,0]
				},{
					moduleId:"frontpagestats",
					ratio:"30%",
					position:[0,1]
				},{
					moduleId:"challenges",
					ratio:"100%",
					position:[1,0]
				}],
				  
				footer:[]
			}
		},
		stats:{
			sections:{
				navigation:[{
					moduleId:"navigation",
					ratio:"100%",
					position:[0,0]
				}],
				main:[{
					moduleId:"lineChart",
					ratio:"100%",
					position:[0,0]
				},{
					moduleId:"kegstatus",
					ratio:"30%",
					position:[1,0]
				},{
					moduleId:"bestlist",
					ratio:"30%",
					position:[1,1]
				},
				{
					moduleId:"achievementBestlist",
					ratio:"30%",
					position:[1,2]
				}],
				  
				footer:[]
			}
		},		
		achievements:{
			sections:{
				navigation:[{
					moduleId:"navigation",
					ratio:"100%",
					position:[0,0]
				}],
				main:[{
					moduleId:"drawfeed",
					ratio:"70%",
					position:[0,0]
				},{
					moduleId:"frontpagestats",
					ratio:"30%",
					position:[0,1]
				}],
				  
				footer:[]
			}
		},		
		members:{
			sections:{
				navigation:[{
					moduleId:"navigation",
					ratio:"100%",
					position:[0,0]
				}],
				main:[{
					moduleId:"members",
					ratio:"100%",
					position:[0,0]
				}],
				  
				footer:[]
			}
		}
};



//ZMO.modules_properties ={
//		front:{
//			sections:{
//				navigation:[[new ZMO.ModuleModel("navigation", ZMO.modules.navigation, "100%")]],
//				main:[[new ZMO.ModuleModel("drawfeed", ZMO.modules.drawfeed, "70%")],
//				      [new ZMO.ModuleModel("frontpagestats", ZMO.modules.frontpageStats, "30%")]
//				      ],
//				footer:[]
//			}
//		},
//		stats:{
//			sections:{
//				navigation:[[new ZMO.ModuleModel("navigation", ZMO.modules.navigation, "100%")]],
//				main:[[new ZMO.ModuleModel("drawfeed", ZMO.modules.drawfeed, "70%")]],
//				footer:[]
//			}
//		},		
//		achievements:{
//			sections:{
//				navigation:[[new ZMO.ModuleModel("navigation", ZMO.modules.navigation, "100%")]],
//				main:[[new ZMO.ModuleModel("drawfeed", ZMO.modules.drawfeed, "70%")]],
//				footer:[]
//			}
//		},		
//		members:{
//			sections:{
//				navigation:[[new ZMO.ModuleModel("navigation", ZMO.modules.navigation, "100%")]],
//				main:[[new ZMO.ModuleModel("kegstatus", ZMO.modules.kegstatus, "70%"),new ZMO.ModuleModel("kegstat", ZMO.modules.kegstatus, "70%")],
//				      [new ZMO.ModuleModel("drawfeed", ZMO.modules.drawfeed, "30%")]],
//				footer:[]
//			}
//		},
//		challenges:{
//			sections:{
//				navigation:[[new ZMO.ModuleModel("navigation", ZMO.modules.navigation, "100%")]],
//				main:[[new ZMO.ModuleModel("challenges", ZMO.modules.challenges, "100%")]],
//				footer:[]
//			}
//		}
//}