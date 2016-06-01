package com.wwb.service;

import java.util.List;

import com.wwb.dao.PersonDAO;
import com.wwb.entity.Person;

public class MyServiceImpl implements MyService {

    private PersonDAO personDAO;

    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public boolean valid(String username, String pass) {
        // 此处只是简单示范，故直接判断用户名、密码
// 是否符合要求
        if (personDAO.isExit(username, pass)) {
            return true;
        }
        return false;
    }

	 public List<Person> UserSelect() {
		// TODO Auto-generated method stub
		 System.out.println("UserSelect");
		 return personDAO.getPersons();
	}

	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		System.out.println("deleteUser");
		 personDAO.deleteUser(id);
	}

	public boolean insertUser(String username, String password) {
		// TODO Auto-generated method stub
		System.out.println("insertUser");
		return personDAO.insertUser(username, password);
		
	}

	public Person selectUpdateUser(String id) {
		// TODO Auto-generated method stub
		System.out.println("selectUpdateUser");
		return personDAO.selectUpdateUser(id);
	}
	
	public boolean updateUser(String username ,String password ,String id)
	{
		System.out.println("updateUser");
		return personDAO.updateUser(username, password,id);
	}
}