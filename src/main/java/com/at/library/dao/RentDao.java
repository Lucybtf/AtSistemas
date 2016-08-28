package com.at.library.dao;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.at.library.dto.RentDTO;
import com.at.library.model.Rent;

public interface RentDao extends CrudRepository<Rent, Integer>{

	@Query("select new com.at.library.dto.RentDTO(r.rentpk.book.id, r.user.id, r.employee.id) from Rent as r where  r.rentpk.book.id=?1")
	RentDTO findByUserAndBook(Integer idLibro);

}
