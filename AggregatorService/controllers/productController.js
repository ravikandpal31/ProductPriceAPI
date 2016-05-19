
var http = require('http');
var async = require('async');
var config = require('../config.js');

exports.getProducts = function(req, res){
	var productsWithPrice = [];
  	var options = config.productDetailServiceOptions;
  	async.waterfall([
	 function(callback) {
	    var req = http.request(options, function(res) {
	      res.setEncoding('utf-8');

	      var responseString = '';

	      res.on('data', function(data) {
	        responseString += data;
	      });

	      res.on('end', function() {
	        console.log(responseString);
	        responseObject = JSON.parse(responseString);
	        callback(null, responseObject);
	      });
	    });
    	req.end();
  	},
  	function(productList, callback) {

		// 1st para in async.each() is the array of items
		async.each(productList,
		  // 2nd param is the function that each item is passed to
		  function(item, cb){
		    // Call an asynchronous function
		    
		    var tempResult = {};
		  	var options = config.priceServiceOptions;
		  	options.path = '/api/productPrices/' + item.productId;
		  	var req = http.request(options, function(res) {
		      res.setEncoding('utf-8');

		      var responseString = '';

		      res.on('data', function(data) {
		        responseString += data;
		      });

		      res.on('end', function() {
		        console.log(responseString);
		        responseObject = JSON.parse(responseString)
		        tempResult[item.productId] = item;
		        tempResult['price'] = responseObject.productPrice;
		        productsWithPrice.push(tempResult);
		        cb(null);
		      });
	    	});
		    req.end();
		  },
		  // 3rd param is the function to call when everything's done
		  function(err){
		    // All tasks are done now
		    console.log("productsWithPrice : ",productsWithPrice);
		    callback(null,productsWithPrice);
		  }

		);
    	
  	},
  	], function(error,results) {
	    console.log("result--> ",results);
	    if(error){
	      res.json({message:"error occuered..."});
	    } 
	    res.json(results);
  	});
}

exports.getProductById = function(req, res){
	var productWithPrice = {};
  	var options = config.productDetailServiceOptions;
  	options.path = '/api/products/' + req.params.id;
  	async.waterfall([
	 function(callback) {
	    var req = http.request(options, function(res) {
	      res.setEncoding('utf-8');

	      var responseString = '';

	      res.on('data', function(data) {
	        responseString += data;
	      });

	      res.on('end', function() {
	        console.log(responseString);
	        responseObject = JSON.parse(responseString);
	        callback(null, responseObject);
	      });
	    });
    	req.end();
  	},
  	function(product, callback) {

		// 1st para in async.each() is the array of items
		//async.each(productList,
		  // 2nd param is the function that each item is passed to
		 // function(item, cb){
		    // Call an asynchronous function
		    
		    //var tempResult = {};
		  	var options = config.priceServiceOptions;
		  	options.path = '/api/productPrices/' + product.productId;
		  	var req = http.request(options, function(res) {
		      res.setEncoding('utf-8');

		      var responseString = '';

		      res.on('data', function(data) {
		        responseString += data;
		      });

		      res.on('end', function() {
		        console.log(responseString);
		        responseObject = JSON.parse(responseString)
		        productWithPrice[product.productId] = product;
		        productWithPrice['price'] = responseObject.productPrice;
		       // productWithPrice.push(tempResult);
		        callback(null,productWithPrice);
		      });
	    	});
		    req.end();
		 // },
		  // 3rd param is the function to call when everything's done
		 /* function(err){
		    // All tasks are done now
		    console.log("productsWithPrice : ",productsWithPrice);
		    callback(null,productsWithPrice);
		  }
*/
		//);
    	
  	},
  	], function(error,results) {
	    console.log("result--> ",results);
	    if(error){
	      res.json({message:"error occuered..."});
	    } 
	    res.json(results);
  	});
}
