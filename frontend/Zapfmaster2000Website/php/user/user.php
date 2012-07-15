<link rel="stylesheet" href="php/user/user.css">

<script type="text/javascript" src="js/ext/bootstrap.js"></script>

<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/scriptaculous/src/effects.js"></script>
<?php 
$row = getUserById($_GET['user'],"NAME,IMAGE_PATH");
$name = $row->NAME;
$imagepath = $row->IMAGE_PATH;
echo '<script type="text/javascript">';
echo 'this.GET_USER_ID='.$_GET['user'].';';
echo 'this.GET_NAME="'.$name.'";';
echo 'this.GET_IMAGE_PATH="'.$imagepath.'";';


echo '</script>';
?>
<script type="text/javascript" src="php/user/user.js"></script>


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
	<div class="statsHeadline">Trinker</div>
		<table>
		<tr>
			
  			<td >
				<div id="stats-best-drinker" class="kegtext-drinker"  width="100%"></div>
			</td>
			<td valign="top" align="right">
				<div class="statsImage"  height="100px"><img src="<?php echo $imagepath; ?>"/></div>
			</td>
		</tr></table>
	</div>
	<div class="statsdiv">
	
		<div class="statsHeadline">Trinkverteilung</div>
		<center><div id="keg-chart-gauge" class="keg-chart"></div></center>
  		<div id="keg-amount" class="kegtext"></div>
	</div>
</div>
</div>

<div class="stats drinkstats">
  <div class="newsdiv"><center><?php echo $name; ?></center>
  <div class="newscut"></div>
 

<div class="statsdiv">
	<div class="statsHeadline">Wochenverlauf - Vergleich</div>
		
				<center><div id="stats-radar"></div></center>
		
</div>
	 <div class="statsdiv">
	<div class="statsHeadline">Trinkstatistiken</div>
		
		<center><div id="stats-drinks">Noch nichts getrunken!</div></center>
		
	
	
  </div>
</div>

</div>
</div>


<div class="stats bestlist">
 
<div class="newsdiv">

	<div class="statsdiv">
		<div class="statsHeadline">Achievementliste</div>
  		<div id="stats-mostachievements" class="stats-bestlist"></div>
        <div id="divStatsAchievementList" style="margin-top:-2px;display:none;"></div>
        <div style="text-align:right;margin-bottom:0px;margin-top:-5px;"><a id="expandAchievements" onclick="slideInOrOut('expandAchievements','divStatsAchievementList')" style="cursor:pointer;font-size:10px">expand</a></div>

	</div>
<!--	<div class="statsdiv">
	
       
		
        <center><div id="keg-chart-pie-achievement" class="keg-chart-bar"></div></center>
	</div>
-->
</div>
</div>

</div>


