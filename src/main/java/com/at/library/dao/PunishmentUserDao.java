package com.at.library.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.at.library.model.Book;
import com.at.library.model.PunishmentUser;
import com.at.library.model.User;

public interface PunishmentUserDao extends CrudRepository<PunishmentUser, Integer>{

	@Query("select pu from PunishmentUser as pu where (pu.user=?1 and pu.startDate=?2) and (pu.endDate=?3 and pu.punishmentdays=?4)")
	PunishmentUser findOne(User user, Date startDate, Date endDate, Integer punishmentdays);

	@Query("select pu from PunishmentUser as pu where pu.user.id=?1")
	PunishmentUser findUser(Integer iduser);

}
