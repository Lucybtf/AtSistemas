package com.at.library.service.rent;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	//	final EmployeeDTO employee = employeeService.findbyId(rentDto.getIdEmployee());
	//	final UserDTO user = userService.findbyId(rentDto.getIdUser());
	//	final BookDTO book = bookService.findbyId(rentDto.getIdBook());
		final RentDTO rent = new RentDTO();
		Rent rent2;
		RentPK PK;
		
		//PK.setBook(rentDto.getIdBook());
		rent.setIdEmployee(rentDto.getIdEmployee());
		rent.setIdUser(rentDto.getIdUser());
		rent.setIdBook(rentDto.getIdBook());
		Rent rentfinal = transform(rent);
		//final Rent rent=transform(rentDto);
		return transform(rentDao.save(rentfinal));	
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

	
	//Devolución de un libro
//	@Override
/*	public void returnBook(Integer id) {
		// TODO Auto-generated method stub
		
	}*/

	
	
/*	@Override
	public void rentBook(Integer id) {
		// TODO Auto-generated method stub
		
	}*/

	@Override
	public RentDTO findbyId(Integer id) {
		// TODO Auto-generated method stub
		return transform(rentDao.findOne(id));
	}

	


}
