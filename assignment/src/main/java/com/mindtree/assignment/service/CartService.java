package com.mindtree.assignment.service;

public interface CartService {

	public void addToCart(long userid, int productid, int quantity);
	
	public void removeFromCart(long userid, int productid);
	
	public void removeAllFromCart(long userid);
	
	public void updateCart(long userid, int productid, int quantity);
	
	public void viewCart(long userid);
}
