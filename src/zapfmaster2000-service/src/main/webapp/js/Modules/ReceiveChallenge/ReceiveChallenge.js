/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.receiveChallenge = (function($,ajax){
	var mC = ZMO.modules.Constants;
	
	var container =null;
	var sendChallengeResponse =function(yep,notId,datas){
		if(yep){
			ajax.sendChallengeConfirmation(datas);
		}else{
			ajax.sendChallengeRejection(datas);
		}
		ZMO.modules.header.clearNotification(notId);
	}
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var onChallengeReceive = function(datas){
		if(ZMO.exists(datas.type)){
			var type = datas.type.toLowerCase();
			if(type=="challengerequest"){
//				var yep = confirm(datas.challengerUserName+" hat dich zu einem Duell herausgefordert! Nimmst du an?");
//				if(yep){
//					ajax.sendChallengeConfirmation(datas);
//				}else{
//					ajax.sendChallengeRejection(datas);
//				}
				ZMO.Notifications.send(datas.challengerUserName+" hat dich zu einem Duell herausgefordert!","Du wurdest herausgefordert!");
				ZMO.modules.header.pushNotification(datas,sendChallengeResponse);
			}else if(type=="challengeaccepted"){
				alert("Die Herausforderung zwischen "+datas.user1Name+" und "+ datas.user2Name+ " wurde gestartet!");
			}else if(type=="challengedeclined"){
				alert("Die Herausforderung zwischen "+datas.user1Name+" und "+ datas.user2Name+ " wurde abgelehnt!");
			}

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
			onChallengeReceive:onChallengeReceive,
			sendChallengeResponse:sendChallengeResponse
	};
	return pub;
}(jQuery,ZMO.ajax));
