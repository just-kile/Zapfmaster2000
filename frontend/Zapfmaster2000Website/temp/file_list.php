<?php
	/**
	 * -----------------------------------------
	 * @author Nico Schubert / www.php-space.info
	 * @copyright Das Script kann unter Verwendung des Copyright uneingeschränkt genutzt / verändert werden. Das Copyright muss im Code sowie in der Ausgabe erhalten bleiben.
	 * @version Datei Upload Version 1.10 - 27.05.2011
	 * @abstract Das Script läuft erst ab der Php Version 5.0 oder höher, wenn Sie Thumbnail erstellen wollen, benötigen Sie GD Bibliothek in der Version 2.0.1 oder höher. Wenn Sie Probleme mit den Einrichten haben, so schauen Sie bitte in die Anleitung-> Installationsanleitung_1.09.pdff
	 * -----------------------------------------
	*/
	if(strpos("file_list.php",$_SERVER["PHP_SELF"])) {
  		exit;
	}
	$ausgabe.='<br><strong>'.$lang['navigationselemente_ueberscrift_file_list'].'</strong><br><br> '."\n";
	$ausgabe.='<table border="0" bordercolor="Gray" bgcolor="DarkGray" cellspacing="2" cellpadding="2" width="490">'."\n";
	$ausgabe.='	<tr align="middle" bgcolor="Gainsboro" style="font-weight:bold">'."\n";
	$ausgabe.='		<td><SPAN class="fett">'.$lang['navigationselemente_file'].'</SPAN></td>'."\n";
	$ausgabe.='		<td><SPAN class="fett">'.$lang['navigationselemente_groesse'].'</SPAN></td>'."\n";
	$ausgabe.='		<td><SPAN class="fett">'.$lang['navigationselemente_letzte_aenderung'].'</SPAN></td>'."\n";
	if($delete_link==1){
		$ausgabe.='		<td><SPAN class="fett">'.$lang['navigationselemente_aktion'].'</SPAN></td>'."\n";
	}
	$ausgabe.='	</tr>'."\n";
	$page = isset($_GET['page']) ? $_GET['page'] : 0;
	$offset = 0;
	$offset = $page * $length;
	$verzeichnis_daten = array();
	$fp = opendir ($document_root.$img_ordner);
	while (false !== ($file = readdir($fp))) {
		if (filetype($document_root.$img_ordner.$file)!="dir"){
			$verzeichnis_daten[] = $file;
		}
	}
	closedir($fp);
	asort($verzeichnis_daten);
	for ($i = $offset; $i < $offset+$length AND $i < count($verzeichnis_daten); $i++) {
		$ausgabe.='<tr bgcolor="Gainsboro">'."\n";
		$type_check = explode(".",$verzeichnis_daten[$i]);
		foreach($type_check as $key => $value) $type_check[$key] = strtolower($value);
		if($thumbnail_create==1 AND ($type_check[count($type_check)-1]=='jpg' OR $type_check[count($type_check)-1]=='gif' OR $type_check[count($type_check)-1]=='png' OR $type_check[count($type_check)-1]=='jpeg')){
			$typ=thumbnail($verzeichnis_daten[$i], $document_root.$img_ordner , $document_root.$thumbnail_ordner, $thumbnail_neuebreite );
			if($typ==1 OR $typ==2 OR $typ==3) $ausgabe.='   <td align="center"><a target="_blank" href="'.htmlspecialchars($url.$img_ordner.$verzeichnis_daten[$i]).'"><img border="0" src="'.htmlspecialchars($url.$thumbnail_ordner.'TN'.$verzeichnis_daten[$i]).'" alt="'.$verzeichnis_daten[$i].'"></a></td>'."\n";
			else $ausgabe.='	<td><a href="'.htmlspecialchars($url.$img_ordner.$verzeichnis_daten[$i]).'"  target="_blank">'.$verzeichnis_daten[$i].'</a></td>'."\n";
		} else {
			$ausgabe.='	<td><a href="'.htmlspecialchars($url.$img_ordner.$verzeichnis_daten[$i]).'"  target="_blank">'.$verzeichnis_daten[$i].'</a></td>'."\n";
		}
		$ausgabe.='	<td>'.fs_convert($document_root.$img_ordner.$verzeichnis_daten[$i],2).'</td>'."\n";
		$ausgabe.='	<td>'.date("d.m.Y H:i", last_change($document_root.$img_ordner.$verzeichnis_daten[$i])).'</td>'."\n";
		if($delete_link==1){
			$ausgabe.='	<td><a href="'.htmlspecialchars($_SERVER["PHP_SELF"]).'?file='.$verzeichnis_daten[$i].'&tn=TN'.$verzeichnis_daten[$i].'&loechen=1" >'.$lang['navigationselemente_loeschen'].'</td>'."\n";
		}
		$ausgabe.='</tr>'."\n";
	}
	if(count($verzeichnis_daten)==0){
		$ausgabe.='<tr bgcolor="Gainsboro">'."\n";
		$ausgabe.='	<td colspan="4">'.$lang['file_list_no_file'].'</td>'."\n";
		$ausgabe.='</tr>'."\n";
	}
	$ausgabe.='</table>'."\n";

	if ($page > 0 AND count($verzeichnis_daten)!=0) {
		$ausgabe.='<a href="'.htmlspecialchars($_SERVER['PHP_SELF']).'?page='.($page-1).'">&laquo;</a> '."\n";
	}
	if (( count($verzeichnis_daten)/$length ) > ($page+1) AND count($verzeichnis_daten)!=0 ) {
		$ausgabe.='<a href="'.htmlspecialchars($_SERVER['PHP_SELF']).'?page='.($page+1).'">&raquo;</a> '."\n";
	}
?>