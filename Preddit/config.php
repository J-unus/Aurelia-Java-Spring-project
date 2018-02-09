<?php

/* Attempt to connect to MySQL database */
$link = mysqli_connect("localhost","st2014","progress","st2014");
 
// Check connection
if($link === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}
?>
