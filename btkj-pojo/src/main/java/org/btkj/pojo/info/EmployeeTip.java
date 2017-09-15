package org.btkj.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;

/**
 * 雇员基本信息
 * 
 * @author ahab
 */
public class EmployeeTip implements Serializable {

	private static final long serialVersionUID = -273300203666288418L;

	private int id;
	private int mod;
	private int uid;
	private int tid;
	private int tmod;
	private int amod;
	private int appId;
	private int aregion;
	private int tregion;
	private String name;				// 用户名字
	private String aname;				// app名字
	private String tname;				// 商户名字
	private String mobile;				// 手机号
	private String avatar;
	private String identity;			// 身份证号
	private String identityFace;
	private String identityBack;
	private String nonAutoBind;
	private int layer;
	private int CMRate;
	private int CPRate;
	private int created;
	
	public EmployeeTip() {}
	
	public EmployeeTip(Employee employee, App app, User user, Tenant tenant) {
		this.id = employee.getId();
		this.mod = employee.getMod();
		this.uid = employee.getUid();
		this.tid = employee.getTid();
		this.tmod = tenant.getMod();
		this.amod = app.getMod();
		this.appId = employee.getAppId();
		this.aregion = app.getRegion();
		this.tregion = tenant.getRegion();
		this.nonAutoBind = tenant.getNonAutoBind();
		this.name = user.getName();
		this.aname = app.getName();
		this.tname = tenant.getName();
		this.mobile = user.getMobile();
		this.avatar = user.getAvatar();
		this.identity = user.getIdentity();
		this.identityFace = user.getIdentityFace();
		this.identityBack = user.getIdentityBack();
		this.CMRate = employee.getCommercialRate();
		this.CPRate = employee.getCompulsoryRate();
		this.created = employee.getCreated();
		this.layer = employee.getLayer();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getMod() {
		return mod;
	}
	
	public void setMod(int mod) {
		this.mod = mod;
	}
	
	public int getAmod() {
		return amod;
	}
	
	public void setAmod(int amod) {
		this.amod = amod;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String getNonAutoBind() {
		return nonAutoBind;
	}
	
	public void setNonAutoBind(String nonAutoBind) {
		this.nonAutoBind = nonAutoBind;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public int getTmod() {
		return tmod;
	}
	
	public void setTmod(int tmod) {
		this.tmod = tmod;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getName() {
		return name;
	}
	
	public String getAname() {
		return aname;
	}
	
	public void setAname(String aname) {
		this.aname = aname;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTname() {
		return tname;
	}
	
	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public String getIdentity() {
		return identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public String getIdentityBack() {
		return identityBack;
	}
	
	public void setIdentityBack(String identityBack) {
		this.identityBack = identityBack;
	}
	
	public String getIdentityFace() {
		return identityFace;
	}
	
	public void setIdentityFace(String identityFace) {
		this.identityFace = identityFace;
	}

	public int getAregion() {
		return aregion;
	}

	public void setAregion(int aregion) {
		this.aregion = aregion;
	}

	public int getTregion() {
		return tregion;
	}

	public void setTregion(int tregion) {
		this.tregion = tregion;
	}

	public int getCMRate() {
		return CMRate;
	}

	public void setCMRate(int cMRate) {
		CMRate = cMRate;
	}

	public int getCPRate() {
		return CPRate;
	}

	public void setCPRate(int cPRate) {
		CPRate = cPRate;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}
	
	public int getLayer() {
		return layer;
	}
	
	public void setLayer(int layer) {
		this.layer = layer;
	}
}
