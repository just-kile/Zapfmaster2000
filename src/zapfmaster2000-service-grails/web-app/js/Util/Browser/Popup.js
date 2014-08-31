ZMO.Util= ZMO.Util || {};

ZMO.Util.Popup = function(){
	var container = null;
var that = this;
this.open = function(content){
	if(!ZMO.scrolling.isActuallyScrolling()){
		var mC = ZMO.modules.Constants;
		ZMO.scrolling.disable();
		var wrapper = $("<div>").addClass("zmo-popup-wrapper").css({
			top:$(window).scrollTop()
		}).on(mC.clickEvent,that.close);
		var contentContainer = $("<div>").addClass("inner");
		
		var closeBtn = $("<div>").addClass("zmo-clickbutton closeBtn").on(mC.clickEvent,close).text("Close");
		contentContainer.append($("<div>").addClass("content").append(content)).append(closeBtn);
		wrapper.append(contentContainer).appendTo("body").hide().fadeIn("fast");
		container =wrapper;
	}
};
this.close = function(){
	ZMO.scrolling.enable();
	if(container)container.fadeOut("fast",function(){
		setTimeout(function(){
			container.remove();
			container = null;
		},50);
	});
}

return this;
};