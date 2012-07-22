<?php	echo '
<script type="text/javascript" src="js/ext/bootstrap.js"></script>
<script type="text/javascript" src="js/charts.js"></script>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/comet.js"></script>
<script type="text/javascript" src="js/rfid_comet.js"></script>';
if(md5("zapfmaster2000") ==$_COOKIE['adminmode'] ){
	echo '<script type="text/javascript" src="js/speak/speak.js"></script>';
}
echo ' <div class="news-backgrnd"><div class="newsfeed">
  <div class="newsdiv"> News.<span class="statstext" id="rfid"></span>
	<div class="newscut"> </div>
  </div>
 
  <div id="news">';
  
  
  $limit = 8;
	  
	  
	  $ergebnis =  mysql_query("SELECT * FROM t_news ORDER BY NEWS_ID DESC LIMIT ". $limit) or die("Fehler:".mysql_error($conn)."<br><br>$sql");

	
	  while($row = mysql_fetch_object($ergebnis))
	  {
		  $id =  $row->NEWS_ID;
		  		

		  $type = $row->TYPE;
		  $contents = $row->CONTENTS;
 		  $image_path = $row->IMAGE_PATH;
		  //Type = drawing: userid;amount;start_date;duration
		  
		  if($type==="DRAWING"){
			  $DRAWING_ID = $contents;
			 
			  $entry = getDrawingsById($DRAWING_ID);
			  if($entry != undefined){
				 
			 	$userid = utf8_encode($entry->USER_ID);
				 
			  	$amount = $entry->REAL_AMOUNT;
			  	$startDate = $entry->DATE;
			  	$duration =  $entry->DURATION;
			  
			  $roww =  getUserById($userid,"NAME,IMAGE_PATH");
			 	$user = utf8_encode($roww->NAME);
				$image_path = $roww->IMAGE_PATH;
				$kegId = $entry->KEG_ID;
			  	$kegBrand = getKegById($kegId,"BRAND")->BRAND;
				
			  	echo getNewsDrawing($user,$userid,$amount,$duration,$startDate,$id,$image_path,$kegId,$kegBrand);
			  }
		  }else if($type==="ACHIEVEMENT"){
			 $ACH_ID = $contents; //Group Id in T_Gained_Achievements
			 // $entry = getGainedAchievementById($ACH_ID);
			  
			
			  
			  $ergebns = mysql_query("SELECT GAINED_ACHIEVEMENT_ID,ACHIEVEMENT_ID,USER_ID,DATE FROM t_gained_achievements WHERE GROUP_ID='".$ACH_ID."'") or die("Fehler(main.php,achievements:".mysql_error($conn)."<br><br>$sql");
			  $idUsrArr = array(); // contains usernames
			  $userArr =array(); // contains user ids
			  $j=0;
			  $achievementid; $drawid;
			  while($entry = mysql_fetch_object($ergebns)){
					$userArr[$j] =  $entry->USER_ID;
					
					$idUsrArr[$userArr[$j]] = utf8_encode(getUserById($userArr[$j],"NAME")->NAME);
					$j++;
					
					$achievementid = $entry->ACHIEVEMENT_ID;
			 		$drawid =  $entry->DATE;
			  }
			 
  
			  
			 
			  $startDate =$drawid;
			  $row = getAchievementById($achievementid);
			  
		 
			  $name = $row->NAME;
			  $description = $row->DESCRIPTION;
			  
			  $public = $row->PUBLIC;
			  $achType = $row->TYPE;
		 	 $image_path = $row->IMAGE_PATH;
			  
			  echo getNewsAchievement($userArr,$idUsrArr,$id,$achievementid,utf8_encode($name),utf8_encode($description),$image_path,$public,$achType,$startDate);				
			  	 
			  


		  }else if($type ==="OTHER"){
			  	$headline = $contents;
				
				echo getNewsOthers($id,$image_path,utf8_encode($headline),"");
		  }
		  
	  }


echo '<script type="text/javascript" src="js/scriptaculous/src/effects.js"></script>';
echo '<div class="pagination">';
$erg =  mysql_query("SELECT count(*) as summe FROM t_news") or die("Fehlr:".mysql_error($conn)."<br><br>$sql");
 $pages =-5;
 if($entry = mysql_fetch_object($erg)){
		$actPage =1; 
		$complete = $entry->summe;	
		 $pages = ceil($complete / $limit);
		
	
		 
		 
		 if($pages >2){
			if($actPage <=2){
				echo '<a style="color:green;" href="index.php">'.($actPage).'</a>, ';
				echo '<a href="index.php?p='.($actPage+1).'">'.($actPage+1).'</a>, ';
				
				
			}else if($actPage <$pages-1){
				echo '<a href="index.php">1</a>... ';
				echo '<a href="index.php?p='.($actPage-1).'">'.($actPage-1).'</a>, ';
				echo '<a style="color:green;" href="index.php?p='.($actPage).'">'.($actPage).'</a>, ';
				echo '<a href="index.php?p='.($actPage+1).'">'.($actPage+1).'</a>, ';
			
			}else{
				echo '<a href="index.php">1</a>... ';
				echo '<a href="index.php?p='.($actPage-1).'">'.($actPage-1).'</a>, ';
			echo '<a style="color:green;" href="index.php?p='.($actPage).'">'.($actPage).'</a>, ';
				
			}
			if($pages>2&&$actPage <$pages){
				echo '...<a href="index.php?p='.($pages).'">'.($pages).'</a> ';
				
			}
			
			 
		 }else{
			if($pages==2){
				 echo '<a href="index.php">1</a>, ';
				  echo '<a href="index.php?p=2">2</a> ';
			}else{
					 echo '<a href="index.php">1</a>, ';
			}
			
			
		
			 
		 }
 }

 





echo '</div><div></div></div>
  </div>
</div>
<div class="stats">
  <div class="newsdiv"> Stats.
	<div class="newscut"></div>
	';
	if(md5("zapfmaster2000") ==$_COOKIE['adminmode'] ){
		echo'<div class="statsdiv">
		<a href="index.php?donate"><div class="statsHeadline">Donate!</div></a>
		
		</div>';
		
	}
	
	echo '<div class="statsdiv">
		<div class="statsHeadline">Trinkstatistiken</div>
		<table id="stats-best">
		<tr>
			
  			<td>
				<div id="stats-best-drinker" class="kegtext-drinker"></div>
			</td>
			<td valign="top" align="right" width="100%">
				<div height="100px" width="100px"><img id="drink-stats-image" src="images/stats/barney.png"></div>
			</td>
		</tr></table>
	</div>
	<div class="statsdiv">
		<div class="statsHeadline">Aktuelles Fass</div>
		<div id="keg-chart-gauge" class="keg-chart"></div>
  		<div id="keg-amount" class="kegtext"></div>
	</div>
	
	<div class="statsdiv">
		<div class="statsHeadline">Verlauf</div>
		<div id="keg-chart-line" class="keg-chart-bar"></div>
  		<div id="keg-amount" class="kegtext"></div>
	</div>';
	
	
	if(md5('zapfmaster2000') ===$_COOKIE['adminmode'] ){
	echo '
	<div class="statsdiv">
	<a href="index.php?admin">Admin Control Panel</a>
	
	</div>';
	}
	echo '
  </div>
</div>
<div class="impr">
<div class="statsdiv imprDiv3">
<div class="imprDiv">
	<iframe src="http://www.facebook.com/plugins/like.php?href=www.zapfmaster2000.de"
        scrolling="no" frameborder="0"
        style="border:none; width:450px; height:80px"></iframe></div>
	<div class="imprDiv2">	
		<a href="http://twitter.com/Zapfmaster2000" class="twitter-follow-button" data-lang="de">Follow @Zapfmaster2000</a>
<script src="http://platform.twitter.com/widgets.js" type="text/javascript"></script></div>
</div></div><div id="audio"></div>
';
?>
<!--
<div style="width:100%;position: fixed;bottom:0; background-color:white;">
<div id="specialevents_header" style="width="100%;position:relative;top:0;height:32px;">
	<span>Special Event</span>
	<span style="right:5px;position:absolute">
		<a class="specialevents_close">close</a>
	</span>
</div>
<div id="specialevents_container"  style="width="100%;position:relative">

</div>-->
</div>
