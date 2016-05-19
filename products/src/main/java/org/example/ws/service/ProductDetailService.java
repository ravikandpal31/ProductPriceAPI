package org.example.ws.service;

import java.util.Collection;


import org.example.ws.model.ProductDetail;

public interface ProductDetailService {
	Collection<ProductDetail> findAll();
	ProductDetail findOne(Long id);
	ProductDetail create(ProductDetail productDetail);
	ProductDetail update(ProductDetail productDetail);
	boolean delete(Long id);
}
