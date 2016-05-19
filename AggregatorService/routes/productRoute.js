var express = require('express');
var router = express.Router();
var ProductController = require('../controllers/productController.js');

router.route('/products').get(ProductController.getProducts);

router.route('/products/:id').get(ProductController.getProductById);

module.exports= router;