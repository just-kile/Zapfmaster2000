ZMO.Util.Net = ZMO.Util.Net || {};
ZMO.Util.Net.Ajax = (function($){
	var c = ZMO.Constants.ajax;
	var aspirantModules ={};
	var stop=true;
	/**
	 * Get datas instant
	 */
	var getDatas = function(url,callback,datas){
		$.ajax({
			url:url,
			type:"GET",
			data:datas,
			success:function(resp){
				try{
					callback(resp);
				}catch(e){
					ZMO.log(e);
				}
				
			}
		});
	};

	/**
	 * If function want to get datas once this function removes aspirant after it gets the datas
	 */
	var removeOnceCalls = function(){
		var tmpObj = {};
		$.each(aspirantModules,function(url,arr){
			
			var tmpArr = [];
			$.each(arr,function(ind,val){
				if(!val.once){
					tmpArr.push(val);
				}
				tmpObj[url] = tmpArr;
				
			});
			
		});
		aspirantModules =  tmpObj;
		
		
	};
	/**
	 * Get datas with pull request (every 30s)
	 * Module inscribe to ajax module, that it will have the information
	 * @param {String or Array} keywordQueue separated by comma or array
	 * @param {Function} callback
	 * @param {Boolean} onlyOnce
	 */
	var enqueueDatas = function(url,callback,onlyOnce){
		if(!ZMO.exists(aspirantModules[url])){
			aspirantModules[url] = [];
		}
		aspirantModules[url].push(  {
			callback:callback,
			once:!!onlyOnce
		});
	};
	/**
	 * Gets the datas in the Queue, aber pullTimeout time, calls self again
	 */
	var getEnqueueDatas = function(){
		$.each(aspirantModules,function(url,modulesArr){
			getDatas(url,function(response){
				//send datas to aspirants
				$.each(modulesArr,function(ind,val){
					if(val.callback)val.callback(response);
				});
				//remove the modules which only wanted info once
				removeOnceCalls();
				

			});
		});
		setTimeout(function(){
			//if we dont wanna stop, again
			if(!stop)getEnqueueDatas();
		},c.pullTimeout);
	};
	var timeout = null;
	var stopPull = function(){
		stop = true;
		if(ZMO.exists(timeout))clearTimeout(timeout);
	};
	//wait for all requests to enqueue-> then start
	var startPull = function(){
		timeout = setTimeout(function(){
			if(stop){
				stop = false;
				getEnqueueDatas();
			}
		},200);
		
	};
	/**
	 * Resets the modules
	 */
	var resetQueue = function(){
		aspirantModules = {};
	};
	var dummyCallback;
	var connectToChannel = function(channel,callback){
		//DUMMY
//		var tmp = {
//				"userid" : "10",
//				"amount" : "0.05",
//				"date" : "2012-09-01 03:40:45",
//				"duration" : "0",
//				"name" : "PUSHEXAMPLE",
//				"image" : "images\/avatars\/43985828483840.jpg",
//				"keg" : "2",
//				"brand" : "Warsteiner",
//				"type" : "DRAWING"
//			};
		dummyCallback = callback;
//		if(callback)callback(tmp);
		$.ajax({
			type:"GET",
			url:ZMO.modules.Constants.push.NEWS,
			timeout:100000, 
			success:function(data){
				if(callback)callback(data);
				connectToChannel(callback);
			},
			error:function(){
				ZMO.log("Error: reconnect in 1000ms")
				setTimeout(function(){
					connectToChannel(callback);
				},1000);
			}
		});
		
	};
	var rfidDummyCallback;
	var rfidLogin = function(callb){
		//if(callb)callb();
//		$.ajax({
//			type:"GET",
//			url:ZMO.modules.Constants.push.RFID,
//			timeout:100000, 
//			success:callb,
//			error:function(){
//				setTimeout(function(){
//					rfidLogin(callb);
//				},1000);
//			}
//		});
		rfidDummyCallback = callb;
	};
	var pushDummyData = function(refresh){
		dummyCallback({
				"userid" : "10",
				"amount" : Math.random(),
				"date" : "2012-09-01 03:40:45",
				"duration" : "0",
				"name" : "PUSHEXAMPLE",
				"image" : "images\/avatars\/43985828483840.jpg",
				"keg" : "2",
				"brand" : "Warsteiner",
				"type" : "DRAWING"
			},refresh);
	};
	var pushRfidDummyData = function(name,logout){
		rfidDummyCallback(name,logout);
	};
	var pub = {
			getDatas:getDatas,
			enqueueDatas:enqueueDatas,
			stopPull:stopPull,
			startPull:startPull,
			resetQueue:resetQueue,
			connectToChannel:connectToChannel,
			rfidLogin:rfidLogin,
			
			pushDummyData:pushDummyData,
			pushRfidDummyData:pushRfidDummyData,

	};
	return pub;
	
	
	
})(jQuery);

/*
 * NOTUSED DUE TO USING FULL PATHS TO RESTAPI, NOT KEYWORDS
 */
//ZMO.Util.Net.Ajax213213 = (function($){
//	var c = ZMO.Constants.ajax;
//	var aspirantModules =[];
//	var stop=true;
//	/**
//	 * Get datas instant
//	 */
//	var getDatas = function(url,callback,datas){
//		$.ajax({
//			url:url,
//			type:"GET",
//			data:datas,
//			success:function(resp){
//				try{
//					callback(resp);
//				}catch(e){
//					ZMO.log(e);
//				}
//				
//			}
//		});
//	};
//	/**
//	 * Parse the wanted queues from each aspirant and returns a unique array with the keywords
//	 */
//	var generateKeywordQueue = function(){
//		var tmpArr = [];
//		$.each(aspirantModules,function(ind,val){
//			$.merge(tmpArr,val.keywords);
//		})
//		return $.unique(tmpArr);
//	}
//	/**
//	 * If function want to get datas once this function removes aspirant after it gets the datas
//	 */
//	var removeOnceCalls = function(){
//		var tmpArr = [];
//		$.each(aspirantModules,function(ind,val){
//			if(!val.once){
//				tmpArr.push(val);
//			}
//		});
//		aspirantModules = tmpArr;
//	}
//	/**
//	 * Get datas with pull request (every 30s)
//	 * Module inscribe to ajax module, that it will have the information
//	 * @param {String or Array} keywordQueue separated by comma or array
//	 * @param {Function} callback
//	 * @param {Boolean} onlyOnce
//	 */
//	var enqueueDatas = function(keywordQueue,callback,onlyOnce){
//		aspirantModules.push({
//			keywords:$.isArray(keywordQueue)?keywordQueue:keywordQueue.split(","),
//			callback:callback,
//			once:!!onlyOnce
//		});
//	}
//	/**
//	 * Gets the datas in the Queue, aber pullTimeout time, calls self again
//	 */
//	var getEnqueueDatas = function(){
//
//		getDatas(c.url,function(response){
//			//send datas to aspirants
//			$.each(aspirantModules,function(ind,val){
//				val.callback(response);
//			});
//			//remove the modules which only wanted info once
//			removeOnceCalls();
//			
//			setTimeout(function(){
//				//if we dont wanna stop, again
//				if(!stop)getEnqueueDatas();
//			},c.pullTimeout);
//		},$.param({
//			keywords:generateKeywordQueue(),
//		},true));
//		
//	}
//	var stopPull = function(){
//		stop = true;
//	}
//	
//	var startPull = function(){
//		if(stop){
//			stop = false;
//			getEnqueueDatas();
//		}
//		
//	}
//	/**
//	 * Resets the modules
//	 */
//	var resetQueue = function(){
//		aspirantModules = [];
//	}
//	var pub = {
//			getDatas:getDatas,
//			enqueueDatas:enqueueDatas,
//			stopPull:stopPull,
//			startPull:startPull,
//			resetQueue:resetQueue
//	}
//	return pub;
//	
//})(jQuery);