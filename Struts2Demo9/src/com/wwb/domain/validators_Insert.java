package com.wwb.domain;

import java.io.File;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.wwb.bean.User;

public class validators_Insert extends MethodFilterInterceptor {
	private List<File> file;
	public List<File> getFile() {
		return file;
	}
	public void setFile(List<File> file) {
		this.file = file;
	}
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		  
		User user = new User();
	    System.out.println("进入总拦截器，防止直接访问Action");
	    String id = ServletActionContext.getRequest().getParameter("id");
	    String name = ServletActionContext.getRequest().getParameter("name");
	    String file = ServletActionContext.getRequest().getParameter("file");
	    System.out.println("id---"+id);
	    System.out.println("name---"+name);
	    System.out.println("file---"+file);
	    
	    System.out.println("id"+id);
	    if(id==null||("").equals(id))
	    {
	    	System.out.println("id为空");
	    	user.setMag("id为空");
	    	ServletActionContext.getRequest().setAttribute("user", user);
	    	return "false";//返回登录页面
	    }
	    else if(name==null||("").equals(name))
	    {
	    	System.out.println("name为空");
	    	user.setMag("name为空");
	    	ServletActionContext.getRequest().setAttribute("user", user);
	    	return "false";//返回登录页面
	    }
	    else {
	    	invocation.invoke();//相当于Servlet的chain.doFilter();
		      return null;
	    }
	  }
	}