package com.mindtree.assignment.entity;

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
@Table(name="User")
@NamedQuery(name = "UserEntity.findUserByName", query = "FROM UserEntity WHERE username like ?1")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "pincode")
	private String pincode;

}
