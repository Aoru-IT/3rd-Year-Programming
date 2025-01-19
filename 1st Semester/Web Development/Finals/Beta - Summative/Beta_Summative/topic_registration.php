<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles.css"/>
    <title>Document</title>
</head>
<body>
<header>
        <h1>Welcome to KnowledgeHub</h1>
        <nav>
            <a href="topic_registration.php">Book Registration</a>
            <a href="viewbooks.php">View Books</a>
            <a href="logout.php">Logout</a>
        </nav>
    </header>   
<br>
<main>
    <form action="savebooks.php" method="POST">
        <label for="author">Author:</label><br>
        <input type="text" name="author" id="author" required/><br><br>
        <label for="book_title">Book Title: </label><br>
        <input type="text" name="book_title" id="book_title" required/><br><br>
        <label for="genre">Genre: </label><br>
        <input type="text" name="genre" id="genre" required/><br><br>
        <label for="date_published">Date Published: </label><br>
        <input type="date" name="date_published" id="date_published" required/><br><br>
        <input type="submit" name="savebook" id="savebook"/>
    </form>
</main>
</body>
</html>
