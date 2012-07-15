<?php
//echo $hostname = $_SERVER['HTTP_HOST'];
 $path = explode("/",dirname($_SERVER['PHP_SELF']));
echo $path[1]
//$ch = curl_init('http://'.$hostname.($path == '/' ? '' : $path).'/backend.php');




?>