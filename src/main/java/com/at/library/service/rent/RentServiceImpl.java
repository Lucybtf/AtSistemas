package com.at.library.service.rent;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.at.library.controller.RentController;
import com.at.library.dao.BookDao;
import com.at.library.dao.RentDao;
import com.at.library.dto.BookDTO;
import com.at.library.dto.EmployeeDTO;
import com.at.library.dto.HistoryRentedDTO;
import com.at.library.dto.RentDTO;
import com.at.library.dto.RentPostDTO;
import com.at.library.dto.UserDTO;
import com.at.library.enums.StatusBook;
import com.at.library.enums.StatusEnum;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.exceptions.RentNotFoundException;
import com.at.library.exceptions.UserNotFoundException;
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
	 * @throws UserNotFoundException 
	 * @throws BookNotFoundException 
	 * @throws EmployeeNotFoundException 
	 * */
	@Override
	public RentPostDTO create(RentDTO rentDto) throws UserNotFoundException, BookNotFoundException, EmployeeNotFoundException{
		//Obtenemos los DTOs de Employee, UserDTO y BookDTO
		//PENDIENTE: Comprobar que el Usuario exista, que el Empleado exista y que el BookExista y no este Alquilado
		final EmployeeDTO employee = employeeService.findbyId(rentDto.getIdEmployee());
		final UserDTO user = userService.findbyId(rentDto.getIdUser());
		final BookDTO book = bookService.findbyId(rentDto.getIdBook());
		
		log.debug(String.format("EMPLEADO: %s", employee.toString()));
		log.debug(String.format("USER: %s", user.toString()));
		log.debug(String.format("BOOK: %s", book.toString()));
		
		//Compruebo primero si el Libro está disponible
		if(book.getStatus()=="OK")
		{
			//Creamos un objeto Rent
			log.debug(String.format("El libro esta disponible para alquilar"));
			Rent r = new Rent();
			RentPK rpk= new RentPK();
			
			book.setStatus(null); //Vacio el status para establecer el status
			rpk.setBook(bookService.transform(book)); //Le asigno el BookDTO tranformado a Book
			rpk.setInitDate(new Date()); //Cojo la Fecha del Servidor
			r.setRentpk(rpk);
			r.setEmployee(employeeService.transform(employee)); //Le asigno el EmployeeDTO transformado a Employee
			r.setUser(userService.transform(user)); // Le asigno el UserDTO transformado a User
			r.setEndDate(null); //Inicializo a Null porque no se ha devuelto
			
			//Guardo el objeto en BD
			rentDao.save(r);
		
			return new RentPostDTO(bookService.transform(book).getId(),userService.transform(user).getId());
		}
		else{
			log.debug(String.format("El libro NO esta disponible para alquilar")); //¿Tendría que lanzar una excepcion?
			return new RentPostDTO();
		}
		
	}
	
	//Devolución de un libro : Buscar el Libro, colocar la fecha 
	@Override
	public void delete(Integer idrentbook) throws RentNotFoundException {
		// TODO Auto-generated method stub
		final Rent rentend=rentDao.findByBook(idrentbook);
		if(rentend == null) throw new RentNotFoundException();
		rentend.setEndDate(new Date());
		//MIrar si el usuario se ha pasado del numero de días de alquiler y castigarlo en ese caso CRON
		rentDao.save(rentend);
		
	}

	@Override
	public List<HistoryRentedDTO> RentsHistory(Integer page, Integer size) {
		// TODO Auto-generated method stub
		if(page!=null && size!=null)
			return rentDao.RentsHistory(new PageRequest(page-1,size)).getContent();
		else
			return rentDao.RentsHistory(new PageRequest(0,100)).getContent();
	}
	
	@Override
	public RentDTO transform(Rent rent) {
		return dozer.map(rent, RentDTO.class);
	}

	@Override
	public Rent transform(RentDTO rent) {
		return dozer.map(rent, Rent.class);
	}

	public Integer diferenceBetweenDays(Date day1, Date day2){
		
		long days2=(day1.getTime()-day2.getTime())/(24 * 60 * 60 * 1000);
		return (int)days2;
	}
	


	/*@Override
	public List<RentDTO> rentBookHistory(Integer idbook) {
		// TODO Auto-generated method stub
		return rentDao.findByBook(idbook);
	}*/
	
}
