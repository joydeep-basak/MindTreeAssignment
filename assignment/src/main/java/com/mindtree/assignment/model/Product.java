package com.mindtree.assignment.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class Product implements Serializable {

	protected long productid;
	
	protected String prodName;
	
	protected float price;
	
	protected ProductEnum productType;
}
