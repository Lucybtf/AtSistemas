package com.at.library.service.book;

import java.text.ParseException;
import java.util.List;

import org.json.JSONException;

import com.at.library.dto.BookDTO;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.model.Book;

public interface BookService {

	/**
	 * Realiza la busqueda de todos los libros existentes
	 * 
	 * @return listado de libros
	 */
	List<BookDTO> findAll();

	/**
	 * Transfrma un libro en un libroDTO
	 * 
	 * @param book
	 * @return
	 */
	BookDTO transform(Book book);

	/**
	 * Transforma un libroDTO en un libro
	 * 
	 * @param book
	 * @return
	 */
	Book transform(BookDTO book);

	/**
	 *  Crear un libroDTO 
	 *  @param book
	 *  @return Devuelve un BookDTO
	 * @throws UserNotFoundException 
	 * @throws BookNotFoundException 
	 * */
	
	BookDTO create(BookDTO book) throws BookNotFoundException;

	/**
	 * Borrar un libroDTO 
	 * @param id
	 * @throws BookNotFoundException 
	 * */
	void delete(Integer id) throws BookNotFoundException;

	/**
	 * Actualiza un libroDTO
	 * @param book 
	 * @throws BookNotFoundException 
	 * */
	void update(BookDTO book) throws BookNotFoundException;

	/**
	 * Encontrar un libro por Id
	 * @param id
	 * @return Devuelve el BookDTO cuya id es igual a la que se la ha pasado como par�metro
	 * @throws BookNotFoundException 
	 * */
	BookDTO findbyId(Integer id) throws BookNotFoundException;
	
	/**
	 * Activar un libro dado un Id
	 * @param id
	 * @throws BookNotFoundException 
	 * */
	void activeBook(Integer id) throws BookNotFoundException;
	
	/**
	 *  Desactivar un libro dado un Id
	 * @param id
	 * @throws BookNotFoundException 
	 * */
	void disableBook(Integer id) throws BookNotFoundException;

	/**
	 *  Comprobar la disponibilidad de un libro
	 *  @param id
	 *  @return Devuelve si esta disponible o no un libro
	 * */
	String checkAvailability(Integer id);

	/**
	 * Busqueda por titulo
	 * @param title
	 * @return Devuelve un libro
	 * @throws BookNotFoundException 
	 */
	//BookDTO findByTitle(String title) throws BookNotFoundException;

	/**
	 * Busqueda por Isbn
	 * @param isbn
	 * @return Devuelve un libro
	 * @throws BookNotFoundException 
	 */
	//BookDTO findByIsbn(String isbn) throws BookNotFoundException;
	
	/**
	 * Busqueda por autor
	 * @param author
	 * @return Devuelve un libro
	 * @throws BookNotFoundException 
	 */

	BookDTO findByAuthor(String author) throws BookNotFoundException;

	/**
	 * Busqueda de todos los libros disponibles
	 * @return
	 */
	List<BookDTO> findBooksAvailable();

	List<BookDTO> findInGoogle(String title) throws JSONException, ParseException;

	List<BookDTO> findByTitleAndIsbn(String title, String isbn) throws BookNotFoundException, JSONException, ParseException;

	List<BookDTO> listBookDTOs(Iterable<Book> findAll);
	

}
