<!DOCTYPE html>
<html lang="en">
<head>
    <style>
            body{
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }
        </style>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Enabling Assessment 9</title>
</head>
<body>
    <form method="post">
        <h2>Programmed by: Earl Lawrence Alonzo</h2>
        <h3>Number Input:</h3>
        <label for="firstNumber">Enter First Number:</label>
        <input type="text" name="firstNumber" required/><br>
        <label for="secondNumber">Enter Second Number:</label>
        <input type="text" name="secondNumber" required/><br>
        <input type="submit" value="Submit"/>
    </form>
        <h2>Output:</h2>
    <?php
    if ($_SERVER["REQUEST_METHOD"] == "POST"){
        $firstNumber = $_POST["firstNumber"];
        $secondNumber = $_POST["secondNumber"];
        if($firstNumber == $secondNumber){
            echo "<h3>$firstNumber(Number 1) and $secondNumber(Number 2) are equal.</h3>";
        }

        else{
            echo "<h3>$firstNumber(Number 1) and $secondNumber(Number 2) are NOT equal.</h3>";
        }

    } ?>
</body>
</html>