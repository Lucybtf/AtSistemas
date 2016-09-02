package com.at.library.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.BookDTO;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.model.Book;
import com.at.library.service.book.BookService;

@RestController
@RequestMapping(value = "/book")
public class BookController {
	
	@Autowired
	private BookService bookservice;
	
	private static final Logger log = LoggerFactory.getLogger(BookController.class);

	@RequestMapping( method = { RequestMethod.GET })
	public List<BookDTO> getAll() {
		log.debug("Buscando todos los libros del sistema");
		return bookservice.findAll();
	}
	
	//Crear
	@RequestMapping( method = { RequestMethod.POST })
	public BookDTO create(@RequestBody BookDTO book) throws BookNotFoundException {
		log.debug(String.format("Vamos a crear el libro siguiente", book));
		return bookservice.create(book) ;
	}
	
	//Recuperar
	@RequestMapping(value="/{id}" , method = {RequestMethod.GET})
	public BookDTO findOne(@PathVariable("id")Integer id) throws BookNotFoundException{
		log.debug(String.format("Buscando el libro con el id %s", id));
		return bookservice.findbyId(id);
	}
	
	//Modificar
	@RequestMapping(value="/{id}", method =  { RequestMethod.PUT})
	public void update( @PathVariable("id")Integer id, @RequestBody BookDTO book) throws BookNotFoundException{
		log.debug(String.format("Vamos a modificar el siguiente", book));
		bookservice.update(book);
	}
	
	//Borrar
	@RequestMapping(value="/{id}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable("id")Integer id) throws BookNotFoundException{
		log.debug(String.format("Vamos a borrar un libro dado un id %s", id));
		bookservice.delete(id);
	}
	
	@RequestMapping( method = { RequestMethod.GET}, params={"title", "isbn"})
	public BookDTO findByTitleAndIsbn(@RequestParam(value="title",required = false)String title, @RequestParam(value="isbn",required = false)String isbn) throws BookNotFoundException{
		log.debug(String.format("Devolver los libros con titulo: %s, %s", title, isbn));
		//log.debug(String.format("Devolver los libros con isbn: %s", isbn));
		return bookservice.findByTitleAndIsbn(title, isbn);
	}
	
/*	@RequestMapping( value="/findBooksAvailable", method = { RequestMethod.GET})
	public List<BookDTO> findBooksAvailable(){
		log.debug(String.format("Devolvemos todos los libros disponibles"));
		return bookservice.findBooksAvailable();
	}*/
	
	/*Obtener los campos de Google*/
/*	@RequestMapping(value="/findInGoogle", method = {RequestMethod.POST}, params={"title"})
	public BookDTO findInGoogle(@RequestParam(value="title")String title) throws JSONException, ParseException{
		log.debug(String.format("Devolvemos un libro dado el titulo: %s", title));
		return bookservice.findInGoogle(title);	
	}*/
	
	/*@RequestMapping(method = { RequestMethod.GET}, params={"author"})
	public BookDTO findByAuthor(@RequestParam(value="author")String author) throws BookNotFoundException{
		log.debug(String.format("Devolver los libros con author: %s", author));
		final Book book =bookservice.transform(bookservice.findByAuthor(author));
		if(book == null) throw new BookNotFoundException();
		return bookservice.findByAuthor(author);
	}*/
	
	
	/*	@RequestMapping( value="/active/{id}", method = { RequestMethod.PUT })
	public void activeBook(@PathVariable("id")Integer id){
		log.debug(String.format("Activar el libro dado la siguiente id: %s", id));
		bookservice.activeBook(id);
	}
	
	@RequestMapping( value="/disable/{id}", method = { RequestMethod.PUT })
	public void disableBook(@PathVariable("id")Integer id){
		log.debug(String.format("Desactivar el libro dado la siguiente id: %s", id));
		bookservice.disableBook(id);
	}*/
	
	/** No me queda claro como poner las url, en el caso de disponibilidad entiendo que solo se usa en el servicio
	 * pero en el caso de las busquedas ¿como estaría correcta la url?
	 */
	/*@RequestMapping( value="/availability/{id}", method = { RequestMethod.GET})
	public boolean checkAvailability(@PathVariable("id")Integer id){
		log.debug(String.format("Comprobar la disponibilidad del Libro id: %s", id));
		return bookservice.checkAvailability(id);
	}*/
	
}
