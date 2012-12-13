/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.createChallengeView = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var init = function(cont){
		
	};

	var createChallengeOverview = function(container,types,callb){
		container.empty();
		var ul = $("<ul>").addClass("createChallenges");
		container.append(ul);
		var template = ich["ZMO-createChallengeRow"];
		$.each(types,function(ind,typeModel){
			var row = $(template(typeModel)).on("mousedown",function(e){
				if(callb)callb(e,typeModel);
			});
			ul.append(row);
		});
		
	};
	var pub = {
			init:init,
			createChallengeOverview:createChallengeOverview,
	};
	return pub;
}(jQuery,ZMO.ajax));
