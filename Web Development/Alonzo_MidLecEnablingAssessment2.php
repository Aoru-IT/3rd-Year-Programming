<!DOCTYPE html>
<html lang="en">
    <head>
        <style>
            body{
                font-family: Bahnschrift;
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

            .name{
                color: #001F3F;
                font-weight: 30;
                text-align: center;
                margin: 10vw 0 2vw auto;
            }

            .bodyArea {
                background-color: #001F3F;
                height: 15vw;
                width: 60vw;
                margin: 0 auto;
            }

            .userArea {
                padding-left: 22.5vw;
                padding-top: 1.3%;
                color: white;
            }
            
            .nameFields{
                height: 30px;
                width: 100px;
            }

            .textFields{
                height: 30px;
                width: 325px;
            } 

            .resultsArea {
                background-color: #3A6D8C;
                height: 16vw;
                width: 60vw;
                margin-top: 30px;
                margin-left: auto;
                margin-right: auto;
            }

            .userResultsArea{
                color: white;
                padding-top: 0.02%;
                
            }

            .userResultsArea ul{
                padding-left: 22.5vw;
            }
            
            .userResultsArea li{
                list-style-type: none;
            }

            .userResultsArea h3{
                text-align: center; 
            }

         

        </style>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=content-width, initial-scale=1.0">
    <title>Total Salary Computation</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
<body>
              <h2 class="name">Programmed by: Earl Lawrence Alonzo</h2>

              <?php if (isset($_POST['submitted'])): 
                $fullName = $_POST['lastName'].", ".$_POST['firstName']." ".$_POST['middleInitial'].".";
                $daysWorked = $_POST['daysWorked'];
                $monthlyGrossSalary = 535.00 * $daysWorked;

                $tax = $monthlyGrossSalary * 0.12;
                $pagIbig = 200.00;
                $sss = 1800.00;
                $deductions = $tax + $pagIbig + $sss;
                $totalNetSalary = $monthlyGrossSalary - $deductions;
                $commission = $totalNetSalary * 0.30;
                $totalSalary = $totalNetSalary + $commission;
                ?>
               <div class="resultsArea">
                <div class="userResultsArea">
                    <h3>Computation Result:</h3>
                    <ul>
                    <li>Full Name: <b><?php echo $fullName ?></b></li>
                    <li>Days Worked: <b><?php echo $daysWorked ?></b></li>
                    <li>Total Gross Salary for the Month: <b><?php echo "Php $monthlyGrossSalary"?></b></li><br>
                    <li>Pag Ibig: <b><?php echo "Php $pagIbig"?></b></li>
                    <li>Tax: <b><?php echo "Php $tax"?></b></li>
                    <li>SSS: <b><?php echo "Php $sss"?></b></li>
                    <li>Total Deduction: <b><?php echo "Php $deductions"?></b></li><br>
                    <li>Total Commission: <b><?php echo "Php $commission" ?></b></li>
                    <li>Total Net Salary: <b><?php echo "Php $totalNetSalary" ?></b></li>
                    <li>Total Salary: <b><?php echo "Php $totalSalary" ?></b></li>
                    </ul>
                 </div>  
               </div>
               <p style="text-align:center">Go <a href="/index.php">back</a> to the form</p>


            <?php else: ?>
              <div class="bodyArea">
                <div class="userArea">
                    <form method="post" action="index.php">
                    <p>Full Name:</p>
                    <input class="nameFields" type="text" name="lastName" placeholder="Last Name" required>
                    <input class="nameFields" type="text" name="firstName" placeholder="First Name" required>
                    <input class="nameFields" type="text" name="middleInitial" placeholder="M.I." required>
                    <p>Numbers of Days Worked:</p>
                    <input class="textFields" type="text" name="daysWorked" required><br>
                    <input type="hidden" name="submitted" value="1">
                    <input class="submitButton" type="submit">
                    </form>
                    </div>
               </div>
            <?php endif; ?>
              
</body>
</html>