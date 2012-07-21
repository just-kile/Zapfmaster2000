ZMUCA.getRfidByQrCode = function(qrCode){
	return ZMUCA.rfidQrTable[qrCode];
	
}

ZMUCA.log = function(mess){
	if(console){
		console.log(mess)
	}
}
ZMUCA.get = function(url,callback,datas,action){
	var getDatas = datas|| {};
	getDatas.token = ZMUCA.Constants.securityToken;
	getDatas.action = action;
	$.ajax({
		   type:"GET",
		   data:getDatas,
		   url:url,
		   complete:function(response){
			   try{
				   var json = eval("(" + response.responseText + ")");
				   ZMUCA.log("Get Response: ")
				   ZMUCA.log(json)
				   callback(json)
			   }catch(e){
				   ZMUCA.log(e);
			   }
			 
		   }
	   })
}

ZMUCA.postData =function(url,callback,datas,action){
	var postDatas = datas|| {};
	postDatas.token = ZMUCA.Constants.securityToken;
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

ZMUCA.login = function(pin,success,fail){
	var url = ZMUCA.Constants.phpUrl;
	var getDatas = {};
	getDatas.token = ZMUCA.Constants.securityToken;
	getDatas.user=MD5(pin);
	ZMUCA.log("Pin: "+pin)
	getDatas.action = "login";
	$.ajax({
		   type:"GET",
		   data:getDatas,
		   url:url,
		   success:function(response){
			   try{
				   var userModel = new ZMUCA.UserModel(eval("(" + response+ ")"));
				   ZMUCA.log("Login Response: ")
				   ZMUCA.log(userModel);
				   ZMUCA.setUser(userModel)
				   ZMUCA.connect(userModel,function(){
					   success(userModel);
				   });
				   
			   }catch(e){
				   TOMO.log(e);
				   alert("Interner Serverfehler")
			   }
			  
		   },
		   error:function(response){
			   fail(response);
		   }
	   })
}
ZMUCA.setUser = function(userModel){
	 ZMUCA.log("Setting User Cookie")
	 //$.cookie('ZM_usermodel', JSON.stringify(userModel), { expires: 7 });
	 localStorage.setItem("ZM_usermodel", JSON.stringify(userModel)); 
	 ZMUCA.log("Setting User to Constants")
	 ZMUCA.Constants.actualUser = userModel;
}
ZMUCA.getUser = function(){
	try{
		ZMUCA.log("Get User... ");
		if(typeof ZMUCA.Constants.actualUser.userid == "undefined"){
			ZMUCA.log("...from Local Storage");
			ZMUCA.Constants.actualUser =  new ZMUCA.UserModel(eval("(" + localStorage.getItem("ZM_usermodel")+ ")"));
			if(typeof ZMUCA.Constants.actualUser.userid == "undefined")throw "No User!";
		}else{
			ZMUCA.log("...from Constants");
		}
		ZMUCA.log(ZMUCA.Constants.actualUser);
		return ZMUCA.Constants.actualUser;
	}catch(e){
		ZMUCA.log("...error: No User logged in")
	}
}
ZMUCA.connect = function(userModel,callback){
	if(typeof userModel=="undefined"){
		$.mobile.changePage("#" + frontPageId)
		
	}else{
	var c = ZMUCA.Constants;
	ZMUCA.log("Connect to Node Server ");
	ZMUCA.disconnect();
	
	ZMUCA.log("Connect to "+c.node.drawUrl);
	ZMUCA.drawChannel = io.connect(c.node.drawUrl);
	
	ZMUCA.log("Connect to "+c.node.actionsUrl);
	if(typeof ZMUCA.actionsChannel =="undefined"){
		ZMUCA.actionsChannel = io.connect(c.node.actionsUrl).on('connect', function () {
			ZMUCA.actionsChannel.emit("initUser",userModel);
			if(typeof callback != "undefined")callback();
		});
	}else{
		ZMUCA.actionsChannel.emit("initUser",userModel);
		if(typeof callback != "undefined")callback();
	}

	ZMUCA.log("Connect to "+c.node.datasUrl);
	ZMUCA.getdatasChannel = io.connect(c.node.datasUrl);
	
	
//	ZMUCA.drawChannel.on('connect', function () {
//		
//	  });
//	ZMUCA.drawChannel.on("draw",onDraw)
//	ZMUCA.actionsChannel.on('challenge',onChallenge) ;
//
//	ZMUCA.getdatasChannel.on('connect', function () {
//		ZMUCA.getdatasChannel.emit('getdatas', function (data) {
//		     ZMUCA.log(data);
//		      });
//		  });
	}
}

/**
 * Test if connections is ok, 
 * yes: call callback
 * no: go back to login page
 */
ZMUCA.testConnection  = function(callback){
	//Check if connected to Node js Server Module
	if(typeof ZMUCA.actionsChannel == "undefined"){
		ZMUCA.connect(ZMUCA.getUser(),callback);
	}else{
		if(typeof callback != "undefined") callback();
	}	
}
ZMUCA.disconnect = function(){
	 if(typeof ZMUCA.drawChannel != "undefined"){
		 ZMUCA.log("Disconnect drawChannel")
		 ZMUCA.drawChannel.disconnect(function(){
		 });
	 };
	 if(typeof ZMUCA.actionsChannel != "undefined"){
		 ZMUCA.log("Disconnect actionsChannel")
		 ZMUCA.actionsChannel.disconnect();
	 };
	 if(typeof ZMUCA.getdatasChannel != "undefined"){
		 ZMUCA.log("Disconnect getDatasChannel")
		 ZMUCA.getdatasChannel.disconnect();
	 };
	 
	 
}

ZMUCA.logout = function(){
	ZMUCA.disconnect();
	ZMUCA.setUser();
	$.mobile.changePage("#index");
}
