package com.at;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.at.library.dto.BookDTO;

public class LibraryApplicationTests {

	@Test
	public void contextLoads() {
		/* Se coloca la url con la que vamos a llamar a REST*/
		final String url = "http://localhost:8080/book";
		
		/* TEST:Obtener todos los libros
		 * url:/book
		 * Obtenemos un listado de libros
		 * */
		final BookDTO book = new RestTemplate().getForObject(url, BookDTO.class);
		/* TEST:Recuperar un libro
		 * url:/book/{id} 
		 * Obtenemos el libro que tiene como id la variable que hemos pasado
		 * */
		Integer id=1;
		final String url2 = "http://localhost:8080/book/"+id;
		final BookDTO book2 = new RestTemplate().getForObject(url2, BookDTO.class);
		/* TEST:Crear un libro
		 * url:/book/ 
		 * Devuelve el libro que hemos creado
		 * */
		BookDTO bookp = new BookDTO();
		bookp.setAuthor("Ramiro Morilla");
		bookp.setIsbn("23435455445");
		final BookDTO book3 = new RestTemplate().postForObject(url2, bookp ,BookDTO.class);
		System.out.println(book3);
		
		
	}

}
