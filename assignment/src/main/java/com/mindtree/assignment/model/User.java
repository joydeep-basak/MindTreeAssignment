package com.mindtree.assignment.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User implements Serializable {

    private Long userid;
	
	private String username;
	
	private String address;
	
	private String pincode;
}
