/**
 * Core View Class
 * Handles the basic module injection and the view in browser
 */
ZMO.view=(function(){
	var site = {};
	/**
	 * Declares the 3 areas of page
	 */
	var activeItems={main:[[]],navigation:[[]],footer:[[]]};
	/**
	 * Renders a given Module Model at the position
	 * @param {ZMO.PosititionModel} posModel
	 *  
	 */
	var renderModuleModel =function(posModel,moduleModel){
		var container =$(site[posModel.key]);
		var ratio = moduleModel.ratio;
		var module = moduleModel.element.getInstance();
		
		var children = container.children();
		if(children.length-1<posModel.col){
			var col = createCol().css("width",ratio);
			col.append(module);
			container.append(col);
		}else{
			$(children[posModel.col]).css("width",ratio).append(module);
		}
		//container.append(wrapper);
		if(moduleModel.element.init)moduleModel.element.init();
		return module;
	}
	var createCol = function(){
		return $("<div>").addClass("col");
	}

	var isRendered = function(id,key){
		return ZMO.exists(activeItems[key])
				&&ZMO.exists(activeItems[key][id])
	}
	var isRenderedInPositon  = function(id,key,posModel){
		return isRendered(id,key) && activeItems[key][id].position 
								  && (activeItems[key][id].position.equals(posModel));
	}
	
	var removeOldModules =function(newList,oldList){
		$.each(oldList,function(posKey,elObj){
			$.each(elObj,function(elKey,el){
				if(!ZMO.exists(newList[posKey])
						||!ZMO.exists(newList[posKey][elKey])
						||!newList[posKey][elKey].position.equals(el.position) ){
					ZMO.log("remove "+posKey+", "+elKey);
					$(el.element).remove();
				}
			});
		});
		
	}
	/**
	 * @notused
	 * Moves the given element to the new PositionModel
	 * @param {DOMElement or Selector} el the element which should be moved
	 * @param {ZMO.PositionModel} newPos the new position
	 */
	var moveToNewPosition = function(el,newPos){
			var newContainer = $(site[newPos.key]).children(":nth-child("+(newPos.col+1)+")");
			if(newContainer.length==0){
				newContainer = createCol().appendTo($(site[newPos.key]));
			}
			$(el).detach().appendTo(newContainer);
			ZMO.log("Move Container");
			ZMO.log(el);
			ZMO.log("to "+newPos.key+", col "+newPos.col);

	}
	
	var createPage =function(pageKey,moduleMap){
		//iterate through site position (navi,main,footer)
		var  tmpActiveItems={main:{},navigation:{},footer:{}};
		
		$.each(moduleMap.sections,function(modulePosKey,moduleModelArrArr){
			
			//Iterate through Columns
			$.each(moduleModelArrArr,function(col,moduleModelArr){
				
				//iterate through column rows
				$.each(moduleModelArr,function(row,moduleModel){
					
					var id=moduleModel.id;
					var newPos = new ZMO.PositionModel([modulePosKey,col,row]);
					
					//if(isRendered(moduleModel.id,modulePosKey)){
					if(isRenderedInPositon(moduleModel.id,modulePosKey,newPos)){
						
						var oldPos = activeItems[modulePosKey][id].position;
						//renderModuleModel();
						//passe Breite an:
						ZMO.log("same module, same position");
						ZMO.log(moduleModel);
						
						//set new active items
						tmpActiveItems[modulePosKey][id] = activeItems[modulePosKey][id];
						//move to new position (deprecated)
						if(!oldPos.equals(newPos)){
							//moveToNewPosition(tmpActiveItems[modulePosKey][id].element,newPos);
						}
							
						//set new position
						tmpActiveItems[modulePosKey][id].position = newPos;
						tmpActiveItems[modulePosKey][id].element.parent().css("width",moduleModel.ratio);
						
					}
					else{
						var element = renderModuleModel(newPos,moduleModel);
						
						tmpActiveItems[modulePosKey][id] = {
								id:id,
								element:element,
								position:newPos
						};
						ZMO.log("other module")
						ZMO.log(moduleModel)
					}

				});
				
			})
		});
		removeOldModules(tmpActiveItems,activeItems)
		activeItems = tmpActiveItems;
	}
	var empty = function(){
		$.each(site,function(ind,val){
			val.empty();
		});
	}
	var init =function(){
		site.navigation = $(".main .nav");
		site.main=$(".main .major");
		site.footer=$(".main .footer");
		
	}
	var pub = {
			init:init,
			createPage:createPage
			
	}
	return pub;
}(jQuery,document))