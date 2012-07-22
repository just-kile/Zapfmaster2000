ZMUPSA.getRfidByQrCode = function(qrCode){
	return ZMUPSA.rfidQrTable[qrCode];
	
}

ZMUPSA.log = function(mess){
	if(console){
		console.log(mess)
	}
}
ZMUPSA.get = function(url,callback,datas,action){
	var getDatas = datas|| {};
	getDatas.token = ZMUPSA.Constants.securityToken;
	getDatas.action = action;
	$.ajax({
		   type:"GET",
		   data:getDatas,
		   url:url,
		   complete:function(response){
			   try{
				   var json = eval("(" + response.responseText + ")");
				   ZMUPSA.log("Get Response: ")
				   ZMUPSA.log(json)
				   callback(json)
			   }catch(e){
				   ZMUPSA.log(e);
			   }
			 
		   }
	   })
}

ZMUPSA.postData =function(url,callback,datas,action){
	var postDatas = datas|| {};
	postDatas.token = ZMUPSA.Constants.securityToken;
	postDatas.action = action;
	$.ajax({
		   type:"POST",
		   data:postDatas,
		   url:url,
		   success:function(resp){
			  callback(resp)
		   }
	   })
}
