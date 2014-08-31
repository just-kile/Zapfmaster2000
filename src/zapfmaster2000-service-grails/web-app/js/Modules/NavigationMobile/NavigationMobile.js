ZMO.modules = ZMO.modules || {};

ZMO.modules.navigationMobile = (function($,ajax){
	
	var c = ZMO.modules.Constants;
	var container = null;
	var createNavigation = function(){
		var links = c.navbarStats;
		var ul = $("<ul>").addClass("navBtn");
		container.append(ul);
		$.each(links,function(ind,link){
			var li = $("<li>");
			var containerDiv = $("<div>").addClass("listContainer");
			var textSpan = $("<span>").text(link.text);
			var icon = $("<img>").attr("src",link.image);
			var iconCont = $("<div>").addClass("iconContainer").append(icon);
			containerDiv.append(iconCont).append(textSpan);
			li.append(containerDiv);
			ul.append(li);
			
			li.on(c.clickEvent,function(){
				//alert(link.text);
				ZMO.changePage(link.link);
			})
		});
		
	};
	var init = function(){
		createNavigation();
	};
	var getInstance = function(){
		return container = $("<div>").addClass("navMobile");
	};
	var pub = {
			init:init,
			getInstance:getInstance
	};
	return pub;
}(jQuery,ZMO.ajax));
