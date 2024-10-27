<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #F5EFFF;
        }

        .pageTitle {
            color: #5C2FC2;
            font-weight: 400;   
            text-align: center;
            margin: 4vw 0 2vw auto;
        }

        .bodyArea, .resultsArea {
            background-color: #CDC1FF;
            height: auto;
            width: 40vw;
            max-width: 90%; 
            margin: 2vw auto;
            border-radius: 15px;
            padding: 1.5vw;
            text-align: center;
            overflow: hidden; 
        }

        .bodyArea {
            height: 7vw;
        }

        .resultsArea {
            height: 3.5vw;
        }

        h3 {
            color: #5C2FC2;
        }

        label, p {
            color: #5C2FC2;
            font-weight: 500;
            margin: 0.5vw 0;
        }

        input {
            font-weight: 500;
        }

        input[type=number] {
            height: 25px;
            width: 300px;
            text-align: center;
        }

        input[type=submit] {
            margin-top: 0.8vw;
            width: 310px;
            height: 35px;
            background-color: #5C2FC2;
            color: white;
            border: none;
            border-radius: 10px;
            cursor: pointer;
        }

        .resultText{
            text-align: center;
            font-size: 20px;
            padding-top: 0.5vw;
            display: block;
        }

        .resultText p{
            text-align:  center;
        }
    </style>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alonzo_FinLabEA1</title>
    </head>
        <body>
            <?php if (!isset($_POST['form-submitted'])): ?>
            <h1 class="pageTitle">Guess the Number</h1>
            <div class="bodyArea">
                <form method="post">
                    <label for="numberInput">Enter your number:</label><br>
                    <input type="number" name="numberInput" min="1" max="100" required /><br>
                    <input type="hidden" name="form-submitted" value="1"/>
                    <input type="submit" value="Submit and Calculate"/>
                </form>
            </div>
            <h3 class="pageTitle">Programmed by: Earl Lawrence Alonzo</h3>

            <?php else:   
                $numberInput = $_POST["numberInput"];
                $secretNumber = 42;
                do{
                
                if($numberInput == $secretNumber){
                    echo "<h1 class='pageTitle'>Congratulations! You gussed the right number.</h1>
                    <div class='resultsArea'>
                    <label class='resultText'>The right number is <b>$secretNumber</b></label><br/>
                    </div>
                    <p class='resultText'>Go <a href='/FinLabEA1P2.php'>back</a> to the form.</p>
                    <h3 class='pageTitle'>Programmed by: Earl Lawrence Alonzo</h3>";
                    break;
                }
            
                echo "<h1 class='pageTitle'>Guess the Number</h1>
                <div class='bodyArea'>
                <form method='post'>
                    <label for='numberInput'>Enter your number:</label><br>
                    <input type='number' name='numberInput' min='1' max='100' required /><br>
                    <input type='hidden' name='form-submitted' value='1'/>
                    <input type='submit' value='Submit and Calculate'/>
                </form>";

            if($numberInput > $secretNumber){
                echo "<h3>Too High! Try Again.</h3>";
            }
            else if($numberInput < $secretNumber){
                echo "<h3>Too Low! Try Again.</h3>";
            }        
            
            echo "</div>";  
            echo "<h3 class='pageTitle'>Programmed by: Earl Lawrence Alonzo</h3>";

            $numberInput = null;
            }while ($numberInput != null); 
            endif;?>
        </body>
</html>