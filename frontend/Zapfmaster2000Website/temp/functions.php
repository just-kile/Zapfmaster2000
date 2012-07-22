<?php
	/**
	 * -----------------------------------------
	 * @author Nico Schubert / www.php-space.info
	 * @copyright Das Script kann unter Verwendung des Copyright uneingeschränkt genutzt / verändert werden. Das Copyright muss im Code sowie in der Ausgabe erhalten bleiben.
	 * @version Datei Upload Version 1.10 - 27.05.2011
	 * @abstract Das Script läuft erst ab der Php Version 5.0 oder höher, wenn Sie Thumbnail erstellen wollen, benötigen Sie GD Bibliothek in der Version 2.0.1 oder höher. Wenn Sie Probleme mit den Einrichten haben, so schauen Sie bitte in die Anleitung-> Installationsanleitung_1.09.pdff
	 * -----------------------------------------
	*/
	if(strpos("functions.php",$_SERVER["PHP_SELF"])) {
  		exit;
	}
	function fs_convert ($datei, $nachkommastellen = 0) {
		$size = @filesize($datei);
		if($size >= 1073741824) {
			return round($size/(1073741824), $nachkommastellen)." GB";
		}

		if($size >= 1048576) {
			return round($size/(1048576), $nachkommastellen)." MB";
		}

		if($size >= 1024) {
			return round($size/(1024), $nachkommastellen)." KB";
		}
    return $size." Byte";
	}
	function last_change ($site) {
		if(empty($site)) {
			$site = $_SERVER['DOCUMENT_ROOT'];
			$site.= $_SERVER['PHP_SELF'];
		}
    return filemtime($site);
	}
	function uploadmoeglichkeitpruefen(){
		$uploadmoeglichkeit=true;
		if(strtolower(@ini_get('file_uploads'))=='off' || @ini_get('file_uploads')==0){
			$uploadmoeglichkeit=false;
		}
	return $uploadmoeglichkeit;
	}
	function maximaledateiuploadgroesseermitteln(){
		$maximaledateiuploadgroesse=0;
		if($dateigroesse=ini_get('upload_max_filesize')){
			$maximaledateiuploadgroesse=phpiniwertumwandeln($dateigroesse);
		}
		if($postgroesse=ini_get('post_max_size')){
			$postgroesse=phpiniwertumwandeln($postgroesse);
			if($postgroesse<$maximaledateiuploadgroesse){
				$maximaledateiuploadgroesse=$postgroesse;
			}
		}
		return $maximaledateiuploadgroesse;
	}
	function phpiniwertumwandeln($groesse){
		$werte['MB'] = 1048576;
		$werte['Mb'] = 1048576;
		$werte['M'] = 1048576;
		$werte['m'] = 1048576;
		$werte['KB'] = 1024;
		$werte['Kb'] = 1024;
		$werte['K'] = 1024;
		$werte['k'] = 1024;

		while(list($schluessel)=each($werte)){
			if((strlen($groesse)>strlen($schluessel)) && (substr($groesse, strlen($groesse)-strlen($schluessel))==$schluessel))		{
				$groesse=substr($groesse, 0, strlen($groesse)-strlen($schluessel))*$werte[$schluessel];
				break;
			}
		}
		return $groesse;
	}
	function thumbnail($bild='', $bilder_path_orginalbild='', $bilder_path_thumbnail='', $thumbnail_neuebreite ){
		global $lang;
		$size= getimagesize($bilder_path_orginalbild.$bild) OR die($lang['file_groesse']);
		$breite=$size[0];
		$hoehe=$size[1];
		$neuebreite=$thumbnail_neuebreite;
		$neuehoehe= intval($hoehe*$neuebreite/$breite);

		if($size[2]==1) {
		// GIF
			$altesbild= imagecreatefromgif($bilder_path_orginalbild.$bild);
			$neuesbild= imagecreatetruecolor($neuebreite,$neuehoehe);
			imagecopyresized($neuesbild,$altesbild,0,0,0,0,$neuebreite,$neuehoehe,$breite,$hoehe);
			imagegif($neuesbild,$bilder_path_thumbnail."TN".$bild);
		}

		if($size[2]==2) {
		// JPG
			$altesbild= imagecreatefromjpeg($bilder_path_orginalbild.$bild);
			$neuesbild= imagecreatetruecolor($neuebreite,$neuehoehe);
			imagecopyresized($neuesbild,$altesbild,0,0,0,0,$neuebreite,$neuehoehe,$breite,$hoehe);
			imagejpeg($neuesbild,$bilder_path_thumbnail."TN".$bild);
		}

		if($size[2]==3) {
		// PNG
			$altesbild= imagecreatefrompng($bilder_path_orginalbild.$bild);
			$neuesbild= imagecreatetruecolor($neuebreite,$neuehoehe);
			imagecopyresized($neuesbild,$altesbild,0,0,0,0,$neuebreite,$neuehoehe,$breite,$hoehe);
			imagepng($neuesbild,$bilder_path_thumbnail."TN".$bild);
		}
		return $size[2];
	}
?>