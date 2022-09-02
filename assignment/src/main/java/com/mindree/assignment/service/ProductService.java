package com.mindree.assignment.service;

import java.util.List;

import com.mindree.assignment.entity.ProductEntity;
import com.mindree.assignment.model.Product;

public interface ProductService {
	
	List<Product> findAllProduct();

	Product findProductById(long id);
	
	List<ProductEntity> findProductByName(String productName);
	
	List<ProductEntity> findProductByType(String productType);
}
