ZMO.Util.Event = ZMO.Util.Event || {};

ZMO.Util.Event.Delegate = (function($){
	var on = function(ev,el,callb){
		$(document).on(ev,$(el).selector,callb);
	}
	
	var pub={
			on:on
	};
	return pub;
})(jQuery);