<?php
	/**
	 * -----------------------------------------
	 * @author Nico Schubert / www.php-space.info
	 * @copyright Das Script kann unter Verwendung des Copyright uneingeschr�nkt genutzt / ver�ndert werden. Das Copyright muss im Code sowie in der Ausgabe erhalten bleiben.
	 * @version Datei Upload Version 1.10 - 27.05.2011
	 * @abstract Das Script l�uft erst ab der Php Version 5.0 oder h�her, wenn Sie Thumbnail erstellen wollen, ben�tigen Sie GD Bibliothek in der Version 2.0.1 oder h�her. Wenn Sie Probleme mit den Einrichten haben, so schauen Sie bitte in die Anleitung-> Installationsanleitung_1.09.pdff
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