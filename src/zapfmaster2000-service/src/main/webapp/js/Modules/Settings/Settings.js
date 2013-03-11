/**
 * Dummy Module if u want to write your own
 * 
 */
ZMO.modules = ZMO.modules || {};
ZMO.modules.settings = (function($,ajax){
	var mC = ZMO.modules.Constants;
	var container =null,form = null;
	var uploadImage = function(formular){
		var formData = new FormData ($(formular)[0]);
		formData.append("token",localStorage.getItem("token"));
		$.ajax({
		    url: baseUrl+'rest/image/user',
		    data: formData,
		    cache: false,
		    contentType: false,
		    processData: false,
		    type: 'POST',
		    success: function(data){
		        alert("Bild ge�ndert!");
	
		    }
		    ,
			error:function(){
				alert("Fehler beim Bild hochladen")
			}
		});
	}
	var generateForm = function(){
		var form = $("<form>").appendTo(container);
		//label
		$("<p>").text("Lade ein Bild von dir hoch").appendTo(form);
		//image upload field
		$("<input>").attr({
			name:"image",
			type:"file"
		}).appendTo(form); 
		//ok button
		$("<input>").attr({
			name:"image",
			type:"submit",
			id:"uploadImage"
		}).appendTo(form); 
		return form;
	}
	/**
	 * Gets called after the "getInstance" container is appended to DOM
	 */
	var init = function(hashParams,moduleParams){
		//container.text("Hello drinkers worldwide!");
		form.find("#uploadImage").on("click",function(e){
			e.preventDefault();
			uploadImage(form);
		});
		
	};
	
	/**
	 * Gets called when page contains the module. This container will be added to DOM
	 */
	var getInstance = function(){
		
		container = $("<div class='statsDiv'>");
		form = generateForm().appendTo(container);
		return container;
	};
	var pub = {
			getInstance:getInstance,
			init:init
	};
	return pub;
}(jQuery,ZMO.ajax));
