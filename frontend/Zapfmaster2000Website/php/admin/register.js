var NEWSCOUNT = 5;
var RfidComet = Class.create();
var FIRSTREFRESH =true;
var FIRSTNEW = false;

RfidComet.prototype = {

  timestamp: 0,
  url: './rfid.php',
  noerror: true,

  initialize: function() { },

  connect: function()
  {
    this.ajax = new Ajax.Request(this.url, {
      method: 'get',
      parameters: { 'timestamp' : this.timestamp },
      onSuccess: function(transport) {
        // handle the server response
        var response = transport.responseText.evalJSON();
        this.rfidcomet.timestamp = response['timestamp'];
        this.rfidcomet.handleResponse(response);
        this.rfidcomet.noerror = true;
      },
      onComplete: function(transport) {
        // send a new ajax request when this request is finished
        if (!this.rfidcomet.noerror)
          // if a connection problem occurs, try to reconnect each 5 seconds
          setTimeout(function(){ rfidcomet.connect() }, 5000); 
        else
          this.rfidcomet.connect();
        this.rfidcomet.noerror = false;
      }
    });
    this.ajax.rfidcomet = this;
  },

  disconnect: function()
  {
  },

  handleResponse: function(response)
  {	 
	if(!FIRSTREFRESH){
	  
	  
	  	FIRSTNEW=true;
	  	//hier kommt request
    	//$('rfid').update(req_text);
		mkAjaxReq(response);	
		
	  
	}else{
		FIRSTREFRESH = false;
		}
  },
}

function mkAjaxReq(response){
	if(response != "" && response != undefined){
new Ajax.Request("php/admin/check.php", {
      method: 'post',
      parameters: {  'RFID_TAG' : response['RFID_TAG'] },
      onSuccess: function(transport) {
        // handle the server response
       	$('rfid').update("Weiterleitung erfolgt...");
		window.location.replace("admincp.php");
      }
    });
	}
}
var rfidcomet = new RfidComet();
rfidcomet.connect();
