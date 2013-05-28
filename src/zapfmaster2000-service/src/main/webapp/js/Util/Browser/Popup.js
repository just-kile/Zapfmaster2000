ZMO.Util= ZMO.Util || {};

ZMO.Util.Popup = (function(hash){
	var container = null;

var open = function(content){
	if(!ZMO.scrolling.isActuallyScrolling()){
		var mC = ZMO.modules.Constants;
		ZMO.scrolling.disable();
		var wrapper = $("<div>").addClass("zmo-popup-wrapper").css({
			top:$(window).scrollTop()
		}).on(mC.clickEvent,close);
		var contentContainer = $("<div>").addClass("inner");
		
		var closeBtn = $("<div>").addClass("zmo-clickbutton closeBtn").on(mC.clickEvent,close).text("Close");
		contentContainer.append($("<div>").addClass("content").append(content)).append(closeBtn);
		wrapper.append(contentContainer).appendTo("body").hide().fadeIn("fast");
		container =wrapper;
	}
}
var close = function(){
	ZMO.scrolling.enable();
	if(container)container.fadeOut("fast",function(){
		container.remove();
		container = null;
	})
}
var pub = {
		open:open,
		close:close
}
return pub;
})();