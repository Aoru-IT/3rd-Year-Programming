<!-- savebooks.php -->
<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['savebook'])) {

    $conn = mysqli_connect('localhost', 'root', '', 'knowledgehub') or die("Connection Failed: " . mysqli_connect_error());

    if (isset($_POST['author'], $_POST['book_title'], $_POST['genre'], $_POST['date_published'])) {
        $author = htmlspecialchars($_POST['author']);
        $book_title = htmlspecialchars($_POST['book_title']);
        $genre = htmlspecialchars($_POST['genre']);
        $date_published = $_POST['date_published'];

        $stmt = $conn->prepare("INSERT INTO book_information (author, book_title, genre, date_published) VALUES (?, ?, ?, ?)");
        $stmt->bind_param("ssss", $author, $book_title, $genre, $date_published);

        if ($stmt->execute()) {
            echo '<script type="text/javascript">alert("Book Successfully Registered!");window.location.href="viewbooks.php";</script>';
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
