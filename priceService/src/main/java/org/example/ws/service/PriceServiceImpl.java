package org.example.ws.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.example.ws.model.ProductPrice;
import org.springframework.stereotype.Service;




@Service
public class PriceServiceImpl implements PriceService {
	
	private static Long nextId;
	private static Map<Long,ProductPrice> productPriceMap;
	
	private static ProductPrice save(ProductPrice prodPrice){
		if(productPriceMap == null){
			productPriceMap = new HashMap<Long, ProductPrice>();
			nextId = new Long(1);
		}
		//Update Existing Product
		if(prodPrice.getProductId() != null){
			ProductPrice oldProdDtl = productPriceMap.get(prodPrice.getProductId());
			if(oldProdDtl == null){
				return null;
			}
			productPriceMap.remove(prodPrice.getProductId());
			productPriceMap.put(prodPrice.getProductId(), prodPrice);
			return prodPrice;
		}
		//Create New Product
		prodPrice.setProductId(nextId);
		nextId += 1; 
		productPriceMap.put(prodPrice.getProductId(), prodPrice);
		return prodPrice;
		
	}
	//Populate Map with ProductPrices
	static{
		ProductPrice prodPrice1 = new ProductPrice();
		//prodDtl1.setProductId(new Long(1));
		prodPrice1.setProductPrice(100.0);
		save(prodPrice1);
		
		ProductPrice prodPrice2 = new ProductPrice();
		prodPrice2.setProductPrice(150.0);
		save(prodPrice2);
	}
	
	private boolean remove(Long id){
		ProductPrice prodDtl = productPriceMap.get(id);
		
		if(prodDtl == null){
			return false;
		}
		productPriceMap.remove(id);
		return true;
	}
	
	@Override
	public Collection<ProductPrice> findAll() {
		Collection<ProductPrice> productPrices = productPriceMap.values();
		return productPrices;
	}

	@Override
	public ProductPrice findOne(Long id) {
		ProductPrice prodPrice = productPriceMap.get(id);
		return prodPrice;
	}

	@Override
	public ProductPrice create(ProductPrice productPrice) {
		ProductPrice savedProductPrice = save(productPrice);
		return savedProductPrice;
	}

	@Override
	public ProductPrice update(ProductPrice productPrice) {
		ProductPrice updatedProductPrice = save(productPrice);
		return updatedProductPrice;
	}

	@Override
	public boolean delete(Long id) {
		return remove(id);
	}
	
}
