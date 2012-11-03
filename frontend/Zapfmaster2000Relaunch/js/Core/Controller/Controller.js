ZMO.controller = (function($,document,view,ajax){
	var chat,
	news,
	getDatas,
	c = ZMO.Constants,
	mP =ZMO.modules_properties;
	var onDraw = function(data){
		//jQuery("#test").text(data) 
	}
	
	var onNews = function () {
	    //news.emit('woot');
	  }
	var connect = function(){
//		 chat = io.connect(c.node.drawUrl)
//		    , news = io.connect(c.node.newsUrl);
//		 getdatas = io.connect(c.node.datasUrl);
//		  chat.on('connect', function () {
//		    chat.emit('hi!');
//		  });
//		  chat.on("draw",onDraw)
//		  news.on('news',onNews) ;
//
//		  getdatas.on('connect', function () {
//			  getdatas.emit('getdatas', function (data) {
//			      //  alert(data); // data will be 'woot'
//			      });
//			  });

	}
/**
 * Gets called when a page changes
 */
	var onPageChange =function(event,datas){
		var url = $.bbq.getState();
		ajax.resetQueue();
		ajax.stopPull();
		var pageLoaded = false;
		$.each(mP,function(pageId,val){
			if(ZMO.exists(url[pageId])&&!pageLoaded){
				var datas =ZMO.hashHandler.queryToObj( url[pageId]);
				view.createPage(pageId,val,datas);
				pageLoaded = true;
			}
		});
		if(!pageLoaded)view.createPage("front",mP.front);
		
	}
	var init = function(){
		//Getting Templates
		ZMO.getTemplates(function(templates){
			//Add Templates to ICanHaz - Engine
			$.each(templates, function (template) {
		        ich.addTemplate(template.name, template.template);
		    });
			
			view.init();
			$(window).bind("hashchange",onPageChange);
			$(window).trigger( "hashchange" );
		})
		
	}
	var pub = {
			init:init
	}
	return pub;
}(jQuery,document,ZMO.view,ZMO.ajax));


jQuery(document).ready(function(){
	ZMO.controller.init();
});
