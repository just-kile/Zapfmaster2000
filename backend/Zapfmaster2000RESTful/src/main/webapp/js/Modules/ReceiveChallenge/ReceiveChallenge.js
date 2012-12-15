/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.receiveChallenge = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var container =null;
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var onChallengeReceive = function(datas){
		var yep = confirm("Pete hat dich herausgefordert zu: 1v1 den ganzen Abend. Nimmst du die Herausforderung an?");
		if(yep){
			ajax.sendChallengeConfirmation(datas);
		}else{
		}
	};
	var init = function(hashParams,moduleParams){
		ajax.connectChallengeReceive(onChallengeReceive);

	};
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		return (container = $("<div>"));
	};
	var pub = {
			getInstance:getInstance,
			init:init,
			//debug
			onChallengeReceive:onChallengeReceive
	};
	return pub;
}(jQuery,ZMO.ajax));
