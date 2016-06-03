package com.wwb.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage.Severity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.wwb.entity.Things;
import com.wwb.service.MyService;
import com.wwb.service.MyServiceThings;


public class ThingsManageAction {

	private MyServiceThings ms ;
	private List<File> file;
	private List<String> fileFileName;
	private String fileContentType;
	private List<String> dataUrl;

	public MyServiceThings getMs() {
		return ms;
	}
	public void setMs(MyServiceThings ms) {
		this.ms = ms;
	}
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
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}


	public String insertThings () throws Exception
	{
		Things things = new Things();
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
			//    	user.setMag("file为空");
			//    	ServletActionContext.getRequest().setAttribute("user", user);
			return "false";//返回登录页面
		}     
		String price=request.getParameter("price");
		String name=request.getParameter("name");
		String description=request.getParameter("description");
		ms.insertThings(name, price, description, dataUrl);
		return "success";

	}

	public String selectThings()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		List things = new ArrayList<Things>();
		things = ms.selectThings() ;
		request.setAttribute("things", things);
		System.out.println(request.getRequestURL());
		System.out.println(request.getRequestURL().indexOf("param=JD"));
		System.out.println(request.getParameter("param"));
		if(request.getParameter("param")!=null&&request.getParameter("param").indexOf("JD")!=-1){
			return "successJD";
		}
		return "success";

	}
	public String selectThingsDJ()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		List things = new ArrayList<Things>();
		things = ms.selectThings() ;
		request.setAttribute("things", things);
		
		return "success";

	}
	
	public String deleteThings()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		if(ms.deleteThings(id))
		{
			return "success";
		}
		else return "false";
	}

	public String selectUpdateThings()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		Things thing = new Things();
		thing = ms.selectUpdateThings(id);
		request.setAttribute("thing", thing);

		return "success";
	}
	public String updateThings()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String id =request.getParameter("#request.thing.id");
		String name =request.getParameter("#request.thing.name");
		String picture =request.getParameter("#request.thing.picture");
		String description =request.getParameter("#request.thing.description");
		String price =request.getParameter("#request.thing.price");
		if(ms.updateThings(id, name, price, description))
		{
			return "success";
		}
		return "false";
	}

	public String addCar() {
		List things = new ArrayList<Things>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		Things thing =ms.selectUpdateThings(id);
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null)
		{
			return "login";
		}
		else{
			things.add(thing);
			if(session.getAttribute("car")==null)
			{
				session.setAttribute("car", things);
				return "success";
			}else{
				List<Things> car = new ArrayList<Things>();
				car =  (List<Things>) session.getAttribute("car");
				if(car.isEmpty())
				{
					car.add(thing);
					session.setAttribute("car", car);
					return "success";
				}else{
					boolean flag = false;
					for(Things cc :car)
					{
						if(cc.getId().equals(id)){
					    flag = true;
						car.remove(cc);
						cc.setSum(cc.getSum()+1);
						car.add(cc);
						break;
						}else{
							flag =false;
						}
					}
					if(flag)
					{
						session.setAttribute("car", car);
						return "success";
					}else{
						car.add(thing);
						session.setAttribute("car", car);
						return "success";
					}
				}
			}

		}
	}
	
	public String deleteCar()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String id =request.getParameter("id");
		HttpSession session = request.getSession();
		List<Things> things = (List<Things>) session.getAttribute("car");
		for(Things cc :things)
		{
			if(cc.getId().equals(id))
			{
				if(cc.getSum()>1)
				{
					things.remove(cc);
					cc.setSum(cc.getSum()-1);
					things.add(cc);
					session.setAttribute("car", things);
					return "success";
				}else{
					things.remove(cc);
					session.setAttribute("car", things);
					return "success";
				}
			}
		}
		return "success";
		
	}
}
