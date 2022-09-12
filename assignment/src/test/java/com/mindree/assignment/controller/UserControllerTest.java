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
import com.mindtree.assignment.model.Product;
import com.mindtree.assignment.response.ApiResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = AssignmentApplication.class)
public class UserControllerTest {
	
	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void getAllUser() throws Exception {

        ResponseEntity<ApiResponse> response = restTemplate.getForEntity(new URL("http://localhost:9080/api/user/v1/allusers").toString(), ApiResponse.class);
        assertEquals(4, ((List<Product>) response.getBody().getData()).size());
        assertEquals(200, response.getStatusCodeValue());
    }
    

}
