ZMO.modules = ZMO.modules || {};

ZMO.modules.navigationMobile = (function($,ajax){
	var c = ZMO.modules.Constants;
	var container = null;
	var createNavigation = function(){
		var links = c.navbarMobile;
		var ul =$("<ul>").attr("id","topnav");
		jQuery(links).each(function(index,val){
			var li = jQuery(document.createElement("li")).attr({
				"id":val.id
			});
			jQuery(document.createElement("a")).attr({
				href:val.link,
				title:val.title
			}).text(val.text).appendTo(li);
			li.appendTo(ul);
		});
		
		return ul;
	};
	var init = function(){
		createNavigation();
	};
	var getInstance = function(){
		return container = $("<div>");
	};
	var pub = {
			init:init,
			getInstance:getInstance
	};
	return pub;
}(jQuery,ZMO.ajax));
