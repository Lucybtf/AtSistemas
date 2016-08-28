package com.at.library.service.book;

import java.util.List;

import com.at.library.dto.BookDTO;
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
	 * */
	
	BookDTO create(BookDTO book);

	/**
	 * Borrar un libroDTO 
	 * @param id
	 * */
	void delete(Integer id);

	/**
	 * Actualiza un libroDTO
	 * @param book 
	 * */
	void update(BookDTO book);

	/**
	 * Encontrar un libro por Id
	 * @param id
	 * @return Devuelve el BookDTO cuya id es igual a la que se la ha pasado como par�metro
	 * */
	BookDTO findbyId(Integer id);
	
	/**
	 * Activar un libro dado un Id
	 * @param id
	 * */
	void activeBook(Integer id);
	
	/**
	 *  Desactivar un libro dado un Id
	 * @param id
	 * */
	void disableBook(Integer id);

	/**
	 *  Comprobar la disponibilidad de un libro
	 *  @param id
	 *  @return Devuelve si esta disponible o no un libro
	 * */
	boolean checkAvailability(Integer id);
	

}
