<?php
session_start();
$logout = isset($_GET['logout']) ? $_GET['logout']:undefined;
if(!isset($_SESSION["username"])){

    include 'php/logout.php';
	
}else if($logout==""){
	
    
	
   include 'php/logout.php';
}else if(isset($_POST['NEWKEG_BRAND'])){
 include 'php/dbconnect.php';
 include 'php/newshelper.php';
 $BRAND = $_POST["NEWKEG_BRAND"];
  $SIZE = $_POST["NEWKEG_SIZE"];
  $DATE = date("Y-m-d H:i:s");
 mysql_query("UPDATE t_kegs SET END_DATE ='".$DATE."' ORDER BY KEG_ID DESC LIMIT 1"); 
 mysql_query("INSERT INTO t_kegs (BRAND, SIZE, START_DATE) VALUES ('$BRAND', '$SIZE', '$DATE')");
 $content = 'Ein <b>neues Fass '.$BRAND.'</b> wurde angezapft!';
 $image= 'images/others/newkeg.jpg';
 createNews('OTHER', $content,$image);
 sendRequestOther($content,$image);

 include 'php/logout.php';
echo $DATE;
	
}else if(isset($_POST["NEWUSER_NAME"])){
	
 include 'php/dbconnect.php';
  include 'php/newshelper.php';
 $NAME = $_POST["NEWUSER_NAME"];
 $PASSWORD = $_POST["NEWUSER_PASSWORD"];
 $RFID_TAG =  $_POST["NEWUSER_RFID"];
 $IMAGE_PATH =  ($_POST["NEWUSER_IMAGE"]=="") ? "images/avatars/default.png":   $_POST["NEWUSER_IMAGE"];
 $GENDER = $_POST["NEWUSER_GENDER"];
 $WEIGHT =$_POST["NEWUSER_WEIGHT"];
 
 mysql_query("INSERT INTO T_USERS (NAME, PASSWORD, RFID_TAG,IMAGE_PATH,GENDER,WEIGHT) VALUES ('$NAME', '$PASSWORD', '$RFID_TAG','$IMAGE_PATH','$GENDER','$WEIGHT')");
 
 $content = 'Willkommen <b>'.$NAME.'</b>! Endlich kannst du an diesem intelligent durchkonstruierten Bierzapfsystem teilhaben!';
 $image= 'images/others/newuser.jpg';
 //@see newshelper.php
 createNews('OTHER', $content,$image);
 sendRequestOther($content,$image);

include 'php/logout.php';


}else if(isset($_POST["UPDATEUSER_NAME"])){
	
 include 'php/dbconnect.php';

 $NAME = $_POST["UPDATEUSER_NAME"];
 $PASSWORD = $_POST["UPDATEUSER_PASSWORD"];
 $RFID_TAG =  $_POST["UPDATEUSER_RFID"];

 mysql_query("UPDATE t_users SET RFID_TAG ='".$RFID_TAG."' WHERE NAME='".$NAME."'"); 
 
include 'php/logout.php';


	
	
}else{
	echo '	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>B.I.E.R</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="admincp.js"></script>
<style type="text/css">
body {
	background: none repeat scroll 0 0 #FFFFFF;
	background-image:url(images/view/backgrnd.jpg);
	color: black;
	font-family:Arial, Helvetica, sans-serif;
	margin: 0;
}
a:link {
	color: navy;
	font-weight: bold;
	text-decoration: none;
}
a:visited {
	color: navy;
	font-weight: bold;
	text-decoration: none;
}
a:active {
	color: navy;
	font-weight: bold;
	text-decoration: none;
}
a:hover {
	color: navy;
	font-weight: bold;
	text-decoration: none;
}
.news-box-info {
	color: #999999;
	font-size: 0.7em;
}
.news-box-info a:link {
	color: #999999;
	font-weight: normal;
	text-decoration: underline;
}
.news-box-info a:visited {
	color: #999999;
	font-weight: normal;
	text-decoration: underline;
}
.news-box-dateline {
	color: #CCCCCC;
	font-size: 0.7em;
	font-style: italic;
	text-align:right;
}
.news-drink-box-mugshot {
	float: left;
	margin-right: 12px;
}
.news-drink-box-details-headline {
	font-size:1.4em;
}
ul#topnav {
	width:950px;
	list-style:none;
	height:44px;
}
ul#topnav li {
	display:inline;
}
ul#topnav li a {
	height:44px;
	float:left;
	text-indent:-9999px;
}
ul#topnav li#topnav-0 a {
	width:115px;
	background:url(images/view/navi.png) no-repeat 0 0; /* X and Y position at 0 */
}
ul#topnav li#topnav-1 a {
	width:182px;
	background:url(images/view/navi.png) no-repeat -116px 0; /* X and Y position at 0 */
}
ul#topnav li#topnav-1 a:hover {
	background-position:-116px -44px; /* Y position -40px for Over instance image */
}
ul#topnav li#topnav-2 a {
	width:180px;
	background:url(images/view/navi.png) no-repeat -298px 0; /* X and Y position at 0 */
}
ul#topnav li#topnav-2 a:hover {
	background-position:-298px -44px; /* Y position -40px for Over instance image */
}
ul#topnav li#topnav-3 a {
	width:182px;
	background:url(images/view/navi.png) no-repeat -478px 0; /* X and Y position at 0 */
}
ul#topnav li#topnav-3 a:hover {
	background-position:-478px -44px; /* Y position -40px for Over instance image */
}
ul#topnav li#topnav-4 a {
	width:270px;
	background:url(images/view/navi.png) no-repeat -673px 0; /* X and Y position at 0 */
}
.news-drink-box {
	-webkit-border-radius: 4px;
	-khtml-border-radius:4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	border: 1px solid silver;
	padding: 8px;
	margin:3px;
	background-image:url(images/view/news_background.png);
}

.newsfeed {
	margin-bottom:5px;
	margin-left:40px;
	-webkit-border-radius: 4px;
	-khtml-border-radius:4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	border: 1px solid silver;
	padding: 8px;
	-moz-box-shadow: 1px 1px 3px #888;
	-webkit-box-shadow:1px 1px 3px #888;
	box-shadow: 1px 1px 3px #888;
	background-image:url(images/view/news_background.png);
	width:600px;
	height:auto;
}
.stats {
	float:left;
	margin-left:15px;
	-webkit-border-radius: 4px;
	-khtml-border-radius:4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	border: 1px solid silver;
	padding: 8px;
	-moz-box-shadow: 1px 1px 3px #888;
	-webkit-box-shadow:1px 1px 3px #888;
	box-shadow: 1px 1px 3px #888;
	background-image:url(images/view/news_background.png);
	width:270px;
	
}
.newsdiv {
	font-family:Verdana, Geneva, sans-serif;
	font-size:24px;
	margin-left:5px;
}
.statstext{
	font-family:Verdana, Geneva, sans-serif;
	font-size:12px;	
}
.newscut {
	background-image:url(images/view/cut.png);
	background-repeat:no-repeat;
	height:1px;
	
}
.achievement{
text-align:center;	
}
.statsdiv{
	font-size:12px;
	-webkit-border-radius: 4px;
	-khtml-border-radius:4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	border: 1px solid silver;
	padding: 3px;
	margin:3px;
	background-image:url(images/view/news_background.png);
	}
.statsHeadline{
	border:solid silver	1px;
	padding:5px;
	
	background-image:url(images/view/balken.png);
	-webkit-border-radius: 4px;
	-khtml-border-radius:4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	
	-moz-box-shadow: 1px 0px 2px #000;
	-webkit-box-shadow:1px 0px 2px #000;
	box-shadow: 1px 0px 2px #000;
	color:white;	
	text-align:center;
		
		}
.kegtext{
	margin-top:-10px;
	text-align:center;
	}
.kegtext-drinker{
	height:180px;
	width:130px;
	
	float:right;
	
}
.keg-chart{
	margin-top:-25px;
	margin-left:40px;
	
		
}
.statsImage{


}
.main{


}
	
}
</style>
</head>
<body>
';
	echo '<div class="newsfeed"><h1>Admin CP </h1><a href="admincp.php?logout">Logout</a></div>';
	//New Keg
	echo '<div class="newsfeed">
  <div class="newsdiv"> Register New Keg.
	<div class="newscut"> </div>
  </div>
  <div>';
  	
 	
	echo '<form action="admincp.php" method="post">
<table border="0" cellpadding="5" cellspacing="0">
    <tr>
      <td align="right">Marke:</td>
      <td><input name="NEWKEG_BRAND" type="text" size="30"></td>
    </tr>
    <tr>
      <td align="right">Fassgröße:</td>
      <td><input name="NEWKEG_SIZE" type="text" size="3" > Liter</td>
    </tr>
    <tr>
      <td align="right">Formular:</td>
      <td>
        <input type="submit" value=" Absenden ">
        
      </td>
    </tr>
  </table>
</form>
';
  echo '</div>';
    echo '</div>';
	
	//New User
	echo '<div class="newsfeed">
  <div class="newsdiv"> Register New User.
	<div class="newscut"> </div>
  </div>
  <div>';
  	
	echo '<form action="admincp.php" method="post">
<table border="0" cellpadding="5" cellspacing="0">
    <tr>
      <td align="right">Name:</td>
      <td><input name="NEWUSER_NAME" type="text" size="30"></td>
    </tr>
    <tr>
      <td align="right">Password:</td>
      <td><input name="NEWUSER_PASSWORD" type="password" size="30" ></td>
    </tr>
	<tr>
      <td align="right">Image:</td>
      <td><input name="NEWUSER_IMAGE" type="text" size="30" ></td>
    </tr>
	<tr>
      <td align="right">RFID_TAG</td>
      <td><input id="NEWUSER_RFID" name="NEWUSER_RFID" type="text" size="30"></td>
    </tr>
	<tr>
      <td align="right">Geschlecht</td>
      <td><input id="NEWUSER_GENDER" name="NEWUSER_GENDER" type="text" size="5"></td>
    </tr>
	<tr>
      <td align="right">Gewicht</td>
      <td><input id="NEWUSER_WEIGHT" name="NEWUSER_WEIGHT" type="text" size="5"></td>
    </tr>
	<tr>
      <td align="right">Formular:</td>
      <td>
        <input type="submit" value=" Absenden ">
      </td>
    </tr>
  </table>
</form>
';






 
  echo '</div>';
   echo '</div>';

	//New User
	echo '<div class="newsfeed">
  <div class="newsdiv"> Update Users Rfid Tag.
	<div class="newscut"> </div>
  </div>
  <div>';
  	
	echo '<form action="admincp.php" method="post">
<table border="0" cellpadding="5" cellspacing="0">
    <tr>
      <td align="right">Name:</td>
      <td><input name="UPDATEUSER_NAME" type="text" size="30"></td>
    </tr>
    <tr>
      <td align="right">Password:</td>
      <td><input name="UPDATEUSER_PASSWORD" type="password" size="30" ></td>
    </tr>
	<tr>
      <td align="right">RFID_TAG</td>
      <td><input id="UPDATEUSER_RFID" name="UPDATEUSER_RFID" type="text" size="30"></td>
    </tr>
	<tr>
      <td align="right">Formular:</td>
      <td>
        <input type="submit" value=" Absenden ">
      </td>
    </tr>
  </table>
</form>
';






  echo '</div>';
  echo '</div>';

}


?>




</body>
</html>