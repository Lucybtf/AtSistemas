package com.at;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.at.library.dto.BookDTO;

public class LibraryApplicationTests {

	@Test
	public void contextLoads() {
		/* Se coloca la url con la que vamos a llamar a REST*/
		final String url = "";
		final BookDTO book = new RestTemplate().getForObject(url, BookDTO.class);
		//final BookDTO book = new RestTemplate().post /put/delete ...
	}

}
