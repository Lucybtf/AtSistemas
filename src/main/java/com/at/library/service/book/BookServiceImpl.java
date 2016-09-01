package com.at.library.service.book;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.dozer.DozerBeanMapper;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.at.library.controller.BookController;
import com.at.library.dao.BookDao;
import com.at.library.dto.BookDTO;
import com.at.library.dto.RentMigrationDTO;
import com.at.library.enums.StatusBook;
import com.at.library.enums.StatusEnum;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.model.Book;
import com.at.library.model.User;


@Service
public class BookServiceImpl implements BookService {

	private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
	
	
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
			bDTO.setStatus(checkAvailability(bDTO.getId()));
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

	//Comprobar que el JSON esta bien
	@Override
	public BookDTO create(BookDTO bookDTO) {
		final Book book=transform(bookDTO);
		final BookDTO bookend=transform(bookDao.save(book));
		bookend.setStatus(checkAvailability(bookend.getId()));
		return bookend;
	}

	@Override
	public void delete(Integer id) throws BookNotFoundException{
		final Book book= transform(findbyId(id));
		if(book == null) throw new BookNotFoundException();
		bookDao.delete(id);
	}

	@Override
	public void update(BookDTO bookDTO) throws BookNotFoundException{
		final Book book=transform(bookDTO);
		if(book == null) throw new BookNotFoundException();
		bookDao.save(transform(bookDTO));
	}

	@Override
	public String checkAvailability(Integer id){
		return  ((bookDao.findOne(id).getStatus() == StatusEnum.ACTIVE) && (bookDao.checkAvailability(id)==id))?"RENTED":"OK";
	}
	
	@Override
	public BookDTO findbyId(Integer id) throws BookNotFoundException{
		final Book book=bookDao.findOne(id);
		if(book == null) throw new BookNotFoundException();
		final BookDTO bookend=transform(book);
		return bookend;
		
	}
	
	
	@Override
	public void activeBook(Integer id) throws BookNotFoundException {
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
	public void disableBook(Integer id) throws BookNotFoundException {
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
	public BookDTO findByTitle(String title) throws BookNotFoundException {
		// TODO Auto-generated method stub
		final Book book = bookDao.findByTitle(title);
		log.debug(String.format("LIBRO", transform(book)));
		if(book == null) throw new BookNotFoundException();
		return transform(bookDao.findByTitle(title));
	}

	@Override
	public BookDTO findByIsbn(String isbn) throws BookNotFoundException {
		// TODO Auto-generated method stub
		final Book book = bookDao.findByIsbn(isbn);
		if(book == null) throw new BookNotFoundException();
		return transform(bookDao.findByIsbn(isbn));
	}

	@Override
	public BookDTO findByAuthor(String author) throws BookNotFoundException {
		// TODO Auto-generated method stub
		final Book book = bookDao.findByAuthor(author);
		if(book == null) throw new BookNotFoundException();
		return transform(book);
	}

	@Override
	public List<BookDTO> findBooksAvailable() {
		// TODO Auto-generated method stub
		log.debug("Mostramos el listado de libros disponibles");
		log.debug(String.format("Libros: ", bookDao.findBooksAvailable()));
		return bookDao.findBooksAvailable();
	}

	@Override
	public BookDTO findInGoogle(String title) throws JSONException, ParseException  {
		// TODO Auto-generated method stub
		//log.debug(String.format("Buscamos el primer libro en el API de Google"));
		
		RestTemplate restemplate = new RestTemplate();
		//log.debug("Hacemos la peticion");
		String url="https://www.googleapis.com/books/v1/volumes?q="+ title + "&maxResults=1";
		ResponseEntity<String> res= restemplate.getForEntity(url, String.class);

		//log.debug(String.format("Devolvemos el objeto: %s",res));
		
		JSONObject json= new JSONObject(res.getBody().toString());
		BookDTO resbook = new BookDTO();
		
		//log.debug(String.format("Devolvemos el json: %s",json));
		//JSONArray jsonbook = ;
		JSONObject json_content= json.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo");

		//log.debug(String.format("Devolvemos el primer Libro: %s", json_content));
		//log.debug(String.format("Devolvemos fecha de publicación: %s", json_content.get("publishedDate")));
		//log.debug(String.format("Devolvemos descripcion: %s", json_content.get("description")));
		//log.debug(String.format("Devolvemos imagen: %s", json_content.getJSONObject("imageLinks").get("thumbnail") ));
		
		//log.debug(String.format("Buscamos nuestro libro en la BD con nombre: %s", transform(bookDao.findByTitle(title))));
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		resbook =transform(bookDao.findByTitle(title));
		//Date publishedDate = formatter.parse(json_content.get("publishedDate").toString());
		
		DateTimeFormatter df =DateTimeFormat.forPattern("yyyy-MM-dd"); 
		long millis = df.parseMillis(json_content.get("publishedDate").toString()); 
		DateTime dt = new DateTime(millis);
		
		resbook.setYear(dt.getYear());
		resbook.setDescription(json_content.get("description").toString());
		resbook.setImage(json_content.getJSONObject("imageLinks").get("thumbnail").toString());
	
		return resbook;
	}
}
