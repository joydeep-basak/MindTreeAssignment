package com.mindtree.assignment.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

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

	@NotNull(message = "User id should not be blank")
	private Long userid;
	
	private long cartid;
	
	@NotNull(message = "Product id should not be blank")
	private Long productid;
	
	private double itemPrice;
	
	private String productName;
	
	@Negetive(message="Quantity can not be negetive")
	private int quantity;
}
