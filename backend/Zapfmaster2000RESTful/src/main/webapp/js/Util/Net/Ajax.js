ZMO.Util.Net = ZMO.Util.Net || {};
ZMO.Util.Net.Ajax = (function($){
	var c = ZMO.UtilConstants.ajax;
	/*****
	 * Receive Datas
	 *****/
	var aspirantModules ={};
	var conf = {
			MODULE_DATA:"data",
			MODULE_PARAMS:"params"
	};
	var stop=true;
	/**
	 * Get datas instant
	 */
	var getDatas = function(url,callback,datas){
		if(!ZMO.exists(datas))datas = {};
		if(ZMO.Constants.debugMode)datas["_"] = new Date().getTime();
		datas["token"] = localStorage.getItem(ZMO.UtilConstants.tokenName);
		$.ajax({
			url:url,
			type:"GET",
			data:datas,
			complete:function(resp){
			if(resp.status==200){
				var data = {};
				try{
					data = $.parseJSON(resp.responseText);
				}catch(e){
					ZMO.log(e);
				}
				callback(data);
			}else{
				ZMO.log("AJAX ERROR "+resp.status);
			}
			if(ZMO.throbber)ZMO.throbber.hide();
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
			var tmpArr = {};
			tmpArr[conf.MODULE_DATA] = [];
			tmpArr[conf.MODULE_PARAMS] = arr[conf.MODULE_PARAMS];
			$.each(arr[conf.MODULE_DATA],function(ind,val){
				if(!val.once){
					tmpArr[conf.MODULE_DATA].push(val);
				}
			});
			tmpObj[url] = tmpArr;
		});
		aspirantModules =  tmpObj;
		
		
	};
	/**
	 * Get datas with pull request (every 30s)
	 * Module inscribe to ajax module, that it will have the information
	 * the handled object can contain following attributes
	 * @param {String} url
	 * @param {Function} callback
	 * @param {Object} datas request params
	 * @param {Boolean} onlyOnce flag, if request should not be repeated every 30s
	 * @param {Boolean} rawData return only the raw datas without ZMO.StatsModel wrapper
	 * @param {Boolean} uniqueRequest forces a unique request even if urls are equal
	 */
	//var enqueueDatas = function(url,callback,data,onlyOnce,rawData){
	var enqueueDatas = function(attr){
		//default settings
		var settings = {
				url:"",
				callback:null,
				data:{},
				onlyOnce:false,
				rawData:false,
				uniqueRequest:false
		};
		//override them
		settings = $.extend(settings,attr);
		var url = settings.uniqueRequest?settings.url+"?_"+new Date().getTime():settings.url;
		if(!ZMO.exists(aspirantModules[url])){
			aspirantModules[url] = {};
			aspirantModules[url][conf.MODULE_DATA]= [];
			aspirantModules[url][conf.MODULE_PARAMS]= {};
		}
		aspirantModules[url][conf.MODULE_DATA].push(  settings);
		aspirantModules[url][conf.MODULE_PARAMS] = $.extend(aspirantModules[url][conf.MODULE_PARAMS],settings.data);
	};
	/**
	 * Gets the datas in the Queue, wait pullTimeout time, calls self again
	 */
	var getEnqueueDatas = function(){
		$.each(aspirantModules,function(url,modulesArr){
			getDatas(url,function(response){
				//send datas to aspirants
				var statsModel = new ZMO.modules.StatsModel(response);
				$.each(modulesArr[conf.MODULE_DATA],function(ind,val){
					if(val.callback)val.callback(val.rawData?response:statsModel);
				});
				//remove the modules which only wanted info once
				removeOnceCalls();
				

			},modulesArr[conf.MODULE_PARAMS]);
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
	var pushRequests = {};
	/**
	 * General Server Push Connection Function
	 * @param {String} url
	 * 			The Url to the channel
	 * @param {Function} successCb
	 * 			The function that will be executed, when data received
	 * @param {Function} errorCb
	 * 			The function that will be executed, when error occurs, or timeout
	 */
	var connectToChannel = function(url,successCb,errorCb,data){
		if(!data)data ={};
		data["token"] = localStorage.getItem(ZMO.UtilConstants.tokenName);
		pushRequests[url] = $.ajax({
			type:"GET",
			url:url,
			timeout:10000000, 
			data:data,
			success:function(data){
				if(successCb && data!="")successCb(data);
				connectToChannel(url,successCb,errorCb,data);
			},
			error:function(e){
				if(e.status==0){
					ZMO.log("Request abort success!");
				}else if(e.status==c.NO_DATAS_RECEIVED_CODE){
					ZMO.log("No Datas Received! Reconnect...");
//					if(errorCb)errorCb(e);
					connectToChannel(url,successCb,errorCb,data);
				}else{
					ZMO.log("Error! Status "+e.status);
					ZMO.log("Reconnect in 5s...");
				
					if(errorCb)errorCb(e);
					setTimeout(function(){
						connectToChannel(url,successCb,errorCb,data);
					},5000);
				}
			}
		});
		
	};
	var connectToNewsPush = function(callback){
		
		connectToChannel(ZMO.modules.Constants.push.NEWS,callback);
	};
	var rfidLogin = function(callback){
		connectToChannel(ZMO.modules.Constants.push.RFID,callback);
	};

	var abortReq = function(url){
		var push = pushRequests[url];
		if(ZMO.exists(push))push.abort();
		ZMO.log(" Push aborted: "+url);
		pushRequests[url] = null;
	};
	var abortNewsPush = function(){
		abortReq(ZMO.modules.Constants.push.NEWS);
	};
	var abortRfidPush = function(){
		abortReq(ZMO.modules.Constants.push.RFID);
	};

	var abortPushRequests = function(){
		abortNewsPush();
		abortRfidPush();
	};
	
	/*****
	 * Challenge Requests
	 *****/
	var abortChallengePush = function(){
		abortReq(ZMO.modules.Constants.push.CHALLENGE);
	};
	var connectChallengeReceive = function(callback){
		connectToChannel(ZMO.modules.Constants.push.CHALLENGE,callback);
	};
	var sendChallengeConfirmation = function(data){
			
	};
	var sendChallengeRequest = function(datas,callback){
		if(callback)callback();
	};
	var pub = {
			getDatas:getDatas,
			enqueueDatas:enqueueDatas,
			stopPull:stopPull,
			startPull:startPull,
			resetQueue:resetQueue,
			
			connectToChannel:connectToChannel,
			connectToNewsPush:connectToNewsPush,
			rfidLogin:rfidLogin,
			abortNewsPush:abortNewsPush,
			abortRfidPush:abortRfidPush,
			abortPushRequests:abortPushRequests,
			
			connectChallengeReceive:connectChallengeReceive,
			abortChallengePush:abortChallengePush,
			sendChallengeConfirmation:sendChallengeConfirmation,
			sendChallengeRequest:sendChallengeRequest
	};
	return pub;
	
	
})(jQuery);
