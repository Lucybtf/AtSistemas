package com.at.library.service.rent;

import java.util.Date;
import java.util.List;

import com.at.library.dto.BookDTO;
import com.at.library.dto.HistoryRentedDTO;
import com.at.library.dto.RentDTO;
import com.at.library.dto.RentPostDTO;
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
	RentPostDTO create(RentDTO rentDto) throws UserNotFoundException, BookNotFoundException, EmployeeNotFoundException;

	/**
	 * Borrar un alquiler dado un id
	 * @return
	 */
	void delete(Integer idrentbook);

	
	Rent transform(RentDTO rent);

	RentDTO transform(Rent rent);

	/**
	 * Devolución de un Libro
	 * @param idlibro
	 */

	//void returnBook(Integer idlibro);

	/**
	 * Historial de Alquileres
	 * @param size 
	 * @param page 
	 * @return Devuelve los alquileres 
	 */
	//List<RentDTO> rentBookHistory(Integer idbook);

	//List<RentDTO> findAll();

	//List<HistoryRentedDTO> listRentsDTOs(Iterable<Rent> findAll);

	List<HistoryRentedDTO> RentsHistory(Integer page, Integer size);

}
