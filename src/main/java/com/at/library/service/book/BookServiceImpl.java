package com.at.library.service.book;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.controller.BookController;
import com.at.library.dao.BookDao;
import com.at.library.dto.BookDTO;
import com.at.library.enums.StatusEnum;
import com.at.library.model.Book;
import com.at.library.model.User;

@Service
public class BookServiceImpl implements BookService {

	private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
	//private static final org.apache.log4j.Logger logger = LogManager.getLogger("HelloWorld");
	
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
		//book.setStatus(StatusEnum.DISABLE);
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
	
	
	@Override
	public void activeBook(Integer id) {
		// TODO Auto-generated method stub
		final Book b = transform(findbyId(id));
		//Comprobar que un libro no este alquilado por un usuario (¿?)
		if(b.getStatus()== null){
			b.setStatus(StatusEnum.ACTIVE);
		}
		else{
			if(b.getStatus() == StatusEnum.DISABLE){
				b.setStatus(StatusEnum.ACTIVE);
			}	
			
		}
		bookDao.save(b);
	}

	@Override
	public void disableBook(Integer id) {
		// TODO Auto-generated method stub
		final Book b = transform(findbyId(id));
		//Comprobar que un libro no este alquilado por un usuario (¿?)
		if(b.getStatus()== null){
			b.setStatus(StatusEnum.DISABLE);
		}
		else{
			if(b.getStatus() == StatusEnum.ACTIVE){
				b.setStatus(StatusEnum.DISABLE);
			}	
			
		}
		bookDao.save(b);
	}
	
	
	@Override
	public boolean checkAvailability(Integer id){
		return  ((bookDao.findOne(id).getStatus() == StatusEnum.ACTIVE) && (bookDao.checkAvailability(id)==id))?false:true;
	}

	@Override
	public BookDTO findByTitle(String title) {
		// TODO Auto-generated method stub
		return transform(bookDao.findByTitle(title));
	}

	@Override
	public BookDTO findByIsbn(String isbn) {
		// TODO Auto-generated method stub
		return transform(bookDao.findByIsbn(isbn));
	}

	@Override
	public BookDTO findByAuthor(String author) {
		// TODO Auto-generated method stub
		return transform(bookDao.findByAuthor(author));
	}

	@Override
	public List<BookDTO> findBooksAvailable() {
		// TODO Auto-generated method stub
		log.debug("Mostramos el listado de libros disponibles");
		log.debug(String.format("Libros: ", bookDao.findBooksAvailable()));
		return bookDao.findBooksAvailable();
	}
}
