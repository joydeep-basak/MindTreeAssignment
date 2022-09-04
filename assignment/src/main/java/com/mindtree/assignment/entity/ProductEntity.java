package com.mindtree.assignment.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
	@Column(name = "productid")
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
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "productid", referencedColumnName = "productid")
//	private CartProductEntity cartProduct;
	
}
