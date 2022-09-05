package com.mindtree.assignment.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.assignment.entity.CartProductEntity;
import com.mindtree.assignment.entity.CartProductId;

@Repository
public interface CartProductRepository extends JpaRepository<CartProductEntity, CartProductId>{

	@Modifying
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	int updateToCart(long cartid, long productid, int quantity);

	List<CartProductEntity> getCartData(long cartid);

	CartProductEntity getCartDataByCartAndProduct(long cartid, long productid);
	
	@Modifying
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	List<CartProductEntity> deleteFromCart(long cartid, long productid);
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Modifying
	int removeAllFromCart(long cartid);

}
