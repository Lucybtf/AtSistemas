package com.at.library.service.book;

import java.text.ParseException;
import java.util.List;

import org.json.JSONException;

import com.at.library.dto.BookDTO;
import com.at.library.dto.HistoryRentedDTO;
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
	 * @return Devuelve el BookDTO cuya id es igual a la que se la ha pasado como parámetro
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

	//BookDTO findByTitle(String title) throws BookNotFoundException;

	//BookDTO findByIsbn(String isbn) throws BookNotFoundException;
	
	//BookDTO findByAuthor(String author) throws BookNotFoundException;

	//List<BookDTO> findBooksAvailable();
	/**
	 * Busqueda en Google
	 * @param title
	 * @param page
	 * @param size
	 * @return Devuelve el listado de libros que buscamos por title y les añade los parámetros description, image, publishedDate de la api de Google
	 * @throws JSONException
	 * @throws ParseException
	 */

	List<BookDTO> findInGoogle(String title, Integer page, Integer size) throws JSONException, ParseException;
	
	/**
	 * Busqueda por titulo y por isbn
	 * @param page
	 * @param size
	 * @param title
	 * @param isbn
	 * @return Devuelve los libros por titulo y por ibsn
	 * @throws BookNotFoundException
	 * @throws JSONException
	 * @throws ParseException
	 */

	List<BookDTO> findByTitleAndIsbn(Integer page, Integer size,String title, String isbn) throws BookNotFoundException, JSONException, ParseException;

	/**
	 * Actualiza los Book a una lista de BookDTOs
	 * @param findAll
	 * @return Devuelve una lista de BookDTOs
	 */
	
	List<BookDTO> listBookDTOs(Iterable<Book> findAll);
	
	/**
	 * Historial de Alquileres de un Libro
	 * @param id
	 * @return Devuelve el listado de Rents de 1 Book
	 */

	List<HistoryRentedDTO> HistoryRentedBook(Integer id);
	

}
