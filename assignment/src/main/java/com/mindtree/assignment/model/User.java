package com.mindtree.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long userid;
	
	private String username;
	
	private String address;
	
	private String pincode;
}
