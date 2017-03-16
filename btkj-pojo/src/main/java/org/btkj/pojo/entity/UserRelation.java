package org.btkj.pojo.entity;

import org.rapid.data.storage.db.Entity;
import org.rapid.util.common.key.Pair;

/**
 * 租户用户表：只有保途的租户才会使用该表
 * 
 * @author ahab
 */
public class UserRelation implements Entity<Pair<Integer, Integer>> {

	private static final long serialVersionUID = -315073031541553807L;

	private int id;
	private int uid;
	private int tid;
	private int parentId;
	private int left;
	private int right;
	private int created;
	private int updated;

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
	
	public int getParentId() {
		return parentId;
	}
	
	public void setParentId(int parentId) {
		this.parentId = parentId;
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

	@Override
	public Pair<Integer, Integer> key() {
		return new Pair<Integer, Integer>(tid, uid);
	}
}
