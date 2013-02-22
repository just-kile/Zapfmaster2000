var ZMO = ZMO || {};
ZMO.exists = ZMO.Util.Object.exists;
ZMO.ajax = ZMO.Util.Net.Ajax;
ZMO.throbber =ZMO.Util.Browser.throbber;
ZMO.changePage = ZMO.Util.Browser.changePage;

ZMO.parseHash = function(str){
	return str.substr(1);
}
ZMO.getTemplates = function(callback){
	var url = ZMO.UtilConstants.templateUrl;
	$.ajax({
		"url":url,
		type:"GET",
		complete:function(resp){
			var datas = [];
			try{
				//datas = jQuery.parseJSON(resp.responseText);
//				datas = eval("("+resp.responseText+")");
				var text =  resp.responseText;
				var templates = $(resp.responseText);
			}catch(e){
				ZMO.logger.warning("Parsing error! No ICH Templates loaded: "+e.toLocaleString());
			}
			$.each(templates,function(ind,template){
				if(template.nodeName.toLowerCase()=="template"){
					template = $(template);
					var name = template.attr("id");
					var templateText = new RegExp('<template id=\"'+name+'\"[^>]*>((.|[\r\n])*?)<\/template>').exec(text);
					datas.push({
						name:name,
						template:templateText?templateText[1]:""
					});
				}
			});
//			callback(datas.templates);
			callback(datas);
		}
	});
};


ZMO.logger = (function(){
	var log = function(str){
		if(console && ZMO.Constants.debugMode){
	
		console.log(str);
		}
	};
	var warning = function(str){
		if(console && ZMO.Constants.debugMode){
	
		console.log(str);
		}
	};
	var error = function(str){
		if(console && ZMO.Constants.debugMode){
			
			console.log(str);
			}
	}
	var pub = {
			log:log,
			warning:warning,
			error:error
	};
	return pub;
})();

//ZMO.changePage = function(pageId,datas){
//	var hash= hashHandler.objToQuery(pageId,datas);
//	window.location.replace(hash);
//};
ZMO.hashHandler = (function($){
	var objToQuery  = function(id,datas){
		var dataArr = [];
		$.each(datas,function(ind,val){
			dataArr.push(ind+":"+val);
		});
		var dataQuery = dataArr.join(";");
		return id +"="+ dataQuery;
	};
	var queryToObj = function(query){
		var tmp  = query.split("?");
		var respObj = {};
		if(tmp.length>1){
			query = tmp.length>1?tmp[tmp.length-1]:"";
			var arr = query.split("&");
			$.each(arr,function(ind,val){
				var tmpArr = val.split("=");
				respObj[tmpArr[0]] = tmpArr[1];
			});
		}
		var tmp1 = tmp[0].split("#");
		var url =tmp1[tmp1.length-1];
		return [url,respObj];
	};
	var pub = {
		objToQuery:objToQuery,
		queryToObj:queryToObj
	};
	return pub;
})(jQuery);












