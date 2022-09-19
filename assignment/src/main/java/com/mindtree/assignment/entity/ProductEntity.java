package com.mindtree.assignment.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="Product")
@NamedQuery(name = "ProductEntity.findProductByName", query = "FROM ProductEntity WHERE prodName like ?1")
@NamedQuery(name = "ProductEntity.findProductByType", query = "SELECT p FROM ProductEntity p WHERE p.productType = ?1")
public class ProductEntity implements Comparable<Long> {

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
	
	@OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinColumns({    
		@JoinColumn(name = "productid", referencedColumnName = "productid", insertable = false, updatable = false),
		})
	private List<CartProductEntity> cartProduct;
	
	@Override
	public int compareTo(Long productid) {
		if (this.productid == productid) {
			return 0;
		} else if (this.productid > productid) {
			return 1;
		} else {
			return -1;
		}
		
	}
	
}
