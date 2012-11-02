ZMO.modules = ZMO.modules || {};

ZMO.modules.kegstatus = (function($,ajax){
	var c = ZMO.modules.Constants;
	var init = function(){
		ajax.enqueueDatas();
	}
	var getInstance = function(){
		return $("<div>").addClass("stats");
	}
	var pub = {
			getInstance:getInstance,
			init:init
	}
	return pub
}(jQuery,ZMO.ajax))
