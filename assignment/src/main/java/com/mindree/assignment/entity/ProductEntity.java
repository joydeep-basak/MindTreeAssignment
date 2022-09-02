package com.mindree.assignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="Product")
@NamedQuery(name = "ProductEntity.findProductByName", query = "FROM ProductEntity WHERE prodName like ?1")
@NamedQuery(name = "ProductEntity.findProductByType", query = "SELECT p FROM ProductEntity p WHERE p.productType = ?1")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productid;
	
	@Column(name = "prodName")
	private String prodName;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "productType")
	private String productType;
	
	@Column(name = "genre")
	private String genre;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "publications")
	private String publications;
	
	@Column(name = "atype")
	private String atype;
	
	private String brand;
	
	private String design;
	
	/*price FLOAT NOT NULL,
	  productType VARCHAR(30) NOT NULL,
	  genre VARCHAR(50),
	  author VARCHAR(50),
	  publications VARCHAR(50),
	  atype VARCHAR(50),
	  brand VARCHAR(50),
	  design VARCHAR(50),*/
}
