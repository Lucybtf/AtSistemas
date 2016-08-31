package com.at.library.dao;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.dto.RentDTO;
import com.at.library.model.Rent;

@Repository
public interface RentDao extends CrudRepository<Rent, Integer>{

	//@Query("select new com.at.library.dto.RentDTO(r.rentpk.book.id, r.user.id, r.employee.id, r.rentpk.initDate) from Rent as r where  (r.rentpk.book.id=?1 and r.endDate is null)")
	//RentDTO findByUserAndBook(Integer idLibro);
	
	//Devuelve el "unico" Libro alquilado correspondiente a un id
	@Query("select new com.at.library.dto.RentDTO(r.rentpk.book.id, r.user.id, r.employee.id, r.rentpk.initDate) from Rent as r where  r.rentpk.book.id=?1")
	List<RentDTO> findByBook(Integer idbook);

}
