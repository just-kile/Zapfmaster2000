Ext.require(['Ext.chart.*', 'Ext.chart.series.*','Ext.data.*']);
Ext.onReady(function () {

new Ajax.Request('getStats.php',{
			method:'get',
			parameters:{'keg':""},
			onSuccess: function (oXHR) {
				var amountJson = Ext.JSON.decode(oXHR.responseText);
				createGaugeChart(amountJson["SIZE"]);
				createLineChart(amountJson['PROGRESS']);
            	refreshCharts();
       	 	},
        	onFailure: function (oXHR, oJson) {
            
        	}});



this.refreshInterval =setInterval("refreshCharts()",30000);


});
function createLineChart(){
	Ext.create('Ext.chart.Chart',{
			renderTo: 'keg-chart-line',
			width:250,
			height:170,
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
				majorTickSteps :4
              
                
                
            }, {
                type: 'Category',
                position: 'bottom',
                fields: ['time'],
             
            }],
            series: [{
				
                type: 'column',
                axis: 'left',
                highlight: true,
				renderer:function(sprite, record, attributes, index, store){
					if(index<11){
						attributes.fill = '#3AA8CB';
					}else{
							attributes.fill = '#f00';
					}
					
					return attributes;
					
					},
                tips: {
                  trackMouse: true,
                  width: 140,
                  height: 28,
                  renderer: function(storeItem, item) {
                    this.setTitle(storeItem.get('time') + 'Uhr: ' + storeItem.get('amount') + ' Liter');
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
                yField: 'amount'
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
                margin: 7
            }],
        series: [{
                type: 'gauge',
                field: 'data1',
                donut: 80,
                colorSet: ['#3AA8CB', '#ddd'],
				
            }]
    });

}
function refreshCharts(){
	document.getElementById("drink-stats-image").src="images/stats/barney.png";
		new Ajax.Request('getStats.php',{
			method:'get',
			parameters:{'kegamount': "",'best':"",'keg':"",'niceword':"",'drinkspeed':"",'duration':"",'progress':"","promille":""},
			onSuccess: function (oXHR) {
				var amountJson = Ext.JSON.decode(oXHR.responseText);
            	refreshKegCharts(amountJson);
       	 	},
        	onFailure: function (oXHR, oJson) {
            
        	}});
}

	 window.generateDataLine = function(progr){
		
        var data = [];
        for(var i=11;i>=0;i--){
			var amount;
			var iMod24 = mod((progr['last']-i),24);
			if(progr[iMod24]!= null){
				amount = progr[iMod24]
			}else{
				amount = 0;
			}
        	data.push({
            	    time: iMod24,
                	amount:amount
              
           	 });
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
	window.lineStore = Ext.create('Ext.data.JsonStore', {
        fields: ['time', 'amount'],
        data: window.generateDataLine(0,0)
    });
function mod(n,m){
	return ((n%m)+m)%m;	
	
}
function refreshKegCharts(amountJson){
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
	
	document.getElementById('keg-amount').innerHTML = "Fass No.:"+mkBold(kegid) +"</b><br/>Menge im Fass:" + mkBold(actAmount) + " Liter<br/>" + "mit "+ amountJson["NICE_WORD"]+ " "+mkBold(actBrand)+" Bier.<br /> Gesamtliteranzahl: " +mkBold(amountJson["COMPLETE_AMOUNT"])+" Liter<br/>"+ "Zapfspeed: " + mkBold(amountJson["DRINK_SPEED"]) + " Liter/h<br/>Und wird voraussichtlich "+kegTime + " leer sein.";
	var bestInHour = "";
	if(amountJson["BEST_HOUR"]==undefined){
		bestInHour = "Keiner";
	}else{
		bestInHour = amountJson["BEST_HOUR"];
	}
	var best ="";
	if(amountJson['BEST']==undefined){
		best = "Keiner";
	}else{
		best = amountJson["BEST"];
	}
	document.getElementById('stats-best-drinker').innerHTML ="<table><tr><td>All Time:</td><td> "+ mkBold(best)+"</td></tr><tr><td>Trinker der Stunde:</td><td> "+mkBold(bestInHour) + "</td></tr><tr><td>" + "Durch- schnittliche Zapfzeit </td><td>" + mkBold(amountJson['AVERAGE_DRAW_DURATION'])+"s</td></tr><tr><td>Komplette Zapfzeit:</td><td>"+ mkBold(Math.round(amountJson['COMPLETE_DRAW_DURATION']*100/60)/100)+' min</td></tr><tr><td>Ungefährer Blutalkohol: </td><td>'+mkBold(amountJson['AVERAGE_PROMILLE'])+'&permil;</td></tr></table>' ;
	
	
	lineStore.loadData(window.generateDataLine(amountJson['PROGRESS']));
	gaugeStore.loadData(window.generateDataGauge(amountJson["ACT_AMOUNT"]));

}

function mkBold(text){
	
return '<b>'+text+'</b>';	
}
