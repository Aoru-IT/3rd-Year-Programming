// Import necessary modules
const express = require('express'); // Main web framework used to build the site
const app = express(); // Create an instance of the Express app
const path = require('path'); // Used to work with file paths

// 🔧 Middleware to handle form data (like input from text fields)
app.use(express.urlencoded({ extended: true }));
// This allows Express to read data sent from HTML forms using POST method

// 🧠 Set up EJS as the view engine (for rendering HTML with dynamic data)
app.set('view engine', 'ejs'); 
// This tells Express to use EJS as the template language (like regform.ejs, viewstudents.ejs)

app.set('views', path.join(__dirname, 'views')); 
// Tells Express where to find your EJS files (in the 'views' folder)


// 🔗 Import all student-related routes from studentRoutes.js
const studentRoutes = require('./routes/studentRoutes');

// 📌 Use the imported routes for any request starting at '/' (root URL)
app.use('/', studentRoutes);

// 🖼️ Serve uploaded images from the 'uploads' folder
// When someone accesses /uploads/image.png, this lets Express show it
app.use('/uploads', express.static('uploads'));

app.use(express.static(path.join(__dirname, 'public')));

// Export the app so it can be used in server.js
module.exports = app;
