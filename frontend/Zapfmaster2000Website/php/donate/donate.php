<?php




echo '<link rel="stylesheet" href="php/donate/register.css">';
echo '<script type="text/javascript" src="js/prototype.js"></script>';
echo '<script type="text/javascript" src="php/donate/donate.js"></script>';
echo '<div class="newsfeed">
  <div class="newsdiv">Donate the Ultimate Zapfmaster!.
	<div class="newscut"> </div>
  </div>
  <div class="news"><div style="float:left;"><img src="images/view/donate.png" width="200px"/></div><div><b>Spendet für den Zapfmaster 2000!</b><br /> Ihr braucht nichts weiter tun, als  die Menge einzutragen und danach eure Original Zapfmaster2000&reg; KeyCard an den Sensor zu halten!</div>';
  

	echo '<form action="php/donate/check.php" method="post">
<table border="0" cellpadding="5" cellspacing="0">
    <tr>
      <td align="right">Menge:</td>
      <td><input id="DONATION" name="DONATION" type="text" size="10">€</td>
    </tr>
  </table>
</form>';
	
 echo '<div name="rfid" id="rfid"></div>';
  
  
  
  
  echo ' </div></div>
</body>
</html>
';


?>