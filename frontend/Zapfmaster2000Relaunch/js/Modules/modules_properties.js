ZMO.modules_properties ={
		front:{
			sections:{
				navigation:[[new ZMO.ModuleModel("navigation", ZMO.modules.navigation, "100%")]],
				main:[[new ZMO.ModuleModel("drawfeed", ZMO.modules.drawfeed, "70%")],
				      [new ZMO.ModuleModel("kegstatus", ZMO.modules.kegstatus, "30%")]
				      ],
				footer:[]
			}
		},
		stats:{
			sections:{
				navigation:[[new ZMO.ModuleModel("navigation", ZMO.modules.navigation, "100%")]],
				main:[[new ZMO.ModuleModel("drawfeed", ZMO.modules.drawfeed, "70%")]],
				footer:[]
			}
		},		
		achievements:{
			sections:{
				navigation:[[new ZMO.ModuleModel("navigation", ZMO.modules.navigation, "100%")]],
				main:[[new ZMO.ModuleModel("drawfeed", ZMO.modules.drawfeed, "70%")]],
				footer:[]
			}
		},		
		members:{
			sections:{
				navigation:[[new ZMO.ModuleModel("navigation", ZMO.modules.navigation, "100%")]],
				main:[[new ZMO.ModuleModel("kegstatus", ZMO.modules.kegstatus, "70%"),new ZMO.ModuleModel("kegstat", ZMO.modules.kegstatus, "70%")],
				      [new ZMO.ModuleModel("drawfeed", ZMO.modules.drawfeed, "30%")]],
				footer:[]
			}
		},
		challenges:{
			sections:{
				navigation:[[new ZMO.ModuleModel("navigation", ZMO.modules.navigation, "100%")]],
				main:[[new ZMO.ModuleModel("challenges", ZMO.modules.challenges, "100%")]],
				footer:[]
			}
		}
}