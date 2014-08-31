/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.settings = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var t = ZMO.Util.localization;
	var wording={
		choseImage:"choseImage",
		selectLang:"selectLang",
		registerRfid:"registerRfid"
	};
	var container =null,form = null,language = null,rfid=null,rfidRegister=null;
	var uploadImage = function(formular,isPhoto){

		if(!isPhoto && (true || typeof FileUploadOptions =="undefined")){
		var formData = null;
			formData = new FormData ($(formular)[0]);
		formData.append("token",localStorage.getItem("token"));
		$.ajax({
		    url: baseUrl+'rest/image/user',
		    data: formData,
		    cache: false,
		    contentType: false,
		    processData: false,
		    type: 'POST',
		    beforeSend: function() {
		        ZMO.Util.Browser.throbber.show("0%");
		       
		        
		    },
		    progress: function(e) {
		    	if(e.lengthComputable) {
		            //calculate the percentage loaded
		            var percentComplete =Math.round( (e.loaded / e.total) * 100);
			        var percentVal = percentComplete + '%';
			        ZMO.Util.Browser.throbber.updateText(percentVal);
		    	}
		    },
		    success: function(data){
		        alert("Bild geändert!");
	
		    }
		    ,
			error:function(){
				alert("Fehler beim Bild hochladen");
			},
			complete:function(){
				ZMO.Util.Browser.throbber.hide();
			}
		});
		}else{
			var options = new FileUploadOptions();
			var imageURI = formular;
			console.log(imageURI);
			options.fileKey="image";
			options.chunkedMode = false;
			options.trustEveryone = true;
			options.fileName=imageURI.substr(imageURI.lastIndexOf('/')+1);
			 options.mimeType="image/jpeg";
			 var params ={
					 token:localStorage.getItem("token")
			 };
			 ZMO.Util.Browser.throbber.show("0%");
			 options.params = params;
			 var ft = new FileTransfer();
			 ft.onprogress  = function(e) {
			    	if(e.lengthComputable) {
			            //calculate the percentage loaded
			            var percentComplete =Math.round( (e.loaded / e.total) * 100);
				        var percentVal = percentComplete + '%';
				        ZMO.Util.Browser.throbber.updateText(percentVal);
			    	}
			    };
	         ft.upload(imageURI, encodeURI(baseUrl+'rest/image/user/'), function(){
	        	 new ZMO.Util.Popup().open("Bild ge&auml;ndert!");
	        	 ZMO.Util.Browser.throbber.hide();
	         }, function(){
					alert("Fehler beim Bild hochladen");
					ZMO.Util.Browser.throbber.hide();
	         }, options);
		}
	};
	var setRFID = function(rfid){
		var yep = confirm("Karte erkannt! Ist das deine Karte?");
		if(yep){
			ajax.postDatas('rest/register/rfid',function(){
				
				hideRegisterRfid();
				alert("RFID erfolgreich geändert!");
			},{
				rfid:rfid
			});
		}
	};
	var oldUrl = null;
	var abortOldReq = function(){
		if(oldUrl){
			ajax.abortReq(oldUrl);
			oldUrl=null;
		}
	};
	
	var showRegisterRfid = function(){
		form.hide();
		language.hide();
		rfid.hide();
		rfidRegister.show();
		ajax.getDatas( 'rest/draftkit',function(data){
			abortOldReq();
			var selectBox = rfidRegister.find("#registerSelectBox").empty();
			selectBox.unbind().on("change",function(e){
				//initRfidPush();
				abortOldReq();
				console.log(e);
				oldUrl = "rest/draftkit/"+$(e.currentTarget).val()+"/rfid";
				ajax.connectToChannel(oldUrl,setRFID);
			});
	        var firstObj=null;
	        $.each(data,function(ind,obj){
	        	$("<option>").attr("value",obj.boxId).text(obj.boxId+obj.name).appendTo(selectBox);
	        	if(!firstObj)firstObj =obj;
	        });
	        if(firstObj){
	        	oldUrl = "rest/draftkit/"+firstObj.boxId+"/rfid";
	        	ajax.connectToChannel("rest/draftkit/"+firstObj.boxId+"/rfid",setRFID);
	        }else{
	        	ZMO.logger.log("Error! No boxes");
	        }
		        
		});
	};
	var hideRegisterRfid = function(){
		abortOldReq();
		form.show();
		language.show();
		rfid.show();
		rfidRegister.hide();
	};
	var generateForm = function(){
		var formDiv = $("<div>").addClass("statsDiv");
		var formula = $("<form>");
		//label
		$("<p>").text(t.translateString(wording.choseImage)).appendTo(formula);
		//image upload field
		$("<input>").attr({
			name:"image",
			id:"imageUpload",
			type:"file"
		}).appendTo(formula); 
		//ok button
//		$("<input>").attr({
//			name:"image",
//			type:"submit",
//			id:"uploadImage"
//		}).appendTo(formula);
		 formDiv.append(formula);
		if( typeof device!="undefined" && device.cordova){
			$("<input>").attr({
				id:"take_photo",
				type:"button"
			}).val("Take a photo!").appendTo(formDiv);
			
		}

			

		
		return formDiv;
	};
	var generateRegisterRfidButton = function(){
		return $("<div>").addClass("statsDiv")
				.append( $("<div>").addClass("zmo-clickbutton").text(t.translateString(wording.registerRfid)))
				.on(mC.clickEvent,function(){
					showRegisterRfid();
				});
	};
	var generateLanguageChooser = function(){
		var span =$("<div>").text(t.translateString(wording.selectLang));
		var de = $("<img>").attr("src",mC.language.deImg).on(mC.clickEvent,function(){
			ZMO.Util.localization.changeLang('de');
		});
		var en = $("<img>").attr("src",mC.language.enImg).on(mC.clickEvent,function(){
			ZMO.Util.localization.changeLang('en');
		});
		
		
		return $("<div>").addClass("statsDiv language").append(span).append(de).append(en);
	};
	var generateRfidRegister = function(){
		var con = $("<div>").addClass("statsDiv");
		var pDescr = $("<p>").text("Lege deine RFID Karte auf die ausgewählte ZM2k Box. Die Karte wird dann automatisch auf dich registriert.");
		var pChose = $("<p>").text("Wähle die Box");
		var selectBox =$("<select>").attr({
			"id":"registerSelectBox",
			"size":"1"
		});
		var pWait = $("<p>").text("Bitte Karte auf Sensor legen ...");
		var cancel =$("<div>").text("Abbrechen").addClass("zmo-clickbutton").on(mC.clickEvent,hideRegisterRfid);
		

		return con.append(pDescr).append(pChose).append(selectBox).append(pWait).append(cancel);
	};
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(hashParams,moduleParams){
		//container.text("Hello drinkers worldwide!");
		//form.find("#uploadImage").on("click",function(e){
		form.find("input[type=file]")
			.on("change",function(e){
				e.preventDefault();
				var inputVal = $(e.currentTarget).val();
				if(inputVal!="")
					uploadImage(form);
		});
		
		if(typeof device!="undefined" && device.cordova){
			$("#take_photo").on("click",function(){
				navigator.camera.getPicture( function(image){
					uploadImage(image,true);
				}, function(){
					ZMO.logger.log("Camera error!");
				}, {
					mediaType:Camera.MediaType.PICTURE,
					width:160,
					heigt:160,
					quality: 50, 
                    destinationType: navigator.camera.DestinationType.FILE_URI,
                   // sourceType: navigator.camera.PictureSourceType.PHOTOLIBRARY
				});
			});
		}
		if(ZMO.throbber)ZMO.throbber.hide();
		
	};
	
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		
		container = $("<div>");
		var formDiv= generateForm().appendTo(container);
		form = formDiv.children();
		language =generateLanguageChooser().appendTo(container);
		rfid = generateRegisterRfidButton().appendTo(container);
		
		rfidRegister = generateRfidRegister().appendTo(container).hide();
		
		return container;
	};
	var pub = {
			getInstance:getInstance,
			init:init
	};
	return pub;
}(jQuery,ZMO.ajax));
