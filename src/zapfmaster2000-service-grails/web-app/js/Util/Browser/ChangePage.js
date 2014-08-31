ZMO.Util.Browser = ZMO.Util.Browser || {};

ZMO.Util.Browser.changePage = function(hash){
//	window.location.replace(hash);
	window.location.href=hash;
	$(window).scrollTop(1);
};