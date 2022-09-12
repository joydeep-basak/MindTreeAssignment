package com.mindtree.assignment.service;

import java.util.List;

import com.mindtree.assignment.entity.ProductEntity;
import com.mindtree.assignment.exception.ProductNotFoundException;
import com.mindtree.assignment.model.Product;

public interface ProductService {
	
	List<Product> findAllProduct();

	Product findProductById(Long id) throws ProductNotFoundException;
	
	List<Product> findProductByName(String productName) throws ProductNotFoundException;
	
	List<Product> findProductByType(String productType)throws ProductNotFoundException;
}
