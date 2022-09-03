package com.mindtree.assignment.util;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mindtree.assignment.entity.ProductEntity;
import com.mindtree.assignment.model.Apparal;
import com.mindtree.assignment.model.Book;

@Mapper(componentModel = "spring")
public interface ProductDtoToEntityMapper {
	ProductDtoToEntityMapper INSTANCE = Mappers.getMapper(ProductDtoToEntityMapper.class);
	
	public abstract Apparal sourceToDestinationApparal(ProductEntity source);
	public abstract ProductEntity destinationToSourceApparal(Apparal destination);
	public abstract Book sourceToDestinationBook(ProductEntity source);
	public abstract ProductEntity destinationToSourceApparal(Book destination);
	public abstract List<Book> modelsToDtosBook(List<ProductEntity> commerces);
	public abstract List<Apparal> modelsToDtosApparal(List<ProductEntity> commerces);
}
