package com.mindree.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public abstract class Product {

	protected int productid;
	
	protected String prodName;
	
	protected float price;
	
	protected ProductEnum productType;
}
