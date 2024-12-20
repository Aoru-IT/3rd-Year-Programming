<?php
// Start session to handle login state
session_start();

// Define variables and initialize with empty values
$email = $password = "";
$email_err = $password_err = $login_err = "";

// Check if the form is submitted
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    // Validate email
    if (empty(trim($_POST["email"]))) {
        $email_err = "Please enter your email.";
    } else {
        $email = trim($_POST["email"]);
    }

    // Validate password
    if (empty(trim($_POST["password"]))) {
        $password_err = "Please enter your password.";
    } else {
        $password = trim($_POST["password"]);
    }

    // If no errors, proceed with authentication
    if (empty($email_err) && empty($password_err)) {
        // Database connection
        $conn = mysqli_connect('localhost', 'root', '', 'knowledgehub');

        if (!$conn) {
            die("Database connection failed: " . mysqli_connect_error());
        }

        // Prepare and execute a query to check credentials
        $sql = "SELECT id, Name, Email, Password FROM users WHERE Email = ?";
        if ($stmt = mysqli_prepare($conn, $sql)) {
            mysqli_stmt_bind_param($stmt, "s", $param_email);
            $param_email = $email;

            // Execute the statement
            if (mysqli_stmt_execute($stmt)) {
                mysqli_stmt_store_result($stmt);

                // Check if email exists, then verify the password
                if (mysqli_stmt_num_rows($stmt) == 1) {
                    mysqli_stmt_bind_result($stmt, $id, $name, $email, $hashed_password);
                    if (mysqli_stmt_fetch($stmt)) {
                        if ($password === $hashed_password) {
                            // Password is correct, start a new session
                            session_start();

                            // Store user data in session variables
                            $_SESSION["loggedin"] = true;
                            $_SESSION["id"] = $id;
                            $_SESSION["name"] = $name;
                            $_SESSION["email"] = $email;

                            // Redirect to welcome page
                            header("location: viewbooks.php");
                            exit;
                        } else {
                            // Invalid password
                            $login_err = "Invalid email or password.";
                        }
                    }
                } else {
                    // Email doesn't exist
                    $login_err = "Invalid email or password.";
                }
            } else {
                echo "Something went wrong. Please try again later.";
            }

            // Close statement
            mysqli_stmt_close($stmt);
        }

        // Close connection
        mysqli_close($conn);
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles.css">
    <title>Login - Book Haven</title>
    <style>
        /* From Uiverse.io by JkHuger */ 
        .singup {
            color: #000;
            text-transform: uppercase;
            letter-spacing: 2px;
            display: block;
            font-weight: bold;
            font-size: x-large;
            margin-top: 1.5em;
        }

        .card {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 350px;
            width: 320px; /* Increased width for better spacing */
            flex-direction: column;
            gap: 20px; /* Adjusted gap between elements */
            border-radius: 15px;
            padding: 20px; /* Added padding to the card for internal spacing */
            background: #e3e3e3;
            box-shadow: 16px 16px 32px #c8c8c8,
                -16px -16px 32px #fefefe;
            border-radius: 8px;
        }

        .inputBox,
        .inputBox1 {
            position: relative;
            width: 250px;
            margin-bottom: 20px; /* Added margin between input fields */
        }

        .inputBox input,
        .inputBox1 input {
            width: 100%;
            padding: 15px; /* Increased padding for input fields */
            outline: none;
            border: none;
            color: #000;
            font-size: 1em;
            background: transparent;
            border-left: 2px solid #000;
            border-bottom: 2px solid #000;
            transition: 0.1s;
            border-bottom-left-radius: 8px;
        }

        .inputBox span,
        .inputBox1 span {
            margin-top: 5px;
            position: absolute;
            left: 0;
            transform: translateY(-4px);
            margin-left: 10px;
            padding: 5px 10px; /* Adjusted padding for the floating label */
            pointer-events: none;
            font-size: 12px;
            color: #000;
            text-transform: uppercase;
            transition: 0.5s;
            letter-spacing: 3px;
            border-radius: 8px;
        }

        .inputBox input:valid~span,
        .inputBox input:focus~span,
        .inputBox1 input:valid~span,
        .inputBox1 input:focus~span {
            transform: translateX(113px) translateY(-15px);
            font-size: 0.8em;
            padding: 5px 10px;
            background: #000;
            letter-spacing: 0.2em;
            color: #fff;
            border: 2px;
        }

        .enter {
            height: 50px; /* Slightly taller button */
            width: 120px; /* Wider button for better visibility */
            border-radius: 8px; /* Rounded corners for better aesthetics */
            border: 2px solid #000;
            cursor: pointer;
            background-color: black;
            transition: 0.5s;
            text-transform: uppercase;
            font-size: 12px; /* Slightly larger text */
            letter-spacing: 2px;
            margin-bottom: 30px; /* Added spacing below the button */
        }

        .enter:hover {
            background-color: rgb(255, 255, 255);
            color: black;
        }

        body {
            background-color: #f4f4f4;
            font-family: Arial, sans-serif;
           
            margin: 0;
            padding: 0;
        }

        header {
            text-align: center;
            margin-bottom: 20px;
        }

        header h1 {
            font-size: 2em;
        }

        nav a {
            margin: 0 15px;
            text-decoration: none;
            font-size: 1.1em;
        }

        .error {
            color: red;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
    <header>
        <h1>Welcome to KnowledgeHub</h1>
        <nav>
            <a href="login.php">Login</a>
            <a href="register.php">Register</a>
        </nav>
    </header>

    <div class="card">
        <h2 class="singup">Login Page</h2>
        <!-- Display login error -->
        <?php if (!empty($login_err)) echo "<p class='error'>$login_err</p>"; ?>

        <form action="login.php" method="post">
            <!-- Email Input -->
            <div class="inputBox1">
                <input type="email" name="email" required value="<?php echo htmlspecialchars($email); ?>">
                <span>Email</span>
            </div>
            <?php if (!empty($email_err)) echo "<p class='error'>$email_err</p>"; ?>

            <!-- Password Input -->
            <div class="inputBox">
                <input type="password" name="password" required>
                <span>Password</span>
            </div>
            <?php if (!empty($password_err)) echo "<p class='error'>$password_err</p>"; ?>

            <!-- Submit Button -->
            <button type="submit" class="enter">Login</button>
        </form>
        <p>Don't have an account? <br><a href="register.php">Register here</a>.</p>
    </div>
</body>
</html>
