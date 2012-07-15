	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ZAPFMASTER 2000</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="style.css" />
<link rel="SHORTCUT ICON" href="images/view/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="main">
  <div class="header"><div class="zapfmaster"></div></div>
  <div class="nav">
  <ul id="topnav">
    <li id="topnav-0"><a href="index.php" title="Home">Home</a></li>
    <li id="topnav-1"><a href="index.php" title="Home">Home</a></li>
    <li id="topnav-2"><a href="index.php?stats" title="Stats">Services</a></li>
    <li id="topnav-3"><a href="index.php?members" title="Members">About Us</a></li>
    <li id="topnav-5"><a href="index.php?achievementlist" title="Achievements">About Us</a></li>
    <li id="topnav-4"><a href="#" >Contact Us</a></li>
  </ul>
  </div>
  <?php

 include 'php/dbconnect.php';
 include 'php/htmlbuilder.php';
 include 'php/dbStatsHelper.php';
 
 $site = isset($_GET['p']) ?$_GET['p']:'news' ;
 $stats = isset($_GET['stats']);
 $members = isset($_GET['members']);
 $admin = isset($_GET['admin']);
 //stats
if($stats){
	include 'php/stats/stats.php';
}else if($members){
	include 'php/members/members.php';
}else if($admin){
	include 'php/admin/register.php';
}else if(isset($_GET['user']) && $_GET['user']!=""){
	
	include 'php/user/user.php';
}else if(isset($_GET['achievement']) && $_GET['achievement']!=""){
	
	include 'php/achievement/achievement.php';
}else if(isset($_GET['donate'])){
	
	include 'php/donate/donate.php';

}else if(isset($_GET['achievementlist'])){
	include 'php/achievementlist/achievementlist.php';

}else if(isset($_GET['p'])){
	include 'php/list/list.php';
}else{
	include 'php/main.php';
}
  ?>
</div>
</body>
</html>
