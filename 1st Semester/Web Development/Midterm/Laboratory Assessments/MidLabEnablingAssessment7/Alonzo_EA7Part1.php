<!DOCTYPE html>
<html lang="en">
    <head>
        <title>EA7 Part 1</title>
    </head>
    <body>
    <h2>Part 1: The IF Statement</h2>
    <h3>Programmed by: Earl Lawrence Alonzo</h3>
    <form method="post">
        <label for="currentHour">Enter current hour:</label>
        <input type="text" name="currentHour" required/>
        <input type="submit" name="submit"><br><br>
        <h3>Output:</h3> 
    </form>

    <?php 
    if ($_SERVER["REQUEST_METHOD"] == "POST"){
        if (isset($_POST["currentHour"])){
            $t = $_POST["currentHour"];
            if($t < "20") {
                echo "Have a good day!";
            }
        }
        }

        ?>
    </body>
</html>