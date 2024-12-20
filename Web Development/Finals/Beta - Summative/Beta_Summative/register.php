<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles.css"/>
    <title>Document</title>
</head>
<header>
        <h1>Welcome to KnowledgeHub</h1>
        <nav>
            <a href="login.php">Login</a>
            <a href="register.php">Register</a>
        </nav>
    </header>
<body>

<br><br>
    <form action="SQLregister.php" method="POST">
        <label for="user">Name:</label><br>
        <input type="text" name="name" id="name" required/><br><br>
        <label for="email">Email: </label><br>
        <input type="email" name="email" id="email" required/><br><br>
        <label for="phone">Phone: </label><br>
        <input type="text" name="phone" id="phone" required/><br><br>
        <label for="password">Password: </label><br>
        <input type="password" name="password" id="password" required/><br><br>
        <input type="submit" name="submit" id="submit"/>
    </form>
</body>
</html>
