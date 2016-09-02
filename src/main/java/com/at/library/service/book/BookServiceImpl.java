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
	public BookDTO create(BookDTO bookDTO) throws BookNotFoundException {
		final Book book=transform(bookDTO);
		//book.setStatus(StatusEnum.ACTIVE); AQUI LLAMAMOS A ACTIVAR LIBRO
		
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
	//	log.debug(String.format("Disponibilidad de libro(ACTIVE/DISABLE): %s", bookDao.findOne(id)));
		log.debug(String.format("Disponibilidad para alquilar y que este activo: %s", bookDao.checkAvailability(id)));
		//return  ((bookDao.findOne(id).getStatus() == StatusEnum.ACTIVE) && (bookDao.checkAvailability(id)==id))?"RENTED":"OK";
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
	
	
	

/*	@Override
	public BookDTO findByTitle(String title) throws BookNotFoundException {
		// TODO Auto-generated method stub
		final List<Book> book = bookDao.findByTitle(title);
		log.debug(String.format("LIBRO", transform(book)));
		if(book == null) throw new BookNotFoundException();
		return transform(bookDao.findByTitle(title));
	}*/

	/*@Override
	public BookDTO findByIsbn(String isbn) throws BookNotFoundException {
		// TODO Auto-generated method stub
		final Book book = bookDao.findByIsbn(isbn);
		if(book == null) throw new BookNotFoundException();
		return transform(bookDao.findByIsbn(isbn));
	}*/

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
	public List<BookDTO> findInGoogle(String title) throws JSONException, ParseException  {
		// TODO Auto-generated method stub
		log.debug(String.format("Buscamos en la BD cuantos libros tengo"));
		List<Book> booksfinded=bookDao.findByTitle(title);
		final List<BookDTO> listDTOs = new ArrayList<>(); //LISTA CON LAS SOLUCIONES
		
		//Si existe en la BD, ha encontrado algun resultado en la lista de libros
		if(booksfinded.size()>0){
			log.debug(String.format("LIbros encontrados: %s", booksfinded));
			RestTemplate restemplate = new RestTemplate();
			log.debug("Hacemos la peticion");
			String url="https://www.googleapis.com/books/v1/volumes?q="+ title + "&maxResults="+ booksfinded.size();
			log.debug(String.format("URL: %s",url));
			ResponseEntity<String> res= restemplate.getForEntity(url, String.class);

			//log.debug(String.format("Devolvemos el objeto: %s",res));
			
			JSONObject json= new JSONObject(res.getBody().toString());
			log.debug(String.format("Devolvemos el objeto: %s",json.getJSONArray("items")));
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
				//log.debug(String.format("JSON:", json_content.toString()));
				log.debug(String.format("Devolvemos fecha de publicación: %s", json_content.get("publishedDate")));
				log.debug(String.format("Devolvemos descripcion: %s", json_content.get("description")));
				log.debug(String.format("Devolvemos imagen: %s", json_content.getJSONObject("imageLinks").get("thumbnail") ));
				DateTimeFormatter df =DateTimeFormat.forPattern("yyyy-MM-dd"); 
				long millis = df.parseMillis(json_content.get("publishedDate").toString()); 
				DateTime dt = new DateTime(millis);
				
				resbook.setYear(dt.getYear());
				resbook.setDescription(json_content.get("description").toString());
				resbook.setImage(json_content.getJSONObject("imageLinks").get("thumbnail").toString());
				log.debug(String.format("Devolvemos el primer Libro: %s", resbook));
				listDTOs.add(resbook);
				i++;
				 
			}
		}
		return listDTOs;
	}

	@Override
	public List<BookDTO> listBookDTOs(Iterable<Book> findAll){
		
		final Iterator<Book> iterator = findAll.iterator();
		final List<BookDTO> ressult = new ArrayList<>();
		while (iterator.hasNext()) {
			final Book b = iterator.next();
			final BookDTO bDTO = transform(b);
			bDTO.setStatus(checkAvailability(bDTO.getId()));
			ressult.add(bDTO);
		}
		return ressult;
	}
	
	
	
	@Override
	public List<BookDTO> findByTitleAndIsbn(String title, String isbn) throws BookNotFoundException, JSONException, ParseException {
		// TODO Auto-generated method stub
		log.debug(String.format("HOLA"));
		//List<BookDTO> res = new ArrayList<>();
		
		//BUSQUEDA POR GOOGLE(FUNCIONA YA PERFECTAMENTE): res = findInGoogle(title);
		Iterable<Book> findAll;
		List<BookDTO> listend;
		if(title!=null && isbn!=null){
			findAll = bookDao.findByTitleAndIsbn(title, isbn);
			listend = listBookDTOs(findAll);
			return listend;
		}
		if(title!=null && isbn== null){
			log.debug(String.format("TITULO NADA MAS"));
			listend =findInGoogle(title); //FUNCIONA PERO VER PORQUE LECHES NO ME VA EL FindTitle normal
			return listend;
		}
		if(title==null && isbn!=null){
			log.debug(String.format("ISBN NADA MAS"));
			findAll = bookDao.findByIsbn(isbn);
			listend = listBookDTOs(findAll);
			//log.debug(String.format("ISBN: %s", findAll));
			return listend;
		}
		findAll = bookDao.findAll();
		listend = listBookDTOs(findAll);
		return listend;
		//BUSQUEDA POR DOS PARAMETROS
		//final <Book> findAll = bookDao.findByTitleAndIsbn(title, isbn);
		/*log.debug(String.format("LIBRO VACIO:",bookDao.findByTitle(title)));
		final Iterable<Book> findAll = bookDao.findByTitleAndIsbn(title, isbn);
		final Iterator<Book> iterator = findAll.iterator();
		while (iterator.hasNext()) {
			final Book b = iterator.next();
			log.debug(String.format("LIBRO:", b.toString()));
		}*/
		//final Iterator<Book> iterator = findAll.iterator();
		
		/*final Iterable<Book> findAll = bookDao.findByTitleAndIsbn(title, isbn);
		
		final List<BookDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Book b = iterator.next();
			log.debug(String.format("BookDTO"));
			
			if(title != null)
			{
				log.debug(String.format("BookDTO",findInGoogle(title) ));
				final List<BookDTO> bDTO=findInGoogle(title);
				//bDTO.setStatus(checkAvailability(bDTO.getId()));
				//res.add(bDTO);
			}
			else  
			{
				final BookDTO bDTO = transform(b);
				bDTO.setStatus(checkAvailability(bDTO.getId()));
				res.add(bDTO);
			}
			
			
		}*/
		
	//	return res;
		//log.debug(String.format("Resultado de Buscar por Titulo: %s"));
		//if(title != null)
			//log.debug(String.format("Resultado de Buscar por Titulo: %s", bookDao.findByTitle(title)));
		/*if(title == null && isbn == null)
			return new BookDTO();
		if(title != null && isbn == null){
			log.debug(String.format("Resultado de Buscar por Titulo: %s", findByTitle(title)));
			book = findByTitle(title);
			return book;
		}
		if(title == null && isbn != null){
			book = findByIsbn(isbn);
		}*/
		//book=findByTitleAndIsbn(title,isbn); BUscar
		//return books;
		//return null;
	}

	
}
