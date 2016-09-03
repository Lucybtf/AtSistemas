package com.at.library.dao;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.dto.HistoryRentedDTO;
import com.at.library.dto.RentDTO;
import com.at.library.model.Book;
import com.at.library.model.Rent;

@Repository
public interface RentDao extends CrudRepository<Rent, Integer>{

	//@Query("select new com.at.library.dto.RentDTO(r.rentpk.book.id, r.user.id, r.employee.id, r.rentpk.initDate) from Rent as r where  (r.rentpk.book.id=?1 and r.endDate is null)")
	//RentDTO findByUserAndBook(Integer idLibro);
	//@Query("select r from Rent as r  where r.rentpk.book.status='ACTIVE'")
	//List<Rent> findAll();
	
	//Devuelve el "unico" Libro alquilado correspondiente a un id
	@Query("select r from Rent as r where  r.rentpk.book.id=?1 and r.endDate is null")
	Rent findByBook(Integer idbook);

	@Query("select new com.at.library.dto.HistoryRentedDTO(r.rentpk.initDate, r.endDate, r.rentpk.book.title, r.rentpk.book.id) from Rent as r where r.rentpk.book.status = 'ACTIVE'")
	Page<HistoryRentedDTO> RentsHistory(Pageable pageable);

}
