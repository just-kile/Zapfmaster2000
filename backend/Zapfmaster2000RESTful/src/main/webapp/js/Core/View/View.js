/**
 * Core View Class
 * Handles the basic module injection and the view in browser
 */
ZMO.view=(function(){
	var site = {};
	/**
	 * Declares the 3 areas of page
	 */
	var activeItems={};
	/**
	 * Renders a given Module Model at the position
	 * @param {ZMO.PosititionModel} posModel
	 *  
	 */
	var renderModuleModel =function(moduleModel,key){
		var container =$(site[key]);
		var ratio = moduleModel.ratio;
		var module = moduleModel.element.getInstance();
		var moduleRow = moduleModel.position.row;
		var moduleCol = moduleModel.position.col;
		var row;
		
		var containerChildren = container.children();
		if(containerChildren.length-1<moduleRow){
			row = createRow();
			container.append(row);
		}else{
			row = $(containerChildren[moduleRow]);
		}
		var rowChildren = row.children();
		if(rowChildren.length-1<moduleCol){
			var col = createCol().css("width",ratio);
			col.append(module);
			row.append(col);
		}else{
			$(rowChildren[moduleCol]).css("width",ratio).append(module);
		}
		//container.append(wrapper);
		if(moduleModel.element.init)moduleModel.element.init();
		return module;
	};
	var createCol = function(){
		return $("<div>").addClass("zm_col");
	};
	var createRow = function(){
		return $("<div>").addClass("zm_row");
	};
	
	var removeOldModules =function(newList,oldList){
		$.each(oldList,function(posKey,elObj){
				if(!ZMO.exists(newList[posKey])
						||!ZMO.exists(newList[posKey][1])
						||!ZMO.exists(newList[posKey][1].position)
						||!newList[posKey][1].position.equals(elObj[1].position) ){
					ZMO.log("remove "+posKey);
					$(oldList[posKey][0]).remove();
				}
		});
		
	};
	var isRenderedAndSamePos = function(moduleModel,oldModel){
		return 	typeof oldModel!="undefined" 
				&& typeof oldModel[1]!="undefined"
				&& moduleModel.position.equals(oldModel[1].position);
	};
	
	var createPage =function(pageKey,moduleMap){
		//iterate through site position (navi,main,footer)
		var  tmpActiveItems={};
		$.each(moduleMap.sections,function(modulePosKey,moduleModelArrArr){
			
			//Iterate through Models
			$.each(moduleModelArrArr,function(ind,module){
				var model = new ZMO.ModuleModel(module,modulePosKey);
				if(isRenderedAndSamePos(model,activeItems[model.moduleId]) ){
					tmpActiveItems[model.moduleId] = activeItems[model.moduleId];
					$(tmpActiveItems[model.moduleId][0]).parent().css("width",model.ratio);
				}else{
					tmpActiveItems[model.moduleId] =[renderModuleModel(model,modulePosKey),model];
					
				}
			});
		});
		removeOldModules(tmpActiveItems,activeItems);
		activeItems = tmpActiveItems;
	};
	var empty = function(){
		$.each(site,function(ind,val){
			val.empty();
		});
	};
	var init =function(){
		site.navigation = $(".main .nav");
		site.main=$(".main .major");
		site.footer=$(".main .footer");
		
	};
	var pub = {
			init:init,
			createPage:createPage
			
	};
	return pub;
}(jQuery,document));