package com.mindtree.assignment.service;

import java.util.List;

import com.mindtree.assignment.entity.CartProductEntity;
import com.mindtree.assignment.exception.CartNotExistsException;
import com.mindtree.assignment.exception.ProductNotFoundException;
import com.mindtree.assignment.exception.UserNotFoundException;
import com.mindtree.assignment.model.Cart;

public interface CartService {

	public CartProductEntity addToCart(long userid, long productid, int quantity) throws ProductNotFoundException, CartNotExistsException;
	
	public void removeFromCart(long userid, long productid) throws ProductNotFoundException, UserNotFoundException;
	
	public void removeAllFromCart(long userid) throws UserNotFoundException, ProductNotFoundException;
	
	public CartProductEntity updateCart(long userid, long productid, int quantity) throws ProductNotFoundException, CartNotExistsException;
	
	public List<Cart> viewCart(long userid) throws ProductNotFoundException, CartNotExistsException;
}
