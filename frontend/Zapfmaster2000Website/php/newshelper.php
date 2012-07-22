<?php
function createNews($type,$contents,$image_path){
 mysql_query("INSERT INTO t_news (TYPE, CONTENTS,IMAGE_PATH) VALUES ('".$type."', '".$contents."', '".$image_path."')");
 

}
function sendRequestOther($content,$image_path){
$hostname = $_SERVER['HTTP_HOST'];

$path2 = explode("/",dirname($_SERVER['PHP_SELF']));

$path =  $path2[1];
$ch = curl_init('http://'.$hostname."/".$path.'/backend.php');

	  
$postVars = "type=other&text=".$content."&imagepath=".$image_path;
 curl_setopt($ch, CURLOPT_POST      ,1);
 curl_setopt($ch, CURLOPT_POSTFIELDS    ,$postVars);
 curl_setopt($ch, CURLOPT_FOLLOWLOCATION  ,1); 
 curl_setopt($ch, CURLOPT_HEADER      ,0);  // DO NOT RETURN HTTP HEADERS 
 curl_setopt($ch, CURLOPT_RETURNTRANSFER  ,1);  // RETURN THE CONTENTS OF THE CALL
 curl_exec($ch);  	

}
function sendRequestAchievement($achievement,$user){

$postVars = "type=achievement&achievement=".$achievement."&user=".$user;
$hostname = $_SERVER['HTTP_HOST'];

$path2 = explode("/",dirname($_SERVER['PHP_SELF']));

$path =  $path2[1];
$ch = curl_init('http://'.$hostname."/".$path.'/backend.php');

 curl_setopt($ch, CURLOPT_POST      ,1);
 curl_setopt($ch, CURLOPT_POSTFIELDS    ,$postVars);
 curl_setopt($ch, CURLOPT_FOLLOWLOCATION  ,1); 
 curl_setopt($ch, CURLOPT_HEADER      ,0);  // DO NOT RETURN HTTP HEADERS 
 curl_setopt($ch, CURLOPT_RETURNTRANSFER  ,1);  // RETURN THE CONTENTS OF THE CALL
 curl_exec($ch);  	
}
function sendRequestDrawing($user,$amount,$duration,$kind){

$postVars = "type=other&user=".$user."&amount=".$amount."&duration=".$duration."&kind=".$kind;
$hostname = $_SERVER['HTTP_HOST'];

$path2 = explode("/",dirname($_SERVER['PHP_SELF']));

$path =  $path2[1];
$ch = curl_init('http://'.$hostname."/".$path.'/backend.php');

 curl_setopt($ch, CURLOPT_POST      ,1);
 curl_setopt($ch, CURLOPT_POSTFIELDS    ,$postVars);
 curl_setopt($ch, CURLOPT_FOLLOWLOCATION  ,1); 
 curl_setopt($ch, CURLOPT_HEADER      ,0);  // DO NOT RETURN HTTP HEADERS 
 curl_setopt($ch, CURLOPT_RETURNTRANSFER  ,1);  // RETURN THE CONTENTS OF THE CALL
 curl_exec($ch);  	
}
?>