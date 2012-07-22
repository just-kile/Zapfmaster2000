var RfidComet = Class.create();
var FIRSTREFRESH_RFID =true;
var FIRSTNEW_RFID = false;
var LAST_DRAWER;
RfidComet.prototype = {

  timestamp: 0,
  url: 'rfid.php',
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
	if(!FIRSTREFRESH_RFID){
	  	var request_kind= response['kind'];
	  	var req_text = "User: "+ response['NAME']+". Du darfst jetzt zapfen!";
	  	if(typeof speak != "undefined" && speak != null && LAST_DRAWER !=response["USER_ID"] ){
	  			var array;
				
				if(response["ADMINS"].split(";").indexOf(response["USER_ID"])>-1){
					array = ["Master","Good looking man", "God", "You are awesome!"];
				
					
				}else{
					array = ["Motherfucker","Liicker of Chiicken Butts","Cocksucker", "Pevert",  "Noob", "Dumb ass",  "Sun of a Biitch","You piiece of Shiit"];
				
				}
				
				LAST_DRAWER =response["USER_ID"] ;
					speak(response['NAME']+", you can draw. "+array[Math.floor(Math.random()*(array.length))], { amplitude: 100, wordgap: 0, pitch: 50, speed: 175 });
		}
		FIRSTNEW_RFID=true;
		
	  	updateDrinkStats(req_text,response["USER_ID"],response["NAME"],response["IMAGE_PATH"]);
    	

	}else{
		FIRSTREFRESH_RFID = false;
		}
  },
}

var rfidcomet = new RfidComet();
rfidcomet.connect();

//this.refreshInterval =setInterval("refreshCharts()",30000);
function updateDrinkStats(req_text,userid,name,imagepath){
	if(userid!=undefined){
		clearInterval(this.refreshInterval);	 
	    if(userid==-1){
			$('rfid').update("");
			refreshCharts();
			this.refreshInterval =setInterval("refreshCharts()",30000);
		}else{
			$('rfid').update(req_text);
		 new Ajax.Request("getStats.php", {
				 method:'get',
				 parameters:{'bestlist':userid,'achievementnumbers':userid,'duration':userid,'amount':userid,'promille':userid},
				 onSuccess: function(transport) {
					var response = transport.responseText.evalJSON();
					var bestlist = typeof response["BESTLIST"]["PLACE"] =="undefined"?"weit unten":response["BESTLIST"]["PLACE"];
					document.getElementById('stats-best-drinker').innerHTML ="<table><tr><td>Name:</td><td> "+ mkBold(name)+"</td></tr><tr><td>Platz</td><td> "+mkBold(bestlist) + "</td></tr><tr><td>" + "Durch- schnittliche Zapfzeit </td><td>" + mkBold(response['AVERAGE_DRAW_DURATION'])+"s</td></tr><tr><td>Komplette Zapfzeit:</td><td>"+ mkBold(Math.round(response['COMPLETE_DRAW_DURATION']*100/60)/100)+' min</td></tr><tr><td>Kompletter Bierkonsum: </td><td>'+mkBold(response['AMOUNT'])+' L</td></tr><tr><td>Ungefährer Blutalkohol: </td><td>'+mkBold(response['AVERAGE_PROMILLE'])+'&permil;</td></tr></table>' ;

        			
					document.getElementById("drink-stats-image").src=imagepath;

				 }
				 
			 });
		}
		
	}
}



