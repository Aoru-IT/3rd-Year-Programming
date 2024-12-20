<!-- book_registration.php -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register a Book</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <header>
        <h1>Register a Book</h1>
    </header>
    <form action="process_book.php" method="POST">
        <label for="name">Book Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="genre">Genre:</label>
        <input type="text" id="genre" name="genre" required>

        <label for="date_published">Date Published:</label>
        <input type="date" id="date_published" name="date_published" required>

        <label for="author">Author:</label>
        <input type="text" id="author" name="author" required>

        <button type="submit">Register Book</button>
    </form>
</body>
</html>