<?php

function myTest(){
    $qS = $_GET['query'];
    $miles = $_GET['miles'];
    $zip = $_GET['zip'];
    $lat = $_GET['lat'];
    $lng = $_GET['lng'];

    
	$json = file_get_contents("http://localhost:8080/LocationSearch/LocationSearch?query=".$qS."&miles=".$miles."&zip=".$zip."&lat=".$lat."&lng=".$lng."");
	return $json;
}
echo myTest();
?>
