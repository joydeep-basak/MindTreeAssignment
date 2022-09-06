package com.mindtree.assignment.exception;

public class CartNotExistsException extends Exception {

	public CartNotExistsException(String message) {
		super("Cart Not Found");
	}
}
