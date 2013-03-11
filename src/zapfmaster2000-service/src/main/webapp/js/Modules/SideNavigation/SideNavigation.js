/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.sideNavigation = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var container =null,mainContainer = null,ul = null,userInfo = null;
	var visible =false;
	
	var show =function(){
		ZMO.scrolling.disable();
		ZMO.Utils.animator.animateLeft(mainContainer,container,function(){
			visible=true;
		});
		createUserInformation();
		
		
	};
	var hide = function(){
		ZMO.scrolling.enable();
		ZMO.Utils.animator.animateLeftRev(mainContainer,container,function(){
			//"container" will be hided at webkiTransitionEnd event
			visible = false;
		});

	};
	var isVisible = function(){
		return visible;
	}
	var toggle = function(){
		visible?hide():show();
		if(console)console.log(visible?"hide":"show");
	};
	var createHeader = function(text){
		return $("<li>").addClass("ZMO-sideNavigation-header").text(text);
	}
	var createUserInformation = function(){
		ajax.getDatas(mC.urls.USERSTATS,function(userStats){
			var user = userStats.user;
			if(userInfo){
				userInfo.remove();
			}
			userInfo = $("<li>").addClass("ZMO-sideNavigation-entry");
			var img = $("<img>").attr("src",baseUrl+user.userImage).addClass("big");
			var span = $("<div>").css("margin-left","4em").text(user.userName+" ("+userStats.amount.amountTotal.toFixed(2)+"l; No.: "+userStats.rank.amount+".)");
			
			userInfo.append(img).append(span);
			ul.prepend(userInfo)
		});
	}
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(hashParams,moduleParams){
		ZMO.Utils.animator.init(container);
		
		createUserInformation();
		
		//Header
		createHeader("Navigation").appendTo(ul);
		//entry creation
		$.each(mC.navbarMobile,function(ind,val){
			var title = $("<div>").addClass("name-navigation").text(val.title);
			var image = $("<img>").attr("src",val.image).addClass("small");
			var li = $("<li>").addClass("ZMO-sideNavigation-entry");//.data("link",val.link);
			li.append(image).append(title);
			new google.ui.FastButton(li[0], function(e){
				e.preventDefault();
				hide();
				ZMO.changePage(val.link);
			
			});
			li.on("mousedown",function(){
				//$(this).css("background","grey");
			});
			ul.append(li);
		});



	};
/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		mainContainer = ZMO.view.getMainContainer();
		container = $("<div class='ZMO-sideNavigation'>").addClass("left")
		ul = $("<ul>").appendTo(container);
		return container;
	};
	var pub = {
			show:show,
			hide:hide,
			toggle:toggle,
			getInstance:getInstance,
			init:init,
			isVisible:isVisible
	};
	return pub;
}(jQuery,ZMO.ajax));
