ZMO.Util.Browser = ZMO.Util.Browser || {};
ZMO.Util.Browser.throbber = (function($){
	var c  =ZMO.UtilConstants;
	var isActive = false;
	var icon;
	var iconArr;
	var getIcon = function(imgUrl){
		var container =  $("<div>").addClass("throbber");
		var throbberContainer =$("<div>");
		var img = $("<img>").attr({
			src:imgUrl
		});
		throbberContainer.append(img);
		container.append(throbberContainer);
		
		return container;
	}
	var show =function(){
		if(!isActive){
			isActive = true;
			icon = getIcon(c.throbberUrl).appendTo("body");
		}
		return icon;
	}
	var hide = function(){
		isActive = false;
		if(icon)icon.fadeOut("slow",function(){
			$(this).remove();
		});

	}
	var get = function(){
		return getIcon(c.throbberUrl);
	}
	var pub = {
			show:show,
			hide:hide,
			get:get
	}
	return pub;
})(jQuery);