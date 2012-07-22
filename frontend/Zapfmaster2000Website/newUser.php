<?php

if( $_POST["token"] == md5("test1234") ||$_GET["token"] == md5("test1234") ){
	
	header('Access-Control-Allow-Origin: *');
	if($_POST["action"] == "adduser"){
		include 'php/dbconnect.php';
		include 'php/newshelper.php';
		$NAME = $_POST["name"];
		$pinArr =  mysql_query("SELECT PASSWORD FROM T_USERS") or die("Fehler:".mysql_error($conn)."<br><br>$sql");
		$PASSWORD =rand(1000,9999);
		while ($row = mysql_fetch_array($result, MYSQL_NUM)) {
			$otherPass = $row->PASSWORD;
			while($PASSWORD ==$otherPass ){
				$PASSWORD =rand(1000,9999);
			};
		}
			
		
		
		
		$RFID_TAG =  $_POST["rfid"];
		//create image from string
		$im = imagecreatefromstring (base64_decode($_POST["image"]));

		//beschneide bild
		$dest = imagecreatetruecolor(48, 48);
		imagecopy($dest,$im,0,0,8,0,48,48);

		//Schreibe bild in ordner (schreibrechte benoetigt im folder)
		imagejpeg($dest,"images/avatars/".$RFID_TAG.".jpg",100);

		$IMAGE_PATH =  ($_POST["image"]=="") ? "images/avatars/default.png":   "images/avatars/".$RFID_TAG.".jpg";
		$GENDER = $_POST["gender"];
		$WEIGHT =$_POST["weight"];

		//insert into database
		mysql_query("INSERT INTO T_USERS (NAME, PASSWORD, RFID_TAG,IMAGE_PATH,GENDER,WEIGHT) VALUES ('$NAME', '$PASSWORD', '$RFID_TAG','$IMAGE_PATH','$GENDER','$WEIGHT')");

		// Free up memory
		imagedestroy($im);
		//set welcome message
		$content = 'Willkommen <b>'.$NAME.'</b>! Endlich kannst du an diesem intelligent durchkonstruierten Bierzapfsystem teilhaben!';
		$image= 'images/others/newuser.jpg';
		//@see newshelper.php
		createNews('OTHER', $content,$image);
		sendRequestOther($content,$image);
		echo $PASSWORD;
	}else if($_GET["action"] == "memberlist"){
		include 'php/dbconnect.php';
		$response = array();
		$ergebnis =  mysql_query("SELECT * FROM T_USERS ORDER BY NAME") or die("Fehler:".mysql_error($conn)."<br><br>$sql");
		while($row = mysql_fetch_object($ergebnis)){
			$tmp  = array();
			$tmp["userid"] =$row->USER_ID;
			$tmp["name"] = $row->NAME;
			$tmp["password"] =$row->PASSWORD;
			$tmp["gender"] =$row->GENDER;
			$tmp["image"] =$row->IMAGE_PATH;
			$tmp["rfid"] =$row->RFID_TAG;
			$tmp["weight"] =$row->WEIGHT;

			array_push($response,$tmp);
		}
		
		 echo json_encode($response);
	}else if($_POST["action"] == "newkeg"){
		include 'php/dbconnect.php';
		include 'php/newshelper.php';
		$BRAND = $_POST["brand"];
		$SIZE = $_POST["size"];
		$DATE = date("Y-m-d H:i:s");
		mysql_query("UPDATE t_kegs SET END_DATE ='".$DATE."' ORDER BY KEG_ID DESC LIMIT 1");
		mysql_query("INSERT INTO t_kegs (BRAND, SIZE, START_DATE) VALUES ('$BRAND', '$SIZE', '$DATE')");
		$content = 'Ein <b>neues Fass '.$BRAND.'</b> wurde angezapft!';
		$image= 'images/others/newkeg.jpg';
		createNews('OTHER', $content,$image);
		sendRequestOther($content,$image);

		//include 'php/logout.php';
		echo $DATE;



	}
}

?>