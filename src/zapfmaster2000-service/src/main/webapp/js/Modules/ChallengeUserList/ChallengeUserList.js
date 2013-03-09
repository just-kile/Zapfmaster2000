/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.challengeUserList = (function($,ajax,view){
	
	var c = ZMO.modules.Constants;
	var container =null,mainContainer = null,wrapper,scrollContainer,pullDownEl,ul,entryListArr = [];
	var refreshMemberlist = function(){
		ajax.getDatas(c.urls.MEMBERS,function(resp){
			//remove old elements
			$.each(entryListArr,function(ind,el){
				el.remove();
			});
			entryListArr = [];
			var memberModelArr = [];
			//create models
			$.each(resp,function(ind,member){
				memberModelArr.push(new ZMO.MemberModel(member));
			});
			//fill view with model data
			$.each(memberModelArr,function(ind,val){
				var title = $("<div>").text(val.userName + ", "+val.totalAmount+"L").addClass("name");
				var image = $("<img>").attr("src",val.userImage).addClass("icon-big");
				var li = $("<li>").addClass("ZMO-sideNavigation-entry");//.data("link","#");
				
				li.append(image).append(title);
//				new google.ui.FastButton(li[0], function(e){
//					e.preventDefault();
//					hide();
//					ZMO.changePage("#");
//				
//				});
				li.on("mouseup",function(e){
//					e.preventDefault();
//					hide();
//					alert("User"+val.userName);
//					ZMO.changePage(val.link);
				});
				ul.append(li);
				entryListArr.push(li);
			});
//			ul.on("mouseup","li",function(){
//				hide();
//				ZMO.changePage($(this).data("link"));
//			});

			if(!scroller){
				addScrollHandler();
			}else{
				scroller.refresh();
			}
		});
	};
	var scroller = null;
	var addScrollHandler = function(){
		setTimeout(function(){
			pullDownOffset = pullDownEl.outerHeight();
			scroller= new iScroll('challengeUser-wrapper',{
				    lockDirection:true,
				    hScroll:false,
				    vScrollbar:false,
				    hScrollbar:false,
				    checkDOMChanges: false,
				    useTransition: true,
					topOffset: pullDownOffset,
					onRefresh: function () {
						if (pullDownEl.hasClass('loading')) {
							pullDownEl.removeClass();
							pullDownEl.find(".pullDownLabel").text('Pull down to refresh...');
						} 
					},
					onScrollMove: function () {
						if (this.y > 5 && !pullDownEl.hasClass('flip')) {
							pullDownEl.removeClass().addClass('flip');
							pullDownEl.find('.pullDownLabel').text('Release to refresh...');
							this.minScrollY = 0;
						} else if (this.y < 5 && pullDownEl.hasClass('flip')) {
							pullDownEl.removeClass();
							pullDownEl.find('.pullDownLabel').text('Pull down to refresh...');
							this.minScrollY = -pullDownOffset;
						}
					},
					onScrollEnd: function () {
						if (pullDownEl.hasClass('flip')) {
							pullDownEl.removeClass().addClass('loading');
							pullDownEl.find('.pullDownLabel').text( 'Loading...');				
							refreshMemberlist();	// Execute custom function (ajax call?)
						}
					}
				   //  momentum:false
				});
		},0);
//		setTimeout(function () { scroller.refresh(); }, 1000);
		
	};

	var show =function(){
		ZMO.scrolling.disable();
		ZMO.Utils.animator.animateRight(mainContainer,container,function(){
			visible =true;
		});

	};
	var hide = function(){
		ZMO.scrolling.enable();
		ZMO.Utils.animator.animateRightRev(mainContainer,container,function(){
			visible =false;
		});

	};
	var visible =false;
	var toggle = function(){
		visible?hide():show();
	};
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(hashParams,moduleParams){
		ZMO.Utils.animator.init(container);
		//init elements for iscroll
		wrapper =$("<div>").attr("id","challengeUser-wrapper");
		scrollContainer = $("<div>").addClass("scroller");
		ul = $("<ul>").appendTo(scrollContainer);
		$("<li>").addClass("ZMO-sideNavigation-header").text("Challengees").appendTo(ul);
		
		
		//init elements for pulldown refresh
		pullDownEl = $("<div>").attr("id","pullDown");
		var pullDownIcon = $("<span>").addClass("pullDownIcon").appendTo(pullDownEl);
		var pullDownText = $("<span>").addClass("pullDownLabel").text("Pull down to refresh").appendTo(pullDownEl);
		
		//Appendix
		pullDownEl.appendTo(scrollContainer);
		ul.appendTo(scrollContainer);
		wrapper.append(scrollContainer).appendTo(container);
		refreshMemberlist();
		


	};
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		mainContainer = ZMO.view.getMainContainer();
		return (container = $("<div class='ZMO-sideNavigation'>").addClass("right"));
	};
	var isVisible = function(){
		return visible;
	}
	var pub = {
			show:show,
			hide:hide,
			toggle:toggle,
			getInstance:getInstance,
			init:init,
			isVisible:isVisible
	};
	return pub;
}(jQuery,ZMO.ajax,ZMO.modules.createChallengeView));
