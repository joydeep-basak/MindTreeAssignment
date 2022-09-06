package com.mindtree.assignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="CartProduct")
@IdClass(CartProductId.class)
@NamedQuery(name = "CartProductEntity.updateToCart", query = "UPDATE CartProductEntity c SET c.quantity = ?3 WHERE c.cartid = ?1 "
		+ "AND c.productid = ?2")
@NamedQuery(name = "CartProductEntity.getCartDataByCartAndProduct", query = "FROM CartProductEntity WHERE cartid = ?1 AND productid = ?2")
@NamedQuery(name = "CartProductEntity.deleteFromCart", query = "DELETE FROM CartProductEntity c WHERE c.cartid = ?1 AND c.productid = ?2")
@NamedQuery(name = "CartProductEntity.getCartData", query = "FROM CartProductEntity WHERE cartid = ?1")
@NamedQuery(name = "CartProductEntity.removeAllFromCart", query = "DELETE FROM CartProductEntity WHERE cartid = ?1 AND productid = ?2")
@NamedQuery(name = "CartProductEntity.removeCart", query = "DELETE FROM CartProductEntity WHERE cartid = ?1")
public class CartProductEntity {

	@Id
	private long cartid;
	
	@Id
	private long productid;
	
	@Column(name = "quantity")
	private int quantity;
	
//	@OneToMany(cascade = CascadeType.DETACH)
//	@JoinColumns({    
//		@JoinColumn(name = "productid", referencedColumnName = "productid"),
//		})
//	private List<ProductEntity> productList;
	
//	@OneToOne(cascade = CascadeType.DETACH)
//	@JoinColumns({    
//		@JoinColumn(name = "cartid", referencedColumnName = "cartid", insertable =  false, updatable = false)
//		})
//	private CartEntity cart;
	
	
}
