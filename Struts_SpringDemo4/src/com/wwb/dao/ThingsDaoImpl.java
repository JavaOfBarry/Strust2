package com.wwb.dao;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;


import com.wwb.entity.Things;
import com.wwb.service.MyServiceThings;

public class ThingsDaoImpl implements ThingsDao {

	private MysqlConn conn;
	
	public MysqlConn getConn() {
		return conn;
	}

	public void setConn(MysqlConn conn) {
		this.conn = conn;
	}

	public boolean insertThings(String name, String price, String description,
			List picture) {
		boolean flag;
		String sql = "insert into spring_struts_things (name,price,description,picture) values ('"+name+"','"+price+"','"+description+"','"+picture+"')";
		System.out.println("Sql:"+sql);
		try {
			 flag = conn.Conn().execute(sql);
			conn.Conn().close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}

		return flag;
	}

	public List<Things> selectThings() {
		// TODO Auto-generated method stub
		
		List things = new  ArrayList<Things>();
		
		String sql = "select * from spring_struts_things";
		try {
			ResultSet rs =  conn.Conn().executeQuery(sql);
			while(rs.next())
			{
				Things thing = new Things();
				List imgUrl = new  ArrayList<Things>();
				String id = rs.getString("id");
				String name = rs.getString("name");
				String price = rs.getString("price");
				String description = rs.getString("description");
				String picture = rs.getString("picture");
				if(picture !=null){
					picture = picture.substring(1, picture.length()-1);
					System.out.println("picture---"+picture);
					String d[] = picture.split(",");
					for (int i = 0; i < d.length; i++) {  
						imgUrl.add(d[i]);       
					}   
				}
				thing.setId(id);
				thing.setDescription(description);
				thing.setName(name);
				thing.setPrice(price);
				thing.setPicture(imgUrl);
				things.add(thing);
				conn.Conn().close();
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return things;
	}

	public boolean deleteThings(String id) {
		// TODO Auto-generated method stub
		boolean flag;
		
		String sql = "delete from spring_struts_things where id ='"+id+"'";
		System.out.println("sql:"+sql);
		try {
			deletePic(id);
			conn.Conn().execute(sql);
			flag = true;
			conn.Conn().close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		System.out.println("flag:"+flag);
		return flag;
	}
	public void deletePic(String id)
	{
		System.out.println("deletepic!...");
		String path = ServletActionContext.getServletContext().getRealPath("/");
		String sql = "select * from spring_struts_things where id = '"+id+"'";
		try {
			ResultSet rs = conn.Conn().executeQuery(sql);
			while(rs.next())
			{
				String picture = rs.getString("picture");
				picture = picture.substring(1,picture.length()-1);
				String d[] = picture.split(",");
				for(int i = 0;i<d.length;i++)
				{
					System.out.println("deletepic...");
					(new File(path+d[i].trim())).delete(); 
				}
			}
			conn.Conn().close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Things selectUpdateThings(String id) {
		// TODO Auto-generated method stub
		String sql = "select * from spring_struts_things where id = '"+id+"'";
		Things thing = new	Things();
		try {
			ResultSet rs = conn.Conn().executeQuery(sql);
			while (rs.next())
			{
				List url = new ArrayList();
				String name =rs.getString("name");
				String price =rs.getString("price");
				String description =rs.getString("description");
				String picture =rs.getString("picture");
				if(picture !=null){
					picture = picture.substring(1,picture.length()-1);
					String d[] = picture.split(",");
					for(int i = 0 ;i<d.length;i ++)
					{
						System.out.println("url:"+d[i]);
						url.add(d[i]);
					}
				}
				thing.setDescription(description);
				thing.setId(id);
				thing.setName(name);
				thing.setPicture(url);
				thing.setPrice(price);
				thing.setSum(1);
				conn.Conn().close();
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thing;
	}

	public  boolean updateThings(String id,String name,String price,String description){
		// TODO Auto-generated method stub
		String sql = "update spring_struts_things set name = '"+name+"',price = '"+price+"',description = '"+description+"' where id = '"+id+"' ";
		boolean flag;
		System.out.println("sql:"+sql);
		try {
			 conn.Conn().execute(sql);
			 flag =true;
			conn.Conn().close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

}
