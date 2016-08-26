package com.at.library.service.book;

import com.at.library.dto.RentDTO;

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
	RentDTO checkout();

	

}
