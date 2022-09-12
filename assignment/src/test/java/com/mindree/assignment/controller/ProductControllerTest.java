package com.mindree.assignment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.mindtree.assignment.AssignmentApplication;
import com.mindtree.assignment.response.ApiResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = AssignmentApplication.class)
public class ProductControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllProduct() throws Exception {

        ResponseEntity<ApiResponse> response = restTemplate.getForEntity(new URL("http://localhost:9080/api/product/v1/all").toString(), ApiResponse.class);
        assertEquals(6, ((List) response.getBody().getData()).size());
        assertEquals(200, response.getStatusCodeValue());
    }
    
    @Test
    public void getAllProductById() throws Exception {

        ResponseEntity<ApiResponse> response = restTemplate.getForEntity(new URL("http://localhost:9080/api/product/v1/1").toString(), ApiResponse.class);
        assertEquals(1, ((java.util.LinkedHashMap<String, Object>) response.getBody().getData()).get("productid"));
        assertEquals(200, response.getStatusCodeValue());
    }
    
    @Test
    public void getAllProductByName() throws Exception {

        ResponseEntity<ApiResponse> response = restTemplate.getForEntity(new URL("http://localhost:9080/api/product/v1/prodbyname/Appr2").toString(), ApiResponse.class);
        assertEquals(1, ((List) response.getBody().getData()).size());
        assertEquals(200, response.getStatusCodeValue());
    }
    
    @Test
    public void getAllProductByType() throws Exception {

        ResponseEntity<ApiResponse> response = restTemplate.getForEntity(new URL("http://localhost:9080/api/product/v1/prodbytype/APPARAL").toString(), ApiResponse.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(3, ((List) response.getBody().getData()).size());

    }

}

