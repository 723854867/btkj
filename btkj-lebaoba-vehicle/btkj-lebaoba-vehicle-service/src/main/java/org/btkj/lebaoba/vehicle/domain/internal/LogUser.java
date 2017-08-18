package org.btkj.lebaoba.vehicle.domain.internal;

import javax.xml.bind.annotation.XmlElement;

public class LogUser {

	private String Username;
	private String Password;
	public LogUser(String username, String password) {
		this.Username = username;
		this.Password = password;
	}
	@XmlElement(name = "Username")
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	@XmlElement(name = "Password")
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
}
