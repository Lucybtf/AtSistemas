package com.at;

import java.util.Date;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.at.library.dao.RentDao;
import com.at.library.dto.RentDTO;
import com.at.library.model.Rent;
import com.at.library.service.rent.RentService;
import com.at.library.service.rent.RentServiceImpl;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class RentTestService {

	private static final Date INIT = new Date();

	private Rent rent = new Rent();
	
	@InjectMocks
	private RentService rentService = new RentServiceImpl();
	
	@Mock
	private DozerBeanMapper dozer;
	
	@Mock 
	private RentDao rentDao;
	
	@Before
	public void init(){
		final RentDTO r =new RentDTO();
		r.setInitDate(INIT);
		Mockito.when(dozer.map(rent, RentDTO.class)).thenReturn(r);
		Mockito.when(rentDao.findOne((int) 1L)).thenReturn(rent); // 1L: Long
	//	Mockito.when(rentDao.save(rent)).thenReturn(value);
	}
	
	@Test
	@Ignore //Para que vaya más rápido
	public void create(){
		createRent();
	}


	
	@Test
	public void transformRent(){
		final RentDTO rentDTO = rentService.transform(rent);
		Assert.assertEquals("Fecha", rentDTO.getInitDate(), INIT);
	}
	
	private void createRent(){
		rent.setInitDate(INIT);
	}
}
