<?php
	/**
	 * -----------------------------------------
	 * @author Nico Schubert / www.php-space.info
	 * @copyright Das Script kann unter Verwendung des Copyright uneingeschränkt genutzt / verändert werden. Das Copyright muss im Code sowie in der Ausgabe erhalten bleiben.
	 * @version Datei Upload Version 1.10 - 27.05.2011
	 * @abstract Das Script läuft erst ab der Php Version 5.0 oder höher, wenn Sie Thumbnail erstellen wollen, benötigen Sie GD Bibliothek in der Version 2.0.1 oder höher. Wenn Sie Probleme mit den Einrichten haben, so schauen Sie bitte in die Anleitung -> Installationsanleitung_1.09.pdf
	 * @editor Torsten Reuschel u. Nico Schubert (Übersetzung ins Englische)
	 * -----------------------------------------
	*/

	/**
	 * Ausgaben / user information
	 */
	$lang['file_delete_true']='File successfully deleted!';
	$lang['file_delete_groesse']=' the file must not be bigger than ~groesse~ KB (KiloByte).';
	$lang['file_true']='File already exists!';

	/**
	 * Fehler / error
	 */
	$lang['fehler_file_delete']='Could not delete thumbnail!';
	$lang['no_file_delete']='Could not delete file.';
	$lang['file_delete_no_directory']='The requested file is not in the upload directory!!';
	$lang['fehler_file_delete_no']='Currently it is not possible to delete a file. Please edit Config.php and set $delete_link=\'1\';';
	$lang['file_list_no_file']='No files uploaded yet.';
	$lang['file_groesse']='Was not able to determine size of image.';
	$lang['file_auswaehlen']='Please choose a file you would like to upload.';
	$lang['file_img']='	Please do upload GIF, PNG, JPEG or JPG files only!';
	$lang['file_d']='Please do upload GIF, PNG, JPEG, JPG, Text, PDF, Zip or RAR files only!';
	$lang['file_name']='Corrupt filename!';
	$lang['fehler_upload']='Was not able to upload file to server.';
	$lang['fehler_upload_rechte']='No write-permission for the upload directory!';
	$lang['fehler_upload_groesse']='The file is too big! The file must not be bigger than ~groesse~ KB (KiloByte).';
	$lang['fehler_upload_nicht_m']='You are not allowed to use the PHP upload-feature. Please contact your webhoster and ask for permission.';
	$lang['fehler_upload_no_directory']='There is no directory for storage of images!';

	/**
	 * Navigationselemente / navigation
	 */
	$lang['navigationselemente_zurueck']='back';
	$lang['navigationselemente_ueberscrift_file_delete']='Delete File';
	$lang['navigationselemente_ueberscrift_file_list']='Filelist';
	$lang['navigationselemente_ueberscrift_file_upload']='Upload File';
	$lang['navigationselemente_file']='File';
	$lang['navigationselemente_loeschen']='Delete';
	$lang['navigationselemente_groesse']='Size';
	$lang['navigationselemente_letzte_aenderung']='Last Update';
	$lang['navigationselemente_aktion']='Action';
	$lang['navigationselemente_speichern']='Store';
	$lang['navigationselemente_nochmal']='Try again...';
	$lang['navigationselemente_noch_eine_datei']='Upload another file...';
	$lang['navigationselemente_test']='Test';
?>
