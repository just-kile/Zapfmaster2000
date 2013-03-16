ZMO.modules = ZMO.modules || {};

ZMO.modules.navigation = (function($,ajax){
	var c = ZMO.modules.Constants;
	
	var createNavigation = function(){
		var links = c.navbar;
		var ul =jQuery(document.createElement("ul")).attr("id","topnav");
		jQuery(links).each(function(index,val){
			var li = jQuery(document.createElement("li")).attr({
				"id":val.id
			});
			jQuery(document.createElement("a")).attr({
				href:val.link,
				title:val.title
			}).text(val.text).appendTo(li);
			li.appendTo(ul)
		});
		
		return ul;
	}
	var init = function(){
		
	}
	var getInstance = function(){
		return createNavigation();
	}
	var pub = {
			init:init,
			getInstance:getInstance
	}
	return pub
}(jQuery,ZMO.ajax))
