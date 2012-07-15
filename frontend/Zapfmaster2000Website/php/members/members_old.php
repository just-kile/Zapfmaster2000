
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/scriptaculous/src/effects.js"></script>

<?php

echo '<link rel="stylesheet" href="php/members/members.css">';
echo '<div class="newsfeed">
  <div class="newsdiv">Members. <div class="newscut"> </div>';
  $ach =  mysql_query("SELECT COUNT(USER_ID) as coun FROM t_users") or die("Fehler:".mysql_error($conn)."<br><br>$sql");
	
  if($rowAch = mysql_fetch_object($ach)){
		echo 'Users:'. ($rowAch->coun-1); 
		
	}
  echo'
	
	
  </div>
  <div class="news"> ';
  $achievementList = array();
  
	
  $ach =  mysql_query("SELECT * FROM t_achievements") or die("Fehler:".mysql_error($conn)."<br><br>$sql");
	
  while($rowAch = mysql_fetch_object($ach)){
		 $achievementList[$rowAch->ACHIEVEMENT_ID] = array("IMAGE_PATH" => $rowAch->IMAGE_PATH,"NAME" => utf8_encode($rowAch->NAME),"DESCRIPTION"=>utf8_encode($rowAch->DESCRIPTION)); 
		
	}
	
 $ergebnis =  mysql_query("SELECT * FROM t_users ORDER BY NAME") or die("Fehler:".mysql_error($conn)."<br><br>$sql");
 while($row = mysql_fetch_object($ergebnis)){
	$ach =  mysql_query("SELECT ACHIEVEMENT_ID FROM t_gained_achievements WHERE USER_ID=".$row->USER_ID) or die("Fehler:".mysql_error($conn)."<br><br>$sql");
	$achArr = array();
	$i = 0;
	while($rowAch = mysql_fetch_object($ach)){
		 $achArr[$i] = $rowAch->ACHIEVEMENT_ID; 
		 $i++;
	}
	
	if($row->USER_ID != 1 ){
		getUserHTML( $row->NAME,$row->USER_ID,$row->IMAGE_PATH, $achArr,$achievementList);
 	
	}
	}
echo '</div>';
echo '</div>';


function getUserHTML($user,$userid,$imagepath, $achievements, $achievementList){
$string ="";
 $ergebnis =  mysql_query("SELECT sum(REAL_AMOUNT) as summe FROM t_drawings WHERE USER_ID=".$userid) or die("Fehler:".mysql_error($conn)."<br><br>$sql");
 if($row = mysql_fetch_object($ergebnis)){
	$string =  '<div class="news-drink-box"><table border="0"><tr><td><img src="'.$imagepath.'" /></td><td width="100%"><a href="index.php?user='.$userid.'">'.$user.'</a><table><tr><td>Bisher getrunken: '.(isset($row->summe)?number_format($row->summe,2,".","") : 0).' Liter </td></tr></table>';
 }
$length = count($achievements);
for($i=0;$i<$length;$i++){
	$temp  =$achievementList[$achievements[$i]];
	
	$string = $string.'<td class="border"><a href="index.php?achievement='.$achievements[$i].'" alt="'.$temp['NAME'].'"><img src="'.$temp["IMAGE_PATH"].'" alt="'.$temp['NAME'].'"></a></td>';
}
	
$string = $string.'</table></div>';
echo $string;	
}
?>

<script type="text/javascript" src="php/members/members.js"></script>