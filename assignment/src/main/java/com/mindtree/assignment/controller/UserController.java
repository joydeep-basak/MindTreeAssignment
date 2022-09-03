package com.mindtree.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.assignment.model.User;
import com.mindtree.assignment.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/user/v1")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping(produces = "application/json", path = "/allusers")
	public ResponseEntity<List<User>> findAllUsers() {
		List<User> users = userService.findAllUser();
		log.info("Total Users listed [{}]", users.size());
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
}
