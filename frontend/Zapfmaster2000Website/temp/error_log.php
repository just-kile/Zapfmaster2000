<?php
	/**
	 * -----------------------------------------
	 * @author Nico Schubert / www.php-space.info
	 * @copyright Das Script kann unter Verwendung des Copyright uneingeschränkt genutzt / verändert werden. Das Copyright muss im Code sowie in der Ausgabe erhalten bleiben.
	 * @version Datei Upload Version 1.10 - 27.05.2011
	 * @abstract Das Script läuft erst ab der Php Version 5.0 oder höher, wenn Sie Thumbnail erstellen wollen, benötigen Sie GD Bibliothek in der Version 2.0.1 oder höher. Wenn Sie Probleme mit den Einrichten haben, so schauen Sie bitte in die Anleitung-> Installationsanleitung_1.09.pdff
	 * -----------------------------------------
	*/
	function myHandler($code, $msg, $file, $line) {
		global $log_file;
	    $logData = date("d-M-Y h:i:s", time()) . ", $code, $msg, $line, $file\n";
	    @file_put_contents($log_file, $logData, FILE_APPEND);
	}
	if(file_exists($log_file)==true){
		set_error_handler('myHandler');
		/**
		 * @abstract Fehler in einer Datei protokollieren
		 */
	} else {
		echo 'No Log File existing.';
		exit;
	}
?>