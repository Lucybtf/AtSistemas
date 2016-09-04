package com.at.library.service.rent;

import java.util.Date;
import java.util.List;

import com.at.library.dto.BookDTO;
import com.at.library.dto.HistoryRentedDTO;
import com.at.library.dto.RentDTO;
import com.at.library.dto.RentPostDTO;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.exceptions.RentNotFoundException;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.model.Book;
import com.at.library.model.Rent;

public interface RentService {

	/**
	 * Crea un alquiler
	 * @return Devuelve un RentPostDTO
	 * @throws UserNotFoundException 
	 * @throws BookNotFoundException 
	 * @throws EmployeeNotFoundException 
	 */
	RentPostDTO create(RentDTO rentDto) throws UserNotFoundException, BookNotFoundException, EmployeeNotFoundException;

	/**
	 * Borrar un alquiler dado un id
	 * @param id
	 * @throws RentNotFoundException 
	 */
	void delete(Integer idrentbook) throws RentNotFoundException;

	/**
	 * Transforma de RentDTO a Rent
	 * @param rent
	 * @return Rent
	 */
	Rent transform(RentDTO rent);

	/**
	 * Transforma de Rent a RentDTO
	 * @param rent
	 * @return RentDTO
	 */
	RentDTO transform(Rent rent);

	/**
	 * Historial de Alquileres
	 * @param size 
	 * @param page 
	 * @return Devuelve los alquileres 
	 */
	List<HistoryRentedDTO> RentsHistory(Integer page, Integer size);
	

}
