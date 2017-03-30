package org.btkj.pojo.model;

import java.io.Serializable;
import java.util.List;

/**
 * 分页信息
 * 
 * @author ahab
 */
public class Pager<T> implements Serializable {

	private static final long serialVersionUID = 1530641020396359356L;
	
	private int total;
	private List<T> list;
	
	public Pager() {}
	
	public Pager(int total, List<T> list) {
		this.total = total;
		this.list = list;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public List<T> getList() {
		return list;
	}
	
	public void setList(List<T> list) {
		this.list = list;
	}
}
