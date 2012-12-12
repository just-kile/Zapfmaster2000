ZMO.controller = (function($,document,view,ajax){
	var mP =ZMO.modules_properties;
	//prevents a bug, that onPageChange will be called up to 3 times
	//TODO: find out why
/**
 * Gets called when a page changes
 */
	var onPageChange =function(event,datas){
		
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
				$.each(templates, function (ind,template) {
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


