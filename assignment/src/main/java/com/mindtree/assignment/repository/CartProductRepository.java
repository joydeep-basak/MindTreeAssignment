package com.mindtree.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.assignment.entity.CartProductEntity;
import com.mindtree.assignment.entity.CartProductId;

public interface CartProductRepository extends JpaRepository<CartProductEntity, CartProductId>{
	
	int updateToCart(long userid, long productid, int quantity);
	
	List<CartProductEntity> viewFromCart(long userid);
	
	List<CartProductEntity> deleteFromCart(long userid, long productid);
	
	int removeAllFromCart(long userid);

}
