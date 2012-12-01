ZMO.Util.Net = ZMO.Util.Net || {};
ZMO.Util.Net.Ajax = (function($){
	var c = ZMO.Constants.ajax;
	var aspirantModules ={};
	var stop=true;
	/**
	 * Get datas instant
	 */
	var getDatas = function(url,callback,datas){
		if(!ZMO.exists(datas))datas = {};
		datas["_"] = new Date().getTime();
		datas["token"] = localStorage.getItem("token");
		$.ajax({
			url:url,
			type:"GET",
			data:datas,
			complete:function(resp){
			if(resp.status==200){
				try{
					var data = $.parseJSON(resp.responseText);
				}catch(e){
					ZMO.log(e);
				}
				callback(data);
			}else{
				
			}	
			}
		});
	};
	/*****
	 * Interval pull
	 *****/
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
	 * @param {Boolean} rawData return only
	 */
	var enqueueDatas = function(url,callback,onlyOnce,rawData){
		if(!ZMO.exists(aspirantModules[url])){
			aspirantModules[url] = [];
		}
		aspirantModules[url].push(  {
			callback:callback,
			once:!!onlyOnce,
			rawData:!!rawData
		});
	};
	/**
	 * Gets the datas in the Queue, wait pullTimeout time, calls self again
	 */
	var getEnqueueDatas = function(){
		$.each(aspirantModules,function(url,modulesArr){
			getDatas(url,function(response){
				//send datas to aspirants
				var statsModel = new ZMO.modules.StatsModel(response);
				$.each(modulesArr,function(ind,val){
					if(val.callback)val.callback(val.rawData?response:statsModel);
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
	/*******
	 * Server Push
	 *******/
	var newsPush=null,rfidPush=null;
	var connectToChannel = function(callback){
		var data ={};
		data["token"] = localStorage.getItem("token");
		newsPush = $.ajax({
			type:"GET",
			url:ZMO.modules.Constants.push.NEWS,
			timeout:10000000, 
			data:data,
			success:function(data){
				if(callback && data!="")callback(data);
				ZMO.log("New Request to "+ZMO.modules.Constants.push.NEWS);
				connectToChannel(callback);
			},
			error:function(e){
				if(e.status==0){
					ZMO.log("Request abort success!");
				}else{
					
				ZMO.log("Error: reconnect news...");
				//setTimeout(function(){
					connectToChannel(callback);
				//},1000);

				}
			}
		});
		
	};
	var rfidLogin = function(callback){
		var datas ={};
		datas["token"] = localStorage.getItem("token");
		rfidPush = $.ajax({
			type:"GET",
			url:ZMO.modules.Constants.push.RFID,
			timeout:10000000, 
			data:datas,
			success:function(data){
				if(callback && data!="")callback(data);
				ZMO.log("New Request to "+ZMO.modules.Constants.push.RFID);
				rfidLogin(callback);
			},
			error:function(e){
				if(e.status==0){
					ZMO.log("Request abort success!");
				}else{
					ZMO.log("Error: reconnect RFID...")
				//setTimeout(function(){
					rfidLogin(callback);
				//},1000);
				}
			}
		});
	};
	var abortNewsPush = function(){
		if(ZMO.exists(newsPush))newsPush.abort();
		ZMO.log("News Push aborted.");
		newsPush = null;
	};
	var abortRfidPush = function(){
		if(ZMO.exists(rfidPush))rfidPush.abort();
		ZMO.log("RFID Push aborted.");
		rfidPush = null;
	};
	var abortPushRequests = function(){
		abortNewsPush();
		abortRfidPush();
	};
	var pub = {
			getDatas:getDatas,
			enqueueDatas:enqueueDatas,
			stopPull:stopPull,
			startPull:startPull,
			resetQueue:resetQueue,
			connectToChannel:connectToChannel,
			rfidLogin:rfidLogin,
			abortNewsPush:abortNewsPush,
			abortRfidPush:abortRfidPush,
			abortPushRequests:abortPushRequests
	
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