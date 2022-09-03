package com.mindtree.assignment.util;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mindtree.assignment.entity.ProductEntity;
import com.mindtree.assignment.model.Apparal;
import com.mindtree.assignment.model.Book;

@Mapper
public interface ProductDtoToEntityMapper {
	ProductDtoToEntityMapper INSTANCE = Mappers.getMapper(ProductDtoToEntityMapper.class);
	
	Apparal sourceToDestinationApparal(ProductEntity source);
	ProductEntity destinationToSourceApparal(Apparal destination);
	Book sourceToDestinationBook(ProductEntity source);
	ProductEntity destinationToSourceApparal(Book destination);
	List<Book> modelsToDtosBook(List<ProductEntity> commerces);
	List<Apparal> modelsToDtosApparal(List<ProductEntity> commerces);
}
