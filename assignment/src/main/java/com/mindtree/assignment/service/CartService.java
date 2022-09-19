package com.mindtree.assignment.service;

import com.mindtree.assignment.entity.CartProductEntity;
import com.mindtree.assignment.exception.CartNotExistsException;
import com.mindtree.assignment.exception.ProductNotFoundException;
import com.mindtree.assignment.exception.UserNotFoundException;
import com.mindtree.assignment.model.CartSummary;

public interface CartService {

	public CartProductEntity addToCart(long userid, long productid, int quantity) throws ProductNotFoundException, CartNotExistsException;
	
	public int removeFromCart(long userid, long productid) throws ProductNotFoundException, UserNotFoundException;
	
	public int removeAllFromCart(long userid) throws UserNotFoundException, ProductNotFoundException;
	
	public CartProductEntity updateCart(long userid, long productid, int quantity) throws ProductNotFoundException, CartNotExistsException;
	
	public CartSummary viewCart(long userid) throws ProductNotFoundException, CartNotExistsException;
}
