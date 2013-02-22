
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
	var translate = function(msg){
		return replaceStringByMap(msg,languagePack||{});
	};
	var init = function(callb){
		ZMO.ajax.getDatas("js/Util/Localization/packs/"+getLang()+".json",function(json){
			languagePack = json;
			callb();
		});
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
	var replaceStringByMap = function(value, map) {
	 var tmp = value;
	 $.each(map, function(key, val) {
		 var placeHolderRegex = new RegExp("\\[\\["+key+"\\]\\]", "g");
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
			translate:translate,
			changeLang:changeLang,
			getLang:getLang,
			init:init
	};
	return pub;
})(jQuery);

