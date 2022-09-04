package com.mindtree.assignment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.mindtree.assignment.entity.CartEntity;
import com.mindtree.assignment.entity.CartProductEntity;
import com.mindtree.assignment.entity.CartUserId;
import com.mindtree.assignment.repository.CartProductRepository;
import com.mindtree.assignment.repository.CartRepository;
import com.mindtree.assignment.service.CartService;

public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartProductRepository cartProducRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	

	@Override
	public void addToCart(long userid, int productid, int quantity) {
		CartEntity entity = cartRepo.findCartByUserId(userid);
		
		CartProductEntity cardProductEntity = cartProducRepo.getCartDataByUserAndProduct(userid, productid);
		
		cardProductEntity = new CartProductEntity();
		cardProductEntity.setCartid(entity.getCartid());
		cardProductEntity.setProductid(productid);
		
		if (quantity == 0) {
			cartProducRepo.removeAllFromCart(entity.getCartid());
		} else {
			cardProductEntity.setQuantity(cardProductEntity.getQuantity() + quantity); 
		}
		cardProductEntity.setQuantity(quantity);
		cartProducRepo.save(cardProductEntity);
		
	}

	@Override
	public void removeFromCart(long userid, int productid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAllFromCart(long userid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCart(long userid, int productid, int quantity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewCart(long userid) {
		// TODO Auto-generated method stub
		
	}

}
