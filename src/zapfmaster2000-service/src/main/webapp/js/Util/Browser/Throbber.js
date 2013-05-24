ZMO.Util.Browser = ZMO.Util.Browser || {};
ZMO.Util.Browser.throbber = (function($){
	var c  =ZMO.UtilConstants;
	var isActive = false;
	var icon;
	var iconArr;
	var textDiv = null;
	var getIcon = function(imgUrl,text){
		var container =  $("<div>").addClass("throbber");
		var throbberContainer =$("<div>");
		var img = $("<img>").attr({
			src:imgUrl
		});
		if(text){
			throbberContainer.append(img).append(textDiv = $("<div>").text(text).css("color","white"));
		}else{
			throbberContainer.append(img);
		}
		
		container.append(throbberContainer);
		
		return container;
	}
	var updateText = function(text){
		if(textDiv)textDiv.text(text);
	}
	var show =function(text){
		if(!isActive){
			isActive = true;
			icon = getIcon(c.throbberUrl,text).appendTo("body");
		}
		return icon;
	}
	
	var hide = function(){
		isActive = false;
		if(icon)icon.fadeOut("slow",function(){
			$(this).remove();
		});
		if(textDiv)textDiv = null;

	}
	var get = function(){
		return getIcon(c.throbberUrl);
	}
	var pub = {
			show:show,
			hide:hide,
			get:get,
			updateText:updateText
	}
	return pub;
})(jQuery);