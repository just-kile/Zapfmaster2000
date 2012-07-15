<?php
	/**
	 * -----------------------------------------
	 * @author Nico Schubert / www.php-space.info
	 * @copyright Das Script kann unter Verwendung des Copyright uneingeschr�nkt genutzt / ver�ndert werden. Das Copyright muss im Code sowie in der Ausgabe erhalten bleiben.
	 * @version Datei Upload Version 1.10 - 26.05.2011
	 * @abstract Das Script l�uft erst ab der Php Version 5.0 oder h�her, wenn Sie Thumbnail erstellen wollen, ben�tigen Sie GD Bibliothek in der Version 2.0.1 oder h�her. Wenn Sie Probleme mit den Einrichten haben, so schauen Sie bitte in die Anleitung -> Installationsanleitung_1.09.pdf
	 * -----------------------------------------
	*/

	/**
	 * Ausgaben
	 */
	$lang['file_delete_true']='Le fichier est supprim�!';
	$lang['file_delete_groesse']='Le fichier doit pas �tre plus grande que ~groesse~ KB (KiloByte).';
	$lang['file_true']='Le fichier ce trouve au serveur!';

	/**
	 * Fehler
	 */
	$lang['fehler_file_delete']='La vignette ne pourrait pas �tre supprim�!';
	$lang['no_file_delete']='Le fichier ne pourrait pas supprimer';
	$lang['file_delete_no_directory']='Ce fichier n\'existe pas dans le dossier upload';
	$lang['fehler_file_delete_no']='Il n\'est pas possible de supprimer un fichier, s\'il vous pla�t tournez-vous � la variable sur Config.php $delete_link=\'1\';';
	$lang['file_list_no_file']='Il n\'y a pas encore un fichier dans le dossier upload';
	$lang['file_groesse']='Il n\'a pas pu �tre d�termin�e la taille.';
	$lang['file_auswaehlen']='S\'il vous pla�t choisir un fichier que vous voulez charger sur le serveur.';
	$lang['file_img']='	Doit �tre en mesure de t�l�charger des fichiers GIF, PNG, JPEG et les fichiers JPG.';
	$lang['file_d']='	Doit �tre en mesure de t�l�charger des fichiers GIF, PNG, JPEG, JPG, Text, PDF, Zip et les fichiers RAR.';
	$lang['file_name']='Le nom de fichier n\'est pas valide!';
	$lang['fehler_upload']='Le fichier n\'a pas pu �tre charg� sur le serveur.';
	$lang['fehler_upload_rechte']='Le dossier d\'upload n\'est pas accessible en �criture!';
	$lang['fehler_upload_groesse']='Le fichier est trop gros! Le fichier ne doit pas d�passer ~groesse~ KB (KiloByte) .';
	$lang['fehler_upload_nicht_m']='Sur ce serveur on ne peut pas faire un upload, s\'il vous pla�t contacter votre h�bergeur et demander l\'activation de la fonction PHP upload.';
	$lang['fehler_upload_no_directory']='Il n\'y a pas de dossier o� les images pourriont charg�es!';

	/**
	 * Navigationselemente
	 */
	$lang['navigationselemente_zurueck']='retour';
	$lang['navigationselemente_ueberscrift_file_delete']='supprimer';
	$lang['navigationselemente_ueberscrift_file_list']='liste de fichiers';
	$lang['navigationselemente_ueberscrift_file_upload']='fichier t�l�charcher';
	$lang['navigationselemente_file']='fichier';
	$lang['navigationselemente_loeschen']='supprimer';
	$lang['navigationselemente_groesse']='taille';
	$lang['navigationselemente_letzte_aenderung']='derni�re modification';
	$lang['navigationselemente_aktion']='action';
	$lang['navigationselemente_speichern']='enregistrer';
	$lang['navigationselemente_nochmal']='encore une fois...';
	$lang['navigationselemente_noch_eine_datei']='un autre fichier t�l�charger...';
	$lang['navigationselemente_test']='tester';
?>