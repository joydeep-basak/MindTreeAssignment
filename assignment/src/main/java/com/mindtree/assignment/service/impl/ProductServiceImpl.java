package com.mindtree.assignment.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.mindtree.assignment.entity.ProductEntity;
import com.mindtree.assignment.exception.ProductNotFoundException;
import com.mindtree.assignment.model.Apparal;
import com.mindtree.assignment.model.Book;
import com.mindtree.assignment.model.Product;
import com.mindtree.assignment.model.ProductEnum;
import com.mindtree.assignment.repository.ProductRepository;
import com.mindtree.assignment.service.ProductService;
//import com.mindtree.assignment.util.ProductDtoToEntityMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
	
//	@Autowired
//	private ProductDtoToEntityMapper mapper;


	@Autowired
	private ProductRepository repository;

	@Override
	public Product findProductById(long id) throws ProductNotFoundException {
		Optional<ProductEntity> productEntity = repository.findById(id);
		Product product = null;
		if (productEntity.isPresent()) {
			ProductEntity entity = repository.findById(id).get();
			if (entity.getProductType().equals(ProductEnum.BOOK.toString())) {
				product = new Book();
//				BeanUtils.copyProperties(entity, product);
//				copySpecialValueBook(entity, (Book) product);
//				product = mapper.sourceToDestinationBook(entity);
				BeanUtils.copyProperties(entity, product);
				product.setProductType(ProductEnum.valueOf(entity.getProductType()));
			} else {
				product = new Apparal();
				product.setProductType(ProductEnum.valueOf(entity.getProductType()));
				BeanUtils.copyProperties(entity, product);
			}
//			product = mapper.sourceToDestinationBook(repository.findById(id).get());
			return product;
		} else {
			throw new ProductNotFoundException("Product Not Found");
//			product = mapper.sourceToDestinationBook(productEntity.orElse(null));
		}
	}

	@Override
	public List<Product> findProductByName(String productName) {
		List<ProductEntity> productEntityList = repository.findProductByName(productName);
		List<Product> productList = new ArrayList<Product>();
		productEntityList.forEach(entity -> {
			if (entity.getProductType().equals(ProductEnum.BOOK.toString())) {
				Product product = new Book();
//				BeanUtils.copyProperties(entity, product);
//				copySpecialValueBook(entity, (Book) product);
				product.setProductType(ProductEnum.valueOf(entity.getProductType()));
				BeanUtils.copyProperties(entity, product);
				productList.add(product);
			} else {
				Product product = new Apparal();
//				BeanUtils.copyProperties(entity, product);
//				copySpecialValueApparal(entity, (Apparal) product);
				product.setProductType(ProductEnum.valueOf(entity.getProductType()));
				BeanUtils.copyProperties(entity, product);
				productList.add(product);
			}
		}); 
		log.info("Total Product listed [{}]", productList.size());
		return productList;
	}

	@Override
	public List<Product> findProductByType(String productType) {
		List<ProductEntity> productEntityList = repository.findProductByType(productType);
		List<Product> productList = new ArrayList<Product>();
		productEntityList.forEach(entity -> {
			if (entity.getProductType().equals(ProductEnum.BOOK.toString())) {
				Product product = new Book();
//				BeanUtils.copyProperties(entity, product);
//				copySpecialValueBook(entity, (Book) product);
				product.setProductType(ProductEnum.valueOf(entity.getProductType()));
				BeanUtils.copyProperties(entity, product);
				productList.add(product);
			} else {
				Product product = new Apparal();
//				BeanUtils.copyProperties(entity, product);
//				copySpecialValueApparal(entity, (Apparal) product);
				product.setProductType(ProductEnum.valueOf(entity.getProductType()));
				BeanUtils.copyProperties(entity, product);
				productList.add(product);
			}
		}); 
		log.info("Total Product listed [{}]", productList.size());
		return productList;
	}

	@Override
	public List<Product> findAllProduct() {
		List<Product> productList = new ArrayList<Product>();
		repository.findAll().forEach(entity -> {
			if (entity.getProductType().equals(ProductEnum.BOOK.toString())) {
				Product product = new Book();
//				BeanUtils.copyProperties(entity, product);
//				copySpecialValueBook(entity, (Book) product);
				product.setProductType(ProductEnum.valueOf(entity.getProductType()));
				BeanUtils.copyProperties(entity, product);
				productList.add(product);
			} else {
				Product product = new Apparal();
//				BeanUtils.copyProperties(entity, product);
//				copySpecialValueApparal(entity, (Apparal) product);
				product.setProductType(ProductEnum.valueOf(entity.getProductType()));
				BeanUtils.copyProperties(entity, product);
				productList.add(product);
			}
		});
		log.info("Total Product listed [{}]", productList.size());
		return productList;
	}
	
	private void copySpecialValueBook(ProductEntity productEntity, Book product) {
		BeanUtils.copyProperties(productEntity, product);
	}
	
	private void copySpecialValueApparal(ProductEntity productEntity, Apparal product) {
		BeanUtils.copyProperties(productEntity, product);
	}

//	@Bean
//	public ProductDtoToEntityMapper getMapper() {
//		ProductDtoToEntityMapper mapper
//	    = Mappers.getMapper(ProductDtoToEntityMapper.class);
//		return mapper;
//	}
}
