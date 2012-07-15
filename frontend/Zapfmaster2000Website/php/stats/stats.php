<link rel="stylesheet" href="php/stats/stats.css">

<script type="text/javascript" src="js/ext/bootstrap.js"></script>

<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/scriptaculous/src/effects.js"></script>
<?php
if(isset($_GET["day"])){


echo '<script type="text/javascript" src="php/stats/stats_day.js"></script>';

}else{

echo '<script type="text/javascript" src="php/stats/stats.js"></script>';

}

?>
<div class="containerStats">
<div class="stats linediv">
  <div class="news"> 
	<div class="statsdiv">
		<div class="statsHeadline">Gesamtsummen-Verlauf</div>
		<div id="keg-chart-line" class="keg-chart-bar"></div>
         
	</div>
   <div style="text-align:right;margin-bottom:-10px;margin-top:-10px;"><a id="expandProgress" href="#" onclick="slideInOrOut('expandProgress','keg-chart-line')" style="font-size:10px">hide</a></div>
 </div>
</div>


<div class="stats drinkstats">
<div class="statsdiv">
	
	<div class="statsHeadline">Aktuelles Fass</div>
	<div id="keg-chart-gauge" class="keg-chart"></div>
  	<div id="keg-amount" class="kegtext"></div>
</div>
<div class="statsdiv">
	<div class="statsHeadline">Trinker</div>
		<table>
		<tr>
			
  			<td>
				<div id="stats-best-drinker" class="kegtext-drinker"></div>
			</td>
			<td>
				<div class="statsImage"><img src="images/stats/barney.png" height="100px" width="100px" /></div>
			</td>
		</tr></table>
	</div>
	
</div>
</div>

<div class="stats drinkstats">
  <div class="newsdiv"><center>Allgemeine Stats.</center>
  <div style="font-size:15px;">
  	
     <span style="position:relative;margin-left:15px;">
     
    		<?php
		 if(isset($_GET['day'])) {
			 echo("Day") ;
			 
		 }else{
			 echo("Week");
		 }
		 
		 
	  ?>
    </span>
    <span>
    	<?php
		
       
		if(isset($_GET['day'])  ){
			  echo "<select>";
        
		for($i=5;$i<9;$i++){
			if(isset($_GET['day']) &&$_GET['day']==$i ){
				echo '<option value="'.$i.'" onclick="javascript: refreshCharts('.$i.')" selected="selected">';
			
			}else{
				
				echo '<option value="'.$i.'" onclick="javascript: refreshCharts('.$i.')">';
			
			}
			
			
			echo '0'.$i.'.09.</option>';
			
			
		}
		echo "</select>";
		}
		?>
    		
           
    
    	
        
    </span>
    <span style="position:absolute;right:17px;">
  <span style="margin-left:20px;font-size:12px;">
    	<a href="index.php?stats&day">
	Day </a>
    </span>/
      <span style="position:relative;font-size:12px;">
    	<a href="index.php?stats&week">Week</a>
    </span>
</span>  </div>
	
  <div class="newscut"></div>
 

<div class="statsdiv">
	<div class="statsHeadline">Wochenverlauf - Vergleich</div>
		
				<center><div id="stats-radar"></div></center>
		
	</div>
	 <div class="statsdiv">
	<div class="statsHeadline">Trinkstatistiken</div>
		
		<center><div id="stats-drinks">test</div></center>
		
	
	
  </div>
</div>

</div>
</div>


<div class="stats bestlist">
  <div class="newsdiv">

	<div class="statsdiv">
		<div class="statsHeadline">Bestenliste</div>
		
  		<div id="stats-bestlist" class="stats-bestlist"></div>
        <div id="divStatsBestList" style="margin-top:-2px;display:none;"></div>
        <div style="text-align:right;margin-bottom:0px;margin-top:-5px;"><a id="expandBestList" href="#" onclick="slideInOrOut('expandBestList','divStatsBestList')" style="font-size:10px">expand</a></div>
	</div>
    <div class="statsdiv">
	
       
		
        <center><div id="keg-chart-pie" class="keg-chart-bar"></div></center>
	</div>
	

</div>
<div class="newsdiv">

	<div class="statsdiv">
		<div class="statsHeadline">Achievements Bestenliste</div>
  		<div id="stats-mostachievements" class="stats-bestlist"></div>
        <div id="divStatsAchievementList" style="margin-top:-2px;display:none;"></div>
        <div style="text-align:right;margin-bottom:0px;margin-top:-5px;"><a href="#" id="expandAchievements" onclick="slideInOrOut('expandAchievements','divStatsAchievementList')" style="font-size:10px">expand</a></div>

	</div>
<!--	<div class="statsdiv">
	
       
		
        <center><div id="keg-chart-pie-achievement" class="keg-chart-bar"></div></center>
	</div>
-->
</div>
</div>

</div>


