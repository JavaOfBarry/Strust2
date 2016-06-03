package com.wwb.service;

import java.util.List;

import com.wwb.entity.Things;

public interface MyServiceThings {

	public boolean insertThings(String name ,String price,String description ,List picture);
	public List<Things> selectThings();
	public abstract boolean deleteThings(String id) ;
	public abstract Things selectUpdateThings(String id) ;
	public abstract boolean updateThings(String id,String name,String price,String description) ;

}
