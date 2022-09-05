package com.mindtree.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.assignment.entity.CartProductEntity;
import com.mindtree.assignment.entity.CartProductId;

public interface CartProductRepository extends JpaRepository<CartProductEntity, CartProductId>{
	
	int updateToCart(long cartid, long productid, int quantity);
	
	List<CartProductEntity> getCartData(long cartid);
	
	CartProductEntity getCartDataByCartAndProduct(long cartid, long productid);
	
	List<CartProductEntity> deleteFromCart(long cartid, long productid);
	
	int removeAllFromCart(long cartid);

}
