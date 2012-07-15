<?php
//sets useful functions for db requests
function getUserById($id,$name){
	return getDBEntryById("t_users","USER_ID",$id,$name);
}

function getAchievementById($id,$name){
	return getDBEntryById("t_achievements","ACHIEVEMENT_ID",$id,$name);
}

function getGainedAchievementById($id,$name){
	return getDBEntryById("t_gained_achievements","GROUP_ID",$id,$name);
}

function getDrawingsById($id,$name){
	return getDBEntryById("t_drawings","DRAWING_ID",$id,$name);
}

function getKegById($id,$name){
	
	return getDBEntryById("t_kegs","KEG_ID",$id,$name);
	
}

function getConfigByKey($id,$colname){
	
	return getDBEntryById("t_config","CONF_KEY","'".$id."'",$colname);
}

function getDBEntryById($tableName,$idColName,$id,$colName){
	if(isset($colName)){
  		$ergebnis =  mysql_query("SELECT ".$colName." FROM ".$tableName." WHERE ".$idColName."=".$id) or die("Fehlr:".mysql_error($conn)."<br><br>$sql");
	}else{
	 	$ergebnis =  mysql_query("SELECT "."*"." FROM ".$tableName." WHERE ".$idColName."=".$id) or die("Feher:".mysql_error($conn)."<br><br>$sql");
	}
	
  if($row = mysql_fetch_object($ergebnis)){
	  return $row;
  }else{
	  return undefined;
  }
}


?>