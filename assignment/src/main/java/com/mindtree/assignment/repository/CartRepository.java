package com.mindtree.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.assignment.entity.CartEntity;
import com.mindtree.assignment.entity.CartUserId;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, CartUserId>{

	CartEntity findCartByUserId(long userid);
} 