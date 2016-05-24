package com.wwb.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.wwb.bean.Login;
import com.wwb.bean.User;
import com.wwb.dao.OracleConn;


public class UserAction {
	OracleConn test = new OracleConn();
	public String selectUser() {
		HttpServletRequest request=ServletActionContext.getRequest();
		List user=test.getNameAndPassword();
		System.out.println(user);
		request.setAttribute("user",user);
		System.out.println(request.getAttribute("user").toString());
		return "success";
	}
	public String selectThings() {
		HttpServletRequest request=ServletActionContext.getRequest();
		List user=test.getUsers();
		System.out.println(user);
		request.setAttribute("user",user);
		System.out.println(request.getAttribute("user").toString());
		return "success";
	}
	public String selectUserJd() {
		HttpServletRequest request=ServletActionContext.getRequest();
		List user=test.getUsers();
		request.setAttribute("user",user);
		return "success";
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
	public String insertUser() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String password=request.getParameter("password");
    	String name=request.getParameter("name");
    	test.insertUser(name,password);
		return "success";
	}
	public String DeleteThings() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String id=request.getParameter("zz");
		test.DeleteUserPic(id);
    	test.DeleteThings(id);
		return "success";
	}
	public String DeleteUser() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String id=request.getParameter("name");
    	test.DeleteUser(id);
		return "success";
	}
	public String selectUpdateUser() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String id=request.getParameter("name");
    	Login selectUser = test.selectUpdateUser(id);
    	
    		System.out.println(selectUser.getName());
    		System.out.println(selectUser.getPassword());
    	
    	request.setAttribute("selectUser", selectUser);
		return "success";
	}
	public String selectUpdateThings() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String id=request.getParameter("id");
    	User selectUser = test.selectUpdateThings(id);
    	
    		System.out.println(selectUser.getName());
    		System.out.println(selectUser.getId());
    	
    	request.setAttribute("selectUser", selectUser);
		return "success";
	}
	public String updateUser() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String id=request.getParameter("#request.selectUser.name");
		String name=request.getParameter("#request.selectUser.password");
    	test.updateUser(id,name);
		return "success";
	}
	public String updateThings() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String id=request.getParameter("#request.selectUser.id");
		String name=request.getParameter("#request.selectUser.name");
    	test.updateThings(id,name);
		return "success";
	}
	public String login() {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String name = null;
		String password = null;
		if(session.getAttribute("user")!=null)
		{
			Login user1 = (Login) session.getAttribute("user");
			name=user1.getName();
			password=user1.getPassword(); 
			List<Login> user=test.getNameAndPassword();
			for(Login us:user)
			{
				System.out.println(us.getName());
				System.out.println(us.getPassword());
				if(us.getName()!=null&&us.getName().equals(name)&&us.getPassword()!=null&&us.getPassword().equals(password))
				{
					request.getSession().setAttribute("user",us);//Struts2中放入Session中
					return "success1";
				}

			}

		}else{
			name=request.getParameter("name");
			password=request.getParameter("password");

			List<Login> user=test.getNameAndPassword();
			for(Login us:user)
			{
				System.out.println(us.getName());
				System.out.println(us.getPassword());
				if(us.getName()!=null&&us.getName().equals(name)&&us.getPassword()!=null&&us.getPassword().equals(password))
				{
					request.getSession().setAttribute("user",us);//Struts2中放入Session中
					return "success";
				}

			}
		}

		return "false";
	}
	
	public String selectlogin() {
		return "success";
	}
	
	    private List<File> file;

	    private List<String> fileFileName;

	    private List<String> dataUrl;
	    
	    
	    
	    
	public List<File> getFile() {
			return file;
		}
		public void setFile(List<File> file) {
			this.file = file;
		}
		public List<String> getFileFileName() {
			return fileFileName;
		}
		public void setFileFileName(List<String> fileFileName) {
			this.fileFileName = fileFileName;
		}
		public List<String> getDataUrl() {
			return dataUrl;
		}
		public void setDataUrl(List<String> dataUrl) {
			this.dataUrl = dataUrl;
		}
		
	public String insertUserAndPic () throws Exception
	{
		User user = new User();
		dataUrl = new ArrayList<String>();
        String imgpath = "upload/";
        HttpServletRequest request=ServletActionContext.getRequest();
		
        request.getAttribute("file");
        System.out.println("file-------"+request.getAttribute("fileFileName"));
        System.out.println("file-------"+request.getAttribute("file"));
        System.out.println("file-------"+request.getAttribute("fileContentType"));
        System.out.println("file-------"+file);
        System.out.println("file-------"+fileFileName);
        
        if(file !=null || "".equals(file)){
        for (int i = 0; i < file.size(); ++i) {
            InputStream is = new FileInputStream(file.get(i));

            String path = ServletActionContext.getServletContext().getRealPath("/");
            System.out.println(path);
        //    String root = "D:\\";
            System.out.println(new String((this.getFileFileName().get(i)).getBytes("GB2312"),"gb2312")); 
            System.out.println(new String((this.getFileFileName().get(i)).getBytes("GBK"),"gb2312")); 
            long sj = System.currentTimeMillis();
            String url = new String((sj+"_"+this.getFileFileName().get(i)).getBytes("GB2312"),"gb2312");
            dataUrl.add(imgpath+url);
            System.out.println(path+imgpath);
            File destFile = new File(path+imgpath, url);

            OutputStream os = new FileOutputStream(destFile);

            byte[] buffer = new byte[400];

            int length = 0;

            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }

            is.close();

            os.close();
        }
       }   else{
    	   System.out.println("file为空");
	    	user.setMag("file为空");
	    	ServletActionContext.getRequest().setAttribute("user", user);
	    	return "false";//返回登录页面
       }     
        String id=request.getParameter("id");
    	String name=request.getParameter("name");
    	test.insertUser(id,name,dataUrl);
        return "success";
		
	}

	public String addCar() {
		List<String> img = new ArrayList<String>();
		List<User> car = new ArrayList<User>();
		List<User> userList = new ArrayList<User>();
		User user = new User();
		Boolean flg = false;
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session =  request.getSession();
		if (session.getAttribute("user") == null)
		{
			System.out.println("还没登录请登录");
			return "login";
		}else{
			String zz = request.getParameter("zz");  
			user = test.selectAddCarThings(zz);
			userList.add(user);
			System.out.println(user.getFile());
			//    	User car1 = (User) request.getSession().getAttribute("user");
			//    	String car2 = car1.getId();
			//    	String str = "#session" +car2;
			//    	request.getSession().setAttribute("str", str);
			if( session.getAttribute("car")==null)
			{
				System.out.println("session.get==null");
				session.setAttribute("car", userList);
				System.out.println(session.getAttribute("car").toString());
				return "success";
			}
			else {

				System.out.println("session.get!=null");
				car = (List<User>) session.getAttribute("car");

				if(car.isEmpty())
				{

					System.out.println("car.isEmpty()");
					car.add(user);
					session.setAttribute("car", car);
					return "success";
				}
				else{
					System.out.println("|1car.isEmpty()"+car);
					System.out.println("|2car.isEmpty()"+user);
					for(User cc :car){
						if((String.valueOf(cc.getZz())).equals(zz)||cc.getZz()==Integer.parseInt(zz))
						{
							flg = true;
							car.remove(cc);
							cc.setSum(cc.getSum()+1);
							car.add(cc);
							break;
						}
						else{
							flg = false;
						}
					}
					if(!flg){
						System.out.println("!car.contains(user)"+car.contains(user));
						System.out.println("!car.contains(user)");
						car.add(user);
						session.setAttribute("car", car);
						System.out.println(session.getAttribute("car").toString());
						return "success";
					}
					else{
						session.setAttribute("car", car);
						System.out.println("car.contains(user)");
						return "success";
					}
				}
			}

		}
	}
	
	public String deleteCar() {
		List<User> car = new ArrayList<User>();
		HttpServletRequest request=ServletActionContext.getRequest();
		String id=request.getParameter("id");
		String zz=request.getParameter("zz");
		HttpSession session =  request.getSession();
		car = (List<User>) session.getAttribute("car");
		System.out.println("car++"+car.toString());
			for(User cc: car)
			{
				if((String.valueOf(cc.getZz())).equals(zz)||cc.getZz()==Integer.parseInt(zz))
				{
					System.out.println("cc.getId()==id");
					if(cc.getSum() ==1){
					car.remove(cc);
					}else{
						car.remove(cc);
						cc.setSum(cc.getSum()-1);
						car.add(cc);
					}
					break;
				}else{
					System.out.println("!cc.getId()==id");
				}
			}
			System.out.println("car1++"+car.toString());
			session.setAttribute("car", car);
			System.out.println("car2++"+car.toString());
			return "success";
		}
		
	
	
	
}
