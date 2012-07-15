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
	$lang['file_delete_true']='Le fichier est supprimé!';
	$lang['file_delete_groesse']='Le fichier doit pas être plus grande que ~groesse~ KB (KiloByte).';
	$lang['file_true']='Le fichier ce trouve au serveur!';

	/**
	 * Fehler
	 */
	$lang['fehler_file_delete']='La vignette ne pourrait pas être supprimé!';
	$lang['no_file_delete']='Le fichier ne pourrait pas supprimer';
	$lang['file_delete_no_directory']='Ce fichier n\'existe pas dans le dossier upload';
	$lang['fehler_file_delete_no']='Il n\'est pas possible de supprimer un fichier, s\'il vous plaît tournez-vous à la variable sur Config.php $delete_link=\'1\';';
	$lang['file_list_no_file']='Il n\'y a pas encore un fichier dans le dossier upload';
	$lang['file_groesse']='Il n\'a pas pu être déterminée la taille.';
	$lang['file_auswaehlen']='S\'il vous plaît choisir un fichier que vous voulez charger sur le serveur.';
	$lang['file_img']='	Doit être en mesure de télécharger des fichiers GIF, PNG, JPEG et les fichiers JPG.';
	$lang['file_d']='	Doit être en mesure de télécharger des fichiers GIF, PNG, JPEG, JPG, Text, PDF, Zip et les fichiers RAR.';
	$lang['file_name']='Le nom de fichier n\'est pas valide!';
	$lang['fehler_upload']='Le fichier n\'a pas pu être chargé sur le serveur.';
	$lang['fehler_upload_rechte']='Le dossier d\'upload n\'est pas accessible en écriture!';
	$lang['fehler_upload_groesse']='Le fichier est trop gros! Le fichier ne doit pas dépasser ~groesse~ KB (KiloByte) .';
	$lang['fehler_upload_nicht_m']='Sur ce serveur on ne peut pas faire un upload, s\'il vous plaît contacter votre hébergeur et demander l\'activation de la fonction PHP upload.';
	$lang['fehler_upload_no_directory']='Il n\'y a pas de dossier où les images pourriont chargées!';

	/**
	 * Navigationselemente
	 */
	$lang['navigationselemente_zurueck']='retour';
	$lang['navigationselemente_ueberscrift_file_delete']='supprimer';
	$lang['navigationselemente_ueberscrift_file_list']='liste de fichiers';
	$lang['navigationselemente_ueberscrift_file_upload']='fichier télécharcher';
	$lang['navigationselemente_file']='fichier';
	$lang['navigationselemente_loeschen']='supprimer';
	$lang['navigationselemente_groesse']='taille';
	$lang['navigationselemente_letzte_aenderung']='dernière modification';
	$lang['navigationselemente_aktion']='action';
	$lang['navigationselemente_speichern']='enregistrer';
	$lang['navigationselemente_nochmal']='encore une fois...';
	$lang['navigationselemente_noch_eine_datei']='un autre fichier télécharger...';
	$lang['navigationselemente_test']='tester';
?>