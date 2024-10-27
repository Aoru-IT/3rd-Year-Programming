<!DOCTYPE html>
<html lang="en">
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registration Form</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>

   <?php if(isset($_POST['form_submitted'])): ?>
<h2>Thank You <?php echo $_POST['firstname']; ?> </h2>

<p>You have been registered as
<?php echo $_POST['firstname'] . ''. $_POST['lastname']; ?>
</p>

<p>Go <a href="/registration_form.php">back</a> to the form</p>

<?php else: ?>


<?php echo "Programmed by: John Arellado, Rovic Rodriguez, Math Dela Rosa, Earl Alonzo, Ken Divino" ?>
<h2>Registration Form</h2>

<form action="registration_form.php" method="POST">

First name:
<input type="text" name="firstname">

<br> Last name:
<input type="text" name="lastname">

<input type="hidden" name="form_submitted" value="1" />

<input type="submit" value="Submit">

</form>

<?php
   $names = array("John Arellado","Earl Alonzo","Ken Divino","Rovic Rodriguez", "Math Dela Rosa");

foreach($names as $name){
   echo "<p>$name</p>";
}
?>



<?php endif; ?>
</body>
</html>