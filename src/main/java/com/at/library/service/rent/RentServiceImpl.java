package com.at.library.service.rent;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.controller.RentController;
import com.at.library.dao.BookDao;
import com.at.library.dao.RentDao;
import com.at.library.dto.BookDTO;
import com.at.library.dto.EmployeeDTO;
import com.at.library.dto.RentDTO;
import com.at.library.dto.UserDTO;
import com.at.library.model.Book;
import com.at.library.model.Employee;
import com.at.library.model.Rent;
import com.at.library.model.RentPK;
import com.at.library.model.User;
import com.at.library.service.book.BookService;
import com.at.library.service.employee.EmployeeService;
import com.at.library.service.user.UserService;

@Service
public class RentServiceImpl implements RentService {
	
	private static final Logger log = LoggerFactory.getLogger(RentServiceImpl.class);
	//Necesito guardar en el modelo de la Base de datos el Rent que creo
	@Autowired
	private RentDao rentDao;
	
	/**
	 * Para crear una instancia Rent necesito: 
	 * - Employee
	 * - User
	 * - Book
	 * Utilizo los servicios ya que de estas intancias no necesito guardar nada en el modelo, ya se 
	 * hace en cada uno de los servicios
	 */
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookService;
	
	@Autowired
	private DozerBeanMapper dozer;

	/**
	 * ¿Qué necesitamos para alquilar un libro?
	 * 1º Employee
	 * 2º User
	 * 3º Book
	 * 4º InitDate
	 * 5º EndDate
	 * */
	@Override
	public RentDTO create(RentDTO rentDto){
		//Obtenemos los DTOs de Employee, UserDTO y BookDTO
		//PENDIENTE: Comprobar que el Usuario exista, que el Empleado exista y que el BookExista y no este Alquilado
		final EmployeeDTO employee = employeeService.findbyId(rentDto.getIdEmployee());
		final UserDTO user = userService.findbyId(rentDto.getIdUser());
		final BookDTO book = bookService.findbyId(rentDto.getIdBook());
		
		//Creamos un objeto Rent
		Rent r = new Rent();
		r.setBook(bookService.transform(book)); //Le asigno el BookDTO tranformado a Book
		r.setEmployee(employeeService.transform(employee)); //Le asigno el EmployeeDTO transformado a Employee
		r.setUser(userService.transform(user)); // Le asigno el UserDTO transformado a User
		r.setInitDate(new Date()); //Cojo la Fecha del Servidor
		r.setEndDate(null); //Inicializo a Null porque no se ha devuelto
		
		
		//final Rent rentend=rentDao.save(r); 
		//Resumo en una línea guardo el objeto r creado y lo tranformo a RentDTO que es lo que devuelve la función
		return transform(rentDao.save(r));
	}
	
	@Override
	public void delete(Integer idrent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RentDTO transform(Rent rent) {
		return dozer.map(rent, RentDTO.class);
	}

	@Override
	public Rent transform(RentDTO rent) {
		return dozer.map(rent, Rent.class);
	}



	//@Override
//	public RentDTO findbyBookAndDate(BookDTO book, Date init) {
		// TODO Auto-generated method stub
	//	return transform(rentDao.findByBookAndDate(bookService.findbyId(book.getId()), init));
	//}

	
	//Devolución de un libro
//	@Override
/*	public void returnBook(Integer id) {
		// TODO Auto-generated method stub
		
	}*/

	
	
/*	@Override
	public void rentBook(Integer id) {
		// TODO Auto-generated method stub
		
	}*/

	


	
}
