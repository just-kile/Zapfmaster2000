/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.header = (function($,ajax){
	var mC = ZMO.modules.Constants;
	
	var container =null,
		naviBtn = null,
		usersBtn = null,
		notificationsBtn = null;
	var createIcon = function(url){
		return $("<img>").attr({
			src:url
		});
	};
	var onNaviBtnClick = function(e){
		//e.stopPropagation();
		e.preventDefault();
		ZMO.modules.sideNavigation.toggle();
	};
	var onUsersBtnClick = function(e){
		e.preventDefault();
		ZMO.modules.challengeUserList.toggle();
	};
	var onNotificationBtnClick = function(){
		//alert("Not");
	};
		
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(hashParams,moduleParams){
		//add icons
		naviBtn.append(createIcon("images/icons/88-beer-mug.png"));
		usersBtn.append(createIcon("images/icons/22-skull-n-bones.png"));
		notificationsBtn.append(createIcon("images/view/zapfmaster2000_klein.png"));
		
		//add Clickhandler
		//naviBtn.on("",onNaviBtnClick);
		new google.ui.FastButton(naviBtn[0],onNaviBtnClick);
		
		//usersBtn.on("mouseup",onUsersBtnClick);
		new google.ui.FastButton(usersBtn[0],onUsersBtnClick);
		notificationsBtn.on("mouseup",onNotificationBtnClick);
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
	var pub = {
			getInstance:getInstance,
			init:init
	};
	return pub;
}(jQuery,ZMO.ajax));
