package org.example.ws.service;

import java.util.Collection;

import org.example.ws.model.ProductPrice;

public interface PriceService {
	Collection<ProductPrice> findAll();
	ProductPrice findOne(Long id);
	ProductPrice create(ProductPrice productPrice);
	ProductPrice update(ProductPrice productPrice);
	boolean delete(Long id);
}
