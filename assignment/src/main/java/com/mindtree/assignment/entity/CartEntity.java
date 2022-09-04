package com.mindtree.assignment.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@IdClass(CartUserId.class)
@Data
@Table(name="Cart")
@NamedQuery(name = "CartEntity.findCartUserId", query = "FROM CartEntity WHERE userid = ?1")
public class CartEntity {
	@Id
	private long cartid;
	
	@Id
	private long userid;
	
//	@OneToOne(cascade = CascadeType.DETACH)
//	@JoinColumns({    
//		@JoinColumn(name = "cartid", referencedColumnName = "cartid"),
//		})
//	private CartProductEntity cart;
//	
//	@OneToOne(cascade = CascadeType.DETACH)
//	@JoinColumns({    
//		@JoinColumn(name = "userid", referencedColumnName = "userid", insertable =  false, updatable = false)
//		})
//	private UserEntity user;
}
