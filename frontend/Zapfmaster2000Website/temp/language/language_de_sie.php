<?php
	/**
	 * -----------------------------------------
	 * @author Nico Schubert / www.php-space.info
	 * @copyright Das Script kann unter Verwendung des Copyright uneingeschr�nkt genutzt / ver�ndert werden. Das Copyright muss im Code sowie in der Ausgabe erhalten bleiben.
	 * @version Datei Upload Version 1.10 - 27.05.2011
	 * @abstract Das Script l�uft erst ab der Php Version 5.0 oder h�her, wenn Sie Thumbnail erstellen wollen, ben�tigen Sie GD Bibliothek in der Version 2.0.1 oder h�her. Wenn Sie Probleme mit den Einrichten haben, so schauen Sie bitte in die Anleitung -> Installationsanleitung_1.09.pdf
	 * -----------------------------------------
	*/

	/**
	 * Ausgaben
	 */
	$lang['file_delete_true']='Die Datei wurde erfolgreich gel�scht!';
	$lang['file_delete_groesse']=' die Datei darf nicht gr��er sein als ~groesse~ KB (KiloByte).';
	$lang['file_true']='Datei ist auf den Server!';

	/**
	 * Fehler
	 */
	$lang['fehler_file_delete']='Das Thumbnail k�nnte nicht gel�scht werden!';
	$lang['no_file_delete']='Die Datei konnte nicht gel�scht werden.';
	$lang['file_delete_no_directory']='Die Datei gibt es nicht im Upload Verzeichnis!';
	$lang['fehler_file_delete_no']='Es ist das L�schen einer Datei nicht m�glich, bitte schalten Sie in der Config.php dazu die Variabel auf $delete_link=\'1\';';
	$lang['file_list_no_file']='Es ist noch keine Datei im Upload Ordner.';
	$lang['file_groesse']='Es konnte keine Bildgr��e ermittelt werden.';
	$lang['file_auswaehlen']='Bitte w�hlen Sie eine Datei aus, die Sie auf den Server laden m�chten.';
	$lang['file_img']='Nur GIF, PNG, JPEG und JPG Dateien d�rfen hochgeladen werden.';
	$lang['file_d']='Nur GIF, PNG, JPEG, JPG, Text, PDF, Zip oder RAR Dateien d�rfen hochgeladen werden.';
	$lang['file_name']='Der Dateiname ist Ung�ltig!';
	$lang['fehler_upload']='Die Datei konnte nicht auf den Server geladen werden.';
	$lang['fehler_upload_rechte']='Der Upload Ordner hat keine Schreibrechte!';
	$lang['fehler_upload_groesse']='Die Datei ist zu gro�! Die Datei darf nicht gr��er als ~groesse~ KB (KiloByte) sein.';
	$lang['fehler_upload_nicht_m']='Es ist auf diesen Server kein Upload m�glich, bitte wenden Sie sich an Ihren Webhoster und bitten um Aktivierung der Php Upload Funktion.';
	$lang['fehler_upload_no_directory']='Es gibt kein Ordner wohin die Bilder geladen werden sollen!';

	/**
	 * Navigationselemente
	 */
	$lang['navigationselemente_zurueck']='zur�ck';
	$lang['navigationselemente_ueberscrift_file_delete']='Datei L�schen';
	$lang['navigationselemente_ueberscrift_file_list']='Datei Liste';
	$lang['navigationselemente_ueberscrift_file_upload']='Datei hochladen';
	$lang['navigationselemente_file']='Datei';
	$lang['navigationselemente_loeschen']='L�schen';
	$lang['navigationselemente_groesse']='Gr��e';
	$lang['navigationselemente_letzte_aenderung']='Letzte �nderung';
	$lang['navigationselemente_aktion']='Aktion';
	$lang['navigationselemente_speichern']='Speichern';
	$lang['navigationselemente_nochmal']='Noch mal versuchen...';
	$lang['navigationselemente_noch_eine_datei']='Eine weitere Datei auf den Server laden...';
	$lang['navigationselemente_test']='Testen';
?>