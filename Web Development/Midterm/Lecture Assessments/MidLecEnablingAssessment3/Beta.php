<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PHP Form Processing</title>
</head>
<body>
<form action="Beta.php" method="POST">
<h1>Color selection</h1>
<h2>Programmed by: Earl Lawrence Alonzo</h2>
<h2>               John Emmanuelle Arellado</h2>
<h2>               Math Daenniel Dela Rosa </h2>
<h2>               Ken Christian Divino</h2>
<h2>               Rovic Rodriguez</h2>
<h2>BIT33</h2>
<h2>09-26-24</h2>
        <label for="inputColor">Input a color: </label>
        <input type="text" name="inputColor" required><br><br>    
        <input type="submit" name="submit" value="Submit">
        
<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $favColor = $_POST['inputColor'];

if($favColor == "red"){
    echo "Your favorite color is red";
}else if($favColor == "blue"){
    echo "Your favorite color is blue";
}else{
    echo "Your favorite color is green";        
}
}
?>
</body>
</html>