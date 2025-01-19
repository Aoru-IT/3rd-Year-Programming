<?php 

if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['submit'])) {

    $conn = mysqli_connect('localhost', 'root', '', 'knowledgehub') or die("Connection Failed: " . mysqli_connect_error());
    

    if (isset($_POST['name'], $_POST['email'], $_POST['phone'], $_POST['password'])) {
        $name = htmlspecialchars($_POST['name']);
        $email = htmlspecialchars($_POST['email']);
        $phone = htmlspecialchars($_POST['phone']);
        $password = $_POST['password']; 
      
        $stmt = $conn->prepare("INSERT INTO `users` (`Name`, `Email`, `Password`, `PhoneNumber`) VALUES (?, ?, ?, ?)");
        $stmt->bind_param("ssss", $name, $email, $password, $phone);

    
        if ($stmt->execute()) {
            echo '<script type="text/javascript">alert("Successfully Registered!");window.location.href="login.php";</script>';
        } else {
            echo 'Error Occurred: ' . $stmt->error;
        }

   
        $stmt->close();
    } else {
        echo 'All fields are required.';
    }


    $conn->close();
} else {
    echo 'Invalid request.';
}

?>