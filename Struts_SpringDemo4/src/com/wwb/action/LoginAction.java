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
    // ���������ڷ�װ�û������������������
    private String username;
    private String password;
    // ���ڷ�װ������������
    private String tip;
    // ϵͳ���õ�ҵ���߼����
    private MyService ms;
    
    private Person user;

    // ����ע��ҵ���߼�����������setter����
    public void setMs(MyService ms) {
        this.ms = ms;
    }

    // username���Ե�setter��getter����
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    // password�����������setter��getter����
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

	// tip���Ե�getter��setter����
    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getTip() {
        return this.tip;
    }

    
    // �����û������execute����
    public String execute() throws Exception {
        // ����ҵ���߼������valid������
// ��֤�û�������û����������Ƿ���ȷ
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null)
		{
			Person user = (Person) session.getAttribute("user");
			if (ms.valid(user.getUsername(), user.getPassword())) {
				setTip("������" + getUsername() + "��¼�ɹ���");
				session.setAttribute("user", user);
				return SUCCESS;
			} else {
				return ERROR;
			}
		}else{
			user.setUsername(username);
			user.setPassword(password);
			if (ms.valid(getUsername(), getPassword())) {
				setTip("������" + getUsername() + "��¼�ɹ���");
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