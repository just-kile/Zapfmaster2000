<?php

$type = isset($_POST['type'])? $_POST['type']: undefined;
$filename  = dirname(__FILE__).'/data.txt';
$user= $_POST["user"];
$date = date('y-m-d-G-i-s');
if( $type != undefined & $type=='drawing'){
	
	include 'php/dbconnect.php';
	include "php/dbStatsHelper.php";
	$response = array();
	//say that getStats write in $response the keg datas
	$keg="";
	
	$ergebnis =  mysql_query("SELECT * FROM t_kegs ORDER BY KEG_ID DESC LIMIT 1") or die("Fehler:".mysql_error($conn)."<br><br>$sql");
	if($row = mysql_fetch_object($ergebnis)){
		$response['KEG_ID']	 = $row->KEG_ID;
		$response['BRAND']	 = utf8_encode($row->BRAND);
	}

	
	$response["userid"] = $user;
	$response["amount"] = $_POST["amount"];
	$response["duration"] = $_POST["duration"];
	$response["kind"]= $_POST["kind"];
	$response["type"]= $type;
	$row = getUserById($user,"NAME,IMAGE_PATH");
	$response["user"] = utf8_encode($row->NAME);
	$response["IMAGE_PATH"] = $row->IMAGE_PATH;
	
	//$msg  = $user.",".$amount.",".$duration.","."refresh";
	
	

	echo json_encode($response);
		// store new message in the file
	putContent($filename,$response);
	die();


	
}
else if( $type != undefined && $type =="achievement"){
	include 'php/dbconnect.php';
	include 'php/dbStatsHelper.php';
	$response = array();
	$response["userid"] = $user;
	$response["type"]= $type;
	$userArr = explode(";",$user);
	if(count($userArr)>1){
		$count = count($userArr);
		for($i =0;$i<$count;$i++){
			if($i==0){
				$response["user"] = utf8_encode(getUserById($userArr[$i],"NAME")->NAME);
			}else 
				$response["user"] = $response["user"].';'. utf8_encode(getUserById($userArr[$i],"NAME")->NAME);
			
			}
		
	}else{
		$row = getUserById($user,"NAME");
		$response["user"] = utf8_encode($row->NAME);
	}
	
	
	
	
	$achievement =$_POST["achievement"];
	$ergebnis =  mysql_query("SELECT * FROM t_achievements WHERE ACHIEVEMENT_ID=".$achievement) or die("Fehr:".mysql_error($conn)."<br><br>$sql");
	if($row = mysql_fetch_object($ergebnis)){
		$response['ACHIEVEMENT_ID'] = $achievement;
		$response['ACHIEVEMENT_NAME'] = utf8_encode($row->NAME);
		$response['ACHIEVEMENT_DESCRIPTION'] = utf8_encode($row->DESCRIPTION);
		$response['ACHIEVEMENT_IMAGE_PATH'] = $row->IMAGE_PATH;
		$response['ACHIEVEMENT_PUBLIC'] = $row->PUBLIC;
		$response['ACHIEVEMENT_TYPE'] = $row->TYPE;
		
		 
	}
	$response['kind']= "new";
	echo json_encode($response);
	putContent($filename,$response); 
	die();
	
}else if( $type != undefined && $type =="other"){
		
	$response = array();
	
	$response["text"] = utf8_encode($_POST["text"]);
	$response["imagepath"]= $_POST["imagepath"]; 
	$response["type"]= $type;
	$response["kind"]= "new";
	echo json_encode($response);
	putContent($filename,$response); 
	die();
}
//http://localhost:8080/BeerometerServlet/BeerometerServlet?set=&timestamp=123&content=123123d

// infinite loop until the data file is not modified
$lastmodif    = isset($_GET['timestamp']) ? $_GET['timestamp'] : 0;
$currentmodif = getTime($filename);
while ($currentmodif <= $lastmodif) // check if the data file has been modified
{
  usleep(1000); // sleep 10ms to unload the CPU
  clearstatcache();
  $currentmodif = getTime($filename);
}

// return a json array
$response = array();
$filecontent =  getContent($filename);

$response = json_decode($filecontent,true);//explode(",",$filecontent);
$response['timestamp'] = $currentmodif;
$response['date'] = date("Y-m-d H:i:s");
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