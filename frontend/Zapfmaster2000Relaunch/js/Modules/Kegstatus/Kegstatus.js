ZMO.modules = ZMO.modules || {};

ZMO.modules.kegstatus = (function($,Ajax){
	var c = ZMO.modules.Constants;
	var init = function(){
		
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
