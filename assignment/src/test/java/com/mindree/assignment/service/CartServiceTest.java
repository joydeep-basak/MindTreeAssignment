package com.mindree.assignment.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.easymock.EasyMock;
import org.easymock.IExpectationSetters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mindtree.assignment.AssignmentApplication;
import com.mindtree.assignment.entity.CartProductEntity;
import com.mindtree.assignment.exception.CartNotExistsException;
import com.mindtree.assignment.exception.ProductNotFoundException;
import com.mindtree.assignment.exception.UserNotFoundException;
import com.mindtree.assignment.model.Cart;
import com.mindtree.assignment.model.CartSummary;
import com.mindtree.assignment.service.CartService;
import com.mindtree.assignment.service.impl.CartServiceImpl;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = AssignmentApplication.class)
public class CartServiceTest {
	
	/*
	 * private static String NO_CART_DATA_RESPONSE = "{\r\n" +
	 * "    \"data\": null,\r\n" + "    \"error\": {\r\n" +
	 * "        \"errorCode\": 404,\r\n" +
	 * "        \"errorMessage\": \"No Product not found in DB\"\r\n" + "    }\r\n"
	 * + "}";
	 * 
	 * private String presentCartDataResponse = "{\r\n" + "    \"data\": [\r\n" +
	 * "        {\r\n" + "            \"userid\": 1,\r\n" +
	 * "            \"cartid\": 1,\r\n" + "            \"productid\": 1,\r\n" +
	 * "            \"quantity\": 3\r\n" + "        },\r\n" + "        {\r\n" +
	 * "            \"userid\": 1,\r\n" + "            \"cartid\": 1,\r\n" +
	 * "            \"productid\": 2,\r\n" + "            \"quantity\": 3\r\n" +
	 * "        }\r\n" + "    ],\r\n" + "    \"error\": null\r\n" + "}";
	 */

    @DisplayName("Cart Service to get all cart information")
	@Test
	void testGetCartInfo() throws ProductNotFoundException, CartNotExistsException, JsonMappingException, JsonProcessingException {
    	IExpectationSetters<CartSummary> cartSummary;

		CartService cartService = EasyMock.mock(CartServiceImpl.class);
		cartSummary = expect(cartService.viewCart(1L)).andThrow(new ProductNotFoundException("Product not found"));
		replay(cartService);

		assertThrows(ProductNotFoundException.class,()->{ cartService.viewCart(1);});
 
	}
    
    @DisplayName("Cart Service to add cart information")
   	@Test
   	void testAddToCart() throws ProductNotFoundException, CartNotExistsException, JsonMappingException, JsonProcessingException {
    	CartProductEntity cart = CartProductEntity.builder().cartid(1).productid(2).quantity(3).build();
    	
   		CartService cartService = EasyMock.mock(CartServiceImpl.class);
   		expect(cartService.addToCart(1, 2, 3)).andReturn(cart);
   		replay(cartService);
   		CartProductEntity resultCart = cartService.addToCart(1, 2, 3);

   		assertEquals(resultCart, cart);

   	}
    
    @DisplayName("Cart Service to add further same cart information")
   	@Test
   	void testAddToCartFurther() throws ProductNotFoundException, CartNotExistsException, JsonMappingException, JsonProcessingException {
    	CartProductEntity cart = CartProductEntity.builder().cartid(1).productid(2).quantity(2).build();
    	CartProductEntity expectedQuantityCart = CartProductEntity.builder().cartid(1).productid(2).quantity(5).build();
   		CartService cartService = EasyMock.mock(CartServiceImpl.class);
   		expect(cartService.addToCart(1, 2, 2)).andReturn(cart);
   		expect(cartService.addToCart(1, 2, 3)).andReturn(expectedQuantityCart);
   		replay(cartService);
   		CartProductEntity resultCart = cartService.addToCart(1, 2, 2);
   		resultCart = cartService.addToCart(1, 2, 3);

   		assertEquals(resultCart, expectedQuantityCart);

   	}
    
    @DisplayName("Cart Service to update cart information")
   	@Test
   	void testUpdateToCart() throws ProductNotFoundException, CartNotExistsException, JsonMappingException, JsonProcessingException {
    	CartProductEntity cart = CartProductEntity.builder().cartid(1).productid(2).quantity(3).build();
    	
   		CartService cartService = EasyMock.mock(CartServiceImpl.class);
   		expect(cartService.updateCart(1, 2, 3)).andReturn(cart);
   		replay(cartService);
   		CartProductEntity resultCart = cartService.updateCart(1, 2, 3);

   		assertEquals(resultCart, cart);

   	}
    
    @DisplayName("Cart Service to remove product information from cart")
   	@Test
   	void testRemoveFromCart() throws ProductNotFoundException, CartNotExistsException, JsonMappingException, JsonProcessingException, UserNotFoundException {
    	
   		CartService cartService = EasyMock.mock(CartServiceImpl.class);
   		expect(cartService.removeFromCart(1, 2)).andReturn(1);
   		replay(cartService);
   		int result = cartService.removeFromCart(1, 2);

   		assertEquals(result, 1);

   	}
    
    @DisplayName("Cart Service to remove all cart information")
   	@Test
   	void testRemoveAllFromCart() throws ProductNotFoundException, CartNotExistsException, JsonMappingException, JsonProcessingException, UserNotFoundException {
    	
   		CartService cartService = EasyMock.mock(CartServiceImpl.class);
   		expect(cartService.removeAllFromCart(1L)).andReturn(1);
   		replay(cartService);
   		int result = cartService.removeAllFromCart(1);

   		assertEquals(result, 1);

   	}
    
}
