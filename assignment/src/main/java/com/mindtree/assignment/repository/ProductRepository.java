package com.mindtree.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.assignment.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	
	List<ProductEntity> findProductByName(String productName);
	
	List<ProductEntity> findProductByType(String productType);

}
