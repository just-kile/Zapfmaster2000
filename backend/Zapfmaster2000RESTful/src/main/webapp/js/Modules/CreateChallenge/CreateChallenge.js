/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.createChallenge = (function($,view,ajax){
	var c = ZMO.modules.Constants;
	var container = null;
	var challengeDatas = {};
	var resetChallengeDatas = function(){
		challengeDatas = {type:"",mode:"",victim:{}};
	}
	resetChallengeDatas();
	var showMemberlist = function(){
		ajax.getDatas("tmp/challenges.json",function(resp){
			alert("MEMBERS")
		});
	}
	var showDurationlist = function(){
		view.createChallengeOverview(container,c.challenges.modes.params,function(e,mode){
			showMemberlist();
			challengeDatas.mode = mode;
		});
	}
	var createChallengesModesOverview= function(){
		view.createChallengeOverview(container,c.challenges.types,function(e,type){
			showDurationlist();
			challengeDatas.type = type;
		});
	};
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(){
		ZMO.throbber.hide();
		createChallengesModesOverview();

	};
	
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		return container = $("<div>").addClass("statsdiv");
	};
	var pub = {
			getInstance:getInstance,
			init:init
	};
	return pub;
}(jQuery,ZMO.modules.createChallengeView,ZMO.ajax))
