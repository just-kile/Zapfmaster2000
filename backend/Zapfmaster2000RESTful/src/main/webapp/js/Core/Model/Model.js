/**
 * The Position model for rendering the modules in view
 * @see js/Core/View/view.js
 * @param {Object} attr
 * 	with attributes
 * 		 	{String} key: can be navigation,main,footer
 * 			{int} col: the wanted column
 * 			{int} row: the wanted row
 */
ZMO.PositionModel =function(key,arr){
	this.key =key;
	this.col = arr[1];
	this.row = arr[0];
	/**
	 * Compares two ZMO.PositionModels
	 * 
	 */
	this.equals = function(newPos){
		return 	typeof newPos !="undefined" 
				&& this.col ==newPos.col 
				&& this.key==newPos.key
				&& this.row==newPos.row;
	}
}

ZMO.PageModel = function(conf){
	this.navigation = conf.navigation;
	this.main = conf.main;
	this.footer = conf.footer;
	
}