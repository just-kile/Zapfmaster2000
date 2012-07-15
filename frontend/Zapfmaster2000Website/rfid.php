<?php

$type = isset($_POST['RFID_TAG'])? $_POST['RFID_TAG']: undefined;
//$user = isset($_POST['USER_ID'])? $_POST['USER_ID']: undefined;
$filename  = dirname(__FILE__).'/rfid.txt';
if( isset($_POST['USER_ID'])  ){
	$userid = $_POST['USER_ID'];
	include 'php/dbconnect.php';
	include "php/dbStatsHelper.php";
	$response = array();
	//say that getStats write in $response the keg datas
	$rfidTag;
	if($userid!=(-1)){
		$row = getUserById($userid);//getDBEntryById("T_USERS","RFID_TAG",$type,"NAME,RFID_TAG,USER_ID,IMAGE_PATH");
		$rfidTag = $row->RFID_TAG;
	
		$response["USER_ID"] =  $row->USER_ID;
		$response['NAME'] =  $row->NAME;
		$response["RFID_TAG"] = $type;
		$response["IMAGE_PATH"] = $row->IMAGE_PATH;
	
	}else{
		$rfidTag = "";
		$response["IMAGE_PATH"]  = "";	
		$response["USER_ID"] =  $userid;
		$response["NAME"] =  "";
		$response["RFID_TAG"] = $type;
	}
	$response["ADMINS"] = getConfigByKey("ADMINS","*")->VALUE;
	
	
	
	
	//$msg  = $user.",".$amount.",".$duration.","."refresh";
	
	


	echo json_encode($response);
	
		// store new message in the file
	putContent($filename,$response);

	die();


	
}
if( $type != undefined &&  (!isset($_POST['USER_ID']) || $_POST['USER_ID']==="")){
	
	
	$response = array();
	//say that getStats write in $response the keg datas
	
	//$row = getDBEntryById("T_USERS","RFID_TAG",$type,"NAME,RFID_TAG,USER_ID");//getUserById($user,"NAME,RFID_TAG");
	
	
	//$response['NAME'] =  $row->NAME;
	//$rfidTag = $row->RFID_TAG;
	
	//$response["USER_ID"] =  $row->USER_ID;

	$response["RFID_TAG"] = $type;
	//$msg  = $user.",".$amount.",".$duration.","."refresh";
	
	

	echo json_encode($response);
	
	// store new message in the file
	putContent($filename,$response);
	die();


	
}



// infinite loop until the data file is not modified
$lastmodif    = isset($_GET['timestamp']) ? $_GET['timestamp'] : 0;
$currentmodif = getTime($filename);
while ($currentmodif <= $lastmodif) // check if the data file has been modified
{
  usleep(10000); // sleep 10ms to unload the CPU
  clearstatcache();
  $currentmodif = getTime($filename);
}

// return a json array
$response = array();
$filecontent =  getContent($filename);

$response = json_decode($filecontent,true);//explode(",",$filecontent);
$response['timestamp'] = $currentmodif;
echo json_encode($response);
flush();



function putContent($filename,$response){
	file_put_contents($filename,json_encode($response)); 
	//$temp = json_encode($response);
	//$params = "set"."&date=".time()."&content=".$temp;
	//mkCurlConnection("http://server:8080/BeerometerServlet/BeerometerServlet",$params);
}

function getContent($filename){
	return file_get_contents($filename);
	//return mkCurlConnection("http://server:8080/BeerometerServlet/BeerometerServlet","get");
}

function getTime($filename){
	return filemtime($filename);
	//return mkCurlConnection("http://server:8080/BeerometerServlet/BeerometerServlet","time");
}

function mkCurlConnection($url,$params){
   $ch = curl_init();
   curl_setopt($ch, CURLOPT_URL, $url);
   curl_setopt($ch, CURLOPT_HEADER, 0);
   curl_setopt($ch, CURLOPT_POST      ,1);
	 curl_setopt($ch, CURLOPT_POSTFIELDS,$params);
 curl_setopt($ch, CURLOPT_RETURNTRANSFER  ,1);
 $ret = curl_exec($ch);
 curl_close($ch);
 return $ret;
}

?>