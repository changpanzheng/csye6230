const express = require('express');
const bodyParser = require('body-parser');

const app = express();
app.use(bodyParser.json());


app.get('', (req, res) => {
  const name = req.query.name;
  console.log(name);
  
  if (name) {
    res.send("Welcome " + name + "\n");
  } else {
    res.end("Hello World \n");
  }
});


const port = 3000;
app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});

// node server.js
// http://localhost:3000/?name=Parker
