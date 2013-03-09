/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.sideNavigation = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var container =null,mainContainer = null;
	var visible =false;
	
	var show =function(){
		ZMO.scrolling.disable();
		ZMO.Utils.animator.animateLeft(mainContainer,container,function(){
			visible=true;
		});
		
		
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
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(hashParams,moduleParams){
		ZMO.Utils.animator.init(container);
		var ul = $("<ul>").appendTo(container);
		//Header
		$("<li>").addClass("ZMO-sideNavigation-header").text("Navigation").appendTo(ul);
		//entry creation
		$.each(mC.navbarMobile,function(ind,val){
			var title = $("<div>").addClass("name-navigation").text(val.title);
			var image = $("<img>").attr("src",val.image);
			var li = $("<li>").addClass("ZMO-sideNavigation-entry");//.data("link",val.link);
			li.append(image).append(title);
			new google.ui.FastButton(li[0], function(e){
				e.preventDefault();
				hide();
				ZMO.changePage(val.link);
			
			});
//			li.on("mouseup",function(){
//				hide();
//				ZMO.changePage($(this).data("link"));
//				$(this).css("background","");
//			});
			li.on("mousedown",function(){
				//$(this).css("background","grey");
			});
			ul.append(li);
		});
//		ul.on("mouseup","li",function(){
//			hide();
//			ZMO.changePage($(this).data("link"));
//		});



	};
/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		mainContainer = ZMO.view.getMainContainer();
		return (container = $("<div class='ZMO-sideNavigation'>").addClass("left"));
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
