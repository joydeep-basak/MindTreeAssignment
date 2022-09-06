package com.mindtree.assignment.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.assignment.entity.CartProductEntity;
import com.mindtree.assignment.entity.CartProductId;

@Repository
public interface CartProductRepository extends JpaRepository<CartProductEntity, CartProductId>{

	@Modifying
	@Transactional(propagation = Propagation.NESTED)
	int updateToCart(long cartid, long productid, int quantity);

	@Transactional(isolation = Isolation.READ_COMMITTED)
	List<CartProductEntity> getCartData(long cartid);

	CartProductEntity getCartDataByCartAndProduct(long cartid, long productid);
	
	@Modifying
	@Transactional(propagation = Propagation.NESTED)
	List<CartProductEntity> deleteFromCart(long cartid, long productid);
	
	@Transactional(propagation = Propagation.NESTED)
	@Modifying
	int removeAllFromCart(long cartid, long productid);
	
	@Transactional(propagation = Propagation.NESTED)
	@Modifying
	int removeCart(long cartid);

}
