package com.at.library.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.BookDTO;

@RestController
public class BookController {

	@RequestMapping(value = "/book", method = { RequestMethod.GET })
	public List<BookDTO> getAll() {
		final BookDTO l1 = new BookDTO("111aaa", "El serñor de los anillos", "El tokien");
		final BookDTO l2 = new BookDTO("111bbb", "La comunidad del anillo", "Tolkien");
		return Arrays.asList(l1, l2);
	}

}
