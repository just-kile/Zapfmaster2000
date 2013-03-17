ZMO.scrolling = (function(){
	var isScroll = true;
	var  getIsScroll = function(){
		return isScroll;
	}
	var preventScrolling = function(e){
		if(!getIsScroll()){
			e.preventDefault();
		}
			
	}
	var enable = function(){
		isScroll = true;
	}
	var disable=function(){
		isScroll = false;
	}
	document.addEventListener('touchmove', preventScrolling, false);
	var pub = {
			enable:enable,
			disable:disable
	}
	return pub;
})();

ZMO.Util.scrollHandler = (function($){
	var pub = {
			
	}
	return pub;
})(jQuery);