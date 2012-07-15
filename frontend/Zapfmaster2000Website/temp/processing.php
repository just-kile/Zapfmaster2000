<?php
	/**
	 * -----------------------------------------
	 * @author Nico Schubert / www.php-space.info
	 * @copyright Das Script kann unter Verwendung des Copyright uneingeschränkt genutzt / verändert werden. Das Copyright muss im Code sowie in der Ausgabe erhalten bleiben.
	 * @version Datei Upload Version 1.10 - 27.05.2010
	 * @abstract Das Script läuft erst ab der Php Version 5.0 oder höher, wenn Sie Thumbnail erstellen wollen, benötigen Sie GD Bibliothek in der Version 2.0.1 oder höher. Wenn Sie Probleme mit den Einrichten haben, so schauen Sie bitte in die Anleitung -> Installationsanleitung_1.10.pdf
	 * -----------------------------------------
	*/
	if(strpos("processing.php",$_SERVER["PHP_SELF"])) {
  		exit;
	}
	$filename = '';
	if($_POST["senden"]==1){
		$ausgabe.='<br>'."\n";
		$ausgabe.='    <strong>'.$lang['navigationselemente_ueberscrift_file_upload'].'</strong><br><br>'."\n";
		if($_FILES['userfile']['tmp_name']==''){
			$ausgabe.='<div style="color:red;">'.$lang['file_auswaehlen'].' <a href="'.htmlspecialchars($_SERVER['PHP_SELF']).'">'.$lang['navigationselemente_nochmal'].'</a><br><br></div>'."\n";
			include_once('footer.php');
			exit;
		}
		if ($_FILES['userfile']['tmp_name']<> 'none' OR $_FILES['userfile']['tmp_name']!=''){
			$file = $_FILES['userfile']['name'];
			$temp = $_FILES['userfile']['tmp_name'];
			$path_parts = @pathinfo($file);
			if(!isset($path_parts["extension"])) $path_parts["extension"]='';
			if($_FILES['userfile']['type'] != 'image/x-png' && $_FILES['userfile']['type'] != 'image/gif' && $_FILES['userfile']['type'] != 'image/jpeg' && $_FILES['userfile']['type'] != 'image/png' && $_FILES['userfile']['type'] != 'image/jpeg' && $_FILES['userfile']['type'] != 'image/pjpeg') $ist_bild=0; else $ist_bild=1;
			if(function_exists("exif_imagetype")==true){
				if (exif_imagetype($temp) == (IMAGETYPE_GIF OR IMAGETYPE_JPEG OR IMAGETYPE_PNG)) $ist_bild=1; else $ist_bild=0;
			}
			if($upload_erlaubnis == 0) {
				if(is_array($dateityp_einschraenkung)==true){
					foreach($dateityp_einschraenkung as $k => $v){
						#echo $_FILES['userfile']['type'] .'=='. trim($v).'<br>';
						if($_FILES['userfile']['type'] == trim($v)){
							$ist_bild=1;
							break;
						}
					}
				} else {
					$ist_bild=1;
				}
			}
			if($ist_bild==0) {
				$ausgabe.='<div style="color:red;">'.(($upload_erlaubnis != 0) ? $lang['file_img'] : $lang['file_d']).' <a href="'.htmlspecialchars($_SERVER['PHP_SELF']).'">'.$lang['navigationselemente_nochmal'].'</a><br><br></div>'."\n";
				include_once('footer.php');
				exit;
			} else {
				if($path_parts["extension"]!=""){
					if($dateiname_dynamisch==1){
						$filename = "datei_" . time() . "." . $path_parts["extension"];
					} else {
						$filename = $file;
						$umlaute = array(
				            'ä' => 'ae',
				            'Ä' => 'ae',
				            'ß' => 'ss',
				            'ö' => 'oe',
				            'Ö' => 'oe',
				            'Ü' => 'ue',
				            'ü' => 'ue',
							' ' => '-',
				            'à' => 'a',
				            'é' => 'e',
				            'è' => 'e'
				        );
				        $filename = str_replace(array_keys($umlaute), array_values($umlaute),$filename);
						if(preg_match($reg_exp, $filename)==false){
							$ausgabe.='<div style="color:red;">'.$lang['file_name'].' <a href="'.htmlspecialchars($_SERVER['PHP_SELF']).'">'.$lang['navigationselemente_nochmal'].'</a><br><br></div>'."\n";
							include_once('footer.php');
							exit;
						}
					}
					if($_FILES['userfile']['size'] <= $groessemax*1024){
						if(decoct(fileperms($document_root.$img_ordner))==40777){
							if(@copy($temp, $document_root.$img_ordner.$filename)){
								chmod ($document_root.$img_ordner.$filename, 0755);
								$ausgabe.='<div style="color:green;">'.$lang['file_true'].'<br>';
								$ausgabe.='Url der Datei: <a href="'.htmlspecialchars($url.$img_ordner.$filename).'" target="_blank">'.htmlspecialchars($url.$img_ordner.$filename).'</a><br><br><a href="'.htmlspecialchars($_SERVER['PHP_SELF']).'">'.$lang['navigationselemente_noch_eine_datei'].'</a><br><br></div>'."\n";
								include_once('footer.php');
								exit;
							} else {
								$ausgabe.='<div style="color:red;">'.$lang['fehler_upload'].' <a href="'.htmlspecialchars($_SERVER['PHP_SELF']).'">'.$lang['navigationselemente_nochmal'].'</a><br><br></div>'."\n";
								include_once('footer.php');
								exit;
							}
						} else {
							$ausgabe.='<div style="color:red;">'.$lang['fehler_upload_rechte'].' <a href="'.htmlspecialchars($_SERVER['PHP_SELF']).'">'.$lang['navigationselemente_nochmal'].'</a><br><br></div>'."\n";
							include_once('footer.php');
							exit;
						}
					} else {
						$lang['fehler_upload_groesse']=str_replace("~groesse~",$groessemax,$lang['fehler_upload_groesse']);

						$ausgabe.='<div style="color:red;">'.$lang['fehler_upload_groesse'].' <a href="'.$_SERVER['PHP_SELF'].'">'.$lang['navigationselemente_nochmal'].'</a><br><br></div>'."\n";
						include_once('footer.php');
						exit;
					}
				}
			}
		}
	}
?>