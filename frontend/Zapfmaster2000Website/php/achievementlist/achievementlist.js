function mod(n,m){
	return ((n%m)+m)%m;	
	
}
var elementVisible = new Array();
elementVisible["expandProgress"] = true;
function slideInOrOut(linkid,slideid){
	
	
		
		
	if(elementVisible[linkid]){
		new Effect.SlideUp(slideid, {duration:0.5});
		elementVisible[linkid] = false;
		document.getElementById(linkid).innerHTML = "expand";
	}else{
		
		new Effect.SlideDown(slideid, {duration:0.5}); 
		elementVisible[linkid] = true;
		document.getElementById(linkid).innerHTML = "hide";
	}

}
