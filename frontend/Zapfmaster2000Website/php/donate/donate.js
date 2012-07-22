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
    	//document.getElementById("UPDATEUSER_NAME").value = response['NAME'];
		window.location.replace('php/donate/check.php?DONATION='+document.getElementById("DONATION").value+"&UPDATEUSER_RFID="+response['RFID_TAG']);
		//document.getElementById("UPDATEUSER_RFID").value = response['RFID_TAG'];
		//alert(response['RFID_ID']);
		//mkAjaxReq(response);	
		
	  
	}else{
		FIRSTREFRESH = false;
		}
  },
}

var rfidcomet = new RfidComet();
rfidcomet.connect();
