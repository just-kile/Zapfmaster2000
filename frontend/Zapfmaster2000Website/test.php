<?php 
function putContent($filename,$response){
	//file_put_contents($filename,json_encode($response)); 
	$temp = json_encode($response);
		$params = "set&"."date=".time()."&content=".$temp;
	
	echo mkCurlConnection("http://server:8080/BeerometerServlet/BeerometerServlet",$params);
}

function getContent($filename){
	//return file_get_contents($filename);
	return mkCurlConnection("http://server:8080/BeerometerServlet/BeerometerServlet","get");
}
function getTime($filename){
	//return filemtime($filename);
	return mkCurlConnection("http://server:8080/BeerometerServlet/BeerometerServlet","time");
}

function mkCurlConnection($url,$params){
   $ch = curl_init();
   curl_setopt($ch, CURLOPT_URL, $url."?".$params);
   curl_setopt($ch, CURLOPT_HEADER,1);
  
	
 curl_setopt($ch, CURLOPT_RETURNTRANSFER  ,1);
 $ret = curl_exec($ch);
 echo $ret;
 return $ret;
}

$respons = array();
$respons['tmp'] = "te";
putContent("",$respons);

?>