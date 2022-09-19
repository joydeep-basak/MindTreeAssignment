package com.mindree.assignment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.mindtree.assignment.AssignmentApplication;
import com.mindtree.assignment.model.Cart;
import com.mindtree.assignment.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = AssignmentApplication.class)
@TestMethodOrder(OrderAnnotation.class)
public class CartControllerTest {

	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    public void getViewCart() throws Exception {
    	log.info("First Test");
        ResponseEntity<ApiResponse> response = restTemplate.getForEntity(new URL("http://localhost:9080/api/cart/v1/all/1").toString(), ApiResponse.class);
        assertEquals(404, response.getBody().getError().getErrorCode());
        assertEquals(404, response.getStatusCodeValue());
    }
    
    @Test
    @Order(2)
    public void addToCart() throws Exception {
    	log.info("Second Test");
    	Cart cart = Cart.builder().userid(1l).productid(2l).quantity(2).build();

        ResponseEntity<ApiResponse> response = restTemplate.postForEntity(new URL("http://localhost:9080/api/cart/v1/addtocart").toString(),cart, ApiResponse.class);
        assertEquals(2, ((java.util.LinkedHashMap<String, Object>) response.getBody().getData()).get("productid"));
        assertEquals(2, ((java.util.LinkedHashMap<String, Object>) response.getBody().getData()).get("quantity"));
        assertEquals(200, response.getStatusCodeValue());
    }
    
    @Test
    @Order(3)
    public void getViewCartAfterInsert() throws Exception {

        ResponseEntity<ApiResponse> response = restTemplate.getForEntity(new URL("http://localhost:9080/api/cart/v1/all/1").toString(), ApiResponse.class);
        assertEquals(200, response.getStatusCodeValue());
    }
//    
    @Test
    @Order(4)
    public void addToCartFurther() throws Exception {
    	
    	Cart cart = Cart.builder().userid(1l).productid(2l).quantity(5).build();

        ResponseEntity<ApiResponse> response = restTemplate.postForEntity(new URL("http://localhost:9080/api/cart/v1/addtocart").toString(),cart, ApiResponse.class);
        assertEquals(2, ((java.util.LinkedHashMap<String, Object>) response.getBody().getData()).get("productid"));
        assertEquals(7, ((java.util.LinkedHashMap<String, Object>) response.getBody().getData()).get("quantity"));
        assertEquals(200, response.getStatusCodeValue());
    }
    
    @Test
    @Order(5)
    public void updateToCart() throws Exception {
    	log.info("Third Test");
    	Cart cart = Cart.builder().userid(1l).cartid(1).productid(2l).quantity(5).build();

        ResponseEntity<ApiResponse> response = restTemplate.postForEntity(new URL("http://localhost:9080/api/cart/v1/updatetocart").toString(),cart, ApiResponse.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, ((java.util.LinkedHashMap<String, Object>) response.getBody().getData()).get("productid"));
        assertEquals(5, ((java.util.LinkedHashMap<String, Object>) response.getBody().getData()).get("quantity"));
        
    }
}
