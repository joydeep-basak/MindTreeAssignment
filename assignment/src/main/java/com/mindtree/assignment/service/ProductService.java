package com.mindtree.assignment.service;

import java.util.List;

import com.mindtree.assignment.entity.ProductEntity;
import com.mindtree.assignment.model.Product;

public interface ProductService {
	
	List<Product> findAllProduct();

	Product findProductById(long id);
	
	List<Product> findProductByName(String productName);
	
	List<Product> findProductByType(String productType);
}
