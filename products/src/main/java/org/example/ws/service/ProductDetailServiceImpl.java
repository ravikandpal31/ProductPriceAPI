package org.example.ws.service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.example.ws.model.ProductDetail;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

	@Override
	public Collection<ProductDetail> findAll() {
		Collection<ProductDetail> prodDetails = productMap.values();
		return prodDetails;
	}

	@Override
	public ProductDetail findOne(Long id) {
		ProductDetail prodDetail = productMap.get(id);
		return prodDetail;
	}

	@Override
	public ProductDetail create(ProductDetail productDetail) {
		ProductDetail savedProdDetail = save(productDetail);
		return savedProdDetail;
	}

	@Override
	public ProductDetail update(ProductDetail productDetail) {
		ProductDetail updatedProdDetail = save(productDetail);
		return updatedProdDetail;
	}

	@Override
	public boolean delete(Long id) {
		return remove(id);

	}
	
	private static Long nextId;
	private static Map<Long,ProductDetail> productMap;
	
	private static ProductDetail save(ProductDetail prodDtl){
		System.out.println("save method...");
		if(productMap == null){
			productMap = new HashMap<Long, ProductDetail>();
			nextId = new Long(1);
		}
		//Update Existing Product
		if(prodDtl.getProductId() != null){
			ProductDetail oldProdDtl = productMap.get(prodDtl.getProductId());
			if(oldProdDtl == null){
				return null;
			}
			productMap.remove(prodDtl.getProductId());
			productMap.put(prodDtl.getProductId(), prodDtl);
			return prodDtl;
		}
		//Create New Product
		prodDtl.setProductId(nextId);
		nextId += 1; 
		productMap.put(prodDtl.getProductId(), prodDtl);
		return prodDtl;
		
	}
	//Populate Map with ProductDetails
	static{
		ProductDetail prodDtl1 = new ProductDetail();
		//prodDtl1.setProductId(new Long(1));
		prodDtl1.setProductName("Bat");
		prodDtl1.setShortDescription("This is bat");
		prodDtl1.setLongDescription("This is cricket bat");
		save(prodDtl1);
		
		ProductDetail prodDtl2 = new ProductDetail();
		//prodDtl1.setProductId(new Long(2));
		prodDtl2.setProductName("Ball");
		prodDtl2.setShortDescription("This is ball");
		prodDtl2.setLongDescription("This is cricket ball");
		save(prodDtl2);
	}
	
	private boolean remove(Long id){
		ProductDetail prodDtl = productMap.get(id);
		
		if(prodDtl == null){
			return false;
		}
		productMap.remove(id);
		return true;
	}

}
