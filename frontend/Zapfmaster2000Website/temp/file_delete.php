<?php
	/**
	 * -----------------------------------------
	 * @author Nico Schubert / www.php-space.info
	 * @copyright Das Script kann unter Verwendung des Copyright uneingeschränkt genutzt / verändert werden. Das Copyright muss im Code sowie in der Ausgabe erhalten bleiben.
	 * @version Datei Upload Version 1.10 - 27.05.2011
	 * @abstract Das Script läuft erst ab der Php Version 5.0 oder höher, wenn Sie Thumbnail erstellen wollen, benötigen Sie GD Bibliothek in der Version 2.0.1 oder höher. Wenn Sie Probleme mit den Einrichten haben, so schauen Sie bitte in die Anleitung-> Installationsanleitung_1.09.pdff
	 * -----------------------------------------
	*/
	if(strpos("file_delete.php",$_SERVER["PHP_SELF"])) {
  		exit;
	}
	if($delete_link!=1 AND $_GET["loechen"]==1 AND $_GET["file"]!=''){
		$ausgabe.='<br>'."\n";
		$ausgabe.='    <strong>'.$lang['navigationselemente_ueberscrift_file_delete'].'</strong><br><br>'."\n";
		$ausgabe.='<div style="color:red;">'.$lang['fehler_file_delete_no'].' <a href="'.htmlspecialchars($_SERVER['PHP_SELF']).'">'.$lang['navigationselemente_zurueck'].'</a><br><br></div>'."\n";
		include_once('footer.php');
		exit;
	} else {
		if($_GET["loechen"]==1 AND $_GET["file"]!=''){
			$ausgabe.='<br>'."\n";
			$ausgabe.='    <strong>'.$lang['navigationselemente_ueberscrift_file_delete'].'</strong><br><br>'."\n";
			if(@file_exists($document_root.$img_ordner.$_GET["file"])==true){
				$type = (explode(".", $document_root.$thumbnail_ordner.$_GET["tn"]));
				foreach($type as $key => $value) $type[$key] = strtolower($value);
				if($type[count($type)-1] == 'gif' OR $type[count($type)-1] == 'jpeg' OR $type[count($type)-1] == 'png' OR $type[count($type)-1] == 'jpg'){
					if($thumbnail_create==1 AND @file_exists($document_root.$thumbnail_ordner.$_GET["tn"])==true){
						if(@unlink($document_root.$thumbnail_ordner.$_GET["tn"])==false){
							$ausgabe.='<div style="color:red;">'.$lang['fehler_file_delete'].' <a href="'.htmlspecialchars($_SERVER['PHP_SELF']).'">'.$lang['navigationselemente_zurueck'].'</a><br><br></div>'."\n";
							include_once('footer.php');
							exit;
						}
					}
				}
				if(@unlink($document_root.$img_ordner.$_GET["file"])==true){
					$ausgabe.='<div style="color:green;">'.$lang['file_delete_true'].' <a href="'.htmlspecialchars($_SERVER['PHP_SELF']).'">'.$lang['navigationselemente_zurueck'].'</a><br><br></div>'."\n";
					include_once('footer.php');
					exit;
				} else {
					$ausgabe.='<div style="color:red;">'.$lang['no_file_delete'].' <a href="'.htmlspecialchars($_SERVER['PHP_SELF']).'">'.$lang['navigationselemente_zurueck'].'</a><br><br></div>'."\n";
					include_once('footer.php');
					exit;
				}
			} else {
				$ausgabe.='<div style="color:red;">'.$lang['file_delete_no_directory'].' <a href="'.htmlspecialchars($_SERVER['PHP_SELF']).'">'.$lang['navigationselemente_zurueck'].'</a><br><br></div>'."\n";
				include_once('footer.php');
				exit;
			}
		}
	}
?>