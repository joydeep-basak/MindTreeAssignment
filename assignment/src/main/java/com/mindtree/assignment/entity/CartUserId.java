package com.mindtree.assignment.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class CartUserId implements Serializable {

	private static final long serialVersionUID = 4213362064457578387L;

	private long cartid;
	
	private long userid;
}
