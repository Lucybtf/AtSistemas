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

	@Query("SELECT new com.at.library.dto.BookDTO(b.id, b.isbn, b.title, b.author) from Book as b where b.id in (select r.rentpk.book.id from Rent as r where r.endDate is null)")
	List<BookDTO> findunAvailable();
}
