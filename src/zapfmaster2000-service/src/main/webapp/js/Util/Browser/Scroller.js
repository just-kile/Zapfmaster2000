ZMO.scrolling = (function() {
	var isScroll = true;
	var isActScroll = false;
	var getIsScroll = function() {
		return isScroll;
	};
	var isActuallyScrolling = function(){
		return isActScroll;
	}
	var enableScrolling = function(){
		isActScroll = false;
	}
	var preventScrolling = function(e) {
		isActScroll = true;
		if (!getIsScroll()) {
			e.preventDefault();
		}

	};
	var enable = function() {
		isScroll = true;
	};
	var disable = function() {
		isScroll = false;
	};
	
	document.addEventListener('touchmove', preventScrolling, false);
	document.addEventListener('touchend', enableScrolling, false);
	var pub = {
		enable : enable,
		disable : disable,
		getIsScroll:getIsScroll,
		isActuallyScrolling:isActuallyScrolling
	};
	return pub;
})();

ZMO.Util.scrollHandler = (function($) {
	var actLoadingFlag = false;
	var pullDownEl = null;
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
	var createPullDownElement = function(){
		//init elements for pulldown refresh
		var pullDownEl = $("<div>").attr("id","pullDown");
		var pullDownIcon = $("<span>").addClass("pullDownIcon").appendTo(pullDownEl);
		var pullDownText = $("<span>").addClass("pullDownLabel").text("Pull down to refresh").appendTo(pullDownEl);
		return pullDownEl;
	}
	var reset = function(){
		if(pullDownEl){
			pullDownEl.remove();
			pullDownEl = null;
		}
		if (scroller)
			scroller.destroy();
		actLoadingFlag = false;
	}
	function initScrolling(config) {
		var defaultSettings = {
				element:null,
				loadMoreCallback:null,
				isMobile:false,
				pullDownCallback:null
		};
		$.extend(defaultSettings,config);
		var isPullDownMode = !!defaultSettings.pullDownCallback;
		if (defaultSettings.isMobile) {
			reset();
			var pullDownOffset;
			if(isPullDownMode){
				pullDownEl = createPullDownElement().prependTo(defaultSettings.element.children(":first"))
				pullDownOffset = pullDownEl.outerHeight();
			}
			
			
			defaultSettings.element.css("height", "100%");
			
			scroller = new iScroll(defaultSettings.element[0], {
				lockDirection:true,
			    hScroll:false,
			    vScrollbar:false,
			    hScrollbar:false,
			    checkDOMChanges: false,
			   //useTransition: true,
				topOffset: pullDownOffset,
				onRefresh: function () {
					if (isPullDownMode&&pullDownEl.hasClass('loading')) {
						pullDownEl.removeClass();
						pullDownEl.find(".pullDownLabel").text('Pull down to refresh...');
					} 
				}, 
			    onScrollMove: function () {
			        // CHECK if we've 350px gap before reaching end of the page
			        if ( (this.y < (this.maxScrollY + 350)) && (!actLoadingFlag) ){ 
			        	actLoadingFlag = true; 
			          // start loading next page content here
			        	if (defaultSettings.loadMoreCallback)
			        		defaultSettings.loadMoreCallback(loadingOk);
			          // update this flag inside load more and set to 0 when complete
			        	
			        }
			        
			        //pull down
			        if (isPullDownMode&& this.y > 5 && !pullDownEl.hasClass('flip')) {
						pullDownEl.removeClass().addClass('flip');
						pullDownEl.find('.pullDownLabel').text('Release to refresh...');
						this.minScrollY = 0;
					} else if (isPullDownMode&&this.y < 5 && pullDownEl.hasClass('flip')) {
						pullDownEl.removeClass();
						pullDownEl.find('.pullDownLabel').text('Pull down to refresh...');
						this.minScrollY = -pullDownOffset;
					}
			      },
			      onScrollEnd: function () {
			        // check if we went down, and then load content
			    	  if ( (this.y < (this.maxScrollY + 350)) && (!actLoadingFlag) ){ 
			    		  actLoadingFlag = true; 
			          // Load more content
			        	if (defaultSettings.loadMoreCallback)
			        		defaultSettings.loadMoreCallback(loadingOk);
			          // update this flag inside load more and set to 0 when complete
			        	
			        } else {
			          // DO NOTHING
			        }
			    	//Pull down 
					if (isPullDownMode&&pullDownEl.hasClass('flip')) {
						pullDownEl.removeClass().addClass('loading');
						pullDownEl.find('.pullDownLabel').text( 'Loading...');				
						//refreshMemberlist();	// Execute custom function (ajax call?)
						defaultSettings.pullDownCallback();
					}
			      }
			});
			// bindScrollHandler(scrollElement);
		} else {
			// native scroll handler
			bindScrollHandler(defaultSettings.loadMoreCallback);
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