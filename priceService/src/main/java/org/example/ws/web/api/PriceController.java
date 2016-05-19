package org.example.ws.web.api;

import java.util.Collection;

import org.example.ws.model.ProductPrice;
import org.example.ws.service.PriceService;
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
public class PriceController {
	@Autowired
	private PriceService priceService;
	
	@RequestMapping(value="/api/productPrices", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE	)
	public ResponseEntity<Collection<ProductPrice>> getProductPrices(){
		Collection<ProductPrice> prodPrices = priceService.findAll();
		return new ResponseEntity<Collection<ProductPrice>>(prodPrices,HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/productPrices/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE	)
	public ResponseEntity<ProductPrice> getPrice(@PathVariable("id") Long id){
		ProductPrice prodPrice = priceService.findOne(id);
		if(prodPrice == null){
			return new ResponseEntity<ProductPrice>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProductPrice>(prodPrice,HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/productPrices", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE	)
	public ResponseEntity<ProductPrice> addPrice(@RequestBody ProductPrice prodPrice){
		ProductPrice productPrice = priceService.create(prodPrice);
		return new ResponseEntity<ProductPrice>(productPrice,HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/api/productPrices", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE	)
	public ResponseEntity<ProductPrice> updatePrice(@RequestBody ProductPrice prodPrice){
		ProductPrice updatedProdPrice = priceService.update(prodPrice);
		if(updatedProdPrice == null){
			return new ResponseEntity<ProductPrice>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ProductPrice>(updatedProdPrice,HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/productPrices/{id}", method=RequestMethod.DELETE	)
	public ResponseEntity<ProductPrice> deletePrice(@PathVariable("id") Long id){
		boolean isDeleted = priceService.delete(id);
		if(!isDeleted){
			return new ResponseEntity<ProductPrice>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ProductPrice>(HttpStatus.NO_CONTENT);
	}
	
}
