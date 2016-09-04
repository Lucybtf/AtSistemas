package com.at.library.service.punishmentuser;

import com.at.library.model.PunishmentUser;

public interface PunishmentUserService {
	
	/**
	 * Creamos un Castigo de Usuario a partir de PunishmentUser
	 * @param p
	 */
	void create(PunishmentUser p);
	
	/**
	 * Buscamos un PunishmentUser por el id de User
	 * @param iduser
	 * @return Devuelve un PunishmentUser
	 */
	PunishmentUser findOne(Integer iduser);

}
