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
        <h3>Day Input:</h3>
        <label for="gradeInput">Enter day of the week:</label>
        <input type="text" name="dayInput" required/>
        <input type="submit" value="Submit"/>
    </form>

    <?php
    if ($_SERVER["REQUEST_METHOD"] == "POST"){
        $day = $_POST["dayInput"];

        if($day== 1){
            echo "<h3>Current Day: Monday</h3>";
        }
        else if($day== 2){
            echo "<h3>Current Day: Tuesday</h3>";
        }
        else if($day == 3){
            echo "<h3>Current Day: Wednesday</h3>";
        }
        else if($day == 4){
            echo "<h3>Current Day: Thursday</h3>";
        }   
        else if($day == 5){
            echo "<h3>Current Day: Friday</h3>";
        }
        else if($day == 6){
            echo "<h3>Current Day: Saturday</h3>";
        }
        else if($day == 7){
            echo "<h3>Current Day: Sunday</h3>";
        }
        else{
            echo "<h3>Invalid Number</h3>";
        }

    } ?>
</body>
</html>