package org.btkj.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

/**
 * 
 * @author ahab
 */
public class Employee implements UniqueModel<Integer> {

	private static final long serialVersionUID = -315073031541553807L;

	private int id;
	private int uid;
	private int tid;
	private String name;
	private String identity;
	private int parentId;
	private int mod;
	private int left;
	private int right;
	private int level;
	private int created;
	private int updated;
	private int payType;
	private int state;

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

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIdentity() {
		return identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public int getParentId() {
		return parentId;
	}
	
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	public int getMod() {
		return mod;
	}
	
	public void setMod(int mod) {
		this.mod = mod;
	}
	
	public int getLeft() {
		return left;
	}
	
	public void setLeft(int left) {
		this.left = left;
	}
	
	public int getRight() {
		return right;
	}
	
	public void setRight(int right) {
		this.right = right;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public int getUpdated() {
		return updated;
	}
	
	public void setUpdated(int updated) {
		this.updated = updated;
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

	@Override
	public Integer key() {
		return id;
	}
}
