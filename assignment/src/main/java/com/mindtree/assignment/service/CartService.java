package com.mindtree.assignment.service;

import java.util.List;

import com.mindtree.assignment.entity.CartProductEntity;
import com.mindtree.assignment.exception.ProductNotFoundException;
import com.mindtree.assignment.exception.UserNotFoundException;
import com.mindtree.assignment.model.Cart;

public interface CartService {

	public CartProductEntity addToCart(long userid, long productid, int quantity) throws ProductNotFoundException;
	
	public void removeFromCart(long userid, long productid) throws ProductNotFoundException, UserNotFoundException;
	
	public void removeAllFromCart(long userid) throws UserNotFoundException, ProductNotFoundException;
	
	public void updateCart(long userid, long productid, int quantity) throws ProductNotFoundException;
	
	public List<Cart> viewCart(long userid) throws ProductNotFoundException, UserNotFoundException;
}
