<!DOCTYPE html>
<html>
    <head>
        <style>
            body{
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }
        </style>
      <title>Enabling Assessment 8</title>
    </head>
<body>
<form method="post">
    <h2>Programmed by: Earl Lawrence Alonzo</h2>
    <h3>Grade Input:</h3>
    <label for="gradeInput">Enter your grade:</label>
    <input type="text" name="gradeInput" requ/>
    <input type="submit" value="Submit">
</form>

<?php
if ($_SERVER["REQUEST_METHOD"] == "POST"){
    $grade = $_POST["gradeInput"];

    if($grade < 1 && $grade >100){
        echo "Grade must range between 0 and 100";
    }
    else if($grade >= 60){
        echo "<h3>Grade of $grade% is First Division.</h3>";
    }
    else if($grade >= 45 && $grade <= 59){
        echo "<h3>Grade of $grade% is Second Division.</h3>";
    }
    else if($grade >= 33 && $grade <= 44){
        echo "<h3>Grade of $grade% is Third Division.</h3>";
    }
    else{
        echo "<h3>Failed.</h3>";
    }

} ?>


</body>
</html>