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
					ratio:"65%",
					position:[0,0]
				},{
					moduleId:"frontpagestats",
					ratio:"30%",
					position:[0,1]
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
					moduleId:"achievements",
					ratio:"100%",
					position:[0,0]
				}],
				footer:[]
			}
		},
		achievementstats:{
			sections:{
				navigation:[{
					moduleId:"navigation",
					ratio:"100%",
					position:[0,0]
				}],
				main:[{
					moduleId:"achievementlist",
					ratio:"70%",
					position:[0,0],
					nocache:true
				},{
					moduleId:"achievementstats",
					ratio:"30%",
					position:[0,1],
					nocache:true
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
		},
		challenges:{
			sections:{
				navigation:[{
					moduleId:"navigation",
					ratio:"100%",
					position:[0,0]
				}],
				main:[{
					moduleId:"challenges",
					ratio:"100%",
					position:[0,0]
				}],
				  
				footer:[]
			}
		}
};
