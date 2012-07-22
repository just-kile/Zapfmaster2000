<?php 

 session_destroy();
 $hostname = $_SERVER['HTTP_HOST'];
$path = dirname($_SERVER['PHP_SELF']);
$url =  'http://'.$hostname.($path == '/' ? '' : $path).'/index.php';

 header("Location: ".$url); 
 exit();
 


?>