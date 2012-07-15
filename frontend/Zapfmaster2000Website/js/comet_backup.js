var NEWSCOUNT = 5;
var Comet = Class.create();
var FIRSTREFRESH =true;
var FIRSTNEW = false;
Comet.prototype = {

  timestamp: 0,
  url: './backend.php',
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
        this.comet.timestamp = response['timestamp'];
        this.comet.handleResponse(response);
        this.comet.noerror = true;
      },
      onComplete: function(transport) {
        // send a new ajax request when this request is finished
        if (!this.comet.noerror)
          // if a connection problem occurs, try to reconnect each 5 seconds
          setTimeout(function(){ comet.connect() }, 5000); 
        else
          this.comet.connect();
        this.comet.noerror = false;
      }
    });
    this.ajax.comet = this;
  },

  disconnect: function()
  {
  },

  handleResponse: function(response)
  {	 
	if(!FIRSTREFRESH){
	  var request_kind= response['kind'];
	  var req_text = getRequestText(response);//  response['user']+" zapfte " +response['amount']+"l "+ " und brauchte "+response['zapfdate']+" Minuten"  ;
	  if(request_kind=='refresh' && FIRSTNEW){
    	$('news').firstDescendant().innerHTML = req_text ;
		
	  }else if(request_kind=='new'){
		  FIRSTNEW=true;
		var chEl = $('news').childElements();
		if(chEl.length<NEWSCOUNT){
			$('news').insert({top: '<div>'+req_text+'</div>'});
		}else{
			chEl[chEl.length-1].remove();
			$('news').insert({top: '<div>'+req_text+'</div>'});
			
		}
		
	  }
	}else{
		FIRSTREFRESH = false;
		}
  },
}
var comet = new Comet();
comet.connect();

function createTag(tagname,value,attributesName,attributesValue){
	
	attributesTag =[];
	if(attributesName != undefined){
	for(var i=0;i<attributesName.length;i++){
		attributesTag.push(" "+attributesName[i]+'="' +attributesValue[i]+'"');
	}
	}
	return '<'+tagname + attributesTag.join("") +">" + value + "</"+tagname+">";
}


function getRequestText(response){
		var user = response['user']
		var amount = response['amount'];
		var zapfdate = response['zapfdate'] ;
		var timestamp = response['timestamp'];
		var date = new Date();
	
	
	
	var ret ='<div class="news-drink-box">\
	<div class="news-drink-box-mugshot">\
    	<div style="">\
    		<a href="/user.php?user='+user+'">\
    			<img src="images/avatars/default.png" border="0" height="48" width="48">\
    		</a>\
    	</div>\
    </div>\
    <div class="news-drink-box-details">\
    	<div class="news-drink-box-details-headline">\
        	<a href="/user.php?user='+user+'">'+user+'</a>  zapfte <b>'+amount+'l</b> in <b>'+zapfdate+'s</b>\
        </div>\
        <div class="news-box-info">\
        	Ort: \
            <a href="/place.php">\
            	Harzhaus</a>\
            , Zapfid:\
        	<a href="/id.php?id=112345">\
            	p1L5_1337\
            </a>\
        </div>\
    </div>\
    <div class="news-box-dateline">\
    	<abbr class="timeago" title="'+timestamp+'">\
        	gepostet am '+date.getDate()+ '.'+date.getMonth() +'\.'+ date.getFullYear() + ' um '+date.getHours()+':'+ date.getMinutes()+':' + date.getSeconds() +' Uhr'+'\
        </abbr>\
    </div>\
    <div class="clear">\
    </div>\
</div>';
var attr = [];
	attr[0] = "kauff";
	
	var test = createTag("div","mein WEert",attr,attr);
	
	return ret;
}
