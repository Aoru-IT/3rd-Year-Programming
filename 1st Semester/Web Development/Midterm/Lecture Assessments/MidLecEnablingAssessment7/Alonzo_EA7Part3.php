<!DOCTYPE html>
<html lang="en">
    <head>
        <title>EA7 Part 3</title>
    </head>
    <body>
    <h2>Part 3: The IF-ELSEIF-ELSE Statement</h2>
    <h3>Programmed by: Earl Lawrence Alonzo</h3>

    <?php
    $t = date("H");

    if($t < "10") {
       echo "Have a good morning!";
    } elseif($t < "20"){
       echo "Have a good day!";   
    } else {
       echo "Have a good night!";
    }
    
?>

    </body>
</html>