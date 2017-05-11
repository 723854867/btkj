package org.btkj.pojo.info;

import java.io.Serializable;
import java.util.List;

/**
 * 员工列表信息
 * 
 * @author ahab
 */
public class EmployeeListInfo implements Serializable {

	private static final long serialVersionUID = -1079996680418465118L;

	private int id;
	private int uid;
	private String name;
	private String mobile;
	private String identity;
	private List<String> tags;
	private int created;
	private int payType;
	private int state;
	private int tagMod;
	private int pid;
	private int puid;
	private String pname;
	private String pmobile;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getIdentity() {
		return identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public int getTagMod() {
		return tagMod;
	}
	
	public void setTagMod(int tagMod) {
		this.tagMod = tagMod;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
	
	public int getPuid() {
		return puid;
	}
	
	public void setPuid(int puid) {
		this.puid = puid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPmobile() {
		return pmobile;
	}

	public void setPmobile(String pmobile) {
		this.pmobile = pmobile;
	}
}
