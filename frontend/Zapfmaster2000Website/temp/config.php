<?php
	/**
	 * -----------------------------------------
	 * @author Nico Schubert / www.php-space.info
	 * @copyright Das Script kann unter Verwendung des Copyright uneingeschr�nkt genutzt / ver�ndert werden. Das Copyright muss im Code sowie in der Ausgabe erhalten bleiben.
	 * @version Datei Upload Version 1.10 - 27.05.2011
	 * @abstract Das Script l�uft erst ab der Php Version 5.0 oder h�her, wenn Sie Thumbnail erstellen wollen, ben�tigen Sie GD Bibliothek in der Version 2.0.1 oder h�her. Wenn Sie Probleme mit den Einrichten haben, so schauen Sie bitte in die Anleitung-> Installationsanleitung_1.09.pdff
	 * -----------------------------------------
	*/
	if (strpos("config.php",$_SERVER["PHP_SELF"])) {
  		exit;
	}
	/**
	 * Ordner zum Speichern der Bilder bzw. Datein
	 * Beispiel $img_ordner = '/img/';
	 */
	$img_ordner='/img/';

	/**
	 * Ordner zum Speichern der Thumbnails von den Bilder
	 * Beispiel $thumbnail_ordner ='/img/thumbnail/';
	 */
	$thumbnail_ordner ='/img/thumbnail/';

	/**
	 *  Thumbnails erstellen, wenn die Datei ein Bild ist?
	 *  0 = nein, 1 = ja
	 */
	$thumbnail_create =1;

	/**
	 *  Link zum L�schen anzeigen? Wenn der Link nicht angezeigt wird, kann keine Datei mehr gel�scht werden
	 *  0 = nein, 1 = ja
	 */
	$delete_link =1;

	/**
	 *  Maximal Thumbnail breite
	 */
	$thumbnail_neuebreite=70;

	/**
	 *  Maximal Datei Gr��e in KB (KiloByte)
	 */
	$groessemax = 10000;

	/**
	 *  Soviele Dateien pro Seite anzeigen
	 */
	$length = 3;

	/**
	 * Nur Bilder Uploaden?
	 * 0 = nein, 1 = ja
	 */
	$upload_erlaubnis = 1;

	/**
	 * Dateityp Einschr�nkungen, wenn Sie auch andere Dateien
	 * au�er Bilder erlauben, sollten Sie nur einen gewissen
	 * Dateityp zum Upload zu lassen.
	 *
	 * Beispiel f�r Text Datein:
	 * 		$dateityp_einschraenkung[]='text/plain';
	 * Beispiel f�r PDF Datein
	 * 		$dateityp_einschraenkung[]='application/pdf';
	 *
	 * Sollten Sie noch einen weiteren Dateityp ben�tigen,
	 * so erfragen Sie den bitte im Forum.
	 */
	$dateityp_einschraenkung[]='text/plain';
	$dateityp_einschraenkung[]='application/pdf';
	$dateityp_einschraenkung[]='application/zip';
	#$dateityp_einschraenkung[]='application/octet-stream';
	$dateityp_einschraenkung[]='application/x-zip-compressed';
	/**
	 * Dateiname Dynamisch erstellen beim Upload?
	 * 0 = nein, 1 = ja
	 */
	$dateiname_dynamisch = 0;

	/**
	 * Sprachdatei
	 */
	$language_file='language_de_sie.php';

	/**
	 * Zeitzone
	 */
	$timezone = 'Europe/Berlin';

	/**
	 * Logdatei f�r Php Fehler, in dieser Datei werden Php Fehler gespeichert
	 */
	$log_file='web.log';;
?>