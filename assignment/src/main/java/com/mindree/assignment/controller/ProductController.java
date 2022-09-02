package com.mindree.assignment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindree.assignment.entity.ProductEntity;
import com.mindree.assignment.model.Book;
import com.mindree.assignment.model.Product;
import com.mindree.assignment.model.ProductEnum;
import com.mindree.assignment.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/product/v1")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProduct() {
		return new ResponseEntity<List<Product>>(productService.findAllProduct(), HttpStatus.OK);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProductDetails(@PathVariable("productId") long productId) {
		log.info("Get ProductDetails called with product id [{}]", productId);
		
		Product product = productService.findProductById(productId);
		
		/*Product product = Book.builder()
				.prodName("Test")
				.genre("Test genre")
				.productid(1)
				.publications("Test publication")
				.prodName("Test Book")
				.author("Test Author")
				.build();*/
		
		product.setProductType(ProductEnum.APPARAL);
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@GetMapping("prodbyname/{prodName}")
	public ResponseEntity<List<Product>> getProductDetailsByName(@PathVariable("prodName") String prodName) {
		log.info("Get ProductDetails called with product name [{}]", prodName);
		
		List<Product> productList = new ArrayList<>();
		Product product = Book.builder()
				.prodName("Test")
				.genre("Test genre")
				.productid(1)
				.publications("Test publication")
				.prodName(prodName)
				.author("Test Author")
				.build();
		
		product.setProductType(ProductEnum.APPARAL);
		
		productList.add(product);
		
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}
	
	@GetMapping("prodbytype/{productType}")
	public ResponseEntity<List<Product>> getProductDetailsByType(@PathVariable("productType") String productType) {
		log.info("Get ProductDetails called with product name [{}]", productType);
		
		List<Product> productList = new ArrayList<>();
		
		Product product = Book.builder()
				.prodName("Test")
				.genre("Test genre")
				.productid(1)
				.publications("Test publication")
				.prodName("Test Product")
				.author("Test Author")
				.build();
		
		productList.add(product);
		
		product.setProductType(ProductEnum.APPARAL);
		
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}
}
