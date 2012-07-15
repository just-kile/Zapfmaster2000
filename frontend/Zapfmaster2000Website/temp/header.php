<?php
	/**
	 * -----------------------------------------
	 * @author Nico Schubert / www.php-space.info
	 * @copyright Das Script kann unter Verwendung des Copyright uneingeschränkt genutzt / verändert werden. Das Copyright muss im Code sowie in der Ausgabe erhalten bleiben.
	 * @version Datei Upload Version 1.10 - 27.05.2011
	 * @abstract Das Script läuft erst ab der Php Version 5.0 oder höher, wenn Sie Thumbnail erstellen wollen, benötigen Sie GD Bibliothek in der Version 2.0.1 oder höher. Wenn Sie Probleme mit den Einrichten haben, so schauen Sie bitte in die Anleitung-> Installationsanleitung_1.09.pdff
	 * -----------------------------------------
	*/
	if(strpos("header.php",$_SERVER["PHP_SELF"])) {
  		exit;
	}
	$ausgabe.='<head> '."\n";
	$ausgabe.='<title>Datei Upload</title> '."\n";
	$ausgabe.='<!-- Php-Space.info / Datei Upload Version 1.09 - 23.12.2009 -->'."\n";
	$ausgabe.='<!-- (c) Nico Schuber '.date("Y").' - Kontakt: www.php-space.info - info[at]schubertmedia.de -->'."\n";
	$ausgabe.='<style type="text/css">'."\n";
	$ausgabe.='	<!--'."\n";
	$ausgabe.='	body, table{'."\n";
	$ausgabe.='		color: #000;'."\n";
	$ausgabe.='		font: 11px Verdana, Tahoma, Arial, Helvetica, sans-serif; '."\n";
	$ausgabe.='	}'."\n";
	$ausgabe.='	div{'."\n";
	$ausgabe.='		margin:0;'."\n";
	$ausgabe.='		padding:0;'."\n";
	$ausgabe.='	}'."\n";
	$ausgabe.='	-->'."\n";
	$ausgabe.='</style>'."\n";
	$ausgabe.='</head> '."\n";
	$ausgabe.='<body>'."\n";
?>