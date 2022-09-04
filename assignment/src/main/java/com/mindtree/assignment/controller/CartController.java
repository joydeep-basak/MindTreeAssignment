package com.mindtree.assignment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.assignment.model.Cart;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/cart/v1")
public class CartController {
	
	@GetMapping
	public ResponseEntity<List<Cart>> viewAllCart(@PathVariable("userid") long userid) {
		log.info("Showing cart of user [{}]", userid);
		List<Cart> cartList = new ArrayList<>();
		log.info("Cart size of user [{}]", cartList.size());
		return new ResponseEntity<>(cartList, HttpStatus.OK);
	}

}
