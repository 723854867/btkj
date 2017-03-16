package org.btkj.common.web.pojo.info;

import java.io.Serializable;

import org.btkj.common.web.Beans;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.model.InviterModel;

public class InviterInfo implements Serializable {

	private static final long serialVersionUID = -6596861609994495980L;

	private int id;
	private int type;
	private String name;
	private String region;
	
	public InviterInfo(InviterModel inviter) {
		Tenant tenant = inviter.getTenant();
		this.region = Beans.cacheService.getRegionByCode(tenant.getRegionCode()).getName();
		User user = inviter.getUser();
		if (null == user) {
			this.id = tenant.getTid();
			this.type = Type.TENANT.mark;
			this.name = tenant.getName();
		} else {
			this.id = user.getUid();
			this.type = Type.USER.mark;
			this.name = user.getName();
		}
	}
	
	public enum Type {
		TENANT(1),
		USER(2);
		private int mark;
		private Type(int mark) {
			this.mark = mark;
		}
		public int mark() {
			return mark;
		}
		public static final Type match(int type) {
			for (Type temp : Type.values()) {
				if (temp.mark == type)
					return temp;
			}
			return null;
		}
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
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
