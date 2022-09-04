package com.mindtree.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Cart {

	private long userid;
	
	private long productid;
	
	private int quantity;
//	 userid BIGINT NOT NULL,
//	  productid BIGINT NOT NULL,
//	  quantity INT,
}
