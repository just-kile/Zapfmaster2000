ZMO.scrolling = (function() {
	var isScroll = true;
	var getIsScroll = function() {
		return isScroll;
	}
	var preventScrolling = function(e) {
		if (!getIsScroll()) {
			e.preventDefault();
		}

	}
	var enable = function() {
		isScroll = true;
	}
	var disable = function() {
		isScroll = false;
	}
	document.addEventListener('touchmove', preventScrolling, false);
	var pub = {
		enable : enable,
		disable : disable
	}
	return pub;
})();

ZMO.Util.scrollHandler = (function($) {
	var actLoadingFlag = false;
	var loadingOk = function() {
		actLoadingFlag = false;
	}

	var bindScrollHandler = function(callback) {

		var doc = $(document);
		$(window).bind("scroll", function(e) {

			var top = doc.height();// scrollElement.offset().top;
			var windowHeight = $(window).height();
			var windowTop = $(window).scrollTop() + windowHeight + 200;
			if (windowTop > top && top >= windowHeight && !actLoadingFlag) {
				actLoadingFlag = true;
				if (callback)
					callback(loadingOk);
			}

		});
	};
	var scroller = null;
	
	function initScrolling(el, callb,isMobile) {
		
		if (isMobile) {
			el.css("height", "100%");
			if (scroller)
				scroller.destroy();
			scroller = new iScroll(el[0], {
			    onScrollMove: function () {
			        // CHECK if we've 350px gap before reaching end of the page
			        if ( (this.y < (this.maxScrollY + 350)) && (!actLoadingFlag) ){ 
			          // start loading next page content here
			        	if (callb)
			        		callb(loadingOk);
			          // update this flag inside load more and set to 0 when complete
			        	actLoadingFlag = true; 
			        }
			      },
			      onScrollEnd: function () {
			        // check if we went down, and then load content
			        if ( !actLoadingFlag ) {
			          // Load more content
			        	if (callb)
			        		callb(loadingOk);
			          // update this flag inside load more and set to 0 when complete
			        	actLoadingFlag = true; 
			        } else {
			          // DO NOTHING
			        }
			      }
			});
			// bindScrollHandler(scrollElement);
		} else {
			// native scroll handler
			bindScrollHandler(callb);
		}
	}
var refresh = function(){
	if(scroller)scroller.refresh();
}
	var pub = {
		initScrolling : initScrolling,
		refresh:refresh
	}
	return pub;
})(jQuery);