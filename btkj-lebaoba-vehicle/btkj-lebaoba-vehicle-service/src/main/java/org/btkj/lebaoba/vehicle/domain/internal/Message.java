package org.btkj.lebaoba.vehicle.domain.internal;

import javax.xml.bind.annotation.XmlElement;

public class Message {

	private String value;
	private String time;
	
	@XmlElement(name = "VALUE")
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	@XmlElement(name = "TIME")
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
}
