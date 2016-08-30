package com.at.library.service.rent;

import java.util.Date;
import java.util.List;

import com.at.library.dto.BookDTO;
import com.at.library.dto.RentDTO;
import com.at.library.model.Book;
import com.at.library.model.Rent;

public interface RentService {

	/**
	 * Crea un alquiler
	 * @return Devuelve un RentDTO
	 */
	RentDTO create(RentDTO rentDto);

	/**
	 * Borrar un alquiler dado un id
	 * @return Devuelve un RentDTO
	 */
	void delete(Integer idrent);

	/**
	 * Devuelve un alquiler: Coloca la fecha de devolución y el libro vuelve a estar disponible
	 * @return Devuelve un RentDTO
	 */
	//void returnBook(Integer id);


	
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
