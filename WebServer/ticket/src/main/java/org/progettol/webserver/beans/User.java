package org.progettol.webserver.beans;

public class User {
	String username;
	String name;
	String surname;
	String cf;
	
	public User(String username){
		this.username = username;
	}
	public User(String username, String name, String surname, String cf) {
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.cf = cf;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	
}
