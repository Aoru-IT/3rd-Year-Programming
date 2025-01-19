<!DOCTYPE html>
<html lang="en">
<head>
<style>
            body{
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f8f1e4;  
            }
            
            .submitButton{
                font-weight: bold;
                font-family: Bahnschrift;
                margin: 30px 0;
                padding: 5px 30px;
            }

            .pageTitle{
                color: #001F3F;
                font-weight: 400;
                text-align: center;
                margin: 4vw 0 2vw auto;
            }

            .bodyArea {
                background-color: #a7e5ea;
                height: 20vw;
                width: 40vw;
                margin: 0 auto;
            }

            .userArea {
                padding-left: 12.2vw;
                padding-top: 6%;
                color: #001F3F;
            }

            .userArea label {
               font-weight: 500;
            }
            
            .userArea input{
                height: 25px;
                width: 300px;
                font-weight: 500;
            }
            
            .userArea input[type=submit]{
                margin-top: 0.8vw;
                width: 310px;
                height: 35px;
                background-color: #001F3F;
                color: white;
                border: none;
            }

            .userArea select{
                width: 310px;
                height: 32px;
            }

            .resultsArea {
                background-color: #a7e5ea;
                height: 28vw;
                width: 40vw;
                margin: 0 auto;
                padding-top: 1.5vw;
                text-align:center;
                color: #001F3F;
            }


            .resultsArea label{
                font-size: 35px;
            
            }

            .resultsArea p{
                font-weight: 500;
            }
            
        </style>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tuition Calculator</title>
</head>
<body>

<?php if(!isset($_POST['form-submitted'])): ?>
    <h1 class="pageTitle">Tuition Calculator</h1>


    <div class="bodyArea">
    <form method="post" class="userArea">
        <label for="firstName">First Name:</label><br>
        <input type="text" name="firstName" required /><br>

        <label for="lastName">Last Name:</label><br>
        <input type="text" name="lastName" required /><br>

        <label for="course">Course:</label><br>
        <select name="course">
            <option>BCS</option>
            <option>BIT</option>
        </select><br>

        <label for="units">Units to Enroll:</label><br>
        <input type="number" name="units" required /><br><br>
        <input type="hidden" name="form-submitted" value="1"/>
        <input type="submit" value="Submit and Calculate"/>


    </form>
    </div>
    <h3 class="pageTitle">Programmed by: Earl Lawrence Alonzo</h3>
    <?php else: 
        $firstName = $_POST["firstName"];
        $lastName = $_POST["lastName"];
        $course = $_POST["course"];
        $units = $_POST["units"];
        
        $unitRate = 0;
        $miscellaneous = 1500;

        if($course == "BCS"){
            $unitRate = 100;
        }
        else if($course == "BIT"){
            $unitRate = 150;
        }
        $totalTuition = ($unitRate * $units) + $miscellaneous;
    ?>
         
        <h1 class="pageTitle">Calculation Results:</h1>
        <div class="resultsArea">
        <h3>First Name:</h3>
        <label><?php echo "$firstName" ?></label><br/>
        <h3>Last Name:</h3>
        <label><?php echo "$lastName" ?></label><br/>
        <h3>Course:</h3>
        <label><?php echo "$course" ?></label><br/>
        <h3>Total Tuition:</h3>
        <label><?php echo "$totalTuition" ?></label><br/><br/>

        <p>Go <a href="/Midterm.php">back</a> to the form</p>
        </div>
        <h3 class="pageTitle">Programmed by: Earl Lawrence Alonzo</h3>
    <?php endif; ?>
</body>
</html>