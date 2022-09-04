package com.mindtree.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.assignment.entity.CartEntity;
import com.mindtree.assignment.entity.CartUserId;

public interface CartRepository extends JpaRepository<CartEntity, CartUserId>{

	CartEntity findCartByUserId(long userid);
} 