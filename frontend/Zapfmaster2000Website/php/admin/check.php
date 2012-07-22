<?php
session_start();
?>

<?php

include '../dbconnect.php';
include '../dbStatsHelper.php';

//$username = $_POST["USER_ID"];
$RFID_TAG = $_POST["RFID_TAG"];

$abfrage = "SELECT USER_ID,RFID_TAG FROM t_users WHERE RFID_TAG=".$RFID_TAG;
$ergebnis = mysql_query($abfrage);
$row1 = mysql_fetch_object($ergebnis);

$abfrage = "SELECT VALUE FROM t_config WHERE CONF_KEY='ADMINS'";
$ergebnis = mysql_query($abfrage);
$row = mysql_fetch_object($ergebnis);
$admins = explode(";",$row->VALUE);

	

if(in_array($row1->USER_ID,$admins ))
    {
    $_SESSION["username"] =$row1->USER_ID;
    echo "true";
	}
else
    {
    echo "RFID Benutzer ist kein Admin!";
    }

?>