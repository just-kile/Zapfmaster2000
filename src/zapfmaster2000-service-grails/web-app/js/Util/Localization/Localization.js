
ZMO.loadTemplate = function(name,val,callb){
	var ajax = ZMO.ajax;
	if(ich && ich[name]){
		callback(ich[name](val));
	}else{
		var url = "js/Templates/"+name+".html";
		$.ajax({
			type:"GET",
			url:url,
			success:function(template){
				ich.addTemplate(name, template);
				callback(ich[name](val));
			}
			
		});
	}
};

ZMO.Util.localization = (function($){
	var c = ZMO.UtilConstants;
	var languagePack = null;
	var translateTemplate = function(msg){
		return replaceStringByMap(msg,languagePack||{},"template");
	};
	var translateAndFillTemplate = function(key,data){
		var template = translateString(key);
		return replaceStringByMap(template,data||{});
	}
	var translateString = function(msg){
		return replaceStringByMap(msg,languagePack||{},"simple");
	}
	var init = function(callb){
		ZMO.ajax.getDatas("js/Util/Localization/packs/"+getLang()+".json",function(json){
			languagePack = json;
			callb();
		});
	};
	var fillLinks = function(link){
		 var regexString = "{{\\*(.*?)Name\\*}}";
		 var linkPlaceHolderRegex = new RegExp(regexString,"g");
		 var matchArr =[],aTag=null;
		 while(linkPlaceHolderRegex.test(link)){
			 var div = $("<div>");
			 matchArr = new RegExp(regexString,"g").exec(link);
			 var placeHolder = matchArr[0];
			 var str = matchArr[1];
			 if(c.linkUrls[str]){
				 aTag = $("<a>").attr({
					 href:c.linkUrls[str]+"?id={{"+str+"Id}}"
				 }).text("{{"+str+"Name}}");
				 div.append(aTag);
				 link = link.replace(placeHolder,div.html());
			 }else{
				 link = link.replace(placeHolder,"{{"+str+"Name}}");
			 }
			 linkPlaceHolderRegex = new RegExp(regexString,"g");
		 }
		return link;
	};

	/**
	 * This method replaces placeholders ({{foo}}, {{bar}}, {{baz}},...) in a given
	 * String with values from a given Object. The return value is either the
	 * unchanged String, if no placeholders could be found or the newly generated
	 * String with appropriate values replacing the placeholders.
	 * 
	 * @param {String}
	 *         value String value with placeholders, ie
	 *         'basePath/param1/{{foo}}/param2/{{bar1}}/
	 * @param {Object}
	 *         map object with the values to the keywords
	 */
	var replaceStringByMap = function(value, map,type) {
	 var tmp = value;
	 $.each(map, function(key, val) {
		 val = fillLinks(val); 
		 var placeHolderRegex = null;
		 if(type=="simple"){
			 placeHolderRegex =new RegExp(key, "g");
		 }else if(type=="template"){
			 placeHolderRegex = new RegExp("\\[\\["+key+"\\]\\]", "g");
		 }else{
			 placeHolderRegex = new RegExp("\\{\\{"+key+"\\}\\}", "g");
		 }
		 
		 tmp = tmp.replace(placeHolderRegex, val);
	 });
	 // replace all elements which are not in map
	 //placeHolderRegex = new RegExp("{{.*}}", "g");
	 //tmp = tmp.replace(placeHolderRegex, "");
	 return tmp;
	};
	
	var changeLang = function(lang){
		localStorage.setItem(c.langToken,lang);
		window.location.reload();
	};
	var getLang = function(){
		return localStorage.getItem(c.langToken)|| c.defaultLang;
	};
	var pub = {
			replaceStringByMap:replaceStringByMap,
			translateTemplate:translateTemplate,
			translateString:translateString,
			translateAndFillTemplate:translateAndFillTemplate,
			changeLang:changeLang,
			getLang:getLang,
			init:init
	};
	return pub;
})(jQuery);

