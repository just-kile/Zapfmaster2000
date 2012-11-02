ZMO.PositionModel =function(arr){
	this.key =arr[0];
	this.col = arr[1];
	this.row = arr[2];
}

ZMO.PageModel = function(conf){
	this.navigation = conf.navigation;
	this.main = conf.main;
	this.footer = conf.footer;
	
}