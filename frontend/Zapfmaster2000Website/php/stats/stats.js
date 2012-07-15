
Ext.require(['Ext.chart.*', 'Ext.chart.series.*','Ext.data.*']);
Ext.onReady(function () {
INTERVALL_NUMBERS = 12;
new Ajax.Request('getStats.php',{
			method:'get',
			parameters:{"bestlist":"",'kegamount': "",'best':"",'keg':"",'niceword':"",'drinkspeed':"",'duration':"",'progressweek':"","promille":"","mostachievements":"","drinkstats":""},
			onSuccess: function (oXHR) {
		
			var amountJson = Ext.JSON.decode(oXHR.responseText);
			createGaugeChart(amountJson["SIZE"]);
			if(amountJson['BESTLIST'][0] == undefined){
		document.getElementById('stats-drinks').innerHTML = "Noch keine Trinkdaten, deswegen keine Statistiken";
				gaugeStore.loadData(window.generateDataGauge(amountJson["ACT_AMOUNT"]));
					var actAmount = amountJson["ACT_AMOUNT"];
	var actBrand = amountJson["BRAND"];
	var startDate = amountJson["START_DATE"];
	var kegid = amountJson["KEG_ID"];
	var kegTime = "";
	var tempTime = Math.round(amountJson["ACT_AMOUNT"] / amountJson["DRINK_SPEED"] * 100)/100 ;
	if(tempTime == Number.POSITIVE_INFINITY){
		kegTime = mkBold("NIEMALS");
	}else{
		kegTime ="in "+ mkBold(tempTime)+ " h";
	}
	
	document.getElementById('keg-amount').innerHTML = "<table><tr><td>Fass Id.:</td><td>"+mkBold(kegid) +"</td></tr><tr><td>Fassanzahl:</td><td>"+mkBold(amountJson["KEG_NUMBERS"]) +"</td></tr><tr><td>Menge im Fass:</td><td>" + mkBold(actAmount) + " l</td></tr><tr><td>" + "Marke "+  "</td><td> "+mkBold(actBrand)+"</td></tr><tr> <td>Gesamtliteranzahl:</td><td> " +mkBold(amountJson["COMPLETE_AMOUNT"])+" l</td></tr><tr><td>"+ "Zapfspeed: </td><td>" + mkBold(amountJson["DRINK_SPEED"]) + " l/h</td></tr><tr><td>Zeit bis Fass verbraucht: </td><td>"+kegTime + " </td></tr></table>";
	

			}else{
				window.pieStore = Ext.create('Ext.data.JsonStore', {
        			fields: ['name', 'amount'],
        			data: window.generateDataPie(amountJson)
    			});
				window.pieStoreAchievement = Ext.create('Ext.data.JsonStore', {
        			fields: ['name', 'amount'],
        			data: window.generateDataPieAchievement(amountJson)
    			});
				window.radarStore =  Ext.create('Ext.data.JsonStore', {
        			fields: ['name', 'data1','data2','data3'],
        			data: window.generateDataRadar(amountJson)
    			});
				window.lineStore = Ext.create('Ext.data.JsonStore', {
        			fields: ['time', 'amount'],
        			data: generateDataLine(amountJson)
    			});

				createPieChart();
				
				createLineChart(amountJson['PROGRESS']);
            			createRadarChart(amountJson);
				
				refreshChartCol1(amountJson);
				
				refreshBestList(amountJson);
				refreshMostAchievements(amountJson);
            	
		}
       	 	},
        	onFailure: function (oXHR, oJson) {
            
        	}});



setInterval("refreshCharts()",30000);


});
function createPieChart(){
	
	Ext.create('Ext.chart.Chart', {
        renderTo: 'keg-chart-pie',
        width: 250,
        height: 180,
		
        
		xtype: 'chart',
		
		animate: true,
		shadow: true,
		store: window.pieStore,
		series: [{
			type: 'pie',
			animate: true,
			angleField: 'amount', //bind angle span to visits
			//lengthField: 'amount', //bind pie slice length to views
			highlight: {
			  segment: {
				margin: 20
			  }
			},
			label: {
				field: 'name',   //bind label text to name
				display: 'rotate', //rotate labels (also middle, out).
				font: '12px Helvetica',
				contrast: true
			},                                
			style: {
				'stroke-width': 1,
				'stroke': '#fff',
				
				
			},
			tips: {
                  trackMouse: true,
                  width: 140,
                  height: 28,
                  renderer: function(storeItem, item) {
                    this.setTitle(storeItem.get('amount') + ' Liter');
                  }
             },
			//add renderer
			renderer: function(sprite, record, attr, index, store) {
				var value = index % 9;
				var color = [ "#94ae0a", "#115fa6","#a61120", "#ff8809", "#ffd13e", "#a61187", "#24ad9a", "#7c7474", "#a66111"][value];
				return Ext.apply(attr, {
					fill: color
				});
			}
            }]
    });
	/*Ext.create('Ext.chart.Chart', {
        renderTo: 'keg-chart-pie-achievement',
        width: 250,
        height: 200,
		store: window.pieStoreAchievement,
        
		xtype: 'chart',
		
		animate: true,
		shadow: true,
	
		series: [{
			type: 'pie',
			animate: true,
			angleField: 'amount', //bind angle span to visits
			lengthField: 'amount', //bind pie slice length to views
			highlight: {
			  segment: {
				margin: 10
			  }
			},
			label: {
				field: 'name',   //bind label text to name
				display: 'rotate', //rotate labels (also middle, out).
				font: '12px Helvetica',
				contrast: true
			},                                
			style: {
				'stroke-width': 1,
				'stroke': '#fff',
				
				
			},
			tips: {
                  trackMouse: true,
                  width: 140,
                  height: 28,
                  renderer: function(storeItem, item) {
                    this.setTitle(storeItem.get('amount') + ' Achievement(s)');
                  }
             },
			//add renderer
			renderer: function(sprite, record, attr, index, store) {
				var value = index % 9;
				var color = [ "#94ae0a", "#115fa6","#a61120", "#ff8809", "#ffd13e", "#a61187", "#24ad9a", "#7c7474", "#a66111"][value];
				return Ext.apply(attr, {
					fill: color
				});
			}
            }]
    });
*/
}

function createLineChart(){
	Ext.create('Ext.chart.Chart',{
			renderTo: 'keg-chart-line',
			width:870,
			height:140,
			store:window.lineStore,
			
            animate: true,
            shadow: true,
            
            axes: [{
                type: 'Numeric',
                position: 'left',
				grid:true,
                fields: ['amount'],
              	minimum:0,
				  label: {
                    renderer: Ext.util.Format.numberRenderer('0.0')
                },
				majorTickSteps :3
              
                
                
            }, {
                type: 'Numeric',
                position: 'bottom',
                fields: ['time'],
				  label: {
                    renderer: function(sprite, record, attributes, index, store){
						
					  var time = sprite;
					  var timeFloor =  Math.floor(time)
					  
					  var timeZone =Math.round( (time -timeFloor)*24);
					  					  
                   	  
					  	return timeZone;
					}
					// Ext.util.Format.numberRenderer('0.0')
                },
				majorTickSteps :40,
				grid: {
        			odd:{
							 opacity: 0.2,
          					 fill: '#ddd',
           					 stroke: '#bbb',
            				'stroke-width': 1

					}
				}

             
            }],
            series: [{
				
                type: 'line',
               
				axis: 'left',
                highlight: true,
				fill: true,

                tips: {
                  trackMouse: true,
                  width: 140,
                  height: 28,
                  renderer: function(storeItem, item) {
					  var time = storeItem.get('time');
					  var timeFloor =  Math.floor(time)
					  var timeZone =Math.round( (time -timeFloor)*24);
					  
                   	  this.setTitle(timeFloor + '. Tag, ' + timeZone+" Uhr: "+Math.round(storeItem.get('amount')*100)/100 + ' Liter ');
                  }
                },
               /* label: {
                  display: 'insideEnd',
                  'text-anchor': 'middle',
                    field: 'amount',
                    renderer: Ext.util.Format.numberRenderer('0.00'),
                    orientation: 'vertical',
                    color: '#333'
                },*/
                xField: 'time',
                yField: 'amount',
				smooth: true,
				markerConfig: {
                    type: 'circle',
                    size:2 ,
                    radius: 2,
                    'stroke-width': 0
                }
            }]
	});
		
	
	}
function createGaugeChart(kegsize){
	
	Ext.create('Ext.chart.Chart', {
        renderTo: 'keg-chart-gauge',
        width: 200,
        height: 133,
		theme:'Blue',
        animate: true,
        store: window.gaugeStore,
        shadow: true,
        theme: 'Category1',
        legend: {
            position: 'right'
        },
		insetPadding: 25,
        flex: 1,
		
        axes: [{
                type: 'gauge',
                position: 'gauge',
                minimum: 0,
                maximum: kegsize,
                steps: 10,
                margin: 7,
            }],
        series: [{
                type: 'gauge',
                field: 'data1',
                donut: 80,
                colorSet: ['#3AA8CB', '#ddd'],
				
            }]
    });

}

function createRadarChart(){
	Ext.create('Ext.chart.Chart', {
        renderTo: 'stats-radar',
        width: 200,
        height: 205,
	
		xtype: 'chart',
	  
		theme: 'Category2',
		insetPadding: 25,
		animate: true,
		store: window.radarStore,
		
		axes: [{
			type: 'Radial',
			position: 'radial',
			label: {
                    display: false
                },
				majorTickSteps :3
			
		}],
		series: [{
			showInLegend: true,
			type: 'radar',
			
			xfield:'time',
			yField: 'Heute',
			showMarkers: true,
			style: {
				'stroke-width': 2,
            fill: 'none',

				//opacity: 0.4
			}
		},{
			showInLegend: true,
			type: 'radar',
			xField: 'time',
			yField: 'Gestern',
			style: {
				//opacity: 0.4
				style: {
                    'stroke-width': 2,
                    fill: 'none'
                }
			}
		},{
			showInLegend: true,
			type: 'radar',
			xField: 'time',
			yField: 'Vorgestern',
			style: {
				//opacity: 0.4
				style: {
                    'stroke-width': 2,
                    fill: 'none'
                }
			}
		}]
    });
	
	
	
}
function refreshCharts(){
	
		new Ajax.Request('getStats.php',{
			method:'get',
			parameters:{"bestlist":"",'kegamount': "",'best':"",'keg':"",'niceword':"",'drinkspeed':"",'duration':"",'progressweek':"","promille":"","mostachievements":"","drinkstats":""},
			onSuccess: function (oXHR) {
				var amountJson = Ext.JSON.decode(oXHR.responseText);
            	refreshKegCharts(amountJson);
       	 	},
        	onFailure: function (oXHR, oJson) {
            
        	}});
}

	
 window.generateDataPie = function(json){
        var data = json["BESTLIST"];
        var retData  = [];
		
		for(var i=0;i<data.length;i++){
			var user = data[i];
            retData.push({
                name: user["NAME"],
                amount:user["AMOUNT"]
            });
		
		}
        return retData;
    };

	 window.generateDataPieAchievement = function(json){
        var data = json["MOST_ACHIEVEMENTS"];
        var retData  = [];
		
		for(var i=0;i<data.length;i++){
			var user = data[i];
            retData.push({
                name: user["NAME"],
                amount:user["ACHIEVEMENT_COUNT"]
            });
		
		}
        return retData;
    };
	window.generateDataRadar= function(json){
      var progressweek = json["PROGRESSWEEK"];
		var undef = json["PROGRESSWEEK_LAST"];
        var data = [];
		
		 for(var j = 0;j<INTERVALL_NUMBERS;j++){
			
			 var amount = new Array();
        	for(var i = undef[0];i>(undef[0]-3) ;i--){//progressweek.length
			
        	 var test  =(progressweek[i]==undefined) ? new Array():progressweek[i];
			
				
				
				
				if(test[j] == undefined){
					amount[i] = 0;	
					
				}else{
					amount[i] = Math.round(test[j]*10) /10;
				}
				
				
				
			}
			data.push({
            	    time: j*2,
                	Heute:amount[undef[0]],
					Gestern:amount[undef[0]-1],
					Vorgestern:amount[undef[0]-2]
              
           	 	});
		}
        return data;
    
    };

function refreshKegCharts(amountJson){
	window.pieStore.loadData(window.generateDataPie(amountJson));
	refreshBestList(amountJson);
refreshChartCol1(amountJson);
refreshMostAchievements(amountJson);
lineStore.loadData(window.generateDataLine(amountJson));
radarStore.loadData(window.generateDataRadar(amountJson));
}
function refreshChartCol1(amountJson){
	var actAmount = amountJson["ACT_AMOUNT"];
	var actBrand = amountJson["BRAND"];
	var startDate = amountJson["START_DATE"];
	var kegid = amountJson["KEG_ID"];
	var kegTime = "";
	var tempTime = Math.round(amountJson["ACT_AMOUNT"] / amountJson["DRINK_SPEED"] * 100)/100 ;
	if(tempTime == Number.POSITIVE_INFINITY){
		kegTime = mkBold("NIEMALS");
	}else{
		kegTime ="in "+ mkBold(tempTime)+ " h";
	}
	
	document.getElementById('keg-amount').innerHTML = "<table><tr><td>Fass Id.:</td><td>"+mkBold(kegid) +"</td></tr><tr><td>Fassanzahl:</td><td>"+mkBold(amountJson["KEG_NUMBERS"]) +"</td></tr><tr><td>Menge im Fass:</td><td>" + mkBold(actAmount) + " l</td></tr><tr><td>" + "Marke "+  "</td><td> "+mkBold(actBrand)+"</td></tr><tr> <td>Gesamtliteranzahl:</td><td> " +mkBold(amountJson["COMPLETE_AMOUNT"])+" l</td></tr><tr><td>"+ "Zapfspeed: </td><td>" + mkBold(amountJson["DRINK_SPEED"]) + " l/h</td></tr><tr><td>Zeit bis Fass verbraucht: </td><td>"+kegTime + " </td></tr></table>";
	var bestInHour = "";
	if(amountJson["BEST_HOUR"]==undefined){
		bestInHour = "Keiner";
	}else{
		bestInHour = amountJson["BEST_HOUR"];
		bestInHourUserId = amountJson["BEST_HOUR_USER_ID"];
	}
	var best ="";
	if(amountJson['BEST']==undefined){
		best = "Keiner";
		bestuserid = "";
	}else{
		best = amountJson["BEST"];
		bestuserid = amountJson["BEST_USER_ID"];
	}
	document.getElementById('stats-best-drinker').innerHTML ="<table><tr><td>All Time:</td><td><a href=\"index.php?user="+bestuserid+"\""+ mkBold(best)+"</a></td></tr><tr><td>Trinker der Stunde:</td><td><a href=\"index.php?user="+bestuserid+"\"> "+bestInHour + "</a></td></tr><tr><td>Schnellster Zapfer</td><td><a href=\"index.php?user="+amountJson['FASTEST_DRINKER']['USER_ID']+"\"> "+amountJson['FASTEST_DRINKER']['NAME']+"</a> ("+mkBold(amountJson['FASTEST_DRINKER']['DURATION'])+" l/h)</td></tr><tr><td>Lahmster Zapfer</td><td><a href=\"index.php?user="+amountJson['SLOWEST_DRINKER']['USER_ID']+"\"> "+amountJson['SLOWEST_DRINKER']['NAME']+"</a> ("+mkBold(amountJson['SLOWEST_DRINKER']['DURATION'])+" l/h)</td></tr><tr><td>Treuester Kunde</td><td><a href=\"index.php?user="+amountJson['MOST_LOYAL_USER']['USER_ID']+"\">"+amountJson['MOST_LOYAL_USER']['NAME']+"</a> ("+mkBold(amountJson['MOST_LOYAL_USER']['COUNT'])+" min)</td></tr><tr><td>Ungefährer Blutalkohol: </td><td>"+mkBold(amountJson['AVERAGE_PROMILLE'])+'&permil;</td></tr>' ;
	document.getElementById('stats-drinks').innerHTML ="<table><tr><td>" + "Durchschnittliche Zapfzeit </td><td>" + mkBold(amountJson['AVERAGE_DRAW_DURATION'])+"s</td></tr><tr><td>Komplette Zapfzeit:</td><td>"+ mkBold(Math.round(amountJson['COMPLETE_DRAW_DURATION']*100/60)/100)+' min</td></tr><tr><td>' + "Längste einmalige Zapfzeit </td><td>" + mkBold(amountJson['LONGEST_DURATION'])+'s</td></tr><tr><td>' + "Größte auf einmal gezapfte Menge</td><td>" + mkBold(amountJson['MOST_AMOUNT'])+'l</td></tr><tr><td>' + "Anzahl der Zapfvorgänge</td><td>" + mkBold(amountJson['OPERATIONS'])+'</td></tr><tr><td>' + "Most Activity<sup>&reg;</sup> Hour</td><td>" + mkBold(amountJson['MOST_ACTIVITY_HOUR'])+' Uhr</td></tr><tr><td>' + amountJson["PI"]["POSITION"]+". Nachkommastelle von pi</td><td>" + mkBold(amountJson['PI']["VALUE"])+'</td></tr>' ;
	
	
	//lineStore.loadData(window.generateDataLine(amountJson));
	gaugeStore.loadData(window.generateDataGauge(amountJson["ACT_AMOUNT"]));

}

function mkBold(text){
	
return '<b>'+text+'</b>';	
}
function GetRandom( min, max ) {
	if( min > max ) {
		return( -1 );
	}
	if( min == max ) {
		return( min );
	}
 
        return( min + parseInt( Math.random() * ( max-min+1 ) ) );
}


function refreshBestList(json){
	var data = json["BESTLIST"];
	var list = [];
	var list2 = [];
	for(var i=0;i<data.length;i++){
		var colo= i%2;
		if(i >= 5){
			
			list2.push('<tr class="bestlist_'+colo+'">'+"<td width='25px'>"+(i+1)+".</td>"+'<td width="150px"><a href="index.php?user='+data[i]["USER_ID"]+'">'+data[i]["NAME"]+"</a></td><td>"+data[i]["AMOUNT"]+" L</td></tr>");
		
		
		}else{
			list.push('<tr class="bestlist_'+colo+'">'+"<td width='25px'>"+(i+1)+".</td>"+'<td width="150px"><a href="index.php?user='+data[i]["USER_ID"]+'">'+data[i]["NAME"]+"</a></td><td>"+data[i]["AMOUNT"]+" L</td></tr>");
		
		}
		
	}
			

	document.getElementById("stats-bestlist").innerHTML ='<table width="100%">'+list.join("")+"</table>";
	document.getElementById("divStatsBestList").innerHTML ='<table width="100%">'+list2.join("")+"</table>" ;
	
}
function refreshMostAchievements(json){
	var data = json["MOST_ACHIEVEMENTS"];
	var list = [];
	var list2 = [];
	for(var i=0;i<data.length;i++){
		var colo= i%2;
		if(i >= 5){
			
			list2.push('<tr class="bestlist_'+colo+'">'+"<td width='25px'>"+(i+1)+".</td>"+'<td width="100px"><a href="index.php?user='+data[i]["USER_ID"]+'">'+data[i]["NAME"]+"</a></td><td>"+data[i]["ACHIEVEMENT_COUNT"]+" Achievements</td></tr>");
		
		
		}else{
			list.push('<tr class="bestlist_'+colo+'">'+"<td width='25px'>"+(i+1)+".</td>"+'<td width="100px"><a href="index.php?user='+data[i]["USER_ID"]+'">'+data[i]["NAME"]+"</a></td><td>"+data[i]["ACHIEVEMENT_COUNT"]+" Achievements</td></tr>");
		
		}
		
	}
			

			
	
	
	document.getElementById("stats-mostachievements").innerHTML = '<table width="100%">'+list.join("")+"</table>" ;
	document.getElementById("divStatsAchievementList").innerHTML = '<table width="100%">'+list2.join("")+"</table>";
		
	window.pieStoreAchievement.loadData(window.generateDataPieAchievement(json));
	
	
	
} 
window.generateDataLine = function(json){
		
		var progressweek = json["PROGRESSWEEK"];
		var undef = json["PROGRESSWEEK_LAST"];
        var data = [];
		
		 
        for(var i = 0;i<7 ;i++){//progressweek.length
			var test  =(progressweek[i]==undefined) ? new Array():progressweek[i];
        	
			for(var j = 0;j<INTERVALL_NUMBERS;j++){
				
				var time = j/INTERVALL_NUMBERS;
				var amount=0 ;
				if(test[j] == undefined){
					
					
					if(i>undef[0] || (i==undef[0] && j>undef[1])){
						amount = undefined;
					}else{
				 	 	amount = 0;
					}
				}else{
					amount = Math.round(test[j]*10) /10;
				}
				
				
				data.push({
            	    time: i+time,
					
                	amount:amount
              
           	 });
			}
			
		}
        return data;
    };
	  window.generateDataGauge = function(amount){
        var data = [];
            
            data.push({
                name: "amount",
                data1:amount
              
            });
        
        return data;
    };

window.gaugeStore = Ext.create('Ext.data.JsonStore', {
        fields: ['name', 'data1'],
        data: window.generateDataGauge(0)
    });
function mod(n,m){
	return ((n%m)+m)%m;	
	
}
var elementVisible = new Array();
elementVisible["expandProgress"] = true;
function slideInOrOut(linkid,slideid){
	
	
		
		
	if(elementVisible[linkid]){
		new Effect.SlideUp(slideid, {duration:0.5});
		elementVisible[linkid] = false;
		document.getElementById(linkid).innerHTML = "expand";
	}else{
		
		new Effect.SlideDown(slideid, {duration:0.5}); 
		elementVisible[linkid] = true;
		document.getElementById(linkid).innerHTML = "hide";
	}

}

