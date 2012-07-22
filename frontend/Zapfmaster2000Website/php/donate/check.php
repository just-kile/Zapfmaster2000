

<?php

include '../dbconnect.php';
include '../dbStatsHelper.php';
include '../newshelper.php';
//$username = $_GET["USER_ID"];
$RFID_TAG = $_GET["UPDATEUSER_RFID"];
$DONATION = number_format($_GET["DONATION"],2,".","");
$abfrage = "SELECT USER_ID,NAME FROM t_users WHERE RFID_TAG=".$RFID_TAG;
$ergebnis = mysql_query($abfrage);
if($row1 = mysql_fetch_object($ergebnis)){
	
	
	 mysql_query("INSERT INTO t_donations (USER_ID, DONATION) VALUES ('".$row1->USER_ID."', '".$DONATION."')");
	 $content = '<a href="index.php?user='.$row1->USER_ID.'">'.$row1->NAME.'</a> hat gerade '.$DONATION.'&euro; gespendet. Vielen Dank!';
	 $image = "images/achievements/geld.png";
	 createNews('OTHER',$content  ,$image);
	
}

include("../logout.php");
 


?>