package org.btkj.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.User;

public class InviterInfo implements Serializable {

	private static final long serialVersionUID = -3318563526218689934L;

	private String name;
	private String region;
	
	public InviterInfo() {}
	
	public InviterInfo(User user, Region region) {
		this.name = user.getName();
		this.region = region.getName();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRegion() {
		return region;
	}
	
	public void setRegion(String region) {
		this.region = region;
	}
}
