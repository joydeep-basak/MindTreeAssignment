package com.mindree.assignment.util;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mindree.assignment.entity.ProductEntity;
import com.mindree.assignment.model.Apparal;
import com.mindree.assignment.model.Book;

@Mapper(componentModel = "spring")
public interface ProductDtoToEntityMapper {
	ProductDtoToEntityMapper INSTANCE = Mappers.getMapper(ProductDtoToEntityMapper.class);
	
	Apparal sourceToDestinationApparal(ProductEntity source);
    ProductEntity destinationToSourceApparal(Apparal destination);
    Book sourceToDestinationBook(ProductEntity source);
    ProductEntity destinationToSourceApparal(Book destination);
    List<Book> modelsToDtosBook(List<ProductEntity> commerces);
    List<Apparal> modelsToDtosApparal(List<ProductEntity> commerces);
}
