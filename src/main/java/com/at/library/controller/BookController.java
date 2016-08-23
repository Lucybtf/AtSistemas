package com.at.library.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.BookDTO;
import com.at.library.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookservice;
	
	
/*	@RequestMapping(value = "/book", method = { RequestMethod.GET })
	public List<BookDTO> getAll() {
		final BookDTO l1 = new BookDTO("111aaa", "El serñor de los anillos", "El tokien");
		final BookDTO l2 = new BookDTO("111bbb", "La comunidad del anillo", "Tolkien");
		return Arrays.asList(l1, l2);
	}*/

}
