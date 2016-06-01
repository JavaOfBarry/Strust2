package com.wwb.action;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.http.HttpRequest;

import com.wwb.entity.Person;
import com.wwb.service.MyService;
import com.opensymphony.xwork2.Action;

public class LoginAction implements Action {
    // 下面是用于封装用户请求参数的两个属性
    private String username;
    private String password;
    // 用于封装处理结果的属性
    private String tip;
    // 系统所用的业务逻辑组件
    private MyService ms;
    
    private Person user;

    // 设置注入业务逻辑组件所必需的setter方法
    public void setMs(MyService ms) {
        this.ms = ms;
    }

    // username属性的setter和getter方法
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    // password属性所必需的setter和getter方法
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public Person getUser() {
		return user;
	}

	public void setUser(Person user) {
		this.user = user;
	}

	// tip属性的getter和setter方法
    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getTip() {
        return this.tip;
    }

    
    // 处理用户请求的execute方法
    public String execute() throws Exception {
        // 调用业务逻辑组件的valid方法来
// 验证用户输入的用户名和密码是否正确
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null)
		{
			Person user = (Person) session.getAttribute("user");
			if (ms.valid(user.getUsername(), user.getPassword())) {
				setTip("哈哈，" + getUsername() + "登录成功！");
				session.setAttribute("user", user);
				return SUCCESS;
			} else {
				return ERROR;
			}
		}else{
			user.setUsername(username);
			user.setPassword(password);
			if (ms.valid(getUsername(), getPassword())) {
				setTip("哈哈，" + getUsername() + "登录成功！");
				session.setAttribute("user", user);
				return SUCCESS;
			} else {
				return ERROR;
			}
		}
    }
    public String quit() {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if(session !=null)
		{
			session.invalidate(); 
		}
		return "success";
	}
}