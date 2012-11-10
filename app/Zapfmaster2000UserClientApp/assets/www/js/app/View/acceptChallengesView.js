ZMUCA.acceptChallengesView = (function ($) {
	var init = function () {
       
    };
    var renderText =function(challengeModel){
    	 return ich["ZMUCA-acceptChallenge-info"]({
 			opponent:challengeModel.challenger[0].name,
 			duration:challengeModel.mode.length,
 			challenge:challengeModel.challenge.name
 		})
    }
    var pub = {
        init: init,
        renderText:renderText
    };

    return pub;

} (jQuery));