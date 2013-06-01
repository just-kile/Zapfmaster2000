/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.challengeUserList = (function($,ajax,view){
	var wording = {
			challengeRequest:"challengeRequest"
	}
	var c = ZMO.modules.Constants;
	var container =null,mainContainer = null,wrapper,scrollContainer,pullDownEl,ul,entryListArr = [],oldElement = null;
	var createTimeChooserContainer = function(memberModel){
		timeArr = c.challenges.modes.params;
		var cont = $("<div>").addClass("zmo-challenges-timeChooser").hide();
		
		$.each(timeArr,function(ind,obj){
			var textContainer = $("<div>").text(obj.name);
			textContainer.on(c.clickEvent,function(e){
				if(!getIsScroll()){
					var duration = obj.duration;
					var type = obj.type;//challengeDatas.type.id;
					var challengeeId = memberModel.userId;
					ajax.sendChallengeRequest(type,challengeeId,duration,function(){
						 ZMO.Util.Popup.open(ZMO.Util.localization.translateString(wording.challengeRequest));
						//alert(ZMO.Util.localization.translateString(wording.challengeRequest));
					});
				};
					
			});
			
			$("<div>").addClass("zmo-challenges-timeChooser-button").append(textContainer).appendTo(cont);
			
		});
		return cont;
	};
	var hideTime = function(li,timeChoser){
		if(!timeChoser)timeChoser = li.find(".zmo-challenges-timeChooser");
		var name = li.find(".name");
		timeChoser.fadeOut(100,function(){
			name.fadeIn();
		});
	};
	var showTime = function(li,timeChoser){
		if(!timeChoser)timeChoser = li.find(".zmo-challenges-timeChooser");
		var name = li.find(".name");
		name.fadeOut(100,function(){
			timeChoser.fadeIn();
		});
	};
	var toggleTime = function(li){
		var timeChoser = li.find(".zmo-challenges-timeChooser");
		
		if(timeChoser.is(":visible")){
			hideTime(li,timeChoser);
		}else{
			showTime(li,timeChoser);
		}
	}
	var refreshMemberlist = function(){
		//ajax.getDatas(c.urls.MEMBERS,function(resp){
		ajax.getDatas(c.urls.CHALLENGEEMEMBERS,function(resp){
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
				li.append(image).append(title).append(createTimeChooserContainer(val));
//				new google.ui.FastButton(li[0], function(e){
//					e.preventDefault();
//					hide();
//					ZMO.changePage("#");
//				
//				});
				li.on(c.clickEvent,function(e){
					if(!getIsScroll()){
						//alert("bliub")
						elementClickHandler(e);
					}
				});
				ul.append(li);
				entryListArr.push(li);
			});

			if(!scroller){
				addScrollHandler();
			}else{
				setTimeout(function(){
					scroller.refresh();
				},0);
			}
		});
	};
	var scroller = null;
	var isScroll = false;
	var getIsScroll = function(){
		return isScroll;
	}
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
						if(Math.abs(this.distY+this.distX)>10){
							isScroll = true;
						}
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
						setTimeout(function(){
							isScroll = false;
						},0);
					}
				   //  momentum:false
				});
		},0);
//		setTimeout(function () { scroller.refresh(); }, 1000);
		
	};

	var show =function(){
		ZMO.scrolling.disable();
		refreshMemberlist();
		ZMO.Utils.animator.animateRight(mainContainer,container,function(){
			visible =true;
			
		});
		//if challenge push receives error / abort reconnect
		if(ajax.isRequestPaused(ZMO.modules.Constants.push.CHALLENGE)){
			ajax.resumePushRequests(ZMO.modules.Constants.push.CHALLENGE);
		};

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
	 * Send Challenge Handles
	 */
	var elementClickHandler = function(e){
		var li = $(e.currentTarget);
		if(oldElement)hideTime(oldElement);
		toggleTime(li);
		oldElement =li;
	}
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(hashParams,moduleParams){
		ZMO.Utils.animator.init(container,true);
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
