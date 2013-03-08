/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.challengeUserList = (function($,ajax,view){
	
	var c = ZMO.modules.Constants;
	var container =null,mainContainer = null;
	var showMemberlist = function(){
		//document.addEventListener('touchmove', preventScrolling, false);
		ajax.getDatas(c.urls.MEMBERS,function(resp){
			var wrapper =$("<div>").attr("id","challengeUser-wrapper");
			var scrollContainer = $("<div>").addClass("scroller");
			var memberModelArr = [];
			$.each(resp,function(ind,member){
				memberModelArr.push(new ZMO.MemberModel(member));
			});
			
			var ul = $("<ul>").appendTo(scrollContainer);
			$("<li>").addClass("ZMO-sideNavigation-header").text("Challengees").appendTo(ul);
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
				ul.append(li);
			});
//			ul.on("mouseup","li",function(){
//				hide();
//				ZMO.changePage($(this).data("link"));
//			});
			scrollContainer.appendTo(wrapper);
			wrapper.appendTo(container);
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
			scroller= new iScroll('challengeUser-wrapper',{
				    lockDirection:true,
				    hScroll:false,
				    checkDOMChanges: false,
				    onBeforeScrollMove: function ( e ) {
				    		//alert("tes")
				    },
				   //  momentum:false
				});
		},1000);
//		setTimeout(function () { scroller.refresh(); }, 1000);
		
	}

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
