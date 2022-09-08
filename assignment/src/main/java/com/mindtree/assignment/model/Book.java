package com.mindtree.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Book extends Product{

	private String genre;

	private String author;

	private String publications;

	@Builder
	public Book(String genre, String author, String publications, int productid,		
			String prodName, float price) {
		super(productid, prodName, price, ProductEnum.BOOK);
		this.genre = genre;   
		this.author = author;
		this.publications = publications;
	}
}
