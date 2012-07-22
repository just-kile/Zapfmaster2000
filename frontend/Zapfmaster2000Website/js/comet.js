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
	  var req_text = getRequestText(response);// response['user']+" zapfte "
												// +response['amount']+"l "+ "
												// und brauchte
												// "+response['zapfdate']+"
												// Minuten" ;
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
	return '<'+tagname + attributesTag.join("") +">" + value + '</'+tagname+'>';
}


function getRequestText(response){
	var type = response['type'];
	
	
	if(type == 'drawing'){
		var user = response['user'];
		var userid = response['userid'];
		var amount = response['amount'];
		var duration = response['duration'] ;
		
		var timestamp = response['date'];
		
		
		var brand = response['BRAND'];
		var kegid = response['KEG_ID'];
		var imagePath  = response['IMAGE_PATH'];
		var date = new Date();
	
	
	
	/*
	 * var ret ='<div class="news-drink-box">\ <div
	 * class="news-drink-box-mugshot">\ <div style="">\ <a
	 * href="/user.php?user='+user+'">\ <img src="images/avatars/default.png"
	 * border="0" height="48" width="48">\ </a>\ </div>\ </div>\ <div
	 * class="news-drink-box-details">\ <div
	 * class="news-drink-box-details-headline">\ <a
	 * href="/user.php?user='+user+'">'+user+'</a> zapfte <b>'+amount+'l</b>
	 * in <b>'+duration+'s</b>\ </div>\ <div class="news-box-info">\ Ort: \ <a
	 * href="/place.php">\ Harzhaus</a>\ , Zapfid:\ <a
	 * href="/id.php?id=112345">\ p1L5_1337\ </a>\ </div>\ </div>\ <div
	 * class="news-box-dateline">\ <abbr class="timeago" title="'+timestamp+'">\
	 * gepostet am '+date.getDate()+ '.'+date.getMonth() +'\.'+
	 * date.getFullYear() + ' um '+date.getHours()+':'+ date.getMinutes()+':' +
	 * date.getSeconds() +' Uhr'+'\ </abbr>\ </div>\ <div class="clear">\
	 * </div>\ </div>';
	 */
var attr = [];
	attr[0] = "kauff";
	
	// var test = createTag("div","mein WEert",attr,attr);
	
	return getNewsDrawing(user,userid,amount,duration,timestamp,imagePath,kegid,brand);
	
	}else if(type == 'achievement'){
	var timestamp = response['date'];
		
		return getNewsAchievement(response['user'],response['userid'],response['ACHIEVEMENT_ID'],response['ACHIEVEMENT_NAME'],response['ACHIEVEMENT_DESCRIPTION'],response['ACHIEVEMENT_IMAGE_PATH'],response['ACHIEVEMENT_PUBLIC'],response['ACHIEVEMENT_TYPE'],timestamp);
	}else if(type == 'other'){
		return getNewsOthers(response['text'],response['imagepath']);	
	}
}

function getNewsDrawing(user,userid,amount,zapfdate,timestamp,imagepath,kegId,kegBrand){
	var imgTag;
	
	if(imagepath !=""){
		imgTag = createTag('img',"",new Array("src","border","height","width"),new Array(imagepath,0,48,48));
	}else{
		imgTag = createTag('img',"",new Array("src","border","height","width"),new Array("images/avatars/"+"default"+".png",0,48,48));
	
		}
	var ahrefImg = createTag('a',imgTag,new Array("href"),new Array("index.php?user="+userid));
	var divImg = createTag('div',ahrefImg ,null,null);
	var divImgComplete = createTag('div',divImg ,new Array("class"),new Array("news-drink-box-mugshot"));
	var headlineE = createTag('a',user,new Array("href"),new Array('index.php?user='+userid));
	var headline =""+ headlineE + " zapfte "+mkBold(Math.round(amount*100)/100) +"l in <b>"+zapfdate+ "s</b>";
	
	var divHeadline = createTag('div',headline,new Array("class"),new Array("news-drink-box-details-headline"));
	var ahrefPlace = createTag('a',"Harzhaus",new Array("href"),new Array("index.php?place"));
	var ahrefId = createTag('a',id,new Array("href"),new Array("index.php?id="+id));

	var ahrefKegId = createTag('a',kegId,new Array('href'),new Array("index.php?keg="+kegId));
		
	var divNewsBoxInfo = createTag('div',"Ort: "+ahrefPlace+" Fass: "+ahrefKegId+" der Marke "+kegBrand ,new Array("class"),new Array("news-box-info"));
	
	var divNewsBoxDrinkDetailsComplete =  createTag('div',divHeadline+divNewsBoxInfo ,new Array("class"),new Array("news-drink-box-details"));
	var time = new Date();
	
	var abbrDate = createTag('abbr',"gezapft am "+timestamp,new Array("class","title"),new Array("timeago",timestamp));
	var divDateComplete  = createTag('div',abbrDate ,new Array("class"),new Array("news-box-dateline"));
	
	
	
	var divTextComplete = createTag('div',divImgComplete+divNewsBoxDrinkDetailsComplete+divDateComplete,new Array("class"),new Array("news-drink-box"));
	
	
	return divTextComplete ;
	
	
}
function getNewsAchievement(userName,userid,achievementid,name,description,imagepath,public,type,timestamp){
	var idUsrArr =  userName.split(";");
	var userArr = userid.split(";");
	var count =userArr.length;
	var user = "";
	var reaches = "";
	if(count>1)
	{
		for(var i=0;i<count;i++){
			var temp = "";
			if(i==0){
				temp = idUsrArr[i];
				user = createTag('a',temp,new Array("href"),new Array('index.php?user='+userArr[i]));
			}else if(i == count-1){
				temp = idUsrArr[i];
				user =user+" und "+createTag('a',temp,new Array("href"),new Array('index.php?user='+userArr[i]));
			}else{
				temp = idUsrArr[i];
				user =user+", "+createTag('a',temp,new Array("href"),new Array('index.php?user='+userArr[i]));
			}
			
		}
		reaches = " erreichten";
	 }else{
			user = createTag('a',idUsrArr[0],new Array("href"),new Array('index.php?user='+userArr[0]));
			reaches = " erreichte";
			
	}
	
	
	
	var imgTag = createTag('img',"",new Array("src","border","height","width"),new Array(imagepath,0,48,48));
	var ahrefImg = createTag('a',imgTag,new Array("href"),new Array("index.php?achievement="+achievementid));
	var divImg = createTag('div',ahrefImg ,null,null);
	var divImgComplete = createTag('div',divImg ,new Array("class"),new Array("news-drink-box-mugshot"));
	if(public==1){
		public = ' &ouml;ffentlichen ';
	}else{
		public = ' geheimen ';
	}
	var ahrefAchievementLink = '<br/><b>'+createTag('a',name,new Array('href'),new Array('index.php?achievement='+achievementid))+'</b>';
	var headline = createTag('a',user,new Array("href"),new Array('index.php?user='+userid))+ reaches+' den '+public+' Erfolg '+ahrefAchievementLink;
	var divHeadline = createTag('div',headline,new Array("class"),new Array("news-drink-box-details-headline"));
	var ahrefPlace = createTag('a',"Harzhaus",new Array("href"),new Array("index.php?place"));
	
	var divNewsBoxInfo = createTag('div',"Ort: "+ahrefPlace ,new Array("class"),new Array("news-box-info"));
	var divNewsBoxDrinkDetailsComplete =  createTag('div',divHeadline+divNewsBoxInfo ,new Array("class"),new Array("news-drink-box-details"));
	
	var abbrDate = createTag('abbr',"erhalten am "+timestamp+' Uhr',new Array("class","title"),new Array("timeago",timestamp));
	var divDateComplete  = createTag('div',abbrDate ,new Array("class"),new Array("news-box-dateline"));
	
	
	
	var divTextComplete = createTag('div',divImgComplete+divNewsBoxDrinkDetailsComplete+divDateComplete,new Array("class"),new Array("news-drink-box"));
	
	
	return divTextComplete ;
}

function getNewsOthers(text,imagepath){
	
	var imgTag = createTag('img',"",new Array("src","border","height","width"),new Array(imagepath,0,48,48));
	var divImg = createTag('div',imgTag ,null,null);
	var divImgComplete = createTag('div',divImg ,new Array("class"),new Array("news-drink-box-mugshot"));
	
	
	var divHeadline = createTag('div',text,new Array("class"),new Array("news-drink-box-details-headline"));
	var ahrefPlace = createTag('a',"Harzhaus",new Array("href"),new Array("index.php?place"));
	
	var divNewsBoxInfo = createTag('div',"Ort: "+ahrefPlace  ,new Array("class"),new Array("news-box-info"));
	var divNewsBoxDrinkDetailsComplete =  createTag('div',divHeadline+divNewsBoxInfo ,new Array("class"),new Array("news-drink-box-details"));
	
	// abbrDate = createTag('abbr',"gezapft am "+timestamp+' Uhr',new
	// Array("class","title"),new Array("timeago",timestamp));
	// divDateComplete = createTag('div',abbrDate ,new Array("class"),new
	// Array("news-box-dateline"));
	
	
	
	var divTextComplete = createTag('div',divImgComplete+divNewsBoxDrinkDetailsComplete,new Array("class"),new Array("news-drink-box"));
	
	
	return divTextComplete ;
}

