<?php


//include("dbconnect.php");
$user= $_POST["user"];
$amount = $_POST["amount"];
$zapftime = $_POST["time"];
$date = date('y-m-d-G-i-s');
$chose = $_POST["kind"];

/*while($row = mysql_fetch_object($ergebnis))
{
	echo $row->user;
	echo $row->amount;
	
	echo "<br />";
}*/



$filename  = dirname(__FILE__).'/data.txt';
/*$file = file_get_contents($filename);
if($file !=''){
	$entry_arr = explode(";",$file);
	
}
*/
// store new message in the file
$test = isset($user);
if($chose === "refresh" && $test){
	
		$msg  = $user.",".$amount.",".$zapftime.","."refresh";
		
		
	
		echo $msg;
  		file_put_contents($filename,$msg);
  		die();
}

else if($test){
	$msg  = $user.",".$amount.",".$zapftime.","."new";
	echo $msg;
  	file_put_contents($filename,$msg);
	//stops Script, don't want 2 infinite loops
	die();
}






// infinite loop until the data file is not modified
$lastmodif    = isset($_GET['timestamp']) ? $_GET['timestamp'] : 0;
$currentmodif = filemtime($filename);
while ($currentmodif <= $lastmodif) // check if the data file has been modified
{
  usleep(10000); // sleep 10ms to unload the CPU
  clearstatcache();
  $currentmodif = filemtime($filename);
}

// return a json array
$response = array();
$filecontent =  file_get_contents($filename);
$entry_arr = explode(",",$filecontent);
$response['user']       = $entry_arr[0];

$response['amount'] 	= $entry_arr[1];
$response['zapfdate'] 	= $entry_arr[2];
$response['kind'] 		= $entry_arr[3];
$response['timestamp'] = $currentmodif;
echo json_encode($response);
flush();

?>