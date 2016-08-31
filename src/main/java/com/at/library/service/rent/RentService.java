package com.at.library.service.rent;

import java.util.Date;
import java.util.List;

import com.at.library.dto.BookDTO;
import com.at.library.dto.RentDTO;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.model.Book;
import com.at.library.model.Rent;

public interface RentService {

	/**
	 * Crea un alquiler
	 * @return Devuelve un RentDTO
	 * @throws UserNotFoundException 
	 * @throws BookNotFoundException 
	 * @throws EmployeeNotFoundException 
	 */
	RentDTO create(RentDTO rentDto) throws UserNotFoundException, BookNotFoundException, EmployeeNotFoundException;

	/**
	 * Borrar un alquiler dado un id
	 * @return Devuelve un RentDTO
	 */
	void delete(Integer idrent);

	
	Rent transform(RentDTO rent);

	RentDTO transform(Rent rent);

	/**
	 * Devolución de un Libro
	 * @param idlibro
	 */

	void returnBook(Integer idlibro);

	/**
	 * Historial de Alquileres
	 * @param idbook
	 * @return Devuelve los alquileres de un libro
	 */
	List<RentDTO> rentBookHistory(Integer idbook);
	
}
