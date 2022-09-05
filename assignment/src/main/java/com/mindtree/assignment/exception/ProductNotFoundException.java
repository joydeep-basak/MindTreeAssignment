package com.mindtree.assignment.exception;

public class ProductNotFoundException extends Exception {

	public ProductNotFoundException(String message) {
		super("Product Not Found");
	}
}
