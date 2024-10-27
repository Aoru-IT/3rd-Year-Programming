<?php
echo "Programmed by: Earl Lawrence Alonzo \n";
$t = date("H");

if($t < "10") {
    echo "Have a good morning!";
} elseif($t < "20"){
    echo "Have a good day!";   
} else {
    echo "Have a good night!";
}
    
?>