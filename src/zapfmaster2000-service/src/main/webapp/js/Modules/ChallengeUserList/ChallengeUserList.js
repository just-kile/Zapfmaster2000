/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.challengeUserList = (function($,ajax,view){
	var c = ZMO.modules.Constants;
	var container =null,mainContainer = null;
	var showMemberlist = function(){
		ajax.getDatas(c.urls.MEMBERS,function(resp){
			var memberModelArr = [];
			$.each(resp,function(ind,member){
				memberModelArr.push(new ZMO.MemberModel(member));
			});
			var ul = $("<ul>").appendTo(container);
			$.each(memberModelArr,function(ind,val){
				var title = $("<div>").text(val.userName + ", "+val.totalAmount+"L");
				var image = $("<img>").attr("src",val.userImage);
				var li = $("<li>");//.data("link","#");
				
				li.append(image).append(title);
				new google.ui.FastButton(li[0], function(e){
					e.preventDefault();
					hide();
					ZMO.changePage("#");
				
				});
				ul.append(li);
			});
//			ul.on("mouseup","li",function(){
//				hide();
//				ZMO.changePage($(this).data("link"));
//			});
		});
	};

	var show =function(){
		ZMO.Utils.animator.animateRight(mainContainer,container,function(){
			visible =true;
		});

	};
	var hide = function(){
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
		showMemberlist();
		
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
