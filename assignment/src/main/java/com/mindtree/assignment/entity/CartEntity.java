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
//@IdClass(CartUserId.class)
@Data
@Table(name="Cart")
@NamedQuery(name = "CartEntity.findCartByUserId", query = "FROM CartEntity WHERE userid = ?1")
public class CartEntity implements Comparable<Long>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cartid")
	private long cartid;
	
	@Column(name = "userid")
	private long userid;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumns({    
		@JoinColumn(name = "cartid", referencedColumnName = "cartid", insertable = false, updatable = false),
		})
	private List<CartProductEntity> cart;

	@Override
	public int compareTo(Long cartid) {
		if (this.cartid == cartid) {
			return 0;
		} else if (this.cartid > cartid) {
			return 1;
		} else {
			return -1;
		}
		
	}
	
	
//	
//	@OneToOne(cascade = CascadeType.DETACH)
//	@JoinColumns({    
//		@JoinColumn(name = "userid", referencedColumnName = "userid", insertable =  false, updatable = false)
//		})
//	private UserEntity user;
}
