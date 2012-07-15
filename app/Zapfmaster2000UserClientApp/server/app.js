var ZM = ZM || {};
/**
 * For Socket Initialization
 */
ZM.socketModel = function(config){
	this.events = config.events;
	this.channel = config.channel;
}

ZM.serverUserModel = function(config){
	this.socket = config.socket;
	this.user = config.user;
}
/**
 * Map with
 * key:pin
 * value: socket (with actual User = socket.get("user"))
 */


/**
 * Constants
 */
ZM.constants = {
		port:1337,
		// indexfile:"/home/ben/www/zapfmaster/index.html",
		sockets:{
			draw:new ZM.socketModel({
				events:{
					draw:"draw",
				},
				channel:"/draw"
			}),
			actions:new ZM.socketModel({
				events:{
					challenge:"challenge",
					initUser:"initUser",
					newUserConnected:"newUserConnected",
					getAllUsers:"getAllUsers",
				},
				channel:"/actions"
			}),
			getdatas:new ZM.socketModel({
				events:{
					getdatas:"getDatas"
					},
				channel:"/getdatas"
			})
		}
}

/**
 * User Handler
 */
ZM.UserHandler = (function(){
	var users = {};
	var addUser =function(userModel,socket){
		console.log("Add user "+userModel.qrcode)
		users[userModel.qrcode] = new ZM.serverUserModel({
			socket:socket,
			user:userModel
		});
	};
	var getUserSocket = function(qrcode){
		console.log("Get user socket "+qrcode)
		return users[qrcode].socket;
	}
	var getUserModel = function(qrCode){
		console.log("Get user model "+qrcode)
		return users[qrcode].user;
	}
	var getAllUserSockets = function(){
		var arr = [];
		console.log("getAllUserSockets called")
		for(var user in users){
			console.log("key "+user)
			arr.push(users[user].socket);
		}
		return arr;
	}
	var getAllUserModels = function(){
		var arr = [];
		console.log("getAllUserModels called")
		for(var user in users){
			console.log("key "+user)
			arr.push(users[user].user);
		}
		return arr;
	}
	var getUserToSocket = function(socket){
		var userModel;
		for(var user in users){
			if(users[user].socket == socket){
				userModel = users[user].user;
			}
		}
		return userModel;
	}
	var getIndexToSocket = function(socket){
		for(var user in users){
			if(users[user].socket == socket){
				return user;
				break;
			}
		}
		//return userModel;
	}
	var deleteUser =function(socket){
		
		var key = getIndexToSocket(socket);
		
		if(typeof key != "undefined" && users[key]!= "undefined"){
			console.log("delete User "+key)
			delete users[key]
		}
	}
	var pub = {
			addUser:addUser,
			getUserSocket:getUserSocket,
			getUserModel:getUserModel,
			getAllUserSockets:getAllUserSockets,
			getAllUserModels:getAllUserModels,
			deleteUser:deleteUser
	}
	return pub;
})();

/**
 * Controller
 */
ZM.controller = function(uH){
	var c = ZM.constants,
	uH =ZM.UserHandler,
	sys ,
	url, 
	http, 
	fs ,
	qs ,
	io,
	draw,actions,getdatas;
	var actionEv = c.sockets.actions.events;
	var drawEv = c.sockets.draw.events;
	var getDatasEv = c.sockets.getdatas.events;
	var handler = function handler (req, res) {
	    if(req.method=='POST') {
	        var body='';
	        req.on('data', function (data) {
	            body +=data;
	        });
	        req.on('end',function(){
	            
	            var POST =  qs.parse(body);
	        	draw.emit(drawEv.draw,new Date());
	            res.end("ok");
	        });
	    }
	    else if(req.method=='GET') {
// var datas = req.url;
// if(datas.indexOf('getdata')>-1){
	    		res.end("ok")
// }else{
// fs.readFile(c.indexfile,
// function (err, data) {
// if (err) {
// res.writeHead(500);
// return res.end('Error loading index.html');
// }
//
// res.writeHead(200);
// res.end(data);
// };
		
// }
	}
	}
	
	var onActionInit = function(userModel){
	
	
	}
	var onDraw =function(name,fn){
	   	draw.emit(drawEv.draw,{
	    		"timestamp":new Date()
	   	});
	    	
	}
	var onChallenge =function(name,fn){
    	actions.emit(actionEv.challenge,{
    		"timestamp":new Date()
    	})
	}
	var onGetAllUsers =function(socket,callback){
//		console.log()
		callback(uH.getAllUserModels());
	}
	/**
	 * Saves the socket for initialization
	 */
	var onInitUser = function(userModel,socket){
		  console.log("Set socket for "+userModel.name);
		  //Add User to Store
		  uH.addUser(userModel,socket);
		  //send message to other users, that new user connected
		  onGetAllUsers(socket,function(userModelArr){
			  socket.broadcast.emit(actionEv.newUserConnected,userModelArr)
		  })
	}
	var init = function(){
		sys = require ('util');
		url = require('url');
		http = require('http').createServer(handler);
// http = require('Fabric').createServer(handler);
		fs = require('fs');
		qs = require('querystring');
		
		io = require('socket.io').listen(http);
		http.listen(c.port);
		
		
// require(__dirname.replace(/server/g,"")+"share/Model/Model.js")
		// initialize the draw socket
		draw = io.of(c.sockets.draw.channel)
		  .on('connection', function (socket) {
			    socket.on(drawEv.draw,onDraw);
			    
		  });
			
		
		// initialize the actions socket
		actions = io
		  .of(c.sockets.actions.channel)
		  .on('connection', function (socket) {
			  socket.on(actionEv.initUser,function(userModel){
				  console.log("Init User Called!"+userModel);
				  onInitUser(userModel,socket);
			  })
			  socket.on(actionEv.challenge,onChallenge);
			  socket.on(actionEv.getAllUsers ,function(callback){
				  onGetAllUsers(socket,callback)
			  });
			  socket.on("disconnect",function(){
				  onGetAllUsers(socket,function(userModelArr){
					  socket.broadcast.emit(actionEv.newUserConnected,userModelArr);
					 
				  })
				  uH.deleteUser(socket);
			  });
			  
		  })
		  
		
		// initialize the getDatas socket
		getdatas = io
		  .of(c.sockets.getdatas.channel)
		  .on('connection', function (socket) {
			  socket.on(getDatasEv.getdatas ,function(fn){
				  console.log("ok")
				  fn("woot")
			    });
		  });
		
	}
	var pub = {
			init:init
	}
	return pub;
	
}();

ZM.controller.init();
console.log('Zapfmaster 2000 Front-Backend started!');