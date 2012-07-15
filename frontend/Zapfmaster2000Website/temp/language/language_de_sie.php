<?php
	/**
	 * -----------------------------------------
	 * @author Nico Schubert / www.php-space.info
	 * @copyright Das Script kann unter Verwendung des Copyright uneingeschränkt genutzt / verändert werden. Das Copyright muss im Code sowie in der Ausgabe erhalten bleiben.
	 * @version Datei Upload Version 1.10 - 27.05.2011
	 * @abstract Das Script läuft erst ab der Php Version 5.0 oder höher, wenn Sie Thumbnail erstellen wollen, benötigen Sie GD Bibliothek in der Version 2.0.1 oder höher. Wenn Sie Probleme mit den Einrichten haben, so schauen Sie bitte in die Anleitung -> Installationsanleitung_1.09.pdf
	 * -----------------------------------------
	*/

	/**
	 * Ausgaben
	 */
	$lang['file_delete_true']='Die Datei wurde erfolgreich gelöscht!';
	$lang['file_delete_groesse']=' die Datei darf nicht größer sein als ~groesse~ KB (KiloByte).';
	$lang['file_true']='Datei ist auf den Server!';

	/**
	 * Fehler
	 */
	$lang['fehler_file_delete']='Das Thumbnail könnte nicht gelöscht werden!';
	$lang['no_file_delete']='Die Datei konnte nicht gelöscht werden.';
	$lang['file_delete_no_directory']='Die Datei gibt es nicht im Upload Verzeichnis!';
	$lang['fehler_file_delete_no']='Es ist das Löschen einer Datei nicht möglich, bitte schalten Sie in der Config.php dazu die Variabel auf $delete_link=\'1\';';
	$lang['file_list_no_file']='Es ist noch keine Datei im Upload Ordner.';
	$lang['file_groesse']='Es konnte keine Bildgröße ermittelt werden.';
	$lang['file_auswaehlen']='Bitte wählen Sie eine Datei aus, die Sie auf den Server laden möchten.';
	$lang['file_img']='Nur GIF, PNG, JPEG und JPG Dateien dürfen hochgeladen werden.';
	$lang['file_d']='Nur GIF, PNG, JPEG, JPG, Text, PDF, Zip oder RAR Dateien dürfen hochgeladen werden.';
	$lang['file_name']='Der Dateiname ist Ungültig!';
	$lang['fehler_upload']='Die Datei konnte nicht auf den Server geladen werden.';
	$lang['fehler_upload_rechte']='Der Upload Ordner hat keine Schreibrechte!';
	$lang['fehler_upload_groesse']='Die Datei ist zu groß! Die Datei darf nicht größer als ~groesse~ KB (KiloByte) sein.';
	$lang['fehler_upload_nicht_m']='Es ist auf diesen Server kein Upload möglich, bitte wenden Sie sich an Ihren Webhoster und bitten um Aktivierung der Php Upload Funktion.';
	$lang['fehler_upload_no_directory']='Es gibt kein Ordner wohin die Bilder geladen werden sollen!';

	/**
	 * Navigationselemente
	 */
	$lang['navigationselemente_zurueck']='zurück';
	$lang['navigationselemente_ueberscrift_file_delete']='Datei Löschen';
	$lang['navigationselemente_ueberscrift_file_list']='Datei Liste';
	$lang['navigationselemente_ueberscrift_file_upload']='Datei hochladen';
	$lang['navigationselemente_file']='Datei';
	$lang['navigationselemente_loeschen']='Löschen';
	$lang['navigationselemente_groesse']='Größe';
	$lang['navigationselemente_letzte_aenderung']='Letzte Änderung';
	$lang['navigationselemente_aktion']='Aktion';
	$lang['navigationselemente_speichern']='Speichern';
	$lang['navigationselemente_nochmal']='Noch mal versuchen...';
	$lang['navigationselemente_noch_eine_datei']='Eine weitere Datei auf den Server laden...';
	$lang['navigationselemente_test']='Testen';
?>