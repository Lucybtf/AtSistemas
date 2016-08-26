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
	 *  @return Devuelve un BookDTO
	 * */
	
	BookDTO create(BookDTO book);

	/**
	 * Borrar un libroDTO 
	 * 
	 * */
	void delete(Integer id);


	/**
	 * Actualiza un libroDTO 
	 * */
	void update(BookDTO book);

	/**
	 * Encontrar un libro por Id
	 * */
	BookDTO findbyId(Integer id);
	
	/**
	 * Activar un libro dado un Id
	 *
	 * */
	void activeBook(Integer id);
	
	/**
	 *  Desactivar un libro dado un Id
	 * */
	void disableBook(Integer id);

}
