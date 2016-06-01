package com.wwb.service;

import java.util.List;

import com.wwb.entity.Person;

/**
 * 校验数据
 * 
 */
public interface MyService {
	public boolean valid(String username, String pass);
    public List<Person> UserSelect();
    public void deleteUser(String id);
    public boolean insertUser(String username ,String password);
    public Person selectUpdateUser(String id);
    public boolean updateUser(String username ,String password,String id);
    
    
}