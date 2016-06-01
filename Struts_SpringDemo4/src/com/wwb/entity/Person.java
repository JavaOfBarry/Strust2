package com.wwb.entity;

/**
 * 定义一个Entity
 * 
 * 
 */
public class Person {
    /**
     * id号
*/
    private int id;
    /**
     * 用户名
*/
    private String username;
    /**
     * 密码
*/
    private String password;

    public Person() {
    }

    public Person(int id, String username, String password) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	@Override
	public String toString() {
		return "Person [id=" + id + ", username=" + username + ", password="
				+ password + "]";
	}
    
}