package org.example.ws.web.api;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.example.ws.model.ProductDetail;
import org.example.ws.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	
	@Autowired
	private ProductDetailService productDetailService;
	
	@RequestMapping(value="/api/products", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE	)
	public ResponseEntity<Collection<ProductDetail>> getProductDetails(){
		Collection<ProductDetail> prodDetail = productDetailService.findAll();
		return new ResponseEntity<Collection<ProductDetail>>(prodDetail,HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/products/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE	)
	public ResponseEntity<ProductDetail> getProduct(@PathVariable("id") Long id){
		ProductDetail prodDetail = productDetailService.findOne(id);
		if(prodDetail == null){
			return new ResponseEntity<ProductDetail>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProductDetail>(prodDetail,HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/products", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE	)
	public ResponseEntity<ProductDetail> createProduct(@RequestBody ProductDetail prodDtl){
		ProductDetail prodDetail = productDetailService.create(prodDtl);
		return new ResponseEntity<ProductDetail>(prodDetail,HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/api/products", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE	)
	public ResponseEntity<ProductDetail> updateProduct(@RequestBody ProductDetail prodDtl){
		ProductDetail updatedProdDetail = productDetailService.update(prodDtl);
		if(updatedProdDetail == null){
			return new ResponseEntity<ProductDetail>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ProductDetail>(updatedProdDetail,HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/products/{id}", method=RequestMethod.DELETE	)
	public ResponseEntity<ProductDetail> deleteProduct(@PathVariable("id") Long id){
		boolean isDeleted = productDetailService.delete(id);
		if(!isDeleted){
			return new ResponseEntity<ProductDetail>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ProductDetail>(HttpStatus.NO_CONTENT);
	}
	
	
}
