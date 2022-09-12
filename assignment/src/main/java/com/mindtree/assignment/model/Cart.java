package com.mindtree.assignment.model;

import java.io.Serializable;

import com.mindtree.assignment.constraint.Negetive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class Cart implements Serializable {

	private long userid;
	
	private long cartid;
	
	private long productid;
	
	@Negetive(message="Quantity can not be negetive")
	private int quantity;
}
