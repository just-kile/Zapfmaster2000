<?php
	/**
	 * -----------------------------------------
	 * @author Nico Schubert / www.php-space.info
	 * @copyright Das Script kann unter Verwendung des Copyright uneingeschränkt genutzt / verändert werden. Das Copyright muss im Code sowie in der Ausgabe erhalten bleiben.
	 * @version Datei Upload Version 1.10 - 27.05.2011
	 * @abstract Das Script läuft erst ab der Php Version 5.0 oder höher, wenn Sie Thumbnail erstellen wollen, benötigen Sie GD Bibliothek in der Version 2.0.1 oder höher. Wenn Sie Probleme mit den Einrichten haben, so schauen Sie bitte in die Anleitung-> Installationsanleitung_1.09.pdff
	 * -----------------------------------------
	*/
	if(strpos("form.php",$_SERVER["PHP_SELF"])) {
  		exit;
	}
	$ausgabe.='<br>'."\n";
	$ausgabe.='    <strong>'.$lang['navigationselemente_ueberscrift_file_upload'].'</strong><br><br>'."\n";
	if($_POST["senden"]==0){
		$ausgabe.='<form method="post" enctype="multipart/form-data" action="'.htmlspecialchars($_SERVER["PHP_SELF"]).'">'."\n";
		$ausgabe.='	<input type="hidden" value="1" name="senden">'."\n";
		$lang['file_delete_groesse']=str_replace("~groesse~",(((maximaledateiuploadgroesseermitteln()/1024)>$groessemax) ? $groessemax : (maximaledateiuploadgroesseermitteln()/1024)),$lang['file_delete_groesse']);
		$ausgabe.=$lang['file_delete_groesse'].'<br><br>'."\n";
		$ausgabe.='	<input name="userfile" type="file" size="40"><br><br>'."\n";
		$ausgabe.='	<input type="submit" name="action" value="'.$lang['navigationselemente_speichern'].'">'."\n";
		$ausgabe.='</form> '."\n";
	}
?>