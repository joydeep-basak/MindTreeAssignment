package com.mindree.assignment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.assignment.AssignmentApplication;
import com.mindtree.assignment.entity.ProductEntity;
import com.mindtree.assignment.exception.ProductNotFoundException;
import com.mindtree.assignment.model.Apparal;
import com.mindtree.assignment.model.Book;
import com.mindtree.assignment.model.Product;
import com.mindtree.assignment.model.ProductEnum;
import com.mindtree.assignment.response.ApiResponse;
import com.mindtree.assignment.service.ProductService;
import com.mindtree.assignment.service.impl.ProductServiceImpl;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = AssignmentApplication.class)
public class ProductServiceTest {
	
	private List<Product> productList = new ArrayList<Product>();
	
	private String data = "{\r\n" + 
			"    \"data\": [\r\n" + 
			"        {\r\n" + 
			"            \"productid\": 1,\r\n" + 
			"            \"prodName\": \"Prod1\",\r\n" + 
			"            \"price\": 10.22,\r\n" + 
			"            \"productType\": \"BOOK\",\r\n" + 
			"            \"genre\": \"genrel\",\r\n" + 
			"            \"author\": \"Thomas Cook\",\r\n" + 
			"            \"publications\": \"McGrawHill\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"productid\": 2,\r\n" + 
			"            \"prodName\": \"Appr1\",\r\n" + 
			"            \"price\": 212.33,\r\n" + 
			"            \"productType\": \"APPARAL\",\r\n" + 
			"            \"atype\": \"cat1\",\r\n" + 
			"            \"brand\": \"Anchor\",\r\n" + 
			"            \"design\": \"Glossy\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"productid\": 3,\r\n" + 
			"            \"prodName\": \"Prod2\",\r\n" + 
			"            \"price\": 10.22,\r\n" + 
			"            \"productType\": \"BOOK\",\r\n" + 
			"            \"genre\": \"genre2\",\r\n" + 
			"            \"author\": \"Thomas Cook2\",\r\n" + 
			"            \"publications\": \"McGrawHill2\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"productid\": 4,\r\n" + 
			"            \"prodName\": \"Appr2\",\r\n" + 
			"            \"price\": 212.33,\r\n" + 
			"            \"productType\": \"APPARAL\",\r\n" + 
			"            \"atype\": \"cat2\",\r\n" + 
			"            \"brand\": \"Anchor2\",\r\n" + 
			"            \"design\": \"Glossy2\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"productid\": 5,\r\n" + 
			"            \"prodName\": \"Book3\",\r\n" + 
			"            \"price\": 10.22,\r\n" + 
			"            \"productType\": \"BOOK\",\r\n" + 
			"            \"genre\": \"genre3\",\r\n" + 
			"            \"author\": \"Thomas Cook3\",\r\n" + 
			"            \"publications\": \"McGrawHill3\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"productid\": 6,\r\n" + 
			"            \"prodName\": \"Appr3\",\r\n" + 
			"            \"price\": 212.33,\r\n" + 
			"            \"productType\": \"APPARAL\",\r\n" + 
			"            \"atype\": \"cat3\",\r\n" + 
			"            \"brand\": \"Anchor3\",\r\n" + 
			"            \"design\": \"Glossy3\"\r\n" + 
			"        }\r\n" + 
			"    ],\r\n" + 
			"    \"error\": null\r\n" + 
			"}";


   @PostConstruct
   public void setMockData() throws JsonMappingException, JsonProcessingException {
    	ObjectMapper objectMapper = new ObjectMapper();
    	ApiResponse response = objectMapper.readValue(data, ApiResponse.class);
    	List<LinkedHashMap<String, Object>> objectList = (List<LinkedHashMap<String, Object>>) response.getData();
    	objectList.forEach(e -> {
    		ProductEntity entity = objectMapper.convertValue(e, ProductEntity.class);
    		if (entity.getProductType().equals(ProductEnum.BOOK.toString())) {
				Product product = new Book();
				product.setProductType(ProductEnum.valueOf(entity.getProductType()));
				BeanUtils.copyProperties(entity, product);
				productList.add(product);
			} else {
				Product product = new Apparal();
				product.setProductType(ProductEnum.valueOf(entity.getProductType()));
				BeanUtils.copyProperties(entity, product);
				productList.add(product);
			}
    	});
    }

    @DisplayName("Product Service find All Product")
    @Test
   	void testAllProduct() {

   		ProductService productService;

   		productService = Mockito.mock(ProductServiceImpl.class);

   		when(productService.findAllProduct()).thenReturn(productList);

   		List<Product> prodList = productService.findAllProduct();

   		assertEquals(prodList, productList);

   	}
    
    @DisplayName("Product Service find Product By Id")
    @Test
    void testProductById() throws ProductNotFoundException {

   		ProductService productService;

   		productService = Mockito.mock(ProductServiceImpl.class);

   		when(productService.findProductById(1L)).thenReturn(productList.get(0));

   		Product product = productService.findProductById(1L);

   		assertEquals(product, productList.get(0));
    	
    }
    
    @DisplayName("Product Service find Product By Name")
    @Test
    void testProductByName() throws ProductNotFoundException {
    	ProductService productService;

   		productService = Mockito.mock(ProductServiceImpl.class);
   		
   		List<Product> mockProdList = productList.stream().filter(e -> e.getProdName().equals("Appr2")).collect(Collectors.toList());

   		when(productService.findProductByName("Appr2")).thenReturn(mockProdList);

   		List<Product> resultProdList=  productService.findProductByName("Appr2");

   		assertEquals(mockProdList, resultProdList);
    }
    
    @DisplayName("Product Service find Product By Type")
    @Test
    void testProductByType() throws ProductNotFoundException {
    	
    	ProductService productService;

   		productService = Mockito.mock(ProductServiceImpl.class);
   		
   		List<Product> mockProdList = productList.stream().filter(e -> e.getProductType().name().equals("APPARAL")).collect(Collectors.toList());

   		when(productService.findProductByType("APPARAL")).thenReturn(mockProdList);

   		List<Product> resultList =  productService.findProductByType("APPARAL");

   		assertEquals(resultList, mockProdList);
    	
    }


}
