<?php
	/**
	 * -----------------------------------------
	 * @author Nico Schubert / www.php-space.info
	 * @copyright Das Script kann unter Verwendung des Copyright uneingeschränkt genutzt / verändert werden. Das Copyright muss im Code sowie in der Ausgabe erhalten bleiben.
	 * @version Datei Upload Version 1.10 - 27.05.2011
	 * @abstract Das Script läuft erst ab der Php Version 5.0 oder höher, wenn Sie Thumbnail erstellen wollen, benötigen Sie GD Bibliothek in der Version 2.0.1 oder höher. Wenn Sie Probleme mit den Einrichten haben, so schauen Sie bitte in die Anleitung-> Installationsanleitung_1.09.pdff
	 * -----------------------------------------
	*/
	if(@file_exists('config.php')==true)
		include_once('config.php');
	else {
		 echo 'No Config File existing.';
		 exit;
	}
	if(@file_exists('language/'.$language_file)==true)
		include_once('language/'.$language_file);
	else {
		 echo 'No Language File existing.';
		 exit;
	}
	if(file_exists('error_log.php')==true)
		include_once('error_log.php');
	if(function_exists("date_default_timezone_set")==true)
		@date_default_timezone_set($timezone);
	$reg_exp="/^[a-z0-9_]([a-z0-9_-]*\.?[a-z0-9_-])*\.[a-z]{3,4}$/i";
	$url = (isset($_SERVER['HTTPS']) && $_SERVER['HTTPS'] == 'on') ? 'https://' : 'http://';
	$url .= $_SERVER['HTTP_HOST'] . dirname($_SERVER['PHP_SELF']);
	$document_root=$_SERVER["DOCUMENT_ROOT"].dirname($_SERVER['PHP_SELF']);
	$ausgabe='';
	if (!isset($_GET["loechen"])) $_GET["loechen"] = ""; else  $_GET["loechen"]=sprintf("%d",  $_GET["loechen"]);
	if (!isset($_GET["file"]))	$_GET["file"] = "";
	if(!isset($_POST["senden"])) $_POST["senden"]=''; else  $_POST["senden"]=sprintf("%d",  $_POST["senden"]);
	if(!isset($_POST["tn"])) $_POST["tn"]='';
	if(@file_exists('header.php')==true)
		include_once('header.php');
	if(@file_exists('functions.php')==true)
		include_once('functions.php');
	if(@file_exists('file_delete.php')==true)
		include_once('file_delete.php');
	if(@file_exists('processing.php')==true)
		include_once('processing.php');
	if(uploadmoeglichkeitpruefen()==false){
		$ausgabe.='<div style="color:red;">'.$lang['fehler_upload_nicht_m'].' <a href="'.htmlspecialchars($_SERVER['PHP_SELF']).'">'.$lang['navigationselemente_test'].'</a><br><br></div>'."\n";
		include_once('footer.php');
		exit;
	}
	if(@file_exists($document_root.$img_ordner)==false OR $document_root.$img_ordner ==''){
		$ausgabe.='<div style="color:red;">'.$lang['fehler_upload_no_directory'].' <a href="'.htmlspecialchars($_SERVER['PHP_SELF']).'">'.$lang['navigationselemente_test'].'</a><br><br></div>'."\n";
		include_once('footer.php');
		exit;
	}
	if(@file_exists('form.php')==true)
		include_once('form.php');
	if(@file_exists('file_list.php')==true)
		include_once('file_list.php');
	if(@file_exists('footer.php')==true)
		include_once('footer.php');
?>