<?php
echo "Programmed by Earl Lawrence Alonzo
September 5, 2024\n";

$fullName = readLine("Enter your Full Name: ");
$courseYearSection = readLine("Enter your Course/Year/Section: ");
$prelimGrade = readLine("Enter your Prelim grade: ");
$midtermGrade = readLine("Enter your Midterm grade: ");
$finalTermGrade = readLine("Enter your Final term grade: ");

$finalGrade = ($prelimGrade + $midtermGrade + $finalTermGrade) / 3;

echo "Full Name: $fullName
Course/Year/Section: $courseYearSection

Prelim Grade: $prelimGrade
Midterm Grade: $midtermGrade
Final Term Grade: $finalTermGrade

Final Grade is $finalGrade";

?>