ZMO.Util.Net = ZMO.Util.Net || {};
ZMO.Util.Net.Ajax = (function($){
	var c = ZMO.UtilConstants.ajax;

	var r = ZMO.onlineRecognizer;
	/*****
	 * Receive Datas
	 *****/
	var aspirantModules ={};
	var conf = {
			MODULE_DATA:"data",
			MODULE_PARAMS:"params"
	};
	var stop=true;
	var isAndroid = window.device &&( window.device.platform == 'android' || window.device.platform == 'Android');
	var gcmHandlers = {};
	/**
	 * Get datas instant
	 */
	var getDatas = function(url,callback,datas,type,callOnError){
		if(!ZMO.exists(datas))datas = {};
		//if(ZMO.Constants.debugMode)datas["_"] = new Date().getTime();
		datas["token"] = localStorage.getItem(ZMO.UtilConstants.tokenName);
//		if(r){
//			if(!r.isConnected()||!r.isOffline()){
//				var yep =confirm("Connection unavailable, retry?");
//				if(yep){
//					getDatas(url,callback,datas,type);
//				}
//				return false;
//			}else if(!r.isConnected()){
//				setTimeout(function(){
//					getDatas(url,callback,datas,type);
//				},5000);
//				return false;
//			}
//			
//		}
		$.ajax({
			url:baseUrl+url,
			type:type?type:"GET",
			data:datas,
			complete:function(resp){
			if(resp.status==200){
				var data = {};
				try{
					data = $.parseJSON(resp.responseText);
				}catch(e){
					ZMO.logger.error(e);
				}
				callback(data);
			}else{
				ZMO.logger.error("AJAX ERROR "+resp.status);
				if(callOnError)callback();
			}
			if(ZMO.throbber)ZMO.throbber.hide();
			}
		});
	};
	var postDatas = function(url,callback,datas,callOnError){
		getDatas(url,callback,datas,"POST",callOnError);
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
	var enqueueTimeout = null;
	var getEnqueueDatas = function(){
		$.each(aspirantModules,function(url,modulesArr){
			getDatas(url,function(response){
				//send datas to aspirants
				var statsModel =  ZMO.modules.StatsModel
							?new ZMO.modules.StatsModel(response)
							:null;
				$.each(modulesArr[conf.MODULE_DATA],function(ind,val){
					if(val.callback)val.callback(val.rawData?response:statsModel);
				});
				//remove the modules which only wanted info once
				removeOnceCalls();
				

			},modulesArr[conf.MODULE_PARAMS]);
		});
		if(enqueueTimeout){
			clearTimeout(enqueueTimeout);
			
		}
		enqueueTimeout = setTimeout(function(){
			enqueueTimeout = null;
			//if we dont wanna stop, again
			if(!stop)getEnqueueDatas();
		},c.pullTimeout);
	};
	var updateEnqueueParams = function(url,data){
		if(aspirantModules[url]){
			$.extend(aspirantModules[url][conf.MODULE_PARAMS],data);
		}
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
	var getAspirants = function(){
		return aspirantModules;
	}
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
	var connectToChannel = function(url,successCb,errorCb,data,isNotCancable){
		ZMO.logger.log("connect to channel "+url);
		if(!data)data ={};
		data["token"] = localStorage.getItem(ZMO.UtilConstants.tokenName);
		data["_"] = new Date().getTime();
//		if(r){
//			if(!r.isConnected()||!r.isOffline()){
//				var yep =confirm("Connection unavailable, retry?");
//				if(yep){
//					connectToChannel(url,callback,datas,type);
//				}
//				return false;
//			}else if(!r.isConnected()&&!isAborted(url)){
//				setTimeout(function(){
//					connectToChannel(url,callback,datas,type);
//				},5000);
//				return false;
//			}else if(isAborted(url)){
//				return false;
//			}
//			
//		}
		if(!ZMO.onlineRecognizer || ZMO.onlineRecognizer.isConnected()){
			
		if(pushRequests[url]){
			ZMO.logger.log("Abort "+url);
			pushRequests[url].req && pushRequests[url].req.abort();
		}
		
		var request = $.ajax({
			type:"GET",
			url:baseUrl+url,
			timeout:600000, 
			data:data,
//			headers:{
//				"Connection": "Keep-Alive"
//			},
			complete:function(resp){
				
				if(resp.status == 200){
					var json = $.parseJSON(resp.responseText);
					if(successCb && json!="")successCb(json);
					connectToChannel(url,successCb,errorCb,data);
				}else{
					if(resp.status==c.NO_DATAS_RECEIVED_CODE || (resp.status == 0&&resp.statusText!="abort")){
						ZMO.logger.warning("No Datas Received! Reconnect...");
						ZMO.logger.log(resp.statusText);
						//						if(errorCb)errorCb(e);
						connectToChannel(url,successCb,errorCb,data);
					}else if(resp.status==0){
						ZMO.logger.log("Request abort success!");
						if(false && isNotCancable){
							ZMO.logger.log("Request is not abortable, reconnect in 5s ...");
							setTimeout(function(){
//								connectToChannel(url,successCb,errorCb,data,isNotCancable);
								connectToChannel(url,successCb,errorCb,data);
							},5000);
						}
						
					}else{
						ZMO.logger.error("Error! Status "+resp.status);
						ZMO.logger.log("Reconnect in 5s...");
					
						if(errorCb)errorCb(resp);
						setTimeout(function(){
							connectToChannel(url,successCb,errorCb,data);
						},5000);
					}
				}
			},
			error:function(e){
				if(errorCb)errorCb(e);
			}
		});
		pushRequests[url]={
				req:request,
				success:successCb,
				error:errorCb,
				data:data,
				isNotCancable:isNotCancable
		};
		
	return request;
		}else{
			console.log("No Network Connection!");
		}
		
	};
	var getGcmHandlers = function(){
		return gcmHandlers;
	}
	var setGcmHandler = function(key,val){
			return gcmHandlers[key]=val;
	}
	var connectToNewsPush = function(callback){
		if(false &&isAndroid){//registerCallbackHandler
			ZMO.logger.log("Connect to news push");
			$.each(ZMO.modules.Constants.drawfeed.types,function(key,type){
				setGcmHandler(type,callback);
			});
			
		}else{//start long polling
			ZMO.logger.log("Connect to news push long polling");
			setTimeout(function(){
				connectToChannel(ZMO.modules.Constants.push.NEWS,callback);
			},100);
		}
	};
	var connectToNewsUpdate = function(boxId,callback){
		setTimeout(function(){
		connectToChannel(ZMO.modules.Constants.push.NEWSUPDATE+"/"+boxId,callback);
		},100);
	};
	var isAborted = function(url){
		return !pushRequests[url];
	}
	var pausePushRequests =function(){
		$.each(pushRequests,function(reqUrl,req){
				//pushRequests[reqUrl] = null;
				req.req.abort();
				ZMO.logger.log(" Push Paused for url "+reqUrl);
		});
	}
	var resumePushRequests = function(url){
		if(url && pushRequests[url]){
			var reqObj = pushRequests[url];
			connectToChannel(url,reqObj.success,reqObj.error,reqObj.data,reqObj.isNotCancable);
			ZMO.logger.log(" Push Resumed for Url "+url);
		}else{
			$.each(pushRequests,function(reqUrl,reqObj){
//				pushRequests[reqUrl] = null;
			
				connectToChannel(reqUrl,reqObj.success,reqObj.error,reqObj.data,reqObj.isNotCancable);
//				reqObj.req.abort();
				ZMO.logger.log(" Push Resumed for Url "+reqUrl);
				
		});
		
		}
		
	}
	var getPushRequests = function(){
		return pushRequests;
	}
	var isRequestPaused = function(url){
		if(pushRequests[url]){
			return pushRequests[url].req.readyState ==0;
		}
		return true;
	}
	var abortReq = function(url){
		$.each(pushRequests,function(reqUrl,req){
			var regex = new RegExp("^"+url+"");
			if(regex.test(reqUrl)&& ZMO.exists(req)){
				//pushRequests[reqUrl] = null;
				
				req.req.abort();
				ZMO.logger.log(" Push aborted: "+url);
				delete pushRequests[url];
				
			}
		});
//		var push = pushRequests[url];
//		if(ZMO.exists(push))push.abort();
//		ZMO.log(" Push aborted: "+url);
//		pushRequests[url] = null;
	};
	var abortNewsPush = function(){
		abortReq(ZMO.modules.Constants.push.NEWS);
	};
	var abortNewsUpdatePush = function(){
		abortReq(ZMO.modules.Constants.push.NEWSUPDATE);
	};

	var abortPushRequests = function(){
		abortNewsPush();
		abortNewsUpdatePush();
	};
	
	/*****
	 * Challenge Requests
	 *****/
	var abortChallengePush = function(){
		setTimeout(function(){
			abortReq(ZMO.modules.Constants.push.CHALLENGE);
		},100);
	};
	var onGetPluginCalls = function(e){
		ZMO.logger.log(e);
		
	}
	
	
	
	
	var errorHandler = function(e){
		ZMO.logger.log(e);
	}
	var sendRegisterId = function(id){
		console.log("Send RegId:"+id);
		var gcmId = localStorage.getItem("zm-gcm");
		if(gcmId==id){
			//alert("Gleich")
		}else{
			//alert("verschieden")
		}
		localStorage.setItem("zm-gcm",id);
		//new ZMO.Util.Popup().open($("<input>").val(id));
		$.ajax({
			url:baseUrl+"rest/login/gcm",
			type:"POST",
			data:{
				token:localStorage.getItem(ZMO.UtilConstants.tokenName),
				gcm:id
			},
			complete:function(e){
				//new ZMO.Util.Popup().open($("<input>").val(e&&e.status));
			}
			
		});
		
	}
	var onNotification = function(e){
    	
    	ZMO.logger.log(e);
    	switch(e.event){
    	case "registered":
    		sendRegisterId(e.regid);
    		ZMO.logger.log("Receive Registered");
    		break;
    	case "message":
    		if(true || e.foreground){
    			ZMO.logger.log("Receive Message"+JSON.stringify(e));
    			//if(callback)callback($.parseJSON(e.payload.message));
    			// new ZMO.Util.Popup().open($("<input>").val("Wuff!"));
    			 var json = e.payload;
    			 if(getGcmHandlers()[json.type]){
    				 ZMO.logger.log("Received Handler for "+json.type);
    				 getGcmHandlers()[json.type](json);
    				 
    			 }else{
    				 ZMO.logger.log("No handler for "+JSON.stringify(e));
    				 ZMO.logger.log("GCM Handler: "+getGcmHandlers()[json.type]);
    			 }
    		}else{
    			
    		}
    		break;
    	case "error":
    		errorHandler(e.msg);
    		break;
    	default:
    		ZMO.logger.log("Message received,but dont know what it is");
    	}
    }
	var connectChallengeReceive = function(callback){
		if (isAndroid) {
			$.each(ZMO.modules.Constants.challenges.types,function(key,type){
				setGcmHandler(type,callback);
			});
			
		} else if(window.device && window.device.platform =="ios"){
		    window.plugins.pushNotification.register(tokenHandler, errorHandler, {"badge":"true","sound":"true","alert":"true","ecb":"onNotificationAPN"});
		}else{
			setTimeout(function(){
				connectToChannel(ZMO.modules.Constants.push.CHALLENGE,callback,null,null,true);
			},100);
		}
		
		
		
	};
	var sendChallengeConfirmation = function(data){
		var datas = {
				pendingChallengeId:data["challengeId"]
		};
		var url = ZMO.modules.Constants.urls.ACCEPTCHALLENGE;
		
		postDatas(url,function(){
			
		},datas);
	};
	var sendChallengeRejection = function(data){
		var datas = {
				pendingChallengeId:data["challengeId"]
		};
		var url = ZMO.modules.Constants.urls.DENYCHALLENGE;
		
		postDatas(url,function(){
			
		},datas);
	};
	var sendChallengeRequest = function(type,challengeeId,duration,callback){
		
		var url = ZMO.modules.Constants.urls.STARTCHALLENGE.replace("{0}",type);
		var datas = {
						challengeeId:challengeeId,
						duration:duration
					};
		postDatas(url,callback,datas);
	};
	var init = function(){
		ZMO.logger.log("Init ajax js");
		if(isAndroid){
		//	window.plugin.pushNotification.unregister(function(){
				window.plugins.pushNotification.register(onGetPluginCalls, errorHandler,{"senderID":c.GCM_API_KEY,"ecb":"ZMO.ajax.onNotification"});

			//});
					}
		
	}
	var pub = {
			init:init,
			getDatas:getDatas,
			postDatas:postDatas,
			
			enqueueDatas:enqueueDatas,
			stopPull:stopPull,
			startPull:startPull,
			updateEnqueueParams:updateEnqueueParams,
			resetQueue:resetQueue,
			updateEnqueueDatas:getEnqueueDatas,
			getAspirants:getAspirants,
			
			connectToChannel:connectToChannel,
			connectToNewsPush:connectToNewsPush,
			connectToNewsUpdate:connectToNewsUpdate,
			abortNewsPush:abortNewsPush,
			abortNewsUpdatePush:abortNewsUpdatePush,
			abortPushRequests:abortPushRequests,
			abortReq:abortReq,
			pausePushRequests:pausePushRequests,
			resumePushRequests:resumePushRequests,
			getPushRequests:getPushRequests,
			isRequestPaused:isRequestPaused,
			
			connectChallengeReceive:connectChallengeReceive,
			abortChallengePush:abortChallengePush,
			sendChallengeConfirmation:sendChallengeConfirmation,
			sendChallengeRejection:sendChallengeRejection,
			sendChallengeRequest:sendChallengeRequest,
			onNotification:onNotification,
			sendRegisterId:sendRegisterId
	};
	return pub;
	
	
})(jQuery);

