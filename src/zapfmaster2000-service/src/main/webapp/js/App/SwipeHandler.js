/**
 * This class handles the swipe event in the app
 */

ZMO.swipeHandler = (function(){
	var initSwipe = function(){
		var threshold = 30,noSwipeToken=false;
		$("body").swipe("destroy").swipe({
			 swipeStatus:function(event, phase, direction, distance, duration, fingers){
				 if(phase=="move" && (direction =="up"||direction=="down")&&distance>threshold){
					 noSwipeToken= true;
				 }else
				 if(phase=="end"){
					if(direction=="left" && !noSwipeToken){
						 if(!ZMO.modules.sideNavigation.isVisible()){
							 ZMO.modules.challengeUserList.show();
						 }else{
							 ZMO.modules.sideNavigation.hide();
						 }
					}else if(direction=="right" && !noSwipeToken) {
					
						 if(!ZMO.modules.challengeUserList.isVisible()){
							 ZMO.modules.sideNavigation.show();
						 }else{
							 ZMO.modules.challengeUserList.hide();
						 }
					}
					noSwipeToken = false;
				}
		         
		        },
			swipeRight:function(event, direction, distance, duration, fingerCount) {

				
			  },
			swipeLeft:function(event, direction, distance, duration, fingerCount) {

				
			},allowPageScroll:"auto",
			 threshold:threshold
		});
	};
	var initClickEvents = function(){
		$(".main").on("mouseup",function(){
			 if(ZMO.modules.sideNavigation.isVisible()){
				 ZMO.modules.sideNavigation.hide();
			 }else if(ZMO.modules.challengeUserList.isVisible()){
				 ZMO.modules.challengeUserList.hide();
			 }
		});
	};
	var myscroll = null;
	var initScroller = function(){
//		setTimeout(function(){
//			myScroll = new iScroll('wrapper',{
//				momentum:true,
//				hScroll:false,
//				lockDirection:true
//			});
//		},500)
	}
	var refreshScroller = function(){
		if(myscroll)myscroll.refresh();
	}
	var init = function(){
		initSwipe();
		initClickEvents();
		//initScroller();
	};
	var pub = {
			init:init,
			refreshScroller:refreshScroller
	};
	return pub;
})();