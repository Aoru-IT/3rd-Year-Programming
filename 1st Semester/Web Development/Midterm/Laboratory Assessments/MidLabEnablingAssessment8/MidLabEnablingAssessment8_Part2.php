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
    <h3>Day Input:</h3>
    <label for="gradeInput">Enter day of the week:</label>
    <input type="text" name="dayInput" required/>
    <input type="submit" value="Submit"/>
</form>

<h2>Output:</h2>
<?php
if ($_SERVER["REQUEST_METHOD"] == "POST"){
    $day = $_POST["dayInput"];

    switch($day){
        case 1:
            echo "<h3>Current Day: Monday</h3>";
            break;
        case 2:
            echo "<h3>Current Day: Tuesday</h3>";
            break;
        case 3:
            echo "<h3>Current Day: Wednesday</h3>";
            break;
        case 4:
            echo "<h3>Current Day: Thursday</h3>";
            break;
        case 5:
            echo "<h3>Current Day: Friday</h3>";
            break;
        case 6:
            echo "<h3>Current Day: Saturday</h3>";
            break;
        case 7:
            echo "<h3>Current Day: Sunday</h3>";
            break;
        default:
            echo "<h3>Invalid Number</h3>";
    }

} ?>
</body>
</html>