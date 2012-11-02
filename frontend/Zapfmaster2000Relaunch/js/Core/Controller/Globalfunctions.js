var ZMO = ZMO || {};
ZMO.exists = ZMO.Util.Object.exists;
ZMO.ajax = ZMO.Util.Net.Ajax;
ZMO.throbber =ZMO.Util.Browser.throbber;

ZMO.parseHash = function(str){
	return str.substr(1);
}
ZMO.getTemplates = function(callback){
	var url = ZMO.Constants.templateUrl;
	$.ajax({
		"url":url,
		type:"GET",
		complete:function(resp){
			var datas = jQuery.parseJSON(resp.responseText);
			callback(datas.templates);
		}
	});
};


ZMO.log = function(str){
	if(console && ZMO.Constants.debugMode){
		console.log(str);
	}
}

ZMO.changePage = function(pageId,datas){
	var hash= hashHandler.objToQuery(pageId,datas);
	window.location.replace(hash);
}
ZMO.hashHandler = (function($){
	var objToQuery  = function(id,datas){
		var dataArr = [];
		$.each(datas,function(ind,val){
			dataArr.push(ind+":"+val);
		});
		var dataQuery = dataArr.join(";");
		return id +"="+ dataQuery;
	}
	var queryToObj = function(query){
		var tmp  = query.split("=");
		query = tmp[tmp.length-1];
		var arr = query.split(";");
		var respObj = {};
		$.each(arr,function(ind,val){
			var tmpArr = val.split(":");
			respObj[tmpArr[0]] = tmpArr[1];
		})
		return respObj;
	}
	var pub = {
		objToQuery:objToQuery,
		queryToObj:queryToObj
	}
	return pub;
})(jQuery)
