ZMO.controller = (function($,document,view,ajax){
	var mP =ZMO.modules_properties;

/**
 * Gets called when a page changes
 */
	var onPageChange =function(event,datas){
//		var url = $.bbq.getState();
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
		var token = localStorage.getItem(ZMO.Constants.tokenName);
		if(ZMO.exists(token)){
			if(successCallb)successCallb();
		}else{
			if(errorCallb)errorCallb();
		}
	};
	var init = function(){
		//Getting Templates
		checkLoginStatus(function(){
			ZMO.getTemplates(function(templates){
				//Add Templates to ICanHaz - Engine
				$.each(templates, function (template) {
			        ich.addTemplate(template.name, template.template);
			    });
				view.init();
				$(window).bind("hashchange",onPageChange);
				$(window).trigger( "hashchange" );
			});
		},function(){
			alert("not logged in");
			window.location.replace("/");
		});
		
	};
	var pub = {
			init:init
	};
	return pub;
}(jQuery,document,ZMO.view,ZMO.ajax));


jQuery(document).ready(function(){
	ZMO.controller.init();
});
