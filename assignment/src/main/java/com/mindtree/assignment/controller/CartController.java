package com.mindtree.assignment.controller;

import java.util.List;

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
import com.mindtree.assignment.exception.ProductNotFoundException;
import com.mindtree.assignment.exception.UserNotFoundException;
import com.mindtree.assignment.model.Cart;
import com.mindtree.assignment.service.CartService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/cart/v1")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping(path="/all/{userid}", produces = "application/json")
	public ResponseEntity<List<Cart>> viewAllCart(@PathVariable("userid") long userid) throws ProductNotFoundException, UserNotFoundException {
		log.info("Showing cart of user [{}]", userid);
		List<Cart> cartList = cartService.viewCart(userid);
		log.info("Cart size of user [{}]", cartList.size());
		return new ResponseEntity<>(cartList, HttpStatus.OK);
	}

	@PostMapping(path="/addtocart", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Cart> addToCart(@RequestBody Cart cart) throws ProductNotFoundException {
		CartProductEntity cartProduct = cartService.addToCart(cart.getUserid(), cart.getProductid(), cart.getQuantity());
		Cart cartResponse = new Cart();
		cartResponse.setUserid(cart.getUserid());
		BeanUtils.copyProperties(cartProduct, cartResponse);
		return new ResponseEntity<>(cartResponse, HttpStatus.OK);
	}
	

}
