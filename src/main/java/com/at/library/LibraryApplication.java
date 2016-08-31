package com.at.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class LibraryApplication {

//URL: a la que tenemos que consultar	https://www.googleapis.com/books/v1/volumes?q=coches
	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

}
