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
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.at.library.controller.BookController;
import com.at.library.dao.BookDao;
import com.at.library.dto.BookDTO;
import com.at.library.dto.HistoryRentedDTO;
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
	public BookDTO create(BookDTO bookDTO) throws BookNotFoundException {
		final Book book=transform(bookDTO);
		final BookDTO bookend=transform(bookDao.save(book));
		activeBook(bookend.getId());
		bookend.setStatus(checkAvailability(bookend.getId()));
		return bookend;
	}

	@Override
	public void delete(Integer id) throws BookNotFoundException{
		final Book book= bookDao.findOne(id);
		if(book == null) throw new BookNotFoundException();
		disableBook(book.getId());
		//bookDao.delete(id);
	}

	@Override
	public void update(BookDTO bookDTO) throws BookNotFoundException{

		final Book book = bookDao.findOne(bookDTO.getId()); //BUsco que el Book exista
		if(book == null) throw new BookNotFoundException();
		if (bookDTO.getAuthor() != null) book.setAuthor(bookDTO.getAuthor());//Si el book que me pasan tiene campos distintos, asignamelo 
		if (bookDTO.getIsbn() != null) book.setIsbn(bookDTO.getIsbn());
		if(bookDTO.getTitle()!= null) book.setTitle(bookDTO.getTitle());
		bookDao.save(book);
	}

	@Override
	public String checkAvailability(Integer id){
		log.debug(String.format("Disponibilidad para alquilar y que este activo: %s", bookDao.checkAvailability(id)));
		return (bookDao.checkAvailability(id)==id)?"RENTED":"OK";
	}
	
	@Override
	public BookDTO findbyId(Integer id) throws BookNotFoundException{
		final Book book=bookDao.findOne(id);
		if(book == null) throw new BookNotFoundException();
		final BookDTO bookend=transform(book);
		bookend.setStatus(checkAvailability(bookend.getId()));
		return bookend;
		
	}
	
	
	@Override
	public void activeBook(Integer id) throws BookNotFoundException {
		// TODO Auto-generated method stub
		final Book b = bookDao.findOne(id);
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
		final Book b = bookDao.findOne(id);
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
	public List<BookDTO> findInGoogle(String title, Integer page, Integer size) throws JSONException, ParseException  {
		// TODO Auto-generated method stub
	
		List<Book> booksfinded=bookDao.findByTitle(title, new PageRequest(page-1,size));
		final List<BookDTO> listDTOs = new ArrayList<>(); //LISTA CON LAS SOLUCIONES
		
		//Si existe en la BD, ha encontrado algun resultado en la lista de libros
		if(booksfinded.size()>0){
			
			RestTemplate restemplate = new RestTemplate();
			String url="https://www.googleapis.com/books/v1/volumes?q="+ title + "&maxResults="+ booksfinded.size();
			log.debug(String.format("URL: %s",url));
			ResponseEntity<String> res= restemplate.getForEntity(url, String.class);

			//log.debug(String.format("Devolvemos el objeto: %s",res));
			
			JSONObject json= new JSONObject(res.getBody().toString());
			//log.debug(String.format("Devolvemos el objeto: %s",json.getJSONArray("items")));
			Iterator<Book> it = booksfinded.iterator();
			int i=0;
			while (it.hasNext() && i<booksfinded.size()) {
				 
				Book bookit=it.next();
				BookDTO resbook = new BookDTO();
				
				resbook.setId(bookit.getId());
				resbook.setTitle(bookit.getTitle());
				resbook.setAuthor(bookit.getAuthor());
				resbook.setIsbn(bookit.getIsbn());
				resbook.setStatus(checkAvailability(bookit.getId()));
				JSONObject json_content= json.getJSONArray("items").getJSONObject(i).getJSONObject("volumeInfo");
		
				if(json_content.has("publishedDate")){
					if (json_content.get("publishedDate").toString().matches("\\d{4}-\\d{2}-\\d{2}")) {
						DateTimeFormatter df =DateTimeFormat.forPattern("yyyy-MM-dd"); 
						long millis = df.parseMillis(json_content.get("publishedDate").toString()); 
						DateTime dt = new DateTime(millis);
						resbook.setYear(dt.getYear());
					}
					if (json_content.get("publishedDate").toString().matches("\\d{4}-\\d{2}")) {
						DateTimeFormatter df =DateTimeFormat.forPattern("yyyy-MM"); 
						long millis = df.parseMillis(json_content.get("publishedDate").toString()); 
						DateTime dt = new DateTime(millis);
						resbook.setYear(dt.getYear());
					}
					if (json_content.get("publishedDate").toString().matches("\\d{4}")) {
						DateTimeFormatter df =DateTimeFormat.forPattern("yyyy"); 
						long millis = df.parseMillis(json_content.get("publishedDate").toString()); 
						DateTime dt = new DateTime(millis);
						resbook.setYear(dt.getYear());
					}
					
				}
				if(json_content.has("description")){
					resbook.setDescription(json_content.get("description").toString());
				}
				if(json_content.getJSONObject("imageLinks").has("thumbnail"))
					resbook.setImage(json_content.getJSONObject("imageLinks").get("thumbnail").toString());
				
				listDTOs.add(resbook);
				i++;
				 
			}
		}
		return listDTOs;
	}

	//Convertido una lista de Books en BookDTOS
	@Override
	public List<BookDTO> listBookDTOs(Iterable<Book> findAll){
		
		final Iterator<Book> iterator = findAll.iterator();
		final List<BookDTO> result = new ArrayList<>();
		while (iterator.hasNext()) {
			final Book b = iterator.next();
			final BookDTO bDTO = transform(b);
			bDTO.setStatus(checkAvailability(bDTO.getId()));
			result.add(bDTO);
		}
		return result;
	}
	
	
	
	@Override
	public List<BookDTO> findByTitleAndIsbn(Integer page, Integer size,String title, String isbn) throws BookNotFoundException, JSONException, ParseException {
		// TODO Auto-generated method stub
		
		Iterable<Book> findAll;
		List<BookDTO> listend;
		Integer pageend = null;
		Integer sizeend = null;
		if(page==null && size==null){
			pageend=1;
			sizeend=100;
		}
		else{
			pageend=page;
			sizeend=size;
		}
		if(title!=null && isbn!=null){
			findAll = bookDao.findByTitleAndIsbn(title, isbn, new PageRequest(pageend-1,sizeend));
			listend = listBookDTOs(findAll);
			return listend;
		}
		if(title!=null && isbn== null){
			log.debug(String.format("TITULO NADA MAS"));
			listend =findInGoogle(title, pageend, sizeend);
			return listend;
		}
		if(title==null && isbn!=null){
			log.debug(String.format("ISBN NADA MAS"));
			findAll = bookDao.findByIsbn(isbn, new PageRequest(pageend-1,sizeend)); 
			listend = listBookDTOs(findAll);
				//log.debug(String.format("ISBN: %s", findAll));
			return listend;
		}
		findAll = bookDao.findAll(new PageRequest(pageend-1,sizeend)); 
		listend = listBookDTOs(findAll);
		return listend;	
	}
	

	@Override
	public List<HistoryRentedDTO> HistoryRentedBook(Integer id){
		List<HistoryRentedDTO> books= new ArrayList();
		books= bookDao.HistoryRentedBook(id);
		return books;
		
	}

	
}
