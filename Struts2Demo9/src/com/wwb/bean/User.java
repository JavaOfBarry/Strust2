package com.wwb.bean;

import java.util.List;

public class User  implements java.io.Serializable {
	private String id;
	private String name;
	private String mag;
	private int sum;
	private int zz;
	
	public int getZz() {
		return zz;
	}
	public void setZz(int zz) {
		this.zz = zz;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public String getMag() {
		return mag;
	}
	public void setMag(String mag) {
		this.mag = mag;
	}
	private List<String> file;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getFile() {
		return file;
	}
	public void setFile(List<String> file) {
		this.file = file;
	}
	
}
