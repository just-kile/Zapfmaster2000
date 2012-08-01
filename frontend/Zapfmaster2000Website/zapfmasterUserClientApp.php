<?php

if( $_POST["token"] == md5("test1234") ||$_GET["token"] == md5("test1234") ){
	
	header('Access-Control-Allow-Origin: *');
	if($_GET["action"]=="login"){
		include 'php/dbconnect.php';
		$ergebnis =  mysql_query("SELECT 	u.RFID_TAG RFID_TAG,
											u.USER_ID USER_ID,
											u.NAME NAME,
											u.GENDER GENDER,
											u.IMAGE_PATH IMAGE_PATH,
											u.PASSWORD PASSWORD,
											u.WEIGHT WEIGHT,
											r.QR_CODE QR_CODE
										FROM t_users AS u JOIN t_qr_rfid AS r WHERE u.rfid_tag=r.rfid_tag") or die("Fehler:".mysql_error($conn)."<br><br>$sql");
		$pinHash = $_GET["user"];
		$breakFlag = true;
		$user = array();
		while($row = mysql_fetch_object($ergebnis)){
			$tmp  = array();
			
			$pass = $row->QR_CODE;
			if(md5($pass) == $pinHash){
				$tmp["userid"] =$row->USER_ID;
				$tmp["name"] = $row->NAME;
				$tmp["password"] =$row->PASSWORD;
				$tmp["gender"] =$row->GENDER;
				$tmp["image"] =$row->IMAGE_PATH;
				$tmp["rfid"] =$row->RFID_TAG;
				$tmp["weight"] =$row->WEIGHT;
				$tmp["qrcode"] = $pass;
				$user = $tmp;
				$breakFlag = false;
				
				//echo md5($pass)." ".$pinHash." ".(md5($pass) == $pinHash)."\n" ;
				
			}
			
		}
		if($breakFlag){
			//echo json_encode($user);
			header('HTTP/1.1 403 Forbidden');
		}else{
			echo json_encode($user);
		}
		
		

	}
	if(isset($_GET["newslist"])){
		include 'php/dbconnect.php';
		include 'php/dbStatsHelper.php';
  		$limit = 20;
	  	$start_num = $_GET["newslist"]*$limit;
	  	$end_num = 	$start_num+$limit;
	  $resp = array();
	  $ergebnis =  mysql_query("SELECT * FROM t_news ORDER BY NEWS_ID DESC LIMIT ".$start_num.",".$end_num) or die("Fehler:".mysql_error($conn)."<br><br>$sql");
	  
	
	  while($row = mysql_fetch_object($ergebnis))
	  {
		  $id =  $row->NEWS_ID;
		  $type = $row->TYPE;
		  $contents = $row->CONTENTS;
 		  $image_path = $row->IMAGE_PATH;
		  //Type = drawing: userid;amount;start_date;duration
		  
		  if($type==="DRAWING"){
			  $DRAWING_ID = $contents;
			 	//echo $DRAWING_ID;
			  $erg = mysql_query("SELECT * 
			FROM t_drawings AS d, t_users AS u,t_kegs AS k
				WHERE (
				d.DRAWING_ID =".$DRAWING_ID."
				AND u.user_id = d.user_id
				AND k.KEG_ID = d.KEG_ID
					)");//getDrawingsById($DRAWING_ID);
			 
			  if($entry =mysql_fetch_object($erg)){
				$tmpEntry = array();
			 	$tmpEntry["userid"] = utf8_encode($entry->USER_ID);
				
			  	$tmpEntry["amount"] = number_format($entry->REAL_AMOUNT,2,".","");
			  	$tmpEntry["date"] = $entry->DATE;
			  	$tmpEntry["duration"] =  $entry->DURATION;
			  	 echo $entry->d;
			  	//$roww =  getUserById($userid,"NAME,IMAGE_PATH");
			  	
			 	$tmpEntry["name"]= utf8_encode($entry->NAME);
				$tmpEntry["image"] = $entry->IMAGE_PATH;
				$tmpEntry["keg"] = $entry->KEG_ID;
			  	$tmpEntry["brand"] = $entry->BRAND;//getKegById($kegId,"BRAND")->BRAND;
			  	$tmpEntry["type"] = $type;
				array_push($resp,$tmpEntry);
			  }
		  }else if($type==="ACHIEVEMENT" && false){
			 $ACH_ID = $contents; //Group Id in T_Gained_Achievements
			 // $entry = getGainedAchievementById($ACH_ID);
			  
			
			  
			  $ergebns = $erg = mysql_query("SELECT * 
			FROM t_gained_achievements AS g, t_users AS u,t_achievements as a
				WHERE (
				g.group_id =".$ACH_ID."
				AND u.user_id = g.user_id
				AND a.achievement_id = g.achievement_id
					)") or die("Fehler(main.php,achievements:".mysql_error($conn)."<br><br>$sql");
			  $idUsrArr = array(); // contains usernames
			  
			  while($entry = mysql_fetch_object($ergebns)){
			  	$tmpEntry = array();
			 	$tmpEntry["userid"] = utf8_encode($entry->USER_ID);
			  	$tmpEntry["date"] = $entry->DATE;
			  	$tmpEntry["type"] = $type;
				$tmpEntry["achievement_id"] = $entry->ACHIEVEMENT_ID;
			 	$tmpEntry["date"]=  $entry->DATE;
		 
			  $tmpEntry["name"]  = $entry->NAME;
			  $tmpEntry["description"]  = $entry->DESCRIPTION;
			  
			  $tmpEntry["public"]  = $entry->PUBLIC;
		 		$tmpEntry["image"]  = $entry->IMAGE_PATH;
			  
			  //echo getNewsAchievement($userArr,$idUsrArr,$id,$achievementid,utf8_encode($name),utf8_encode($description),$image_path,$public,$achType,$startDate);				
			  	
		  }
 	array_push($resp,$tmpEntry);

		  }else if($type ==="OTHER" && false){
		  	$tmpEntry = array();
		  		
			  	$tmpEntry["text"] = $contents;
				$tmpEntry["type"] = $type;
				$tmpEntry["image"] = $image_path;
				//echo getNewsOthers($id,$image_path,utf8_encode($headline),"");
				array_push($resp,$tmpEntry);
		  }
		 
		  
	  }
	   echo json_encode($resp);
	}
}


?>