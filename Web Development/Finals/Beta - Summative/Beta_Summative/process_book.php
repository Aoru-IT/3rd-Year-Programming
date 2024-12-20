<!-- process_book.php -->
<?php
require 'db_connection.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $name = $_POST['name'];
    $genre = $_POST['genre'];
    $date_published = $_POST['date_published'];
    $author = $_POST['author'];

    $stmt = $pdo->prepare("INSERT INTO books (name, genre, date_published, author) VALUES (?, ?, ?, ?)");
    if ($stmt->execute([$name, $genre, $date_published, $author])) {
        echo 'Book registered successfully!';
    } else {
        echo 'Error: Could not register the book.';
    }
}
?>