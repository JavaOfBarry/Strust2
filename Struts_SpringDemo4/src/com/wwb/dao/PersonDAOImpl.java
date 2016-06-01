package com.wwb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.wwb.entity.Person;



public class PersonDAOImpl implements PersonDAO {

	private MysqlConn conn;
	

	public MysqlConn getConn() {
		return conn;
	}

	public void setConn(MysqlConn conn) {
		this.conn = conn;
	}

    public void update() {

    }
    public  void deleteUser(String id)
    {
    	String sql = "delete from spring_struts where id = '"+id+"'";
    	System.out.println("sql"+sql);
    	try {
			boolean rs = conn.Conn().execute(sql);
			conn.Conn().close();
			conn.close();
		}
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public List<Person> getPersons() {
		
    	String sql = "select * from spring_struts";
    	System.out.println("sql"+sql);
    	List<Person> list = new ArrayList<Person>();
    	try {
    		ResultSet rs = conn.Conn().executeQuery(sql);
    		while (rs.next()) {
    			Person user = new Person();
    			int id = rs.getInt("id");
    			String username = rs.getString("username");
    			String password = rs.getString("password");
    			System.out.println(id+username+password);
    			user.setId(id);
    			user.setPassword(password);
    			user.setUsername(username);
    			list.add(user);
    		}
    		rs.close();
    		conn.Conn().close();
    		conn.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} 
    	return list;
    }

    public boolean isExit(String username, String password) {
        String sql = "select * from spring_struts where username=" + "'"
                + username + "'" + " and password=" + "'" + password + "'";
    	System.out.println("sql"+sql);
        boolean flag = false;
        try {
            
            ResultSet rs = conn.Conn().executeQuery(sql);
            if (rs.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	conn.close();
        }
        return flag;

    }

	public boolean insertUser(String username, String password) {
		// TODO Auto-generated method stub
		boolean flag;
		String sql = "insert into spring_struts (username ,password) values('"+username+"','"+password+"')";
		System.out.println("sql"+sql);
		try {
			conn.Conn().execute(sql);
			flag = true;
			conn.Conn().close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag =false;
		}
		
		return flag;
	}

	public Person selectUpdateUser(String id) {
		// TODO Auto-generated method stub
		String sql ="select * from spring_struts where id = '"+id+"'";
		Person user = new Person();
		System.out.println("Sql:"+sql);
		try {
			ResultSet rs = conn.Conn().executeQuery(sql);
			while(rs.next())
			{
				String username = rs.getString("username");
				String password = rs.getString("password");
				user.setPassword(password);
				user.setUsername(username);
				int id1 =Integer.parseInt(id);
				user.setId(id1);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean updateUser(String username ,String password,String id)
	{
		boolean flag;
		String sql = "update spring_struts set username = '"+username+"',password = '"+password+"' where id = '"+id+"'";
		System.out.println("sql"+sql);
		try {
			conn.Conn().execute(sql);
			flag = true;
			conn.Conn().close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag =false;
		}
		
		return flag;
	}
    
}