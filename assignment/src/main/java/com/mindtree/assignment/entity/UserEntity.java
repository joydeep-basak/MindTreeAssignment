package com.mindtree.assignment.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="User")
@NamedQuery(name = "UserEntity.findUserByName", query = "FROM UserEntity WHERE username like ?1")
public class UserEntity implements Comparable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userid;

	@Column(name = "username")
	private String username;

	@Column(name = "address")
	private String address;

	@Column(name = "pincode")
	private String pincode;

	@Override
	public int compareTo(Long userid) {
		if (this.userid == userid) {
			return 0;
		} else if (this.userid > userid) {
			return 1;
		} else {
			return -1;
		}
		
	}

//	@OneToOne(cascade = CascadeType.DETACH)
//	@JoinColumn(name = "userid", referencedColumnName = "userid")
//	private List<CartEntity> cart;

}
