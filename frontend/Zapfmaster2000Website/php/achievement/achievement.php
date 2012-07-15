<link rel="stylesheet" href="php/achievement/achievement.css">
<div class="newsfeed">
<div class="newsdiv">Achievement.
<?php
include 'php/dbconnect.php';
$row = getAchievementById($_GET['achievement']);
echo utf8_encode($row->NAME);
 ?>  <div class="newscut">
 </div>

 </div>

  <div class="statsdiv">
<div class="statsHeadline">Personen</div>
<div class="achDescr">
<?php
$ergebnis = mysql_query("SELECT t_gained_achievements.USER_ID as userid,t_users.NAME as name,t_users.IMAGE_PATH as image_path,DATE_FORMAT(t_gained_achievements.DATE,'%e.%c. um %H:%i Uhr') as date FROM t_gained_achievements,t_users WHERE t_gained_achievements.ACHIEVEMENT_ID=".$_GET['achievement']." AND t_users.USER_ID = t_gained_achievements.USER_ID ");
echo '<table style="width:100%">';
$i=0;
while($rowKeg = mysql_fetch_object($ergebnis)){
$i++;
$colo = $i%2;
	//echo "<b><a href='index.php?user=".$rowKeg->userid."'>".$rowKeg->name."</a>,</b> ";
echo '<tr><td width="50px"><a href="index.php?user='.$rowKeg->userid.'">'.'<img src="'.$rowKeg->image_path.'" /></a></td><td class="bestlist_'.$colo.'"><a href="index.php?user='.$rowKeg->userid.'">'.$rowKeg->name.'</a></td><td class="bestlist_'.$colo.'">erreicht am '.$rowKeg->date.'</td></tr>';
}

echo '</table>';
 
 ?></div>
</div>
</div>

</div>


<div class="newsfeed newsfeed2">
  <div class="newsdiv">Stats. <div class="newscut"> </div>
 </div><div class="statsdiv">
<div class="statsHeadline">Achievement Stats</div>
<div class="achDescr">
	<table>
    	<tr>
        <td>
        Illustration:
        </td><td>
        <?php  echo "<img src='".$row->IMAGE_PATH."' />"  ?>
        
        </td>
        </tr>
        <tr>
        	<td>Achievement erreicht: </td>
            <td>
			<?php  
$ergebnis = mysql_query("SELECT count(*) as coun FROM t_gained_achievements WHERE ACHIEVEMENT_ID=".$_GET['achievement']);
if($rowKeg = mysql_fetch_object($ergebnis)){
	echo "<b>".$rowKeg->coun."</b> Person(en)";	
}
			
			
			?></td>
        </tr>
        <tr>
        	<td>
        First Achiever: 
        	</td>
            <td>
                        <?php  
$ergebnis = mysql_query("SELECT t_gained_achievements.USER_ID as userid,t_users.NAME as name FROM t_gained_achievements,t_users WHERE t_gained_achievements.ACHIEVEMENT_ID=".$_GET['achievement']." AND t_users.USER_ID = t_gained_achievements.USER_ID ORDER BY t_gained_achievements.DATE ASC LIMIT 1");
if($rowKeg = mysql_fetch_object($ergebnis)){
	echo "<b><a href='index.php?user=".$rowKeg->userid."'>".$rowKeg->name."</a></b> ";	
}
			
			
			?>

            </td>

        </tr>

<tr><td>Beschreibung</td><td>
<?php echo utf8_encode($row->DESCRIPTION);
 ?>
</td></tr>
	</table></div>
</div>
</div>


















