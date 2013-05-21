/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.header = (function($,ajax){
	var mC = ZMO.modules.Constants,notificationWindow = null,ul=null,badge = null;
	
	var container =null,
		naviBtn = null,
		usersBtn = null,
		notificationsBtn = null;
	var createIcon = function(url){
		return $("<img>").attr({
			src:url
		});
	};
	var badgeCounter = 0;
	var onNaviBtnClick = function(e){
		//e.stopPropagation();
		e.preventDefault();
		if(ZMO.modules.challengeUserList.isVisible()){
			ZMO.modules.challengeUserList.toggle();
		}else{
			ZMO.modules.sideNavigation.toggle();
		}
		
	};
	var onUsersBtnClick = function(e){
		e.preventDefault();
		e.stopPropagation();
		if(ZMO.modules.sideNavigation.isVisible()){
			ZMO.modules.sideNavigation.toggle();
		}else{
			ZMO.modules.challengeUserList.toggle();
		}
	};
	/**
	 * Notifications
	 */
	var notificationVisible = true,scroller = null,isScroll =false;
	var getIsScroll = function(){
		return isScroll;
	}
	var initNotificationWindow = function(){
		notificationWindow = $("<div>").addClass("zmo-popupwindow").appendTo(container);
		notificationWindowInner =$("<div>").appendTo(notificationWindow);
		var scrollerCon = $("<div>").appendTo(notificationWindowInner);
		ul = $("<ul>").addClass("zmo-notification-list").appendTo(scrollerCon);
		toggleNotificationBtnClick();

	}
	var showNotificationWindow = function(){
		notificationVisible = true;
		notificationWindow.css("top","").show();
		if(scroller&&scroller.refresh)scroller.refresh()
	}
	var hideNotificationWindow = function(){
		notificationVisible = false;
		notificationWindow.css("top","-9999px").hide();
	}
	var isNotificationWindowVisible = function(){
		return notificationVisible;
	}
	var toggleNotificationBtnClick = function(e){
		//alert("Not");hide
		if(notificationVisible){
			hideNotificationWindow();
		}else{
			showNotificationWindow();
		}
		
	};
	var initScroller = function(){
//			scroller = new iScroll(ul[0],{
		scroller = new iScroll(notificationWindowInner[0],{
				onScrollMove: function () {
					if(Math.abs(this.distY+this.distX)>10){
						isScroll = true;
					}
				},
				onScrollEnd: function () {
					setTimeout(function(){
						isScroll = false;
					},0);
				}
			});
	}
	var notificationUUID = 0;
	var pushNotification = function(challengeRequest,callb,noStore){
		
		var acceptBtn = $("<div>").addClass("zmo-button accept").text("Accept");
		var declineBtn = $("<div>").addClass("zmo-button decline").text("Decline");
		var div =$("<div>");
		var span = $("<span>").text(challengeRequest.challengerUserName).addClass("bold");
		var spanLabel =$("<div>").addClass("zmo-notification-label").append(span).append($("<br/><span>challenged u!</span>"));
		var img =$("<img>").attr("src",baseUrl+challengeRequest.challengerImagePath);
		var buttonContainer = $("<div>").addClass("zmo-button-container")
			.append(img)
			.append(spanLabel)
			.append(acceptBtn)
			.append(declineBtn);
		var callback = callb;
		
		
		div.append(buttonContainer);
		
		var li = $("<li>").attr("data-notificationid",challengeRequest.challengeId).append(div);
		acceptBtn.on(mC.clickEvent,function(){
			if(!getIsScroll()){
				if(callback)callb(true,li.attr("data-notificationid"),challengeRequest);
			}
			
		});
		declineBtn.on(mC.clickEvent,function(){
			if(!getIsScroll()){
				if(callback)callb(false,li.attr("data-notificationid"),challengeRequest);
			}
		});
		updateBadge(++badgeCounter);
		ul.prepend(li);
		if(!scroller){
			scroller = {};
			setTimeout(function(){
				initScroller()
			},0);
		}else{
			setTimeout(function(){	scroller.refresh();},300);
		}
		if(!noStore){
			ZMO.store.addChallenge(challengeRequest);
		}
		
	};
	var clearNotification = function(uuid){
		ZMO.store.removeChallenge(uuid);
		ul.find("[data-notificationid='"+uuid+"']").fadeOut("fast",function(){
			$(this).remove();
			var leng = ul.children().length;
			badgeCounter = leng;
			updateBadge(leng);
			if(leng<1){
				scroller.destroy();
				scroller = null;
			}else{
				scroller.refresh();
			}
			
		});
	};
	var clearAll = function(){
		ul.empty();
	};
	var updateBadge = function(number){
		if(number&&number>0){
			badge.text(number).show();
		}else{
			badge.text("").hide();
		}
		
	};
	//TODO
	var fillRecentNotifications = function(){
		var ch = ZMO.store.getChallenges()||[];
		$.each(ch,function(ind,val){
			pushNotification(val,ZMO.modules.receiveChallenge.sendChallengeResponse,true)
		});
	};
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(hashParams,moduleParams){
		//add icons
		naviBtn.append(createIcon("images/icons/88-beer-mug.png"));
		usersBtn.append(createIcon("images/icons/22-skull-n-bones.png"));
		notificationsBtn.append(createIcon("images/view/zapfmaster2000_klein.png"))	
						.append(badge = $("<div>").addClass("badge").hide());
		
		//add Clickhandler
		//naviBtn.on("",onNaviBtnClick);
		new google.ui.FastButton(naviBtn[0],onNaviBtnClick);
//		naviBtn.on(mC.clickEvent,onNaviBtnClick);
		//usersBtn.on("mouseup",onUsersBtnClick);
		new google.ui.FastButton(usersBtn[0],onUsersBtnClick);
		notificationsBtn.on(mC.clickEvent,toggleNotificationBtnClick);
		initNotificationWindow();
		
		fillRecentNotifications();
		//DEBUG
//		pushNotification({
//			challengerUserName:"Pete",
//			challengerUserId:1,
//			challengerUserImage:"rest/image/user/1"
//		});
	};
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		container = $("<div>").addClass("ZMO-header");
		naviBtn = $("<div>").addClass("naviBtn button").appendTo(container);
		usersBtn = $("<div>").addClass("usersBtn button").appendTo(container);
		notificationsBtn = $("<div>").addClass("notificationsBtn logo").appendTo(container);
		return container;
	};
	
	var getScroller = function(){
		return scroller;
	}

	var pub = {
			getInstance:getInstance,
			init:init,
			pushNotification:pushNotification,
			clearNotification:clearNotification,
			clearAll:clearAll,
			updateBadge:updateBadge,
			getScroller:getScroller,
			hideNotificationWindow:hideNotificationWindow,
			showNotificationWindow:showNotificationWindow,
			isNotificationWindowVisible:isNotificationWindowVisible
			
	};
	return pub;
}(jQuery,ZMO.ajax));
