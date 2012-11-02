ZMO.Util.Net = ZMO.Util.Net || {};

ZMO.Util.Net.Ajax = (function($){
	var c = ZMO.Constants.ajax;
	var aspirantModules =[];
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
	 * Parse the wanted queues from each aspirant and returns a unique array with the keywords
	 */
	var generateKeywordQueue = function(){
		var tmpArr = [];
		$.each(aspirantModules,function(ind,val){
			$.merge(tmpArr,val.keywords);
		})
		return $.unique(tmpArr);
	}
	/**
	 * If function want to get datas once this function removes aspirant after it gets the datas
	 */
	var removeOnceCalls = function(){
		var tmpArr = [];
		$.each(aspirantModules,function(ind,val){
			if(!val.once){
				tmpArr.push(val);
			}
		});
		aspirantModules = tmpArr;
	}
	/**
	 * Get datas with pull request (every 30s)
	 * @param {String} keywordQueue separated by comma
	 * @param {Function} callback
	 * @param {Boolean} onlyOnce
	 */
	var enqueueDatas = function(keywordQueue,callback,onlyOnce){
		aspirantModules.push({
			keywords:keywordQueue.split(","),
			callback:callback,
			once:!!onlyOnce
		});
	}
	/**
	 * Gets the datas in the Queue, aber pullTimeout time, calls self again
	 */
	var getEnqueueDatas = function(){
		getDatas(c.url,function(response){
			//send datas to aspirants
			$.each(aspirantModules,function(ind,val){
				val.callback(response);
			});
			//remove the modules which only wanted info once
			removeOnceCalls();
			setTimeout(function(){
				//if we dont wanna stop, again
				if(!stop)getEnqueueDatas();
			},c.pullTimeout);
		},$.param({
			keywords:generateKeywordQueue(),
		},true));
		
	}
	var stopPull = function(){
		stop = true;
	}
	var startPull = function(){
		if(stop){
			stop = false;
			getEnqueueDatas();
		}
		
	}
	var resetQueue = function(){
		aspirantModules = [];
	}
	var pub = {
			getDatas:getDatas,
			enqueueDatas:enqueueDatas,
			stopPull:stopPull,
			startPull:startPull,
			resetQueue:resetQueue
	}
	return pub;
	
})(jQuery);