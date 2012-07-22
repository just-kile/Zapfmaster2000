<?php
if(isset($_COOKIE['adminmode'])){
	setcookie('adminmode',"",0); // 86400 = 1 day
	echo "Admin Mode Disabled";
}else{
	$pw = md5('zapfmaster2000');

	setcookie('adminmode',$pw,time() + (86400 * 7)); // 86400 = 1 day
	echo "Admin Mode Enabled";
}


?>