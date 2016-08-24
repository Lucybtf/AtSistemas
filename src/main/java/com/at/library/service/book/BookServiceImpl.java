package com.at.library.service.book;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.BookDao;
import com.at.library.dto.BookDTO;
import com.at.library.model.Book;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public List<BookDTO> findAll() {
		final Iterable<Book> findAll = bookDao.findAll();
		final Iterator<Book> iterator = findAll.iterator();
		final List<BookDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Book b = iterator.next();
			final BookDTO bDTO = transform(b);
			res.add(bDTO);
		}
		return res;
	}

	@Override
	public BookDTO transform(Book book) {
		return dozer.map(book, BookDTO.class);
	}

	@Override
	public Book transform(BookDTO book) {
		return dozer.map(book, Book.class);
	}

	@Override
	public BookDTO create(BookDTO bookDTO){
		final Book book=transform(bookDTO);
		return transform(bookDao.save(book));
	}

	@Override
	public void delete(Integer id){
	//	final BookDTO book= findbyId(id);
		bookDao.delete(id);
	}

	@Override
	public void update(BookDTO book){
		final BookDTO b=book;
		bookDao.save(transform(b));
	}

	@Override
	public BookDTO findbyId(Integer id){
		final Book b=bookDao.findOne(id);
		return transform(b);
		
	}
}
