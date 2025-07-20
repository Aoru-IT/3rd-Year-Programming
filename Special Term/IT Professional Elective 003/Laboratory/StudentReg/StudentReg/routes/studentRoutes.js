// Import required modules
const express = require('express');
const multer = require('multer'); // Used to handle file/image uploads
const router = express.Router();  // Creates a router object for handling routes
const { MongoClient } = require('mongodb'); // For connecting to MongoDB
const path = require('path'); // Helps with file paths (like .png, .jpg)

// MongoDB connection URI (the database is named 'StudentReg')
const uri = 'mongodb://127.0.0.1:27017/StudentReg';

// 🔁 Route: Redirect '/' to the registration form
router.get('/', (req, res) => {
  res.redirect('/register'); // Automatically redirects user to the form page
});

// 🖼️ Set up where and how uploaded images will be stored
const storage = multer.diskStorage({
  // Where to save uploaded files (in 'uploads/' folder)
  destination: function (req, file, cb) {
    cb(null, 'uploads/');
  },
  // How to name the uploaded files (current timestamp + original file extension)
  filename: function (req, file, cb) {
    cb(null, Date.now() + path.extname(file.originalname)); // Example: 172333422.png
  }
});

// Set up multer middleware using the defined storage settings
const upload = multer({ storage: storage });


// 📄 Route: Show the registration form (GET request)
router.get('/register', (req, res) => {
  res.render('regform'); // Renders the EJS template named 'regform.ejs'
});


// 📝 Route: Handle the form submission (POST request)
router.post('/register', upload.single('picture'), async (req, res) => {
  try {
    // Connect to the MongoDB server
    const client = await MongoClient.connect(uri);
    const db = client.db();

    // Access the 'Students' collection inside the database
    const collection = db.collection('Students');

    // Create a new student object using form inputs and uploaded file
    const newStudent = {
      picture: req.file.filename, // Gets the uploaded image's filename
      student_number: req.body.student_number, // Gets the student number from the form
      name: req.body.name, // Gets the student name
      email: req.body.email // Gets the email address
    };

    // Insert the new student into the database
    await collection.insertOne(newStudent);

    // Close the database connection
    await client.close();

    // Redirect to the list of all students
    res.redirect('/students');
  } catch (err) {
    console.error('Error saving student:', err);
    res.status(500).send('Server error'); // Show error if something fails
  }
});


// 📋 Route: Show all registered students
router.get('/students', async (req, res) => {
  try {
    // Connect to the MongoDB server
    const client = await MongoClient.connect(uri);
    const db = client.db();

    // Access the 'Students' collection
    const collection = db.collection('Students');

    // Get all student data from the collection
    const data = await collection.find({}).toArray();

    // Close the database connection
    await client.close();

    // Render the 'viewstudents.ejs' template, and send the data to it
    res.render('viewstudents', { students: data });
  } catch (err) {
    console.error('Error loading students:', err);
    res.status(500).send('Server error');
  }
});

// Make this router file accessible in app.js
module.exports = router;
