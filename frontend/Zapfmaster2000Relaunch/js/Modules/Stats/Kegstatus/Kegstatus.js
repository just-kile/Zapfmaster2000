ZMO.modules = ZMO.modules || {};

ZMO.modules.kegstatus = (function($,ajax){
	var mC = ZMO.modules.Constants;
	
	var onDatasLoaded =function(datas){
		console.log(datas);
	}
	var init = function(){
		ajax.enqueueDatas(mC.urls.FRONTPAGESTATS,onDatasLoaded);
		ajax.startPull();
	}
	var getInstance = function(){
		
		return $("<div>").addClass("statsDiv");
	}
	var pub = {
			getInstance:getInstance,
			init:init
	}
	return pub
}(jQuery,ZMO.ajax));
