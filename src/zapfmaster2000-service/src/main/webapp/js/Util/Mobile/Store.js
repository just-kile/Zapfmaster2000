ZMO = ZMO || {};
ZMO.store = (function($,document){
	var s = localStorage;
	var challengeKey = "challenges";
	var set = function(key,val){
		s.setItem(key,JSON.stringify(val));
	}
	var get = function(key){
		return $.parseJSON(s.getItem(key));
	}
	
	var getChallenges = function(){
		return get(challengeKey);
	}
	var addChallenge =function(receiveChallengeModel){
		if(!get(challengeKey)){
			resetAllChallenges();
		}
		var chObj = get(challengeKey);
		chObj.push(receiveChallengeModel);
		set(challengeKey,chObj);
	}
	var resetAllChallenges= function(){
		set(challengeKey,[]);
	};
	var removeChallenge = function(challengeId){
		var chObj = get(challengeKey);
		chObj = $.grep(chObj,function(val,ind){
//			return ind==index;
			return challengeId==val.challengeId;
		},true);
		set(challengeKey,chObj);
	};
	var init = function(){
		
	};
	init();
	
	var pub = {
			set:set,
			get:get,
			getChallenges:getChallenges,
			addChallenge:addChallenge,
			resetAllChallenges:resetAllChallenges,
			removeChallenge:removeChallenge
			
	};
	return pub;
})(jQuery,document);
