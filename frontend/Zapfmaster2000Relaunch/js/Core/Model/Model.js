/**
 * The Position model for rendering the modules in view
 * @see js/Core/View/view.js
 * @param {Object} attr
 * 	with attributes
 * 		 	{String} key: can be navigation,main,footer
 * 			{int} col: the wanted column
 * 			{int} row: the wanted row
 */
ZMO.PositionModel =function(arr){
	this.key =arr[0];
	this.col = arr[1];
	this.row = arr[2];
	/**
	 * Compares two ZMO.PositionModels
	 * 
	 */
	this.equals = function(newPos){
		return this.col ==newPos.col && this.key==newPos.key;
	}
}

ZMO.PageModel = function(conf){
	this.navigation = conf.navigation;
	this.main = conf.main;
	this.footer = conf.footer;
	
}