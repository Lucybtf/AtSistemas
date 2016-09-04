package com.at.library.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.library.model.Book;
import com.at.library.model.RentPK;
import com.at.library.model.User;
import com.at.library.model.Rent;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
	
	/* Busquedas por dni y por nombre en el Usuario*/
	@Query("select u from User as u  where u.statususer = 'ACTIVE' and (u.dni like %:dni%)")
	List<User> findByDni(@Param("dni")String dni, Pageable pageable);
	@Query("select u from User as u  where u.statususer = 'ACTIVE' and (u.name like %:name%)")
	List<User> findByName(@Param("name")String name, Pageable pageable);

	@Query("select u from User as u  where (u.statususer = 'ACTIVE' and ((u.dni like %:dni% or u.dni is null) and (u.name like %:name% or u.name is null))) ")
	Page<User> findByDniAndName(@Param("dni")String dni, @Param("name")String name, Pageable pageable);
	
	@Query("select u from User as u  where u.statususer = 'ACTIVE')")
	Page<User> findAll(Pageable pageable);
	
	@Query("select r from Rent as r  where r.endDate is CURRENT_DATE")
	List<Rent> findReturnRents();
	
	@Query("select u.id from User as u  where u.statususer='PUNISHED'")
	List<Integer> findUsersPunished();
	
}
