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
import com.at.library.enums.StatusEnum;
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
	 * �Qu� necesitamos para alquilar un libro?
	 * 1� Employee
	 * 2� User
	 * 3� Book
	 * 4� InitDate
	 * 5� EndDate
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
		
		//Resumo en una l�nea guardo el objeto r creado y lo tranformo a RentDTO que es lo que devuelve la funci�n
		return transform(rentDao.save(r));
	}
	
	@Override
	public void delete(Integer idrent) {
		// TODO Auto-generated method stub
		//Comprobamos que no este alquilado y que no este activo
	//	final BookDTO b=bookService.findbyId(idrent);
		//NO esta alquilado y el estado del libro es active
		/*if((bookService.checkAvailability(idrent)== true && bookService.transform(b).getStatus()==StatusEnum.ACTIVE) ||
				//NO esta alquilado y el estado del libro es disable
		(bookService.checkAvailability(idrent)== true && bookService.transform(b).getStatus()==StatusEnum.DISABLE))
		{
			bookService.delete(b.getId());
		}*/
		
	
	}

	@Override
	public RentDTO transform(Rent rent) {
		return dozer.map(rent, RentDTO.class);
	}

	@Override
	public Rent transform(RentDTO rent) {
		return dozer.map(rent, Rent.class);
	}


	//Devoluci�n de un libro : Buscar el Libro, Comprobar que el Libro es del Usuario
	@Override
	public void returnBook(Integer idlibro) {
		// TODO Auto-generated method stub
		final RentDTO r = rentDao.findByUserAndBook(idlibro);
		
		final Rent rent= new Rent();
		final RentPK rentpk=new RentPK();
		final Employee e=employeeService.transform(employeeService.findbyId(r.getIdEmployee()));
		final User u=userService.transform(userService.findbyId(r.getIdUser()));
		
		rentpk.setBook(bookService.transform(bookService.findbyId(idlibro)));
		rentpk.setInitDate(r.getInitDate());
		rent.setRentpk(rentpk);
		rent.setEmployee(e);
		rent.setUser(u);
		rent.setEndDate(new Date());
		
		
		rentDao.save(rent);
		
	}
	
}
