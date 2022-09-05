package com.mindtree.assignment.exception;

public class NoCartDataFoundException extends Exception {

	public NoCartDataFoundException(String message) {
		super("No Product in the cart");
	}
}
