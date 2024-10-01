<! DOCTYPE htmL>
<html lang="en">
<head>
<style>

body{
    font-family: Bahnschrift;
}

</style>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registration Form</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
<?php if(isset($_POST['form_submitted'])): ?>
<?php 
$firstNumber = $_POST['firstNumber'];
$secondNumber = $_POST['secondNumber'];

echo "<h2>Part 1 Output:</h2>";
echo "<p>The first number is $firstNumber and the second number is $secondNumber</p><br>";

$celsiusInput = $_POST['celsiusInput'];
$fahrenheitInput = $_POST['fahrenheitInput'];

echo "<h2>Part 2 Output:</h2>";
$celsiusInput = $_POST['celsiusInput'];
$fahrenheitOutput = ($celsiusInput * 9/5) + 32 ;
echo "<p>$celsiusInput C converted into Fahrenheit is $fahrenheitOutput °F</p>";

$fahrenheitInput = $_POST['fahrenheitInput'];
$celsiusOutput= ($fahrenheitInput - 32) * 5/9;
echo "<p>$fahrenheitInput °F converted into Celsius is $celsiusOutput °C</p><br>";

echo "<h2>Part 3 Output:</h2>";
$birthInput = $_POST['birthYear'];
$futureInput = $_POST['futureYear'];

$ageOutput = $futureInput - $birthInput;
echo "<p>I will be $ageOutput years old in $futureInput</p><br>";
?>

<p>Go <a href="/index.php">back</a> to the form</p>

<?php else: ?>

<?php echo "Programmed by: Earl Lawrence Alonzo" ?>
<h2>Enabling Assessment 5</h2>

<form action="index.php" method="POST">
<h3>Enter Two Values</h3>
<p>First Number: <input type="text" name="firstNumber"/></p>
<p>Second Number: <input type="text" name="secondNumber" /></p>

<br>
<h3>Temperature Conversion</h3>

<p>Input a temperature in Celsius: <input type="text" name="celsiusInput"/></p>
<p>Input a temperature in Fahrenheit: <input type="text" name="fahrenheitInput"/></p>

<br>
<h3>Your Future Age</h3>
<p>Your Birth Year: <input type="text" name="birthYear"/></p>
<p>Enter the Future Year: <input type="text" name="futureYear"/></p>

<input type="hidden" name="form_submitted" value="1" />

<br>
<input type="submit" value="Submit">

</form>


<?php endif; ?>
</body>
</htm1>