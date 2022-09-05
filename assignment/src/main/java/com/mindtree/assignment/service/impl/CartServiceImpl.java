package com.mindtree.assignment.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.assignment.entity.CartEntity;
import com.mindtree.assignment.entity.CartProductEntity;
import com.mindtree.assignment.exception.ProductNotFoundException;
import com.mindtree.assignment.exception.UserNotFoundException;
import com.mindtree.assignment.model.Cart;
import com.mindtree.assignment.repository.CartProductRepository;
import com.mindtree.assignment.repository.CartRepository;
import com.mindtree.assignment.repository.ProductRepository;
import com.mindtree.assignment.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartProductRepository cartProductRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	

	@Override
	public CartProductEntity addToCart(long userid, long productid, int quantity) throws ProductNotFoundException {
		if (!productRepo.existsById(productid)) {
			throw new ProductNotFoundException("Product not found");
		}
		CartEntity entity = cartRepo.findCartByUserId(userid);
		CartProductEntity cardProductEntity = cartProductRepo.getCartDataByCartAndProduct(entity.getCartid(), productid);
		if (cardProductEntity == null) {
			cardProductEntity = new CartProductEntity();
		}
		
		cardProductEntity.setCartid(entity.getCartid());
		cardProductEntity.setProductid(productid);
		
		if (quantity == 0) {
			cartProductRepo.removeAllFromCart(entity.getCartid());
		} else {
			cardProductEntity.setQuantity(cardProductEntity.getQuantity() + quantity); 
		}
		return cartProductRepo.save(cardProductEntity);
		
	}

	@Override
	public void removeFromCart(long userid, long productid) throws ProductNotFoundException, UserNotFoundException {
		if (!productRepo.existsById(productid)) {
			throw new ProductNotFoundException("Product not found");
		}
		CartEntity entity = cartRepo.findCartByUserId(userid);
		if (entity == null) {
			throw new UserNotFoundException("User or Cart not found");
		}
		List<CartProductEntity> cartProductEntityList = cartProductRepo.getCartData(entity.getCartid());
		if (cartProductEntityList == null || cartProductEntityList.isEmpty()) {
			throw new ProductNotFoundException("Product not found");
		} else {
			cartProductRepo.removeAllFromCart(entity.getCartid());
		}
		
	}

	@Override
	public void removeAllFromCart(long userid) throws UserNotFoundException, ProductNotFoundException {
		CartEntity entity = cartRepo.findCartByUserId(userid);
		if (entity == null) {
			throw new UserNotFoundException("User or Cart not found");
		}
		List<CartProductEntity>  cartProductEntity = cartProductRepo.getCartData(entity.getCartid());
		if (cartProductEntity == null || cartProductEntity.isEmpty()) {
			throw new ProductNotFoundException("Product not found");
		} else {
			cartProductRepo.removeAllFromCart(entity.getCartid());
		}
	}

	@Override
	public void updateCart(long userid, long productid, int quantity) throws ProductNotFoundException {

		if (!productRepo.existsById(productid)) {
			throw new ProductNotFoundException("Product not found");
		}
		CartEntity entity = cartRepo.findCartByUserId(userid);
		CartProductEntity cardProductEntity = cartProductRepo.getCartDataByCartAndProduct(entity.getCartid(), productid);
		
		cardProductEntity = new CartProductEntity();
		cardProductEntity.setCartid(entity.getCartid());
		cardProductEntity.setProductid(productid);
		
		if (quantity == 0) {
			cartProductRepo.removeAllFromCart(entity.getCartid());
		} else {
			cardProductEntity.setQuantity(cardProductEntity.getQuantity() + quantity); 
		}
		cardProductEntity.setQuantity(quantity);
		cartProductRepo.save(cardProductEntity);
		
	
	}

	@Override
	public List<Cart> viewCart(long userid) throws UserNotFoundException, ProductNotFoundException {
		CartEntity entity = cartRepo.findCartByUserId(userid);
		if (entity == null) {
			throw new UserNotFoundException("User or Cart not found");
		}
		List<CartProductEntity>  cartProductEntity = cartProductRepo.getCartData(entity.getCartid());
		if (cartProductEntity == null || cartProductEntity.isEmpty()) {
			throw new ProductNotFoundException("Product not found");
		}
		List<Cart> cartList = new ArrayList<>(cartProductEntity.size());
		cartProductEntity.forEach(e -> {
			Cart cart = new Cart();
			cart.setUserid(userid);
			BeanUtils.copyProperties(e, cart);
			cartList.add(cart);
		});
		return cartList;
	}

}
