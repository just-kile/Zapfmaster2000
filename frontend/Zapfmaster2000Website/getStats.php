<?php
include 'php/dbconnect.php';
include 'php/dbStatsHelper.php';
//Kegamount
$response = array();
$keg = isset($_GET['keg']) ? $_GET['keg']:undefined;
$kegAmount =  isset($_GET['kegamount']) ? $_GET['kegamount']:undefined;
$bestDrinker = isset($_GET['best']) ? $_GET['best']:undefined;
$niceword = isset($_GET['niceword']) ? $_GET['niceword']:undefined;
$achievement = isset($_GET['achievement']) ? $_GET['achievement']:undefined;
$drinkspeed = isset($_GET['drinkspeed']) ? $_GET['drinkspeed']:undefined;
$duration = isset($_GET['duration']) ? $_GET['duration']:undefined;
$day = isset($_GET["day"]) && $_GET["day"]!= "" ? $_GET["day"]: date('j');
$oneday = "(DAY(DATE)=".$day." AND HOUR(DATE)>8) OR (DAY(DATE)=".($day+1)." AND HOUR(DATE)<8)";

if($keg=="" ){
	$ergebnis =  mysql_query("SELECT * FROM t_kegs ORDER BY KEG_ID DESC LIMIT 1") or die("Fehler:".mysql_error($conn)."<br><br>$sql");
	if($row = mysql_fetch_object($ergebnis)){
		$response['KEG_ID']	 = $row->KEG_ID;
		$response['BRAND']	 = utf8_encode($row->BRAND);
		$response['SIZE']	 = $row->SIZE;
		$response['START_DATE']	 = $row->START_DATE;
	}
	$ergebnis =  mysql_query("SELECT COUNT(*) as coun FROM t_kegs") or die("Fehler:".mysql_error($conn)."<br><br>$sql");
	if($row = mysql_fetch_object($ergebnis)){
		
		$response['KEG_NUMBERS']	 = $row->coun;
	}
}
if( isset($_GET['keg']) &&$_GET['keg']!="" ){
	$ergebnis =  mysql_query("SELECT sum(REAL_AMOUNT) as summe FROM t_drawings WHERE USER_ID=".$_GET['keg']) or die("Fehler:".mysql_error($conn)."<br><br>$sql");
	if($row = mysql_fetch_object($ergebnis)){
		$response['USER_COMPLETE_AMOUNT']	 = number_format($row->summe,2,".","");
		
	}
	
}


if($kegAmount==""){
	$ergebnis =  mysql_query("SELECT * FROM t_kegs ORDER BY KEG_ID DESC LIMIT 1") or die("Fehler:".mysql_error($conn)."<br><br>$sql");
	if($rowKeg =  mysql_fetch_object($ergebnis)){
		$kegSize = $rowKeg->SIZE;
		$kegid = $rowKeg->KEG_ID;
		$ergebnis =  mysql_query("SELECT REAL_AMOUNT FROM t_drawings WHERE KEG_ID=".$kegid);
		$response['ACT_AMOUNT'] = $kegSize;
		
		while($rowDrawings = mysql_fetch_object($ergebnis)){
			
			$response['ACT_AMOUNT']  =$response['ACT_AMOUNT'] - $rowDrawings->REAL_AMOUNT;
		
		
		}
		$response['ACT_AMOUNT']  = number_format($response['ACT_AMOUNT'] ,2,".","");
		
		$ergebnis =  mysql_query("SELECT sum(SIZE) as summe FROM t_kegs") or die("Fehler:".mysql_error($conn)."<br><br>$sql");;
		if($row = mysql_fetch_object($ergebnis)){
			$response['COMPLETE_AMOUNT']  = $row->summe - $response['ACT_AMOUNT'] ;
		}
		
		
		
	}

} if($bestDrinker==""){
if(isset($_GET['day'])){

$ergebnis  = mysql_query("SELECT sum(REAL_AMOUNT),USER_ID FROM t_drawings WHERE ".$oneday." GROUP BY USER_ID ORDER BY sum(REAL_AMOUNT) DESC LIMIT 1") or die("Fehler:".mysql_error($conn)."<br><br>$sql");
}else{
$ergebnis  = mysql_query("SELECT sum(REAL_AMOUNT),USER_ID FROM t_drawings  GROUP BY USER_ID ORDER BY sum(REAL_AMOUNT) DESC LIMIT 1") or die("Fehler:".mysql_error($conn)."<br><br>$sql");

}
	
	if($rowKeg =  mysql_fetch_object($ergebnis)){
		$response['BEST'] =  getUserById( $rowKeg->USER_ID)->NAME;
		$response['BEST_USER_ID'] =  $rowKeg->USER_ID;
	}
	
	$ergebnis  = mysql_query("SELECT sum(REAL_AMOUNT),USER_ID,DATE FROM t_drawings WHERE DATE > DATE_SUB(NOW(), INTERVAL 1 HOUR) GROUP BY USER_ID ORDER BY sum(REAL_AMOUNT) DESC LIMIT 1") or die("Fehler:".mysql_error($conn)."<br><br>$sql");
	if($rowKeg =  mysql_fetch_object($ergebnis)){
		$response['BEST_HOUR'] =  getUserById( $rowKeg->USER_ID)->NAME;
		$response['BEST_HOUR_USER_ID'] =  $rowKeg->USER_ID;
	}
}
if($niceword==""){
	$ergebnis =  mysql_query('SELECT VALUE FROM t_config WHERE CONF_KEY="NICE_WORDS"') or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	if($row = mysql_fetch_object($ergebnis)){
		$nicewords = $row->VALUE;
		$nicewordeArr = explode(",",$nicewords);
		$completeCount = count($nicewordeArr);
		$index = rand(0,$completeCount-1);
		$response['NICE_WORD'] = $nicewordeArr[$index];
		
	}
}
if($achievement!= undefined && $achievement!=""){
	$ergebnis =  mysql_query("SELECT * FROM t_achievements WHERE ACHIEVEMENT_ID=".$achievement) or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	if($row = mysql_fetch_object($ergebnis)){
		$response['ACHIEVEMENT_ID'] = $achievement;
		$response['ACHIEVEMENT_NAME'] = $row->NAME;
		$response['ACHIEVEMENT_DESCRIPTION'] = $row->DESCRIPTION;
		$response['ACHIEVEMENT_IMAGE_PATH'] = $row->IMAGE_PATH;
		$response['ACHIEVEMENT_PUBLIC'] = $row->PUBLIC;
		$response['ACHIEVEMENT_TYPE'] = $row->TYPE;
		 
	}
}

if(isset($_GET['achievementofuser']) && $_GET['achievementofuser'] !=""){
	
	$userid = $_GET['achievementofuser'];
	$ach =  mysql_query("SELECT ACHIEVEMENT_ID FROM t_gained_achievements WHERE USER_ID=".$userid ." ORDER BY DATE DESC") or die("Fehle:".mysql_error($conn)."<br><br>$sql");
	$achArr = array();
	$i = 0;
	while($rowAch = mysql_fetch_object($ach)){
		$achievemnt = getAchievementById( $rowAch->ACHIEVEMENT_ID);
		 $achArr[$i] = array("ACHIEVEMENT_ID"=>$rowAch->ACHIEVEMENT_ID,"NAME" =>utf8_encode($achievemnt->NAME),"DESCRIPTION" =>utf8_encode($achievemnt->DESCRIPTION),"IMAGE_PATH" =>$achievemnt->IMAGE_PATH,"PUBLIC" =>$achievemnt->PUBLIC,"TYPE" =>$achievemnt->TYPE); 
		
		 $i++;
	}
	$response['ACHIEVEMENT_OF_USER'] = $achArr;
	



	
	
 
}

if($drinkspeed==""){
	$ergebnis =  mysql_query("SELECT sum(REAL_AMOUNT) as summe FROM t_drawings WHERE DATE > DATE_SUB(NOW(), INTERVAL 1 HOUR)") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	if($rowKeg =  mysql_fetch_object($ergebnis)){
		$response['DRINK_SPEED'] =  number_format($rowKeg->summe,2,".","");
	}
	
}
if(isset($_GET['drinkspeed']) && $drinkspeed!=""){
	//$ergebnis =  mysql_query("SELECT sum(REAL_AMOUNT) as summe FROM t_drawings WHERE USER_ID=".$drinkspeed) or die("Fehr:".mysql_error($conn)."<br><br>$sql");
$ergebnis =  mysql_query("SELECT (sum(REAL_AMOUNT)/(sum(DURATION))*3600) as avgdur,USER_ID FROM t_drawings WHERE USER_ID=".$drinkspeed) or die("Fehler:".mysql_error($conn)."<br><br>$sql");
		
	if($rowKeg =  mysql_fetch_object($ergebnis)){
		$response['DRINK_SPEED'] =  number_format($rowKeg->avgdur,2,".","");
	}
	
}
if(isset($_GET['drinkstats'])){
	if(isset($_GET["day"])){
		$ergebnis =  mysql_query("SELECT DURATION FROM t_drawings WHERE ".$oneday."ORDER BY DURATION DESC LIMIT 1") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	
	}else{
		$ergebnis =  mysql_query("SELECT DURATION FROM t_drawings ORDER BY DURATION DESC LIMIT 1") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	
		
	}
	if($rowKeg =  mysql_fetch_object($ergebnis)){
		$response['LONGEST_DURATION'] =  number_format($rowKeg->DURATION,2,".","");
	}
	
	if(isset($_GET["day"])){
		$ergebnis =  mysql_query("SELECT REAL_AMOUNT FROM t_drawings WHERE ".$oneday."ORDER BY REAL_AMOUNT DESC LIMIT 1") or die("Fehr:".mysql_error($conn)."<br><br>$sql");

	}else{
		$ergebnis =  mysql_query("SELECT REAL_AMOUNT FROM t_drawings ORDER BY REAL_AMOUNT DESC LIMIT 1") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	}
	if($rowKeg =  mysql_fetch_object($ergebnis)){
		$response['MOST_AMOUNT'] =  number_format($rowKeg->REAL_AMOUNT,2,".","");
	}
	
	
	if(isset($_GET["day"])){
		$ergebnis =  mysql_query("SELECT USER_ID ,sum(DURATION) as summe FROM t_drawings WHERE ".$oneday." ORDER BY sum(DURATION) DESC LIMIT 1") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	}else{
		$ergebnis =  mysql_query("SELECT USER_ID ,sum(DURATION) as summe FROM t_drawings ORDER BY sum(DURATION) DESC LIMIT 1") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	}
	if($rowKeg =  mysql_fetch_object($ergebnis)){
		if($rowKeg->USER_ID == ""){
			$response['MOST_LOYAL_USER'] = array();
		}else{
			$response['MOST_LOYAL_USER'] = array( "USER_ID"=> $rowKeg->USER_ID,"NAME"=>getUserById($rowKeg->USER_ID,"NAME")->NAME,"COUNT"=>number_format($rowKeg->summe/60,0,"",""));
		
			}
	}
	if(isset($_GET["day"])){
		$ergebnis =  mysql_query("SELECT count(*) as coun, USER_ID FROM t_drawings WHERE ".$oneday."GROUP BY USER_ID ORDER BY count(*) DESC LIMIT 1") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	
	}else{
		$ergebnis =  mysql_query("SELECT count(*) as coun, USER_ID FROM t_drawings GROUP BY USER_ID ORDER BY count(*) DESC LIMIT 1") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	}
	if($rowKeg =  mysql_fetch_object($ergebnis)){
		
		$response['MOST_PROCESSES'] = $rowKeg->coun;
	}
	
	if(isset($_GET["day"])){
		$ergebnis =  mysql_query("SELECT count(*) as coun FROM t_drawings WHERE".$oneday) or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	
	}else{
	$ergebnis =  mysql_query("SELECT count(*) as coun FROM t_drawings") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	}
	if($rowKeg =  mysql_fetch_object($ergebnis)){
		
		$response['OPERATIONS'] = $rowKeg->coun;
	}
	
	
	if(isset($_GET["day"])){
		$ergebnis =  mysql_query("SELECT HOUR(DATE) as hour FROM t_drawings WHERE ".$oneday." GROUP BY HOUR(DATE) ORDER BY sum(REAL_AMOUNT) DESC LIMIT 1") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	
	}else{
	$ergebnis =  mysql_query("SELECT HOUR(DATE) as hour FROM t_drawings GROUP BY HOUR(DATE) ORDER BY sum(REAL_AMOUNT) DESC LIMIT 1") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	}
	if($rowKeg =  mysql_fetch_object($ergebnis)){
		
		$response['MOST_ACTIVITY_HOUR'] = $rowKeg->hour;
	}
	
	$response['PI'] = array("POSITION" => mt_rand(13213,99999),"VALUE" => rand(0,9));
	
	
	
	
}

if(isset($_GET['drinkstatsuser'])){
	$usereid = $_GET['drinkstatsuser'];
	$ergebnis =  mysql_query("SELECT max(DURATION) as dur FROM t_drawings WHERE USER_ID=".$usereid) or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	if($rowKeg =  mysql_fetch_object($ergebnis)){
		$response['LONGEST_DURATION'] =  number_format($rowKeg->dur,2,".","");
	}
	
	$ergebnis =  mysql_query("SELECT max(REAL_AMOUNT) as mx FROM t_drawings WHERE USER_ID=".$usereid) or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	if($rowKeg =  mysql_fetch_object($ergebnis)){
		$response['MOST_AMOUNT'] =  number_format($rowKeg->mx,2,".","");
	}
	
	
	$ergebnis =  mysql_query("SELECT count(*) as coun FROM t_drawings WHERE USER_ID=".$usereid) or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	if($rowKeg =  mysql_fetch_object($ergebnis)){
		
		$response['OPERATIONS'] = $rowKeg->coun;
	}
	
	$ergebnis =  mysql_query("SELECT HOUR(DATE) as hour FROM t_drawings WHERE USER_ID=".$usereid." GROUP BY HOUR(DATE) ORDER BY sum(REAL_AMOUNT) DESC LIMIT 1") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	if($rowKeg =  mysql_fetch_object($ergebnis)){
		
		$response['MOST_ACTIVITY_HOUR'] = $rowKeg->hour;
	}
	
	$response['PI'] = array("POSITION" => mt_rand(13213,99999),"VALUE" => rand(0,9));
	
	
	
	
}

if($duration==""){
	if(isset($_GET['day'])){
		$ergebnis =  mysql_query("SELECT sum(DURATION)as summe,COUNT(*) as anzahl  FROM t_drawings WHERE ".$oneday) or die("Fehler:".mysql_error($conn)."<br><br>$sql");
	}else{
		$ergebnis =  mysql_query("SELECT sum(DURATION)as summe,COUNT(*) as anzahl  FROM t_drawings") or die("Fehler:".mysql_error($conn)."<br><br>$sql");
	
		
	}
	
	
	if($row = mysql_fetch_object($ergebnis)){
		$response['AVERAGE_DRAW_DURATION']  = number_format($row->summe /$row->anzahl,2,".","") ;
	
	
	$response['COMPLETE_DRAW_DURATION']  = number_format($row->summe,2,".","")  ;
	
	}
	if(isset($_GET['day'])){
		$ergebnis =  mysql_query("SELECT (sum(REAL_AMOUNT)/(sum(DURATION))*3600) as avgdur,USER_ID FROM t_drawings WHERE ".$oneday." GROUP BY USER_ID ORDER BY avgdur DESC") or die("Fehler:".mysql_error($conn)."<br><br>$sql");
	}else{
		$ergebnis =  mysql_query("SELECT (sum(REAL_AMOUNT)/(sum(DURATION))*3600) as avgdur,USER_ID FROM t_drawings GROUP BY USER_ID ORDER BY avgdur DESC") or die("Fehler:".mysql_error($conn)."<br><br>$sql");
		
	}
	$first = true;
	while($row = mysql_fetch_object($ergebnis)){
		$user = getUserById($row->USER_ID,"NAME")->NAME;
		if($first){
			$response['FASTEST_DRINKER']  =array("DURATION"=>number_format($row->avgdur,0,".",""),"USER_ID" =>$row->USER_ID,"NAME"=>$user)  ;
			$first = false;
		}
		
		$response['SLOWEST_DRINKER'] = array("DURATION"=>number_format($row->avgdur,0,".",""),"USER_ID" =>$row->USER_ID,"NAME"=>$user)  ;
		
	}

}elseif(isset($_GET['duration']) && $duration !=""){
	 $ergebnis =  mysql_query("SELECT avg(DURATION) as summe,COUNT(*) as anzahl FROM t_drawings WHERE USER_ID=".$duration) or die("Feher:".mysql_error($conn)."<br><br>$sql");
	if($row = mysql_fetch_object($ergebnis)){
		$response['AVERAGE_DRAW_DURATION']  = number_format($row->summe,2,".","") ;
	}
	
	$ergebnis =  mysql_query("SELECT sum(DURATION)as summe FROM t_drawings WHERE USER_ID=".$duration) or die("Fehler:".mysql_error($conn)."<br><br>$sql");;
	if($row = mysql_fetch_object($ergebnis)){
		$response['COMPLETE_DRAW_DURATION']  =number_format($row->summe,2,".","") ;
	}
	
}
if(isset($_GET['amount']) && $_GET['amount']!=""){
	$ergebnis =  mysql_query("SELECT sum(REAL_AMOUNT) as summe FROM t_drawings WHERE USER_ID=".$_GET['amount']) or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	if($rowKeg =  mysql_fetch_object($ergebnis)){
		$response['AMOUNT'] =  number_format($rowKeg->summe,2,".","");
	}
}
$promille_red = 0.0001;
	if($row =  mysql_fetch_object($diffUserErgebnis)){
		$promille_red = $row->VALUE;
	}
	
if(isset($_GET['promille'])){
	$diffUserErgebnis = mysql_query("SELECT VALUE FROM t_config WHERE CONF_KEY='PROMILLE_REDUCTION'") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	$diffUser=1 ;
	if($_GET['promille'] ==""){
	$diffUserErgebnis = mysql_query("SELECT * FROM t_drawings WHERE DATE > DATE_SUB(NOW(), INTERVAL 12 HOUR) GROUP BY USER_ID") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	if($row =  mysql_num_rows($diffUserErgebnis)){
		$diffUser =   $row;
	}
	
	
	$ergebnis =  mysql_query("SELECT sum(REAL_AMOUNT) as summe,HOUR(DATE) as hour FROM t_drawings WHERE DATE > DATE_SUB(NOW(), INTERVAL 12 HOUR) GROUP BY HOUR(DATE)") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	}else{
		$ergebnis =  mysql_query("SELECT sum(REAL_AMOUNT) as summe,HOUR(DATE) as hour FROM t_drawings WHERE DATE > DATE_SUB(NOW(), INTERVAL 12 HOUR) AND USER_ID=".$_GET['promille']." GROUP BY HOUR(DATE)") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
		$diffUser = 1;
		
	}
	
	//$response['DIFFERENT_USER'] = $diffUser ;
	$prom = 0;
	$attenuation = 1;
	$actHour = date('G');
	$promConst =  0.05 * 0.8/ 0.65;
	//Widmark Formel c = V*e*rho / (m*r)
	while($row =  mysql_fetch_object($ergebnis)){
		$hour =  $row->hour;
		$sum =  $row->summe;
		
		
		$hourProm =  $sum  *$promConst/($diffUser*80)-(mod(($actHour - $hour),24)) * $promille_red;
		$prom += $hourProm>0? $hourProm :0 ;
		
	}
	$response['AVERAGE_PROMILLE']  = $prom>0 ? number_format($prom*1000,3,".",""):0;
}elseif(isset($_GET['promille']) && $promille!=""){
	
	$diffUser=1 ;
	
	$ergebnis =  mysql_query("SELECT sum(REAL_AMOUNT) as summe,HOUR(DATE) as hour FROM t_drawings WHERE DATE > DATE_SUB(NOW(), INTERVAL 12 HOUR) AND USER_ID=".$promille." GROUP BY HOUR(DATE)") or die("Fesdfhr:".mysql_error($conn)."<br><br>$sql");
	
	$prom = 0;
	$attenuation = 1;
	$actHour = date('G');
	$promConst =  0.05 * 0.8/ 0.65;
	//Widmark Formel c = V*e*rho / (m*r)
	while($row =  mysql_fetch_object($ergebnis)){
		$hour =  $row->hour;
		$sum =  $row->summe;
		$hourProm =  $sum  *$promConst/($diffUser*80)-(mod(($actHour - $hour),24)) *$promille_red;
		$prom =$prom+ $hourProm>0? $hourProm :0 ;
		
	}
	$response['PROMILLE']  = $prom>0 ? number_format($prom*1000,3,".",""):0;
}



function mod($n,$m){
	return (($n%$m)+$m)%$m;	
}
if(isset($_GET['progress'])){
	$ergebnis =  mysql_query("SELECT sum(REAL_AMOUNT) as summe,HOUR(DATE) as hour FROM t_drawings WHERE DATE > DATE_SUB(NOW(), INTERVAL 12 HOUR) GROUP BY HOUR(DATE)") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	$response['PROGRESS'] = "";
	$temp = array();
	
	while($row =  mysql_fetch_object($ergebnis)){
		 
			 
		 	
		
		 $temp[$row->hour]= number_format($row->summe,2,".","");
		
	}
	$temp['last'] =date('G'); 
	$response['PROGRESS'] = $temp;
}
if(isset($_GET['bestlist']) && $_GET['bestlist'] == "" ){
	$ergebnis;	
	if(isset($_GET['day'])){
		$ergebnis =  mysql_query("SELECT USER_ID,sum(REAL_AMOUNT) as summe FROM t_drawings WHERE ".$oneday." GROUP BY USER_ID ORDER BY sum(REAL_AMOUNT) DESC") or die("Fehr:".mysql_error($conn)."<br><br>$sql");	
	}else{
$ergebnis =  mysql_query("SELECT USER_ID,sum(REAL_AMOUNT) as summe FROM t_drawings GROUP BY USER_ID ORDER BY sum(REAL_AMOUNT) DESC") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	}
	
	$i =0;
	$temp = array();
	while($row =  mysql_fetch_object($ergebnis)){
		if($row->USER_ID !=1){
		$user = getUserById($row->USER_ID,"NAME")->NAME;
		$temp[$i] = array( "USER_ID" => $row->USER_ID,"AMOUNT" => number_format( $row->summe,2,".",""),"NAME" => $user);
		$i++;
}		
		
	}
	$response["BESTLIST"] = $temp;
}else if(isset($_GET['bestlist'])){
	
	
	$ergebnis =  mysql_query("SELECT USER_ID,sum(REAL_AMOUNT) as summe FROM t_drawings GROUP BY USER_ID ORDER BY sum(REAL_AMOUNT) DESC") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	$i =0;
	$temp = "-1";
	while($row =  mysql_fetch_object($ergebnis)){
		$i++;
		if($row->USER_ID ==$_GET['bestlist'] ){
			$user = getUserById($row->USER_ID,"NAME")->NAME;
			$temp = array( "USER_ID" => $row->USER_ID,"AMOUNT" => number_format( $row->summe,2,".",""),"NAME" => $user,"PLACE" =>$i);
		
		}
		
	}
	$response["BESTLIST"] = $temp;

}

if(isset($_GET['mostachievements'])){
	if(isset($_GET["day"])){
		$ergebnis =  mysql_query("SELECT USER_ID,COUNT(ACHIEVEMENT_ID) as count FROM t_gained_achievements WHERE ".$oneday."GROUP BY USER_ID ORDER BY COUNT(ACHIEVEMENT_ID) DESC") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	
		
	}else{
	$ergebnis =  mysql_query("SELECT USER_ID,COUNT(ACHIEVEMENT_ID) as count FROM t_gained_achievements GROUP BY USER_ID ORDER BY COUNT(ACHIEVEMENT_ID) DESC") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	}
	$i =0;
	$temp = array();
	while($row =  mysql_fetch_object($ergebnis)){
		$user = getUserById($row->USER_ID,"NAME")->NAME;
		$temp[$i] = array( "USER_ID" => $row->USER_ID,"ACHIEVEMENT_COUNT" => $row->count,"NAME" => $user);
		$i++;
	}

	$response["MOST_ACHIEVEMENTS"] = $temp;
}
if(isset($_GET['achievementnumbers']) &&$_GET['achievementnumbers']!="" ){
	$ergebnis =  mysql_query("SELECT USER_ID,COUNT(ACHIEVEMENT_ID) as countt FROM t_gained_achievements WHERE USER_ID='".$_GET['achievementnumbers']."'") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	
	$count ;
	if($row =  mysql_fetch_object($ergebnis)){
		$count = $row->countt;
	}
	
	$response["ACHIEVEMENT_NUMBERS"] = $count;
	
	
}
if(isset($_GET['area'])){
	$ergebnis =  mysql_query("SELECT USER_ID,sum(REAL_AMOUNT) as summe,HOUR(DATE) as hour FROM t_drawings WHERE DATE > DATE_SUB(NOW(), INTERVAL 12 HOUR) GROUP BY HOUR(DATE), USER_ID ") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	$i =0;
	$temp = array();
	$hour = -1;
	$hourTemp = array();
	while($row =  mysql_fetch_object($ergebnis)){
		$user = getUserById($row->USER_ID,"NAME")->NAME;
		$test = array( "USER_ID" => $row->USER_ID,"SUM" => number_format( $row->summe,2,".",""),"NAME" => $user);
		if($hour === $row->hour){
			
			
			array_push( $temp[$hour], $test);
		}else{
			$hour =  $row->hour;
			$hourTemp = array();
			array_push($hourTemp,$test);
			$temp[$hour] = $hourTemp;
			
			
		}
		
		
		$i++;
	}
	
	$temp['LAST'] =date('G'); 
	$response["TIME_DRINK"] = $temp;
}

if(isset($_GET['progressweek'])){
	if(isset($_GET['day'])){
		$INTERVAL = 1; //days
		$TIMEINTERVALS = 48;
		$DIVISOR = 24/$TIMEINTERVALS;
		$ergebnis;
		if($_GET['progressweek'] ==""){
			$ergebnis =  mysql_query("SELECT sum(REAL_AMOUNT) as summe,CEIL(HOUR(DATE)/".$DIVISOR.") as period,DAY(DATE) as day FROM t_drawings WHERE ".$oneday." GROUP BY DAY(DATE),period ORDER BY DAY(DATE),period ASC") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
			//$ergebnis =  mysql_query("SELECT sum(REAL_AMOUNT) as summe,CEIL(HOUR(DATE)/".$DIVISOR.") as period,DAY(DATE) as day FROM t_drawings WHERE DATE > DATE_SUB(NOW(), INTERVAL ".$INTERVAL." DAY) GROUP BY DAY(DATE),period ORDER BY DAY(DATE),period ASC") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	
		}else{
			$ergebnis =  mysql_query("SELECT sum(REAL_AMOUNT) as summe,CEIL(HOUR(DATE)/".$DIVISOR.") as period,DAY(DATE) as day FROM t_drawings WHERE ".$oneday." AND USER_ID=".$_GET['progressweek']." GROUP BY DAY(DATE),period ORDER BY DAY(DATE),period ASC") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
		}
		//$firstDaySql = mysql_query("SELECT DAY(DATE) as day FROM t_drawings WHERE DATE > DATE_SUB(NOW(), INTERVAL 7 DAY) GROUP BY DAY(DATE) ORDER BY DAY(DATE) ASC LIMIT 1") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
		$firstDay = $day;	
	}else{
		$INTERVAL = 7; //days
		$TIMEINTERVALS = 12;
		$DIVISOR = 24/$TIMEINTERVALS;
		$ergebnis;
		if($_GET['progressweek'] ==""){
			$ergebnis =  mysql_query("SELECT sum(REAL_AMOUNT) as summe,CEIL(HOUR(DATE)/".$DIVISOR.") as period,DAY(DATE) as day FROM t_drawings WHERE DATE > DATE_SUB(NOW(), INTERVAL ".$INTERVAL." DAY) GROUP BY DAY(DATE),period ORDER BY DAY(DATE),period ASC") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
		}else{
			$ergebnis =  mysql_query("SELECT sum(REAL_AMOUNT) as summe,CEIL(HOUR(DATE)/".$DIVISOR.") as period,DAY(DATE) as day FROM t_drawings WHERE DATE > DATE_SUB(NOW(), INTERVAL 7 DAY) AND USER_ID=".$_GET['progressweek']." GROUP BY DAY(DATE),period ORDER BY DAY(DATE),period ASC") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
			
		}
		$firstDaySql = mysql_query("SELECT DAY(DATE) as day FROM t_drawings WHERE DATE > DATE_SUB(NOW(), INTERVAL 7 DAY) GROUP BY DAY(DATE) ORDER BY DAY(DATE) ASC LIMIT 1") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
		if($firstDayObj = mysql_fetch_object($firstDaySql)){
			$firstDay =  $firstDayObj->day;
		
		}
	}
	$temp = array();
	$day = -1;
	$i = -1;
	$test = array();
	$hourTemp = array();
	$index;
	//$firstDay ="";
	
	
	
	
	
	
	while($row =  mysql_fetch_object($ergebnis)){
		
		if($day== $row->day){
			//$index =$INTERVAL- mod((date('j')-$day),date('t'));
			$index = mod(($day-$firstDay),date('t'));
			$test[ $row->period]= $row->summe;
			//array_push($hourTemp,$test);
			$temp[$index] = $test;
		}else{
			
			$i++;
			$hourTemp = array();
			$test= array( $row->period=> $row->summe);
			
			
			$day =  $row->day;
			//$index =$INTERVAL- mod((date('j')-$day),date('t'));
			$index = mod(($day-$firstDay),date('t'));
			$temp[$index] = $test;
			
			
			
			
			
		}
		$period =$row->period;
		 
		
		
	}

	
	$response['PROGRESSWEEK_LAST'] =array(mod((date('j')-$firstDay),date('t')),floor((date('G')/$DIVISOR)));
	
	$response['PROGRESSWEEK'] = $temp;
}


if(isset($_GET['DURATIONCOUNT']) && ($_GET['DURATIONCOUNT']==="")){
	
	$ergebnis =  mysql_query("SELECT DURATION,count(DURATION) as coun FROM t_drawings WHERE DATE > DATE_SUB(NOW(), INTERVAL 24 HOUR) GROUP BY DURATION  ORDER BY DURATION ASC") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	$temp = array();
	while($rowKeg =  mysql_fetch_object($ergebnis)){
			$test = array("COUNT" => $rowKeg->coun,"DURATION" => $rowKeg->DURATION);
		  array_push($temp,$test);
	}
	$response['DURATIONCOUNT'] =$temp;
}
if(isset($_GET['drinkdistribution']) && $_GET['drinkdistribution'] !=""){
	$ergebnis =  mysql_query("SELECT t_drawings.KEG_ID as id, t_kegs.BRAND as BRAND, t_kegs.SIZE as SIZE, sum(t_drawings.REAL_AMOUNT) as summe FROM t_drawings,t_kegs WHERE t_drawings.USER_ID=".$_GET['drinkdistribution']." AND t_kegs.KEG_ID =t_drawings.KEG_ID  GROUP BY t_drawings.KEG_ID  ORDER BY sum(t_drawings.REAL_AMOUNT) DESC") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	$temp = array();
	while($rowKeg =  mysql_fetch_object($ergebnis)){
			$test = array("KEG_ID" => $rowKeg->id,"BRAND" => $rowKeg->BRAND,"AMOUNT" => number_format($rowKeg->summe,2,".",""),"KEG_SIZE"=> $rowKeg->SIZE);
		  array_push($temp,$test);
	}
	
	$response['DRINK_DISTRIBUTION'] = $temp;
	
}

if(isset($_GET['lastdraw']) && $_GET['lastdraw'] !=""){
	$ergebnis =  mysql_query("SELECT (UNIX_TIMESTAMP(NOW())-UNIX_TIMESTAMP(DATE)) as daate FROM t_drawings WHERE USER_ID=".$_GET['lastdraw']." ORDER BY DATE DESC") or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	if($rowKeg =  mysql_fetch_object($ergebnis)){
			$test = $rowKeg->daate/3600;
	}
	
	$response['LAST_DRAW'] = number_format($test,2,".","");
		;
	
	
}
function addProgressweek($arr,$TIMEINTERVALS){
	$day = $arr[0];
	$inter = $arr[1];
	$inter = mod($inter + 1,$TIMEINTERVALS);
	$day = $inter==0 ? ($day+1) : $day;
	return array($day,$inter);
}
echo $_GET['testt'];
echo json_encode($response);
flush();
?>
