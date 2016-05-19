var express = require('express');

var app = express();

var bodyParser = require('body-parser');
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended:true}));

var productRoutes = require('./routes/productRoute.js');
app.use('/api',productRoutes);

app.listen(3000);
module.exports = app;