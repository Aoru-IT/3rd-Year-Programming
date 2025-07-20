// 📦 Import the app from app.js (the one that contains all setup and routes)
const app = require('./app'); 

// 🌐 Define which port number your server will run on (e.g. http://localhost:8080)
const port = 8080;

// 🚀 Start the server and listen on the specified port
app.listen(port, () => {
    console.log("Successfully open at http://localhost:8080"); // Shows this in the terminal when it's running
});
