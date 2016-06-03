package com.wwb.service;

import java.util.ArrayList;
import java.util.List;

import com.wwb.dao.ThingsDao;
import com.wwb.entity.Person;
import com.wwb.entity.Things;

public class MyServiceThingsImpl implements MyServiceThings {

	private ThingsDao ThingsDAO;
	public ThingsDao getThingsDAO() {
		return ThingsDAO;
	}
	public void setThingsDAO(ThingsDao thingsDAO) {
		ThingsDAO = thingsDAO;
	}
	public boolean insertThings(String name, String price, String description,
			List picture) {
		// TODO Auto-generated method stub
		return ThingsDAO.insertThings(name, price, description,picture) ;
	}
	public List<Things> selectThings() {
		// TODO Auto-generated method stub
		
		return ThingsDAO.selectThings() ;
	}
	public boolean deleteThings(String id) {
		// TODO Auto-generated method stub
		
		return ThingsDAO.deleteThings(id);
	}
	public Things selectUpdateThings(String id) {
		// TODO Auto-generated method stub
		return ThingsDAO.selectUpdateThings(id);
		
	}
	public  boolean updateThings(String id,String name,String price,String description) {
		// TODO Auto-generated method stub
		
		return ThingsDAO.updateThings(id, name, price, description);
				
	}
	
}
