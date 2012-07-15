<?php
session_start();



echo '<link rel="stylesheet" href="php/admin/register.css">';
echo '<script type="text/javascript" src="js/prototype.js"></script>';
echo '<script type="text/javascript" src="php/admin/register.js"></script>';
echo '<div class="newsfeed">
  <div class="newsdiv">Access AdminCP.
	<div class="newscut"> </div>
  </div>
  <div class="news">Berechtigte Nutzer: ';
  


 $ergebnis =  mysql_query("SELECT * FROM t_config WHERE CONF_KEY ='ADMINS'") or die("Fehler:".mysql_error($conn)."<br><br>$sql");
	if($row = mysql_fetch_object($ergebnis)){
		$temp =explode(";",$row->VALUE);
		 	foreach($temp as $user){
	 	echo getUserById($user,"NAME")->NAME . ", ";
		}

	}
	
 echo 'Bitte jetzt RFID Karte an Sensor halten!';
	
 echo '<div name="rfid" id="rfid"></div>';
  
  
  
  
  echo ' </div></div>
</body>
</html>
';


?>