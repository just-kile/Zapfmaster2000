<?php
	/**
	 * -----------------------------------------
	 * @author Nico Schubert / www.php-space.info
	 * @copyright Das Script kann unter Verwendung des Copyright uneingeschränkt genutzt / verändert werden. Das Copyright muss im Code sowie in der Ausgabe erhalten bleiben.
	 * @version Datei Upload Version 1.10 - 26.05.2011
	 * @abstract Das Script läuft erst ab der Php Version 5.0 oder höher, wenn Sie Thumbnail erstellen wollen, benötigen Sie GD Bibliothek in der Version 2.0.1 oder höher. Wenn Sie Probleme mit den Einrichten haben, so schauen Sie bitte in die Anleitung -> Installationsanleitung_1.09.pdf
	 * -----------------------------------------
	*/

	/**
	 * Ausgaben
	 */
	$lang['file_delete_true']='De data is succesvol verwijdert.';
	$lang['file_delete_groesse']=' Het document mag niet groter zijn dan ~groesse~ KB (KiloByte).';
	$lang['file_true']='Document staat op de server.';

	/**
	 * Fehler
	 */
	$lang['fehler_file_delete']='Het voorbeeld kon niet verwijderd worden.';
	$lang['no_file_delete']='Het document kon niet verwijderd worden.';
	$lang['file_delete_no_directory']='Het document bevind zich niet in de upload folder!';
	$lang['fehler_file_delete_no']='Het is niet mogelijk om het document te verwijderen, verander de Config.php van Variabel in $delete_link=\'1\';';
	$lang['file_list_no_file']='Er bevindt zich nog geen document in de upload folder.';
	$lang['file_groesse']='Er kon geen beeldgrootte vastgesteld worden.';
	$lang['file_auswaehlen']='Kies het document dat u op de server wil plaatsen.';
	$lang['file_img']='	Alleen GIF, PNG, JPEG und JPG documenten kunnen ge-upload worden.';
	$lang['file_d']='	Alleen GIF, PNG, JPEG, JPG, Text, PDF, Zip, RAR documenten kunnen ge-upload worden.';
	$lang['file_name']='Het documentnaam is ongeldig!';
	$lang['fehler_upload']='Het document kon niet op de server geplaatst worden.';
	$lang['fehler_upload_rechte']='De upload folder heeft geen wegschrijf authorisatie!';
	$lang['fehler_upload_groesse']='Het document is te groot! Het document mag niet groter zijn dan ~groesse~ KB (KiloByte).';
	$lang['fehler_upload_nicht_m']='Het is niet mogelijk om op deze server documenten te plaatsen, neem contact op met uw Webhoster en vraag om activering van de Php Upload functie.';
	$lang['fehler_upload_no_directory']='Er is geen map waar u uw foto’s kan achterlaten!';

	/**
	 * Navigationselemente
	 */
	$lang['navigationselemente_zurueck']='Terug';
	$lang['navigationselemente_ueberscrift_file_delete']='Document verwijderen';
	$lang['navigationselemente_ueberscrift_file_list']='Document lijst';
	$lang['navigationselemente_ueberscrift_file_upload']='Document uploaden';
	$lang['navigationselemente_file']='Document';
	$lang['navigationselemente_loeschen']='Verwijderen';
	$lang['navigationselemente_groesse']='Grote';
	$lang['navigationselemente_letzte_aenderung']='Laatste verandering';
	$lang['navigationselemente_aktion']='Uitvoertaak';
	$lang['navigationselemente_speichern']='Opslaan';
	$lang['navigationselemente_nochmal']='Nog eens proberen...';
	$lang['navigationselemente_noch_eine_datei']='Nog een document op de server opslaan...';
	$lang['navigationselemente_test']='Testen';
?>
