<?php
// Database connection
$conn = mysqli_connect('localhost', 'root', '', 'knowledgehub') or die("Connection Failed: " . mysqli_connect_error());

// Fetch data from the books table
$sql = "SELECT * FROM book_information";
$result = mysqli_query($conn, $sql);

// Check if the query was successful
if (!$result) {
    die("Query Failed: " . mysqli_error($conn));
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles.css">
    <title>View Books - KnowledgeHub</title>
    <style>
        /* From Uiverse.io by JkHuger */ 
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #333;
            color: white;
            padding: 1rem 0;
            text-align: center;
        }

        header nav a {
            color: white;
            margin: 0 15px;
            text-decoration: none;
        }

        .container {
            width: 90%;
            margin: 20px auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 16px 16px 32px #c8c8c8, -16px -16px 32px #fefefe;
        }

        h2 {
            color: #333;
            text-transform: uppercase;
            letter-spacing: 1px;
            font-size: 1.5em;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #333;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        /* Styling for table and links */
        .singup {
            color: #000;
            text-transform: uppercase;
            letter-spacing: 2px;
            display: block;
            font-weight: bold;
            font-size: x-large;
            margin-top: 1.5em;
        }

        .error {
            color: red;
            font-size: 0.9em;
        }

        nav {
            margin-top: 10px;
        }
    </style>
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

<div class="container">
    <h2>Books in the Library</h2>
    <?php if (mysqli_num_rows($result) > 0): ?>
        <table>
            <thead>
                <tr>
                    <th>Book Title</th>
                    <th>Author</th>
                    <th>Genre</th>
                    <th>Date Published</th>
                </tr>
            </thead>
            <tbody>
                <?php while ($row = mysqli_fetch_assoc($result)): ?>
                    <tr>
                        <td><?php echo htmlspecialchars($row['Book_Title']); ?></td>
                        <td><?php echo htmlspecialchars($row['Author']); ?></td>
                        <td><?php echo htmlspecialchars($row['Genre']); ?></td>
                        <td><?php echo htmlspecialchars($row['Date_published']); ?></td>
                    </tr>
                <?php endwhile; ?>
            </tbody>
        </table>
    <?php else: ?>
        <p>No books found in the database.</p>
    <?php endif; ?>

    <?php
    // Free the result and close the connection
    mysqli_free_result($result);
    mysqli_close($conn);
    ?>
</div>

</body>
</html>
