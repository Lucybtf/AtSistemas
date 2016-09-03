package com.at.library.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.library.dto.BookDTO;
import com.at.library.dto.HistoryRentedDTO;
//import com.at.library.dto.BookDTO;
import com.at.library.model.Book;

@Repository
public interface BookDao extends CrudRepository<Book, Integer> {

	/** Busqueda de los libros:
	 *  - title
	 *  - isbn
	 *  - author
	 *  - disponibilidad
	 * */
	
	@Query("select b from Book as b where (b.title like %:title%) and b.status!= 'DISABLE'")
	List<Book> findByTitle(@Param("title")String title);
	//@Query("select b from Book as b where b.isbn like %:isbn% and b.status!= 'DISABLE")
	@Query("select b from Book as b where (b.isbn like %:isbn%) and b.status = 'ACTIVE'")
	List<Book> findByIsbn(@Param("isbn")String isbn);
	
	@Query("select b from Book as b where (b.isbn like %:isbn% OR :isbn is null) AND (b.title like %:title% OR :title is null)")
	//@Query("select b from Book as b where ((:isbn is NULL or b.title like %:title%) and (:title is NULL or b.isbn like %:isbn%))")
	List<Book> findByTitleAndIsbn(@Param("title")String title,@Param("isbn")String isbn);
	
	Book findByAuthor(String author);
	
	//@Query("select new com.at.library.model.Book(b.id, b.isbn, b.title, b.author) from Book as b where (b.id=?1 and b.status ='ACTIVE')")
	//Book findOne(Integer id);
	
	@Query("select b from Book as b  where (b.status = 'ACTIVE')")
	List<Book> findAll();
	
	@Query("select new com.at.library.dto.BookDTO(b.id, b.isbn, b.title, b.author) from Book as b where b.id in (select r.rentpk.book.id from Rent as r where r.endDate is not null)")
	List<BookDTO> findBooksAvailable();
	
	/**
	 * Devuelve el id de los libros que  están alquilados y no están activos en la base de datos
	 */
	//@Query(value = "SELECT r.pk.book.id FROM Rent AS r, Book AS b WHERE (r.endDate IS null AND r.pk.book.id = ?1) OR (b.id = ?1 AND b.status = 'DISABLE')")
	@Query("select r.rentpk.book.id from Rent as r, Book as b where (r.endDate is null and r.rentpk.book.id=?1) or (b.id=?1 and b.status = 'DISABLE')")
//	@Query("select r.rentpk.book.id from Rent as r, Book as b where (r.endDate is null and r.rentpk.book.id=?1)")
	Integer checkAvailability(Integer id);
	

	@Query("select new com.at.library.dto.HistoryRentedDTO(r.rentpk.initDate, r.endDate, r.rentpk.book.title, r.rentpk.book.id) from Rent as r, Book as b where (r.rentpk.book.id=?1) and (b.id=?1 and b.status = 'ACTIVE')")
	List<HistoryRentedDTO> HistoryRentedBook(Integer id);
}
