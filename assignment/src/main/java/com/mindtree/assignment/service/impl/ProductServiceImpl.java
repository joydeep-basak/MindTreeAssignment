package com.mindtree.assignment.service.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.mindtree.assignment.entity.ProductEntity;
import com.mindtree.assignment.model.Apparal;
import com.mindtree.assignment.model.Book;
import com.mindtree.assignment.model.Product;
import com.mindtree.assignment.model.ProductEnum;
import com.mindtree.assignment.repository.ProductRepository;
import com.mindtree.assignment.service.ProductService;
import com.mindtree.assignment.util.ProductDtoToEntityMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDtoToEntityMapper mapper;

	@Bean
	public ProductDtoToEntityMapper getMapper() {
		ProductDtoToEntityMapper mapper
	    = Mappers.getMapper(ProductDtoToEntityMapper.class);
		return mapper;
	}
//	@PostConstruct
	public void init() throws SQLException, ClassNotFoundException, IOException {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
 
        // initialize database
        initDatabase();
        int records = getTotalRecords();
        log.info("Total Records [{}]", records);
    }
	
	private void initDatabase() throws SQLException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            statement.execute("CREATE TABLE employee (id INT NOT NULL, name VARCHAR(50) NOT NULL,"
                    + "email VARCHAR(50) NOT NULL, PRIMARY KEY (id))");
            connection.commit();
            statement.executeUpdate(
                    "INSERT INTO employee VALUES (1001,'Vinod Kumar Kashyap', 'vinod@javacodegeeks.com')");
            statement.executeUpdate("INSERT INTO employee VALUES (1002,'Dhwani Kashyap', 'dhwani@javacodegeeks.com')");
            statement.executeUpdate("INSERT INTO employee VALUES (1003,'Asmi Kashyap', 'asmi@javacodegeeks.com')");
            connection.commit();
        }
    }
	
	/**
     * Create a connection
     * 
     * @return connection object
     * @throws SQLException
     */
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:mem:employees", "", "");
    }
    
    private int getTotalRecords() {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            ResultSet result = statement.executeQuery("SELECT count(*) as total FROM employee");
            if (result.next()) {
                return result.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
	
	@Autowired
	private ProductRepository repository;

	@Override
	public Product findProductById(long id) {
		Optional<ProductEntity> productEntity = repository.findById(id);
		Product product = null;
		if (productEntity.isPresent()) {
			ProductEntity entity = repository.findById(id).get();
			if (entity.getProductType().equals(ProductEnum.BOOK.toString())) {
				product = new Book();
//				BeanUtils.copyProperties(entity, product);
//				copySpecialValueBook(entity, (Book) product);
				product = mapper.sourceToDestinationBook(entity);
				BeanUtils.copyProperties(entity, product);
			} else {
				product = new Apparal();
				product = mapper.sourceToDestinationApparal(entity);
				BeanUtils.copyProperties(entity, product);
			}
//			product = mapper.sourceToDestinationBook(repository.findById(id).get());
			return product;
		} else {
			BeanUtils.copyProperties(productEntity.orElse(null), product);
//			product = mapper.sourceToDestinationBook(productEntity.orElse(null));
		}
		return product;
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
				product = mapper.sourceToDestinationBook(entity);
				BeanUtils.copyProperties(entity, product);
				productList.add(product);
			} else {
				Product product = new Apparal();
//				BeanUtils.copyProperties(entity, product);
//				copySpecialValueApparal(entity, (Apparal) product);
				product = mapper.sourceToDestinationApparal(entity);
				BeanUtils.copyProperties(entity, product);
				productList.add(product);
			}
		}); 
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
				product = mapper.sourceToDestinationBook(entity);
				BeanUtils.copyProperties(entity, product);
				productList.add(product);
			} else {
				Product product = new Apparal();
//				BeanUtils.copyProperties(entity, product);
//				copySpecialValueApparal(entity, (Apparal) product);
				product = mapper.sourceToDestinationApparal(entity);
				BeanUtils.copyProperties(entity, product);
				productList.add(product);
			}
		}); 
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
				product = mapper.sourceToDestinationBook(entity);
				BeanUtils.copyProperties(entity, product);
				productList.add(product);
			} else {
				Product product = new Apparal();
//				BeanUtils.copyProperties(entity, product);
//				copySpecialValueApparal(entity, (Apparal) product);
				product = mapper.sourceToDestinationApparal(entity);
				BeanUtils.copyProperties(entity, product);
				productList.add(product);
			}
		});
		return productList;
	}
	
	private void copySpecialValueBook(ProductEntity productEntity, Book product) {
		BeanUtils.copyProperties(productEntity, product);
	}
	
	private void copySpecialValueApparal(ProductEntity productEntity, Apparal product) {
		BeanUtils.copyProperties(productEntity, product);
	}

 
}
