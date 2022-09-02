package com.mindree.assignment.service.impl;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindree.assignment.entity.ProductEntity;
import com.mindree.assignment.model.Product;
import com.mindree.assignment.repository.ProductRepository;
import com.mindree.assignment.service.ProductService;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	@PostConstruct
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
	public ProductEntity findProductById(long id) {
		Optional<ProductEntity> product = repository.findById(id);
		if (product.isPresent()) {
			return repository.findById(id).get();
		} else {
			return product.orElse(null);
		}
	}

	@Override
	public List<ProductEntity> findProductByName(String productName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductEntity> findProductByType(String productType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductEntity> findAllProduct() {
		return repository.findAll();
	}


}
