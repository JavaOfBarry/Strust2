package com.wwb.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.wwb.bean.Login;
import com.wwb.bean.User;


public class OracleConn {
	
	 private String username="root";
	    private String password="wwb123";
	    private String url="jdbc:mysql://127.0.0.1:3306/Mysql?useUnicode=true&amp;characterEncoding=utf-8";
	    private String driver="com.mysql.jdbc.Driver";
		public Connection getConnection()throws Exception{
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username,password);
		    return conn;
		}
	
	
	
	    public Statement getStatement(){
	    	Statement st = null;
	    	try {
				st = getConnection().createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return st;
	    }
	    //查询表单 
	    public List getUsers(){
	    	List<User> list = new ArrayList<User>();

	    	try{
	    		String sql="select * from student";
	    		System.out.println("sql--"+sql);
	    		ResultSet rs=getStatement().executeQuery(sql);
	    		while(rs.next()){
	    			List<String> imgUrl = new ArrayList<String>();
	    			User user = new User();
	    			int zz  =rs.getInt("S_ZZ");
	    			String id =rs.getString("S_ID");
	    			String name=rs.getString("S_NAME");
	    			String file=rs.getString("S_FILE");
	    			if(file !=null){
	    			file = file.substring(1, file.length()-1);
	    			System.out.println("file---"+file);
	    			String d[] = file.split(",");
	    			for (int i = 0; i < d.length; i++) {  
	    				imgUrl.add(d[i]);       
	    			}   
	    			}
	    			System.out.println("imgUrl---"+imgUrl);
	    			user.setZz(zz);
	    			user.setId(id);
	    			user.setName(name);
	    			user.setFile(imgUrl);
	    			list.add(user);
	    		}
	    		rs.close();
	    		getStatement().close();
	    		getConnection().close();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	return list;
	    }
	    //查询表单 
	    public List getNameAndPassword(){
	    	List<Login> list = new ArrayList<Login>();

	    	try{
	    		String sql="select * from regist";
	    		System.out.println("sql--"+sql);
	    		ResultSet rs=getStatement().executeQuery(sql);
	    		while(rs.next()){
	    			Login user = new Login();
	    			String name =rs.getString("NAME");
	    			String password=rs.getString("PASSWORD");
	    			
	    			user.setName(name);
	    			user.setPassword(password);
	    			list.add(user);
	    		}
	    		rs.close();
	    		getStatement().close();
	    		getConnection().close();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	return list;
	    }    
	    // 插入表单
	    public void insertUser(String name,String password) 
	    {
	    	String sql = "insert into regist (NAME, PASSWORD )values ('"+name+"','"+password+"')";
	    	try {
	    		System.out.println("sql--"+sql);
				getStatement().execute(sql);
				getStatement().close();
	        	getConnection().close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
	    }
	    // 插入表单
	    public void insertUser(String id,String name,List dataUrl) 
	    {
	    	String sql = "insert into student (S_ID, S_NAME,S_FILE )values ('"+id+"','"+name+"','"+dataUrl+"')";
	    	try {
	    		System.out.println("sql--"+sql);
				getStatement().execute(sql);
				getStatement().close();
	        	getConnection().close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
	    }
	 // 删除pic
	    public void DeleteUserPic(String id) 
	    {
	    	List<User> list = new ArrayList<User>();
	    	String imgpath = "upload/";
	    	String path = ServletActionContext.getServletContext().getRealPath("/");
	    	System.out.println(path);
	    	try{
	    		String sql="select * from student where S_ZZ = '"+id+"'";
	    		System.out.println("sql--"+sql);
	    		ResultSet rs=getStatement().executeQuery(sql);
	    		while(rs.next()){
	    			List<String> imgUrl = new ArrayList<String>();
	    			User user = new User();
	    			String file=rs.getString("S_FILE");
	    			if(file !=null){
	    			file = file.substring(1, file.length()-1);
	    			System.out.println("file---"+file);
	    			String d[] = file.split(",");
	    			for (int i = 0; i < d.length; i++) {
	    				
	    				doDeleteEmptyDir(path+d[i].trim());
	    			}   
	    			}
	    			System.out.println("imgUrl---"+imgUrl);
	    		}
	    		rs.close();
	    		getStatement().close();
	    		getConnection().close();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
        	
	    }
	    private static void doDeleteEmptyDir(String dir) {  
	    	boolean success = (new File(dir)).delete();  
	    	if (success) {   
	    		System.out.println("Successfully deleted empty directory: " + dir); 
	    		} else {  
	    			System.out.println("Failed to delete empty directory: " + dir);
	    			}  
	    	}
	    // 删除表单
	    public void DeleteThings(String id) 
	    {
	    	String sql = "delete from student where S_ZZ = '"+id+"'";
	    	try {
	    		System.out.println("sql--"+sql);
				getStatement().execute(sql);
				getStatement().close();
	        	getConnection().close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
	    }
	    public void DeleteUser(String id) 
	    {
	    	String sql = "delete from regist where name = '"+id+"'";
	    	try {
	    		System.out.println("sql--"+sql);
				getStatement().execute(sql);
				getStatement().close();
	        	getConnection().close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
	    }
	    // 查询更新表单
	    public Login selectUpdateUser(String id) 
	    {
	    	Login user = new Login();
	    	String sql = "select * from regist where name = '"+id+"'";
	    	try {
	    		System.out.println("sql--"+sql);
				ResultSet rs = getStatement().executeQuery(sql);
				while (rs.next())
				{
					user.setName(rs.getString("NAME"));
					user.setPassword(rs.getString("PASSWORD"));
				}
				getStatement().close();
	        	getConnection().close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return user;
        	
	    }
	    public User selectUpdateThings(String id) 
	    {
	    	User user = new User();
	    	String sql = "select * from student where S_ID = '"+id+"'";
	    	try {
	    		System.out.println("sql--"+sql);
				ResultSet rs = getStatement().executeQuery(sql);
				while (rs.next())
				{
					user.setId(rs.getString("S_ID"));
					user.setName(rs.getString("S_NAME"));
				}
				getStatement().close();
	        	getConnection().close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return user;
        	
	    }
	    //购物车查询
	    public User selectAddCarThings(String zz) 
	    {
	    	User user = new User();
	    	String sql = "select * from student where S_ZZ = '"+zz+"'";
	    	try {
	    		System.out.println("sql--"+sql);
	    		ResultSet rs = getStatement().executeQuery(sql);
	    		while (rs.next())
	    		{
	    			List<String> imgUrl = new ArrayList<String>();

	    			int zz1  =rs.getInt("S_ZZ");
	    			String id =rs.getString("S_ID");
	    			String name=rs.getString("S_NAME");
	    			String file=rs.getString("S_FILE");
	    			if(file !=null){
	    				file = file.substring(1, file.length()-1);
	    				System.out.println("file---"+file);
	    				String d[] = file.split(",");
	    				for (int i = 0; i < d.length; i++) {  
	    					imgUrl.add(d[i]);       
	    				}   
	    			}
	    			System.out.println("imgUrl---"+imgUrl);
	    			user.setZz(zz1);
	    			user.setId(id);
	    			user.setName(name);
	    			user.setFile(imgUrl);
	    			user.setSum(1);
	    		}
	    		rs.close();
	    		getStatement().close();
	    		getConnection().close();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	return user;

	    }
	    // 更新表单
	    public void updateUser(String id,String name ) 
	    {
	    	String sql = "UPDATE regist stu set stu.name = '"+id+"',stu.password = '"+name+"' WHERE stu.name = '"+id+"'";
	    	try {
	    		System.out.println("sql--"+sql);
				getStatement().execute(sql);
				getStatement().close();
	        	getConnection().close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
	    }
	    // 更新表单
	    public void updateThings(String id,String name ) 
	    {
	    	String sql = "UPDATE student stu set stu.s_id = '"+id+"',stu.s_name = '"+name+"' WHERE stu.s_id = '"+id+"'";
	    	try {
	    		System.out.println("sql--"+sql);
				getStatement().execute(sql);
				getStatement().close();
	        	getConnection().close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
	    }
}
