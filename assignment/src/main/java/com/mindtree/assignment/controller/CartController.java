package com.mindtree.assignment.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.assignment.entity.CartProductEntity;
import com.mindtree.assignment.exception.CartNotExistsException;
import com.mindtree.assignment.exception.ProductNotFoundException;
import com.mindtree.assignment.exception.UserNotFoundException;
import com.mindtree.assignment.model.Cart;
import com.mindtree.assignment.model.CartSummary;
import com.mindtree.assignment.service.CartService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/cart/v1")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping(path="/all/{userid}", produces = "application/json")
	public ResponseEntity<CartSummary> viewAllCart(@PathVariable("userid") long userid) throws ProductNotFoundException, UserNotFoundException, CartNotExistsException {
		log.info("Showing cart of user [{}]", userid);
		CartSummary cartSummary = cartService.viewCart(userid);
		log.info("Cart size of user [{}]", cartSummary.getCartList().size());
		return new ResponseEntity<>(cartSummary, HttpStatus.OK);
	}

	@PostMapping(path="/addtocart", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Cart> addToCart(@RequestBody @Valid Cart cart) throws ProductNotFoundException, CartNotExistsException {
		CartProductEntity cartProduct = cartService.addToCart(cart.getUserid(), cart.getProductid(), cart.getQuantity());
		Cart cartResponse = new Cart();
		cartResponse.setUserid(cart.getUserid());
		BeanUtils.copyProperties(cartProduct, cartResponse);
		return new ResponseEntity<>(cartResponse, HttpStatus.OK);
	}
	
	@PostMapping(path="/updatetocart", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Cart> updateToCart(@RequestBody @Valid Cart cart) throws ProductNotFoundException, CartNotExistsException {
		CartProductEntity cartProduct = cartService.updateCart(cart.getUserid(), cart.getProductid(), cart.getQuantity());
		Cart cartResponse = new Cart();
		cartResponse.setUserid(cart.getUserid());
		BeanUtils.copyProperties(cartProduct, cartResponse);
		return new ResponseEntity<>(cartResponse, HttpStatus.OK);
	}
	
	@PostMapping(path="/removeproductfromcart", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> removeProductFromCart(@RequestBody @Valid Cart cart) throws ProductNotFoundException, CartNotExistsException, UserNotFoundException {
	    cartService.removeFromCart(cart.getUserid(), cart.getProductid());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path="/removecart", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> removeCart(@RequestBody @Valid Cart cart) throws ProductNotFoundException, CartNotExistsException, UserNotFoundException {
		cartService.removeAllFromCart(cart.getUserid());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

}
