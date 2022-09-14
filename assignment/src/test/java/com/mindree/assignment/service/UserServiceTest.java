package com.mindree.assignment.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.easymock.EasyMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.assignment.AssignmentApplication;
import com.mindtree.assignment.model.User;
import com.mindtree.assignment.response.ApiResponse;
import com.mindtree.assignment.service.UserService;
import com.mindtree.assignment.service.impl.UserServiceImpl;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = AssignmentApplication.class)
public class UserServiceTest {

	private List<User> userList = new ArrayList<User>();

	private String data = "{\r\n" + 
			"    \"data\": [\r\n" + 
			"        {\r\n" + 
			"            \"userid\": 1,\r\n" + 
			"            \"username\": \"user1\",\r\n" + 
			"            \"address\": \"address1\",\r\n" + 
			"            \"pincode\": \"pincode1\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"userid\": 2,\r\n" + 
			"            \"username\": \"user2\",\r\n" + 
			"            \"address\": \"address2\",\r\n" + 
			"            \"pincode\": \"pincode2\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"userid\": 3,\r\n" + 
			"            \"username\": \"user3\",\r\n" + 
			"            \"address\": \"address3\",\r\n" + 
			"            \"pincode\": \"pincode3\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"userid\": 4,\r\n" + 
			"            \"username\": \"user4\",\r\n" + 
			"            \"address\": \"address4\",\r\n" + 
			"            \"pincode\": \"pincode4\"\r\n" + 
			"        }\r\n" + 
			"    ],\r\n" + 
			"    \"error\": null\r\n" + 
			"}";

	@PostConstruct
	public void setMockData() throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		ApiResponse response = objectMapper.readValue(data, ApiResponse.class);
		List<LinkedHashMap<String, Object>> objectList = (List<LinkedHashMap<String, Object>>) response.getData();
		objectList.forEach(e -> {
			User user = objectMapper.convertValue(e, User.class);
			userList.add(user);
		});
	}

	@DisplayName("User Service to add a User")
	@Test
	void testAddUser() {
		User user = User.builder().userid(2L).address("addr1").pincode("pincode").username("User1").build();

		UserService userService = EasyMock.mock(UserServiceImpl.class);
		expect(userService.addUser(user)).andReturn(user);
		replay(userService);
		User resultUser = userService.addUser(user);

		
		assertEquals(resultUser, user);

	}
	
}
