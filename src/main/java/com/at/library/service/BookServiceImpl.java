package com.at.library.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.BookDao;
import com.at.library.dto.*;
import com.at.library.model.Book;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookDao bookDao;
	
	private DozerBeanMapper dozer= new DozerBeanMapper();
	
	public List<BookDTO> getAll() {
		final Iterable<Book> findAll=bookDao.findAll();
		final Iterator<Book> iterator=findAll.iterator();
		final List<BookDTO> res = new ArrayList<>();
		while (iterator.hasNext()){
			final Book b= iterator.next();
			final BookDTO bDTO=dozer.map(b, BookDTO.class)
			res.add(bDTO);
		}
		/*final BookDTO l1 = new BookDTO("111aaa", "El serñor de los anillos", "El tokien");
		final BookDTO l2 = new BookDTO("111bbb", "La comunidad del anillo", "Tolkien");
		return Arrays.asList(l1, l2);*/
	}
}
