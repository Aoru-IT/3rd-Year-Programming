<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #DFF2EB;
        }

        .pageTitle {
            color: #4A628A;
            font-weight: 400;
            text-align: center;
            margin: 4vw 0 2vw auto;
        }

        .bodyArea, .resultsArea {
            background-color: #B9E5E8;
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
            color: #4A628A;
        }

        label, p {
            color: #4A628A;
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
            background-color: #4A628A;
            color: white;
            border: none;
            border-radius: 10px;
            cursor: pointer;
        }

        .errorText label {
            padding-top: 0.5vw;
            display: block;
        }
    </style>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alonzo_FinLecEA1</title>
</head>
<body>
<?php if (!isset($_POST['form-submitted'])): ?>
    <h1 class="pageTitle">Factorial Calculator</h1>
    <div class="bodyArea">
        <form method="post">
            <label for="numberInput">Enter a positive integer:</label><br>
            <input type="number" name="numberInput" required min="0"/><br>
            <input type="hidden" name="form-submitted" value="1"/>
            <input type="submit" value="Submit and Calculate"/>
        </form>
    </div>
    <h3 class="pageTitle">Programmed by: Earl Lawrence Alonzo</h3>
<?php else: 
    $numberInput = $_POST["numberInput"];
    $factorial = 1;


    for($n = 1; $n <= $numberInput; $n++){
        $factorial *= $n;
    }

?>
    <h1 class="pageTitle">Calculation Results:</h1>
    <div class="resultsArea">
        <label><?php echo "The factorial of $numberInput is: <b>$factorial</b>"; ?></label><br/>
        <p>Go <a href="/FinLabEA1.php">back</a> to the form</p>
    </div>
    <h3 class="pageTitle">Programmed by: Earl Lawrence Alonzo</h3>
<?php endif; ?>
</body>
</html>