package com.wwb.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.wwb.entity.Person;
import com.wwb.service.MyService;

public class UserManageAction {
	private MyService ms ;
	
	  // 设置注入业务逻辑组件所必需的setter方法
    public void setMs(MyService ms) {
        this.ms = ms;
    }

	public String UserSelect()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Person> users = new ArrayList<Person>();
		users =  ms.UserSelect();
		for(Person person : users)
		{
			System.out.println("person:"+person.toString());
		}
		System.out.println("User+"+users);
		System.out.println("User+"+request);
		request.setAttribute("user", users);
		return "success";
		
	}
	public String deleteUser()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		ms.deleteUser(id);
		
		return "success";
		
	}
	public String insertUser()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(ms.insertUser(username, password)){
			return "success";
		}
		return "false";
	}
	public String selectUpdateUser()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		Person user = ms.selectUpdateUser(id);
		request.setAttribute("user", user);
		return "success";
	}
	public String updateUser()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("#request.user.username");
		String password = request.getParameter("#request.user.password");
		String id = request.getParameter("#request.user.id");
		if(ms.updateUser(username, password,id)){
			return "success";
		}
		return "false";
	}
}
