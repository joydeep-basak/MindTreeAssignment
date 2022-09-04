package com.mindtree.assignment.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="CartProduct")
@IdClass(CartProductId.class)
//@NamedQuery(name = "CartProductEntity.addToCart", query = "insert into Cart")
@NamedQuery(name = "CartProductEntity.updateToCart", query = "UPDATE CartProductEntity c SET c.quantity = ?3 WHERE c.cartid = ?1 "
		+ "AND c.productid = ?2")
@NamedQuery(name = "CartProductEntity.deleteFromCart", query = "DELETE FROM CartProductEntity c WHERE c.cartid = ?1 AND c.productid = ?2")
@NamedQuery(name = "CartProductEntity.viewFromCart", query = "FROM CartProductEntity WHERE cartid = ?1")
@NamedQuery(name = "CartProductEntity.removeAllFromCart", query = "DELETE FROM CartProductEntity WHERE cartid = ?1")
public class CartProductEntity {

	@Id
	private long cartid;
	
	@Id
	private long productid;
	
	@Column(name = "quantity")
	private int quantity;
	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "productid", referencedColumnName = "productid")
//	private Set<ProductEntity> productList;
}
