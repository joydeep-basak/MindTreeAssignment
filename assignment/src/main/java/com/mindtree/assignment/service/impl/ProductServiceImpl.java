package com.mindtree.assignment.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.slf4j.MDC;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.assignment.entity.ProductEntity;
import com.mindtree.assignment.exception.ProductNotFoundException;
import com.mindtree.assignment.model.Apparal;
import com.mindtree.assignment.model.Book;
import com.mindtree.assignment.model.Product;
import com.mindtree.assignment.model.ProductEnum;
import com.mindtree.assignment.model.Student;
import com.mindtree.assignment.repository.ProductRepository;
import com.mindtree.assignment.service.ProductService;
//import com.mindtree.assignment.util.ProductDtoToEntityMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@CacheConfig(cacheNames = "productCache")
public class ProductServiceImpl implements ProductService {

	//	@Autowired
	//	private ProductDtoToEntityMapper mapper;
	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private CrudServiceImpl crudService;

	@Autowired
	private ProductRepository repository;

	@Override
	@Cacheable(
		      value = "productCache", 
		      key = "#id" 
		      )
	public Product findProductById(Long id) throws ProductNotFoundException {
		log.info("Called findProductById with id :: {id}", id);
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
		final List<Student> studentList = new ArrayList<>();
		
		String rawList = "";
		try {
			rawList = crudService.findAllStudentAsync(MDC.getCopyOfContextMap()).get();
			String rawBookList = crudService.findAllBookAsync(MDC.getCopyOfContextMap()).get();
			log.info(rawBookList);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		String rawList = responseEntity.getBody();
		ObjectMapper mapper = new ObjectMapper();
		try {
			Student[] student = mapper.readValue(rawList, Student[].class);
			for (int i = 0; i < student.length; i++) {
				studentList.add(student[i]);
			}
			log.info("Student :: ",student);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
//		rawList.forEach(e -> {
//			ObjectMapper mapper = new ObjectMapper();
//			try {
//				List student = mapper.readValue(e, Student.class);
//				//studentList.add(student);
//			} catch (JsonMappingException e1) {
//				e1.printStackTrace();
//			} catch (JsonProcessingException e1) {
//				e1.printStackTrace();
//			}
//		});
		Cache cache = cacheManager.getCache("productCache");
		repository.findAll().forEach(entity -> {
			
			if (entity.getProductType().equals(ProductEnum.BOOK.toString())) {
				
				Product product = new Book();
				//				BeanUtils.copyProperties(entity, product);
				//				copySpecialValueBook(entity, (Book) product);
				product.setProductType(ProductEnum.valueOf(entity.getProductType()));
				product.setStudentList(studentList);
				BeanUtils.copyProperties(entity, product);
				productList.add(product);
				cache.put(product.getProductid(), product);
			} else {
				Product product = new Apparal();
				//				BeanUtils.copyProperties(entity, product);
				//				copySpecialValueApparal(entity, (Apparal) product);
				product.setProductType(ProductEnum.valueOf(entity.getProductType()));
				BeanUtils.copyProperties(entity, product);
				product.setStudentList(studentList);
				productList.add(product);
				cache.put(product.getProductid(), product);
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
