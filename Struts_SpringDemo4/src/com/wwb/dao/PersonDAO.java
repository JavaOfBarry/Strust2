package com.wwb.dao;

import java.sql.Statement;
import java.util.List;

import com.wwb.entity.Person;

/**
 * PersonDAO≥ÈœÛ¿‡
 * 
 * @author Hongten
 * 
 */
public interface PersonDAO {

    public abstract void update();

    public abstract void deleteUser(String id);
    
    public abstract Person selectUpdateUser(String id);

    public abstract List<Person> getPersons();

    public abstract boolean isExit(String username, String password );
    
    public abstract boolean insertUser(String username, String password) ;
    
    public abstract boolean updateUser(String username, String password,String id) ;
}