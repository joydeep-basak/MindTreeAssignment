package com.mindtree.assignment.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.assignment.entity.CartEntity;
import com.mindtree.assignment.entity.CartProductEntity;
import com.mindtree.assignment.exception.CartNotExistsException;
import com.mindtree.assignment.exception.ProductNotFoundException;
import com.mindtree.assignment.exception.UserNotFoundException;
import com.mindtree.assignment.model.Cart;
import com.mindtree.assignment.model.CartSummary;
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
	public CartProductEntity addToCart(long userid, long productid, int quantity) throws ProductNotFoundException, CartNotExistsException {
		if (!productRepo.existsById(productid)) {
			throw new ProductNotFoundException("Product not found");
		}
		CartEntity entity = cartRepo.findCartByUserId(userid);
		CartProductEntity cardProductEntity = cartProductRepo.getCartDataByCartAndProduct(entity.getCartid(), productid);
		if (cardProductEntity == null && quantity == 0) {
			throw new CartNotExistsException("No cart exists for the user");
		}
		if (quantity >= 0 && cardProductEntity == null) {
			cardProductEntity = new CartProductEntity();
			cardProductEntity.setCartid(entity.getCartid());
			cardProductEntity.setProductid(productid);
			cardProductEntity.setQuantity(quantity);
			return cartProductRepo.saveAndFlush(cardProductEntity);
		} else {
			if (quantity == 0) {
				cartProductRepo.removeAllFromCart(entity.getCartid(), productid);
				return cardProductEntity;
			} else {
				cardProductEntity.setQuantity(cardProductEntity.getQuantity() + quantity);
				return cartProductRepo.saveAndFlush(cardProductEntity);
			}
		}
	}

	@Override
	public int removeFromCart(long userid, long productid) throws ProductNotFoundException, UserNotFoundException {
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
			return cartProductRepo.removeAllFromCart(entity.getCartid(), productid);
		}
		
	}

	@Override
	public int removeAllFromCart(long userid) throws UserNotFoundException, ProductNotFoundException {
		CartEntity entity = cartRepo.findCartByUserId(userid);
		if (entity == null) {
			throw new UserNotFoundException("User or Cart not found");
		}
		List<CartProductEntity>  cartProductEntity = cartProductRepo.getCartData(entity.getCartid());
		if (cartProductEntity == null || cartProductEntity.isEmpty()) {
			throw new ProductNotFoundException("Product not found");
		} else {
			return cartProductRepo.removeCart(entity.getCartid());
		}
	}

	@Override
	public CartProductEntity updateCart(long userid, long productid, int quantity) throws ProductNotFoundException, CartNotExistsException {

		if (!productRepo.existsById(productid)) {
			throw new ProductNotFoundException("Product not found");
		}
		CartEntity entity = cartRepo.findCartByUserId(userid);
		CartProductEntity cardProductEntity = cartProductRepo.getCartDataByCartAndProduct(entity.getCartid(), productid);
		if (cardProductEntity == null) {
//			if (quantity != 0) {
//				cardProductEntity = new CartProductEntity();
//				cardProductEntity.setCartid(entity.getCartid());
//				cardProductEntity.setProductid(productid);
//				cardProductEntity.setQuantity(quantity);
//				return cartProductRepo.saveAndFlushcardProductEntity);
//			}
			throw new CartNotExistsException("No product with this type exists");
			
		} else {
			if (quantity == 0 && cardProductEntity != null) {
				cartProductRepo.removeAllFromCart(entity.getCartid(), productid);
				return cardProductEntity;
			} else if (cardProductEntity != null){
				cardProductEntity.setQuantity(quantity);
				return cartProductRepo.saveAndFlush(cardProductEntity);
			}
		}
		return cardProductEntity;
		
	
	}

	@Override
	public CartSummary viewCart(long userid) throws ProductNotFoundException, CartNotExistsException {
		CartSummary cartSummary = new CartSummary();
		CartEntity entity = cartRepo.findCartByUserId(userid);
		if (entity == null) {
			throw new CartNotExistsException("User or Cart not found");
		}
		List<CartProductEntity> cartProductEntity = entity.getCart();//cartProductRepo.getCartData(entity.getCartid());
		if (cartProductEntity == null || cartProductEntity.isEmpty()) {
			throw new ProductNotFoundException("Product not found");
		}
		List<Cart> cartList = new ArrayList<>(cartProductEntity.size());
		cartProductEntity.forEach(cartProduct -> {
			Cart cart = new Cart();
			cart.setUserid(userid);
			BeanUtils.copyProperties(cartProduct, cart);
			cart.setItemPrice((cartProduct.getQuantity() * cartProduct.getProduct().getPrice()));
			cartList.add(cart);
		});
		
		cartSummary.setTotalPrice(cartList.stream().collect(Collectors.summingDouble(Cart::getItemPrice)));
		cartSummary.setCartList(cartList);;
		return cartSummary;
	}

}
