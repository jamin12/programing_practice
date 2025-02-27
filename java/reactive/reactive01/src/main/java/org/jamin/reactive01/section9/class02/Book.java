package org.jamin.reactive01.section9.class02;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Book {
	private String isbn;
	private String bookName;
	private String author;
}