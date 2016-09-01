package com.at.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.dto.BookDTO;
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
	Book findByTitle(String title);
	Book findByIsbn(String isbn);
	Book findByAuthor(String author);
	
	@Query("select new com.at.library.model.Book(b.id, b.isbn, b.title, b.author) from Book as b")
	List<Book> findAll();
	
	@Query("select new com.at.library.dto.BookDTO(b.id, b.isbn, b.title, b.author) from Book as b where b.id in (select r.rentpk.book.id from Rent as r where r.endDate is not null)")
	List<BookDTO> findBooksAvailable();
	
	//@Query(value = "SELECT r.pk.book.id FROM Rent AS r, Book AS b WHERE (r.endDate IS null AND r.pk.book.id = ?1) OR (b.id = ?1 AND b.status = 'DISABLE')")
	@Query("select r.rentpk.book.id from Rent as r where (r.endDate is null and r.rentpk.book.id=?1)")
	Integer checkAvailability(Integer id);
}
