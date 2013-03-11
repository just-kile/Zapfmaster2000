ZMO.controller = (function($,document,view,ajax,localization){
	var mP =ZMO.modules_properties;
/**
 * Gets called when a page changes
 */
	var onPageChange =function(event,datas){
				if(ZMO.throbber)ZMO.throbber.show();
				var hash = window.location.hash;
				ajax.resetQueue();
				ajax.stopPull();
				ajax.abortPushRequests();
				$(window).unbind("scroll");
				//var hash = Object.keys(url)[0];
				var arr=[],datas={};
				if(hash){
					arr = ZMO.hashHandler.queryToObj(hash);
					url = arr[0];
					datas = arr[1];
				}
		
				if(typeof hash=="undefined" || hash=="" || typeof mP[url] =="undefined"){
					view.createPage("front",mP.front);
				}else{
					view.createPage(url,mP[url],datas);
				}
	};
	var checkLoginStatus =function(successCallb,errorCallb){
		var token = localStorage.getItem(ZMO.UtilConstants.tokenName);
		if(ZMO.exists(token)){
			if(successCallb)successCallb();
		}
		else{
			if(errorCallb)errorCallb();
		}
	};
	var init = function(){
		//Getting Templates
		checkLoginStatus(function(){
			localization.init(function(){
				ZMO.getTemplates(function(templates){
					//Add Templates to ICanHaz - Engine
					$.each(templates, function (ind,template) {
						ich.addTemplate(template.name, localization.translateTemplate(template.template));
				    });
					view.init();
					$(window).bind("hashchange",onPageChange);
					$(window).trigger( "hashchange" );
				});
			});
			
		},function(){
			//alert("not logged in");
			//var baseUrl = window.location.href.replace(new RegExp("(/[a-zA-Z]*.html)"),"");
			window.location.href="index.html";
		});
		
	};
	var pub = {
			init:init
	};
	return pub;
}(jQuery,document,ZMO.view,ZMO.ajax,ZMO.Util.localization));

