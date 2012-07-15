<?php 
function getNewsDrawing($user,$userid,$amount,$zapfdate,$timestamp,$id,$imagepath,$kegId,$kegBrand){
		
	
	$imgTag = createTag('img',"",array("src","border","height","width"),array($imagepath,0,48,48));
	$ahrefImg = createTag('a',$imgTag,array("href"),array("index.php?user=".$userid));
	$divImg = createTag('div',$ahrefImg ,null,null);
	$divImgComplete = createTag('div',$divImg ,array("class"),array("news-drink-box-mugshot"));
	
	$headline = createTag('a',$user,array("href"),array('index.php?user='.$userid)).' zapfte <b>'.number_format($amount ,2).'l</b> in <b>'.$zapfdate.'s</b>';
	$divHeadline = createTag('div',$headline,array("class"),array("news-drink-box-details-headline"));
	$ahrefPlace = createTag('a',"Harzhaus",array("href"),array("place.php"));
	$ahrefId = createTag('a',$id,array("href"),array("index.php?id=".$id));

	$ahrefKegId = createTag('a',$kegId,array('href'),array("index.php?keg=".$kegId));
		
	$divNewsBoxInfo = createTag('div',"Ort: ".$ahrefPlace. " Fass: ".$ahrefKegId." der Marke ".$kegBrand ,array("class"),array("news-box-info"));
	
	$divNewsBoxDrinkDetailsComplete =  createTag('div',$divHeadline.$divNewsBoxInfo ,array("class"),array("news-drink-box-details"));
	
	$abbrDate = createTag('abbr',"gezapft am ".$timestamp." Uhr",array("class","title"),array("timeago",$timestamp));
	$divDateComplete  = createTag('div',$abbrDate ,array("class"),array("news-box-dateline"));
	
	
	
	$divTextComplete = createTag('div',$divImgComplete.$divNewsBoxDrinkDetailsComplete.$divDateComplete,array("class"),array("news-drink-box"));
	
	
	return $divTextComplete ;
	
	
}
function getNewsAchievement($userArr,$idUsrArr,$newsid,$achievementid,$name,$description,$imagepath,$public,$type,$timestamp){
	$count = count($userArr);
	$user = "";
	$reaches = "";
	if($count>1)
	{
		for($i=0;$i<$count;$i++){
			$temp = "";
			if($i==0){
				$temp = $idUsrArr[$userArr[$i]];
				$user = createTag('a',$temp,array("href"),array('index.php?user='.$userArr[$i]));
			}else if($i == $count-1){
				$temp = $idUsrArr[$userArr[$i]];
				$user =$user." und ".createTag('a',$temp,array("href"),array('index.php?user='.$userArr[$i]));
			}else{
				$temp = $idUsrArr[$userArr[$i]];
				$user =$user.", ".createTag('a',$temp,array("href"),array('index.php?user='.$userArr[$i]));
			}
			$reaches = " erreichten";
		}
	 }else{
			$user = createTag('a',$idUsrArr[$userArr[0]],array("href"),array('index.php?user='.$userArr[0]));
			$reaches = " erreichte";
			
	}
	
	
	
	
	
	
	$imgTag = createTag('img',"",array("src","border","height","width"),array($imagepath,0,48,48));
	$ahrefImg = createTag('a',$imgTag,array("href"),array("index.php?achievement=".$achievementid));
	$divImg = createTag('div',$ahrefImg ,null,null);
	$divImgComplete = createTag('div',$divImg ,array("class"),array("news-drink-box-mugshot"));
	if($public){
		$public = ' öffentlichen ';
	}else{
		$public = ' geheimen ';
	}
	
	$ahrefAchievementLink = '<br/><b>'.createTag('a',$name,array('href'),array('index.php?achievement='.$achievementid)).'</b>';
	$headline = $user. $reaches. ' den '.$public.' Erfolg '.$ahrefAchievementLink;
	$divHeadline = createTag('div',$headline,array("class"),array("news-drink-box-details-headline"));
	$ahrefPlace = createTag('a',"Harzhaus",array("href"),array("place.php"));
	$ahrefId = createTag('a',$newsid,array("href"),array("index.php?id=".$newsid));
	$divNewsBoxInfo = createTag('div',"Ort: ".$ahrefPlace ,array("class"),array("news-box-info"));
	$divNewsBoxDrinkDetailsComplete =  createTag('div',$divHeadline.$divNewsBoxInfo ,array("class"),array("news-drink-box-details"));
	
	$abbrDate = createTag('abbr',"erhalten am ".$timestamp.' Uhr',array("class","title"),array("timeago",$timestamp));
	$divDateComplete  = createTag('div',$abbrDate ,array("class"),array("news-box-dateline"));
	
	
	
	$divTextComplete = createTag('div',$divImgComplete.$divNewsBoxDrinkDetailsComplete.$divDateComplete,array("class"),array("news-drink-box"));
	
	
	return $divTextComplete ;
}

function getNewsOthers($id,$imagepath,$headline,$description){
	
	$imgTag = createTag('img',"",array("src","border","height","width"),array($imagepath,0,48,48));
	$divImg = createTag('div',$imgTag ,null,null);
	$divImgComplete = createTag('div',$divImg ,array("class"),array("news-drink-box-mugshot"));
	
	
	$divHeadline = createTag('div',$headline,array("class"),array("news-drink-box-details-headline"));
	$ahrefPlace = createTag('a',"Harzhaus",array("href"),array("place.php"));
	$ahrefId = createTag('a',$id,array("href"),array("index.php?id=".$id));
	$divNewsBoxInfo = createTag('div',"Ort: ".$ahrefPlace ,array("class"),array("news-box-info"));
	$divNewsBoxDrinkDetailsComplete =  createTag('div',$divHeadline.$divNewsBoxInfo ,array("class"),array("news-drink-box-details"));
	
	//$abbrDate = createTag('abbr',"gezapft am ".$timestamp.'Uhr',array("class","title"),array("timeago",$timestamp));
	//$divDateComplete  = createTag('div',$abbrDate ,array("class"),array("news-box-dateline"));
	
	
	
	$divTextComplete = createTag('div',$divImgComplete.$divNewsBoxDrinkDetailsComplete,array("class"),array("news-drink-box"));
	
	
	return $divTextComplete ;
}


function createTag($tagname,$value,$attributesName,$attributesValue){
	
	$attributesTag =array();
	if($attributesName != null){
	for( $i=0;$i<count($attributesName);$i++){
		array_push($attributesTag," ".$attributesName[$i].'="'.$attributesValue[$i].'"');
	}
	}
	
	if($value===""){
		return '<'.$tagname .implode($attributesTag) ." />";
	}else{
		return '<'.$tagname .implode($attributesTag) .">" . $value . "</".$tagname.">";
		}
}


?>